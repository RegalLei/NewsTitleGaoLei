package com.gl.newtitlegaolei.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.gl.newtitlegaolei.Beans.Bean;
import com.gl.newtitlegaolei.Home.TitleAdapter;
import com.gl.newtitlegaolei.HttpUtils.HttpUtil;
import com.gl.newtitlegaolei.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/2/12 18:51
 * 班级：1501A
 */
public class TitleFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2, HttpUtil.CallbackNewsData {

    private PullToRefreshListView pull_listview;
    private String url;
    private View view;
    private int page=0;
    private boolean isNeedClear = false;
    private String homeUrl;
    private TitleAdapter adapter;
    private List<Bean> blist=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_title, null,false);
        return view;
    }

    @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            url = getArguments().get("url").toString();
            homeUrl = "http://c.m.163.com/nc/article/headline/"+url+"/"+page+"-20.html";
            HttpUtil.httputilload(this, homeUrl,Bean.class);
//        pull_listview = (PullToRefreshListView) view.findViewById(R.id.pull_listview);
            initKun();
            initData();


    }

    private void initData() {
        adapter = new TitleAdapter(getActivity());
        pull_listview.setAdapter(adapter);
    }

    private void initKun() {
        pull_listview = (PullToRefreshListView) view.findViewById(R.id.pull_listview);
        pull_listview.setMode(PullToRefreshBase.Mode.BOTH);
        pull_listview.setOnRefreshListener(this);
        pull_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String urls = blist.get(i-1).getUrl();

                        Intent intent = new Intent(getActivity(), HomeWebViewActivity.class);
                        intent.putExtra("urls", urls);
                        startActivity(intent);
                    }
        });
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page=0;
        isNeedClear=true;
        HttpUtil.httputilload(this,homeUrl,Bean.class);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page+=5;
        isNeedClear=false;
        HttpUtil.httputilload(this,homeUrl,Bean.class);
    }

    @Override
    public void success(ArrayList bean) {
        blist=bean;
        adapter.addData(bean,false);
        adapter.notifyDataSetChanged();
        pull_listview.onRefreshComplete();
    }
}
