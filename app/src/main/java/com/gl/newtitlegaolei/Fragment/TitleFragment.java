package com.gl.newtitlegaolei.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gl.newtitlegaolei.Beans.NewsSummary;
import com.gl.newtitlegaolei.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;
import java.util.Map;

/**
 * 作者：高镭
 * 时间：2017/2/12 18:51
 * 班级：1501A
 */
public class TitleFragment extends Fragment{

    private PullToRefreshListView pull_listview;
    private String url;
    private View view;
    private List<NewsSummary> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_title, null, false);
        url = getArguments().get("url").toString();
        initKun();
        net();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void net() {
        RequestParams requestParams = new RequestParams("http://c.m.163.com/nc/article/headline/"+url+"/0-20.html");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {



            @Override
            public void onSuccess(String result) {


                Map<String,List<NewsSummary>> map=new Gson().fromJson(result,new TypeToken<Map<String,List<NewsSummary>>>(){}.getType());
                list = map.get(url);
                Log.d("aaa", list.toString());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                TitleAdapter adapter = new TitleAdapter();
                pull_listview.setAdapter(adapter);
            }
        });
    }
    class TitleAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            Rong rong=null;
            if(view==null){
                rong=new Rong();
                view = View.inflate(getActivity(),R.layout.item_title, null);
                rong.tv_home_item= (TextView) view.findViewById(R.id.tv_home_item);
                view.setTag(rong);
            }else{
                rong= (Rong) view.getTag();
            }
            rong.tv_home_item.setText(list.get(position).digest);
            return view;
        }
    }
    private void initKun() {

        pull_listview = (PullToRefreshListView) view.findViewById(R.id.pull_listview);

    }

    static class Rong{
        TextView tv_home_item;
    }
}
