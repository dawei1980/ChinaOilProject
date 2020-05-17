package com.china.oil.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

import com.china.oil.R;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class ChartFragment extends Fragment {
    private View view;
    private static final String TAG = "BaseActivity";
    protected Unbinder mUnbinder;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), null);
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        setStateBarColor();
        initLayout();
        requestData();

        return view;
    }

    public void setStateBarColor(){
        setStateBarColor(R.color.color_ff7000);
    }

    public void setStateBarColor(@ColorRes int color){
        if(Build.VERSION.SDK_INT >= 21){
            Window window = getActivity().getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(color));
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initLayout();

    protected abstract void requestData();
}
