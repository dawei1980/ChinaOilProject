package com.china.oil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.china.oil.R;

import butterknife.ButterKnife;

public class MineFragment extends BaseFragment {
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mine, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void requestData() {

    }

    private void initView(){

    }
}
