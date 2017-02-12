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

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：高镭
 * 时间：2017/2/9 10:53
 * 班级：1501A
 */
public class FragmentHome extends Fragment{
    private String[] title={"推荐","热点","阳光宽频","北京","社会","娱乐","问答","图片","科技","汽车","体育","财经","军事","国际","段子","趣图","美女","健康","正能量","特卖","房产"};
    private TabLayout home_tablayout;
    private ViewPager home_vpager;
    private List<TitleFragment> fragmentList;


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
     }

    private void initData() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i <title.length ; i++) {
            TitleFragment fragment=new TitleFragment();
            fragmentList.add(fragment);
        }
    }

}
