package com.gl.newtitlegaolei.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gl.newtitlegaolei.Beans.Bean;
import com.gl.newtitlegaolei.Home.TitleAdapter;
import com.gl.newtitlegaolei.HttpUtils.HttpUtil;
import com.gl.newtitlegaolei.NetUrl.HomeNetUrl;
import com.gl.newtitlegaolei.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * 作者：高镭
 * 时间：2017/2/12 18:51
 * 班级：1501A
 */
public class TitleFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2{

    private PullToRefreshListView pull_listview;
    private String url;
    private View view;
    private int page=0;
    private List<Bean.T1370583240249Bean> list;
    private TitleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_title, null,false);
        url = getArguments().get("url").toString();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pull_listview = (PullToRefreshListView) view.findViewById(R.id.pull_listview);
        initData();
        net(false);

    }

    private void initData() {
        adapter = new TitleAdapter(getActivity());
        adapter.addData(list,false);
        pull_listview.setAdapter(adapter);
    }

    private void net(final boolean isNeedClear) {
        String urls= HomeNetUrl.Url(url,page);
        HttpUtil.httputilload(new HttpUtil.CallbackUtils<Bean>() {


            @Override
            public void success(Bean bean) {
                list = bean.getT1370583240249();
                adapter.addData(list,isNeedClear);
                adapter.notifyDataSetChanged();
                initKun();
            }
        },urls);
//        x.http().get(new RequestParams(urls), new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//

////                Log.d("aaa", list.toString());
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {

//            }
//        });
    }
    //下拉
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page=0;
        net(true);
    }
    //上拉
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page+=20;
        net(false);
    }



    private void initKun() {


        pull_listview.setMode(PullToRefreshBase.Mode.BOTH);
        pull_listview.setOnRefreshListener(this);
        pull_listview.onRefreshComplete();
    }


}
