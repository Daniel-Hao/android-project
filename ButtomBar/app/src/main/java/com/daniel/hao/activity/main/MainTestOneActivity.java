package com.daniel.hao.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.daniel.hao.activity.R;
import com.daniel.hao.activity.main.fragment.AFragment;
import com.daniel.hao.activity.main.fragment.BFragment;
import com.daniel.hao.activity.main.fragment.CFragment;
import com.daniel.hao.activity.main.fragment.DFragment;
import com.daniel.hao.activity.main.fragment.EFragment;
import com.daniel.hao.base.BaseActivity;
import com.daniel.hao.corelib.view.CustomViewPager;

import java.util.ArrayList;

public class MainTestOneActivity extends BaseActivity implements
        BottomNavigationBar.OnTabSelectedListener,
        ViewPager.OnPageChangeListener {

    public static final int DEFAULT_SELECTED = 2;
    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter mAdapter;

    private BottomNavigationBar bottomBar;
    private CustomViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_one);

        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        bottomBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, getString(R.string.a))
                .setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, getString(R.string.b))
                        .setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, getString(R.string.c))
                        .setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, getString(R.string.d))
                        .setActiveColorResource(R.color.brown))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, getString(R.string.e))
                        .setActiveColorResource(R.color.grey))
                .setFirstSelectedPosition(DEFAULT_SELECTED)
                .initialise();
        initData();
    }

    private void initData() {

        fragments = getFragments();
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(2);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(this);
    }

    //region 私有方法

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(AFragment.newInstance(getString(R.string.a)));
        fragments.add(BFragment.newInstance(getString(R.string.b)));
        fragments.add(CFragment.newInstance(getString(R.string.c)));
        fragments.add(DFragment.newInstance(getString(R.string.d)));
        fragments.add(EFragment.newInstance(getString(R.string.e)));
        return fragments;
    }

    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        bottomBar.selectTab(index);
        /*if (fragments.get(index) == null) {
            // 如果MessageFragment为空，则创建一个并添加到界面上
            transaction.add(R.id.viewpager, fragments.get(index));
        } else {
            // 如果MessageFragment不为空，则直接将它显示出来
            transaction.show(fragments.get(index));
        }*/
        if (fragments.get(index) != null) {
            // 如果MessageFragment为空，则创建一个并添加到界面上
            transaction.show(fragments.get(index));
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (fragments == null || fragments.size() <= 0) {
            return;
        }
        for (int i = 0; i < fragments.size(); i++) {
            transaction.hide(fragments.get(i));
        }

    }

    //endregion


    @Override
    public void onTabSelected(int position) {
        setTabSelection(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
