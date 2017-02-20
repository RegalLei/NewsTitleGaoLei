package com.gl.newtitlegaolei.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gl.newtitlegaolei.Beans.VideoBean;
import com.gl.newtitlegaolei.Home.ViewAdapter;
import com.gl.newtitlegaolei.HttpUtils.HttpUtil;
import com.gl.newtitlegaolei.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 作者：高镭
 * 时间：2017/2/14 21:07
 * 班级：1501A
 */
public class Title1Fragment extends Fragment implements PullToRefreshBase.OnRefreshListener2,HttpUtil.CallbackNewsData{

    private String url;
    private String videoUrl;
    private View view;
    private PullToRefreshListView pull_listview;
    private int page=0;
    private boolean isNeedClear = false;
    private ViewAdapter viewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_title, null, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        url = getArguments().get("url").toString();
        videoUrl = "http://c.3g.163.com/nc/video/list/"+url+"/n/10-10.html";
        HttpUtil.httputilload(this,videoUrl, VideoBean.class);
        initKun();
        initData();

    }

    private void initData() {
        viewAdapter = new ViewAdapter(getActivity());
        pull_listview.setAdapter(viewAdapter);
    }

    private void initKun() {
        pull_listview = (PullToRefreshListView) view.findViewById(R.id.pull_listview);
        pull_listview.setMode(PullToRefreshBase.Mode.BOTH);
        pull_listview.setOnRefreshListener(this);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page=0;
        isNeedClear=true;
        HttpUtil.httputilload(this,videoUrl,VideoBean.class);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        isNeedClear=true;
        HttpUtil.httputilload(this,videoUrl,VideoBean.class);
    }


    @Override
    public void success(ArrayList video) {
        viewAdapter.addvData(video,false);
        viewAdapter.notifyDataSetChanged();
        pull_listview.onRefreshComplete();
    }
}
