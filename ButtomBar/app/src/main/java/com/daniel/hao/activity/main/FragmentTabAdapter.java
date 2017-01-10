package com.daniel.hao.activity.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.daniel.hao.utils.CommonUtil;

import java.util.List;

/**
 * Created by 95 on 2016/9/20.
 */
public class FragmentTabAdapter implements BottomNavigationBar.OnTabSelectedListener {

    private static final String FRAGMENT_A_TAG = "fragment_a_tag";
    private static final String FRAGMENT_B_TAG = "fragment_b_tag";
    private static final String FRAGMENT_C_TAG = "fragment_c_tag";
    private static final String FRAGMENT_D_TAG = "fragment_d_tag";
    private static final String FRAGMENT_E_TAG = "fragment_e_tag";
    /**
     * 一个tab页面对应一个Fragment
     */
    private List<Fragment> fragments;
    /**
     * Activity中所要被替换的区域的id
     */
    private int fragmentContentId;
    /**
     * 当前Tab页面索引
     */
    private int currentTab;

    private Context mContext;
    /**
     * 用于让调用者在切换tab时候增加新的功能
     */
    private OnCheckedChangedListener onCheckedChangedListener;
    private BottomNavigationBar mBottomNavigationBar;

    public FragmentTabAdapter(Context context, List<Fragment> fragments, int fragmentContentId,
                              BottomNavigationBar bottomNavigationBar) {
        this.fragments = fragments;
        this.fragmentContentId = fragmentContentId;
        this.currentTab = 0;
        this.mContext = context;
        this.mBottomNavigationBar = bottomNavigationBar;

        // 默认显示第一页
        FragmentTransaction ft = ((MainActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(MainActivity.DEFAULT_SELECTED));
        ft.commit();
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    private void setCurrentSelected(int ii, String checkedId) {
        FragmentManager framentM = ((MainActivity) mContext).getSupportFragmentManager();
        getCurrentFragment().onPause(); // 暂停当前tab

        Fragment fragment = framentM.findFragmentByTag(checkedId);
        boolean isFragmentExist = true;
        if (fragment == null) {
            try {
                isFragmentExist = false;
                fragment = fragments.get(ii);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fragment.isAdded()) {
            fragment.onResume(); // 启动目标tab的onResume()
        } else {
            if (!isFragmentExist) {
                FragmentTransaction ft = obtainFragmentTransaction(ii, framentM);
                ft.add(fragmentContentId, fragment, checkedId);
                ft.commitAllowingStateLoss();
            }
        }
        showTab(ii, framentM); // 显示目标tab
        // 如果设置了切换tab额外功能功能接口
        if (null != onCheckedChangedListener) {
            onCheckedChangedListener.OnCheckedChanged(checkedId, ii);
        }
        currentTab = ii;
    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx, FragmentManager framentM) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx, framentM);
            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index, FragmentManager framentM) {
        FragmentTransaction ft = framentM.beginTransaction();
        //设置切换动画
        /*if (index > currentTab) {
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        } else {
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }*/
        return ft;
    }

    @Override
    public void onTabSelected(int position) {
        if (!CommonUtil.isNotFastDoubleClick(200)) {
            return;
        }
        if (position == 0) {
            setCurrentSelected(0, FRAGMENT_A_TAG);
        } else if (position == 1) {
            setCurrentSelected(1, FRAGMENT_B_TAG);
        } else if (position == 2) {
            setCurrentSelected(2, FRAGMENT_C_TAG);
        } else if (position == 3) {
            setCurrentSelected(3, FRAGMENT_D_TAG);
        } else if (position == 4) {
            setCurrentSelected(4, FRAGMENT_E_TAG);
        }

    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    public OnCheckedChangedListener getOnCheckedChangedListener() {
        return onCheckedChangedListener;
    }

    public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.onCheckedChangedListener = onCheckedChangedListener;
    }

    /**
     * 切换tab额外功能功能接口
     */
    public static class OnCheckedChangedListener {
        public void OnCheckedChanged(String checkedId, int index) {
        }
    }
}
