package com.china.oil.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.china.oil.R;
import com.china.oil.activity.VideoActivity;
import com.china.oil.adapter.SafeAdapter;
import com.china.oil.entity.SafeVideo;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SafeFragment extends Fragment {
    private View view;

    @BindView(R.id.safe_lv)
    ListView safe_lv;
    List<SafeVideo> mList;

    @BindView(R.id.banner)
    Banner mBanner;

    private SafeAdapter safeAdapter;
    private Intent intent;

    private ArrayList<String> imagePath = new ArrayList<>();

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
        mList.add(new SafeVideo("三大电商Q1财报的","据Q1财报显示，阿里巴巴疫情中净利润同比下滑88%","http://seopic.699pic.com/photo/50017/5948.jpg_wh1200.jpg",VIDEO_URL));
        mList.add(new SafeVideo("网易官宣京东沉默","截至3月底，拼多多的年度活跃买家达到6.28亿","http://seopic.699pic.com/photo/50033/2331.jpg_wh1200.jpg",VIDEO_URL));
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

        imagePath.add("http://seopic.699pic.com/photo/50032/5463.jpg_wh1200.jpg");
        imagePath.add("http://seopic.699pic.com/photo/50031/2015.jpg_wh1200.jpg");
        imagePath.add("http://seopic.699pic.com/photo/50052/7815.jpg_wh1200.jpg");
        imagePath.add("http://seopic.699pic.com/photo/40150/3529.jpg_wh1200.jpg");
        imagePath.add("http://seopic.699pic.com/photo/50064/6750.jpg_wh1200.jpg");

        for (int i = 0; i <imagePath.size() ; i++) {
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(Objects.requireNonNull(getActivity())).load(path).into(imageView);
                }
            });
            mBanner.setDelayTime(3000);
            mBanner.setImages(imagePath);
            mBanner.start();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

}

