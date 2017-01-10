package com.daniel.hao.activity.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniel.hao.activity.R;
import com.daniel.hao.base.BaseFragment;

/**
 * Created by 95 on 2016/9/19.
 */
public class BFragment extends BaseFragment {

    public BFragment() {

    }

    public static BFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        BFragment fragment = new BFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_b, null);
        initView(rootView);
        return rootView;
    }


    @Override
    public void initView(View rootView) {
        TextView tv = (TextView) rootView.findViewById(R.id.tv);
        tv.setText(getArguments().getString("ARGS"));
    }

    @Override
    public void initData() {

    }
}
