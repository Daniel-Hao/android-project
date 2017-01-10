package com.daniel.hao.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by hdl on 2016/9/19.
 */
public abstract class BaseFragment extends Fragment {

    //Fragment所属的Activity是否第一次显示，Action动画需要使用
    protected boolean mIsFirstShow = true;

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;

    Toast mToast;

    public void setIsFirstShow(boolean inIsFirstShow) {
        mIsFirstShow = inIsFirstShow;
    }

    public void showToast(final String text) {
        if (!TextUtils.isEmpty(text)
                && getActivity() != null
                && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getActivity().getApplicationContext(), text,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });

        }
    }

    public void showToast(final int resId) {
        if (resId != 0
                && getActivity() != null
                && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getActivity().getApplicationContext(), resId,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(resId);
                    }
                    mToast.show();
                }
            });
        }

    }

    /**
     * 隐藏软键盘输入
     */
    public void hideSoftInputView() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            InputMethodManager manager = ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE));
            if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
                if (getActivity().getCurrentFocus() != null && manager != null) {
                    manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }

    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public void showSoftInputView(View view) {
        if (view != null && getActivity() != null && !getActivity().isFinishing()) {
            InputMethodManager manager = ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE));
            if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
                view.requestFocus();
                if (manager != null) {
                    manager.showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
                }
            }
        }
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(containerViewId, fragment);
        ft.commit();
    }

    protected boolean checkActivityAttached() {
        if (getActivity() != null && !getActivity().isFinishing() && isAdded()) {
            return true;
        }
        return false;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    /**
     * 延迟加载
     */
    protected void lazyLoad() {
    }

    public abstract void initView(View rootView);

    public abstract void initData();
}
