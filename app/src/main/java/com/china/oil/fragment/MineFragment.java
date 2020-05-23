package com.china.oil.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.china.oil.R;
import com.china.oil.activity.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends Fragment {
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mine, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView(){

    }


    @OnClick({R.id.user_layout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_layout:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
