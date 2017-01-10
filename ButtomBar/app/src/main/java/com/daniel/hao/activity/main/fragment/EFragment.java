package com.daniel.hao.activity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.hao.activity.R;
import com.daniel.hao.activity.TestHaoActivity;
import com.daniel.hao.base.BaseFragment;

/**
 * Created by 95 on 2016/9/19.
 */
public class EFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout mLayLinear1;
    private LinearLayout mLayLinear2;
    private LinearLayout mLayLinear3;

    public EFragment() {

    }

    public static EFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        EFragment fragment = new EFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_e, null);
        initView(rootView);
        return rootView;
    }

    @Override
    public void initView(View rootView) {
        TextView tv = (TextView) rootView.findViewById(R.id.tv);
        tv.setText(getArguments().getString("ARGS"));

        tv.setText(null);

        mLayLinear1 = (LinearLayout) rootView.findViewById(R.id.layLinear1);
        mLayLinear2 = (LinearLayout) rootView.findViewById(R.id.layLinear2);
        mLayLinear3 = (LinearLayout) rootView.findViewById(R.id.layLinear3);
        mLayLinear1.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.layLinear1) {
            Intent it = new Intent(getActivity(), TestHaoActivity.class);
            startActivity(it);
        } else if (id == R.id.layLinear2) {

        } else if (id == R.id.layLinear3) {

        }
    }
}
