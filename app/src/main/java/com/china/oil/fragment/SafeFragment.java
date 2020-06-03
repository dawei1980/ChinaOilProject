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

        String VIDEO_URL = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv";

        mList = new ArrayList<>();
        mList.add(new SafeVideo("三大电商Q1财报的 “黑与白”：物流配送扛大旗，亏损缺钱是硬伤","据Q1财报显示，阿里巴巴疫情中净利润同比下滑88%，京东净利润同比下滑99%，拼多多亏损幅度扩大了不只一倍。作为国内电商行业三大巨头，在利润方面，和纯实体零售业并无二致。不同的是，他们依然保持着极强的优势和韧性，1-3月份，社会零售总额同比下降19%，但阿里和京东的营收还保持在同比20%以上的增幅，拼多多更是同比增长超过40%。",VIDEO_URL,""));
        mList.add(new SafeVideo("网易官宣京东沉默，中概股回归加速","截至3月底，拼多多的年度活跃买家达到6.28亿，阿里中国零售市场的年度活跃消费者冲至7.26亿。不足1亿的差距，让外界认为阿里遇到了拼多多的强大挑战。当然，用户的争夺确实给阿里造成了压力，但是从一季度这个特殊时期的财报来看，阿里的整体优势依然明显。",VIDEO_URL,""));

        safeAdapter = new SafeAdapter(getActivity(),mList);
        safe_lv.setAdapter(safeAdapter );

        safe_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("title", mList.get(position).getTitle());
                intent.putExtra("video_url", mList.get(position).getVideoUrl());
                intent.putExtra("content", mList.get(position).getContent());
                startActivity(intent);
            }
        });


    }
}

