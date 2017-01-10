package com.daniel.hao.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.daniel.hao.activity.R;
import com.daniel.hao.activity.main.fragment.AFragment;
import com.daniel.hao.activity.main.fragment.BFragment;
import com.daniel.hao.activity.main.fragment.CFragment;
import com.daniel.hao.activity.main.fragment.DFragment;
import com.daniel.hao.activity.main.fragment.EFragment;
import com.daniel.hao.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    public static final int DEFAULT_SELECTED = 2;
    private ArrayList<Fragment> fragments;
    private FragmentTabAdapter tabAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = getFragments();

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, getString(R.string.a))
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

        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.layFrame, bottomNavigationBar);
        tabAdapter.setOnCheckedChangedListener(new FragmentTabAdapter.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(String checkedId, int index) {
                super.OnCheckedChanged(checkedId, index);
                if (index == 0) {
                    showToast("0");
                } else if (index == 1) {
                    showToast("1");
                } else if (index == 2) {
                    showToast("2");
                } else if (index == 3) {
                    showToast("3");
                } else if (index == 4) {
                    showToast("4");
                }
            }
        });

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
    //endregion

}
