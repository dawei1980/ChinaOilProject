package com.china.oil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.china.oil.R;
import com.github.mikephil.charting.charts.LineChart;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment{

    private View view;
    private LineChart mLineChar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, null);
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
