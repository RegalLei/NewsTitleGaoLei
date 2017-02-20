package com.gl.newtitlegaolei.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gl.newtitlegaolei.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;


/**
 * 作者：高镭
 * 时间：2017/2/9 10:53
 * 班级：1501A
 */
public class FragmentVideo extends Fragment {
    private String title[] = {"热点视频", "娱乐视频", "搞笑视频", "精品视频"};
    private TabLayout video_tablayout;
    private ViewPager video_vpager;
    private List<Title1Fragment> fragmentList;
    private String url[] = {"V9LG4B3A0", "V9LG4CHOR", "V9LG4E6VR", "00850FRB"};
    private PullToRefreshListView pull_listview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initKun();
        initData();
        video_vpager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        video_tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        video_tablayout.setupWithViewPager(video_vpager);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Title1Fragment fragment1 = new Title1Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", url[i]);
            fragment1.setArguments(bundle);
            fragmentList.add(fragment1);
        }
    }

    private void initKun() {
        video_tablayout = (TabLayout) getActivity().findViewById(R.id.video_tablayout);
        video_vpager = (ViewPager) getActivity().findViewById(R.id.video_vpager);
        pull_listview = (PullToRefreshListView) getActivity().findViewById(R.id.pull_listview);
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            JCMediaManager.instance().mediaPlayer.start();
//        }else{
//            JCMediaManager.instance().mediaPlayer.pause();
//        }
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            JCMediaManager.instance().mediaPlayer.pause();
        }else{
            JCMediaManager.instance().mediaPlayer.start();
        }
    }
}
