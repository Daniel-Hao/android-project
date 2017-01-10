package com.daniel.hao.activity.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.hao.activity.R;
import com.daniel.hao.activity.main.fragment.cfrag.CFragmentFrag;
import com.daniel.hao.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 95 on 2016/9/19.
 */
public class CFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private CFragmentFrag cFragmentFrag;

    public CFragment() {

    }

    public static CFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        CFragment fragment = new CFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_c, null);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("hdl", "CFragment onActivityCreated");
        initData();
    }

    @Override
    public void initView(View rootView) {
        /*TextView tv = (TextView) rootView.findViewById(R.id.tv);
        tv.setText(getArguments().getString("ARGS"));*/

        tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout);  //获取tablayout
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);  //获取viewpager
    }

    @Override
    public void initData() {
        mAdapter = new TabPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);
        /*tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {  //设置联动，点击导航卡后viewpager跟着滑动
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class TabPagerAdapter extends FragmentPagerAdapter {
        public List<String> titles = Arrays.asList("tab1", "tab2", "tab3", "tab4", "tab5", "tab6", "tab7");  //给七个导航卡取名字

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
            for (int i = 0; i < titles.size(); i++) {
                mFragments.add(CFragmentFrag.newInstance(titles.get(i)));
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
