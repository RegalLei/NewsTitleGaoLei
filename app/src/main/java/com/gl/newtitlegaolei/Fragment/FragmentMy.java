package com.gl.newtitlegaolei.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gl.newtitlegaolei.MainActivity;
import com.gl.newtitlegaolei.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;



/**
 * 作者：高镭
 * 时间：2017/2/9 10:54
 * 班级：1501A
 */
public class FragmentMy extends Fragment implements View.OnClickListener {

    private ImageView dsf_qq;
    private static String  gAppid="1105924500";
    private Tencent ginstance;
    private Button my_daynight;
    private View view;

    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initKun();

        initlistenter();
        mainActivity = (MainActivity) getActivity();
    }
    //点击事件
    private void initlistenter() {
        dsf_qq.setOnClickListener(this);
        my_daynight.setOnClickListener(this);
    }
    //找控件
    private void initKun() {
        ImageView dsf_phone=  (ImageView) view.findViewById(R.id.DSF_phone);
        ImageView dsf_weixin= (ImageView) view.findViewById(R.id.DSF_weixin);
        dsf_qq = (ImageView) view.findViewById(R.id.DSF_qq);
        my_daynight = (Button) view.findViewById(R.id.my_daynight);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DSF_qq:
                DSFQQ();
                break;
            case R.id.my_daynight:
                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                mainActivity.getDelegate().setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO
                        ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                // 同样需要调用recreate方法使之生效
                getActivity().recreate();

                break;

        }
    }

    private void DSFQQ() {
        ginstance = Tencent.createInstance(gAppid,getContext());
        dsf_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ginstance.login(FragmentMy.this,"all",loginlistener);
            }
        });
    }
    IUiListener loginlistener = new BaseUiListener(){

    };

    private class BaseUiListener implements IUiListener{


        @Override
        public void onComplete(Object o) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:"+ SystemClock.elapsedRealtime());
        }

        @Override
        public void onError(UiError uiError) {
            Log.i("gaolei",uiError.toString());
        }

        @Override
        public void onCancel() {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode,resultCode,data,loginlistener);
    }
}
