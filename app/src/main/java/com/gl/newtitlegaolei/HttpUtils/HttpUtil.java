package com.gl.newtitlegaolei.HttpUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 作者：高镭
 * 时间：2017/2/15 19:21
 * 班级：1501A
 */
public class HttpUtil {
   public static <T>void httputilload(final CallbackNewsData callbackUtils,
                                   String homeurl,final Class<T> clazz){
        x.http().get(new RequestParams(homeurl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                ArrayList<T> t=new ArrayList<T>();
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    Iterator<String> keys = jsonObject.keys();
                    while(keys.hasNext()){
                        String next = keys.next();
                        JSONArray jsonArray=jsonObject.optJSONArray(next);
                        for (int i = 0; i <jsonArray.length() ; i++) {
                            JSONObject object = jsonArray.optJSONObject(i);
                          T t1 = gson.fromJson(object.toString(), clazz);
                           t.add(t1);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callbackUtils.success(t);
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

    public interface CallbackNewsData<T> {
        void success(ArrayList<T> t);
    }

}
