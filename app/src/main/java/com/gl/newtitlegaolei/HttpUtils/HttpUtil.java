package com.gl.newtitlegaolei.HttpUtils;

import com.gl.newtitlegaolei.Beans.Bean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 作者：高镭
 * 时间：2017/2/15 19:21
 * 班级：1501A
 */
public class HttpUtil {
   public static void httputilload(final CallbackUtils callbackUtils,String homeurl){
        x.http().get(new RequestParams(homeurl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Bean bean = new Gson().fromJson(result, Bean.class);
                callbackUtils.success(bean);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public interface CallbackUtils<T>{
        void success(T t);
    }
}
