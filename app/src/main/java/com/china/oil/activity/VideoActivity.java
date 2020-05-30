package com.china.oil.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.china.oil.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.simpleExoPlayerView)
    SimpleExoPlayerView simpleExoPlayerView;
    @BindView(R.id.safe_detail_title)
    TextView safe_detail_title;
    @BindView(R.id.safe_detail_content)
    TextView safe_detail_content;
    @BindView(R.id.title_text)
    TextView title_text;

    static Handler mainHandler = new Handler();
    // step1. 创建一个默认的TrackSelector
    // 创建带宽
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    // 创建轨道选择工厂
    TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

    // 创建轨道选择器实例
    TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

    //step2. 创建播放器
    SimpleExoPlayer player;
    private int resumeWindow;
    private long resumePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_detail);
        ButterKnife.bind(this);

        initExoplayer();
    }

    private void initExoplayer() {
        title_text.setText("安全详情");
        simpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.simpleExoPlayerView);

        Intent intent = getIntent();
        String url = intent.getStringExtra("video_url");
        String title =intent.getStringExtra("title");
        String content =intent.getStringExtra("content");

        safe_detail_title.setText(title);
        safe_detail_content.setText(content);

        //step2. 创建播放器
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        // 创建加载数据的工厂
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "yourApplicationName"), (TransferListener<? super DataSource>) bandwidthMeter);

        // 创建解析数据的工厂
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        // 传入Uri、加载数据的工厂、解析数据的工厂，就能创建出MediaSource
//        String url = "http://7xstkb.com1.z0.glb.clouddn.com/agen_apple.mp4";
        Uri mp4VideoUri = Uri.parse(url);
        MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri,
                dataSourceFactory, extractorsFactory, null, null);
        // Prepare
        player.prepare(videoSource);
    }

    private void startPlayer() {
        simpleExoPlayerView.setPlayer(player);
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (player != null&& player.getCurrentPosition()>0) {

            player.setPlayWhenReady(true);
            player.seekTo(resumePosition);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null && player.getPlayWhenReady()) {
            resumeWindow = player.getCurrentWindowIndex();
            resumePosition = Math.max(0, player.getContentPosition());
            player.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放播放器
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @OnClick({R.id.back_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
        }
    }

}
