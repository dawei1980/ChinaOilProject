package com.china.oil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.china.oil.R;

import butterknife.ButterKnife;

public class ApprovalFragment extends Fragment {
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_approval, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView(){

    }
}
