package com.china.oil.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.china.oil.R;
import com.china.oil.activity.VideoActivity;
import com.china.oil.adapter.SafeAdapter;
import com.china.oil.entity.SafeVideo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SafeFragment extends Fragment {
    private View view;

    @BindView(R.id.safe_lv)
    ListView safe_lv;
    List<SafeVideo> mList;

    private SafeAdapter safeAdapter;
    private Intent intent;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_safe, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView(){
        mList = new ArrayList<>();
        mList.add(new SafeVideo("滴滴剑指货运市场，货拉拉们准备好了吗？","http://seopic.699pic.com/photo/50051/4132.jpg_wh1200.jpg",""));
        mList.add(new SafeVideo("网易官宣京东沉默，中概股回归加速","http://seopic.699pic.com/photo/50064/6750.jpg_wh1200.jpg",""));

        safeAdapter = new SafeAdapter(getActivity(),mList);
        safe_lv.setAdapter(safeAdapter );

        safe_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("title", mList.get(position).getTitle());
                intent.putExtra("video_url", mList.get(position).getVideoUrl());
                startActivity(intent);
            }
        });
    }
}

