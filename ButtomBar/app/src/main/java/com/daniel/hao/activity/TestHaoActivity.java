package com.daniel.hao.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.daniel.hao.activity.main.fragment.cfrag.CFragmentFrag;
import com.daniel.hao.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 95 on 2016/9/19.
 */
public class TestHaoActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private CFragmentFrag cFragmentFrag;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hao);


        tabLayout = (TabLayout) findViewById(R.id.tablayout);  //获取tablayout
        viewPager = (ViewPager) findViewById(R.id.viewpager);  //获取viewpager


        mAdapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        //viewPager.setOffscreenPageLimit(7);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);
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
