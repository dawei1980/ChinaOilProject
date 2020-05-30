package com.china.oil.entity;

import java.io.Serializable;

public class SafeVideo implements Serializable {
    private String title;
    private String imgUrl;
    private String videoUrl;

    public SafeVideo(String title, String imgUrl,String videoUrl){
        this.title = title;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
