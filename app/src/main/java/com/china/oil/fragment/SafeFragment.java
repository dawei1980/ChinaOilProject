package com.china.oil.fragment;

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
import com.china.oil.R;
import com.china.oil.activity.VideoActivity;
import com.china.oil.adapter.SafeAdapter;
import com.china.oil.entity.SafeVideo;
import com.china.oil.uils.LocalImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SafeFragment extends Fragment {
    private View view;

    @BindView(R.id.safe_lv)
    ListView safe_lv;
    List<SafeVideo> mList;

    @BindView(R.id.banner)
    private Banner mBanner;

    private SafeAdapter safeAdapter;
    private Intent intent;

    private LocalImageLoader mImageLoader;
    private ArrayList<String> imagePath = new ArrayList<>();
    private ArrayList<String> imageTitle = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_safe, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);

        initView();
        initData();
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

        mImageLoader = new LocalImageLoader();

        //样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //加载器
        mBanner.setImageLoader(mImageLoader);
        //动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //图片标题
        mBanner.setBannerTitles(imageTitle);
        //间隔时间
        mBanner.setDelayTime(4500);
        //是否为自动轮播
        mBanner.isAutoPlay(true);
        //图片小点显示所在位置
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //图片加载地址
        mBanner.setImages(imagePath);
        //启动轮播图。
        mBanner.start();
        //监听轮播图
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "点击了" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151721118&di=649c9a43aed72fbc4d99ec1a031510c6&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F015c7d574b9f8f6ac72525aee98351.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151956771&di=0eb6f306991d24b67a13ceb336f80102&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F00613def3f1beb7a35ae136341be2b589bc46a2d.jpg_320x200.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151847685&di=c7a4b5d08ec43fa629bcb690039a7629&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_080625%2F20080625_2e91a10c444877e88827vri2ZKdGMvQo.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151825129&di=70bf74b87d8a15cb91a2d79f15ed0eaf&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_081016%2F20081016_fee215664d5740e56c13E2YB8giERFEX.jpg");
        imagePath.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505746504&di=930c4d677a02328a142d6fa85ed14580&imgtype=jpg&er=1&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_090113%2F20090113_6ac58b42bea94f0b318e1B6BZb5lPZl5.jpg");

        imageTitle.add("哆啦A梦1");
        imageTitle.add("哆啦A梦2");
        imageTitle.add("哆啦A梦3");
        imageTitle.add("哆啦A梦4");
        imageTitle.add("哆啦A梦5");
        imageTitle.add("哆啦A梦6");
    }

}

