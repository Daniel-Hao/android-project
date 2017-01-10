package com.daniel.hao.activity.main.fragment.cfrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daniel.hao.activity.R;
import com.daniel.hao.base.BaseFragment;

/**
 * Created by 95 on 2016/9/21.
 */
public class CFragmentFrag extends BaseFragment {

    int i;

    boolean isCome = false;

    public CFragmentFrag() {

    }

    public static CFragmentFrag newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        CFragmentFrag fragment = new CFragmentFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("hdl:", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isCome = true;
        Log.e("hdl:", "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_c_1, null);
        initView(rootView);
        return rootView;
    }

    @Override
    public void initView(View rootView) {
        TextView tv = (TextView) rootView.findViewById(R.id.tv_c_1);
        tv.setText(getArguments().getString("ARGS"));

        final Button bt = (Button) rootView.findViewById(R.id.bt_c_1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.setText("点击后" + i++);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("hdl:", "onActivityCreated");
        initData();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {

        } else {

        }
    }
}
