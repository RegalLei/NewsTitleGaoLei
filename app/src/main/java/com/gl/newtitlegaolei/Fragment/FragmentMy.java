package com.gl.newtitlegaolei.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initKun();
        initlistenter();
    }
    //点击事件
    private void initlistenter() {
        dsf_qq.setOnClickListener(this);
    }
    //找控件
    private void initKun() {
        ImageView dsf_phone=  (ImageView) getActivity().findViewById(R.id.DSF_phone);
        ImageView dsf_weixin= (ImageView) getActivity().findViewById(R.id.DSF_weixin);
        dsf_qq = (ImageView) getActivity().findViewById(R.id.DSF_qq);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.DSF_qq:
                DSFQQ();
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
