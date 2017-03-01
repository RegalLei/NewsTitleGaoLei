package com.gl.newtitlegaolei.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gl.newtitlegaolei.MainActivity;
import com.gl.newtitlegaolei.My.XTsezhi;
import com.gl.newtitlegaolei.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 作者：高镭
 * 时间：2017/2/9 10:54
 * 班级：1501A
 */
public class FragmentMy extends Fragment implements View.OnClickListener {

    private ImageView dsf_qq;
    private static String gAppid = "1105939499";
    private Tencent ginstance;
    private Button my_daynight;
    private View view;
    private TextView text;
    private DisplayImageOptions options;

    private MainActivity mainActivity;
    private TextView my_tv_xtsz;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initKun();

        initlistenter();
        mainActivity = (MainActivity) getActivity();

    }

    private void login() {
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
// 其中APP_ID是分配给第三方应用的appid，类型为String。
        ginstance = Tencent.createInstance(gAppid, getActivity().getApplicationContext());
// 1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取
        if (!ginstance.isSessionValid()) {
            ginstance.login(this, "all", loginlistener);
        }

    }

    //点击事件
    private void initlistenter() {
        dsf_qq.setOnClickListener(this);
        my_daynight.setOnClickListener(this);
        my_tv_xtsz.setOnClickListener(this);
    }

    //找控件
    private void initKun() {
        ImageView dsf_phone = (ImageView) view.findViewById(R.id.DSF_phone);
        ImageView dsf_weixin = (ImageView) view.findViewById(R.id.DSF_weixin);
        dsf_qq = (ImageView) view.findViewById(R.id.DSF_qq);
        my_daynight = (Button) view.findViewById(R.id.my_daynight);
        my_tv_xtsz = (TextView) view.findViewById(R.id.my_tv_xtsz);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DSF_qq:
                DSFQQ();
                login();
                break;
            case R.id.my_daynight:
                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                mainActivity.getDelegate().setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO
                        ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                // 同样需要调用recreate方法使之生效
                getActivity().recreate();
                break;
            case R.id.my_tv_xtsz:
                Intent intent = new Intent(getActivity(), XTsezhi.class);
                startActivity(intent);
                break;

        }
    }

    private void DSFQQ() {
        ginstance = Tencent.createInstance(gAppid, getContext());
        dsf_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ginstance.login(FragmentMy.this, "all", loginlistener);
            }
        });
    }

    IUiListener loginlistener = new BaseUiListener() {

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    };

    private class BaseUiListener implements IUiListener {


        @Override
        public void onComplete(Object response) {

            doComplete((JSONObject) response);
            JSONObject obj = (JSONObject) response;


            String openID = obj.optString("openid");
            String accessToken = null;
            String expires = null;
            try {
                accessToken = obj.getString("access_token");

                expires = obj.getString("expires_in");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            ginstance.setOpenId(openID);
            ginstance.setAccessToken(accessToken, expires);
            QQToken qqToken = ginstance.getQQToken();
            UserInfo userInfo = new UserInfo(getActivity().getApplicationContext(), qqToken);
            userInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    JSONObject obj = (JSONObject) o;
                    String qqName = obj.optString("nickname");
                    String imgURL = obj.optString("figureurl_qq_2");
                    options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
                    ImageLoader.getInstance().displayImage(imgURL, dsf_qq, options);
                }

                @Override
                public void onError(UiError uiError) {
                    Log.i("gaolei",uiError.toString());
                }

                @Override
                public void onCancel() {

                }
            });
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }

        protected void doComplete(JSONObject values) {
            Log.i("gaolei",values.toString());
        }

        }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginlistener);

    }
}

