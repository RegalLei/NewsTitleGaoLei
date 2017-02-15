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


/**
 * 作者：高镭
 * 时间：2017/2/9 10:53
 * 班级：1501A
 */
public class FragmentHome extends Fragment{
    private String[] title={"精选","汽车","电影","笑话","体育","娱乐","论坛","教育","科技","彩票","财经","NBA",
            "数码","移动","游戏","时尚","情感","电台","房产"};
    private TabLayout home_tablayout;
    private ViewPager home_vpager;
    private List<TitleFragment> fragmentList;
    private PullToRefreshListView pull_listview;
    private String url[]={"T1370583240249","T1348654060988","T1348648650048","T1350383429665", "T1348649079062","T1348648517839","T1349837670307","T1348654225495",
            "T1348649580692", "T1356600029035","T1348648756099","T1348654151579","T1348649145984","T1348649776727","T1351233117091","T1348654151579",
            "T1348650593803", "T1348650839000","T1379038288239","5YyX5Lqs"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initKun();
        initData();
        home_vpager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
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
        home_tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        home_tablayout.setupWithViewPager(home_vpager);
    }



    private void initKun() {
        home_tablayout = (TabLayout) getActivity().findViewById(R.id.home_tablayout);
        home_vpager = (ViewPager) getActivity().findViewById(R.id.home_vpager);
        pull_listview = (PullToRefreshListView) getActivity().findViewById(R.id.pull_listview);
     }

    private void initData() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i <title.length ; i++) {
            TitleFragment fragment=new TitleFragment();
            Bundle bundle=new Bundle();
            bundle.putString("url",url[i]);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);

        }
    }

}
