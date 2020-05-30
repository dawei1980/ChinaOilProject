package com.china.oil.entity;

import java.io.Serializable;

public class SafeVideo implements Serializable {
    private String title;
    private String imgUrl;
    private String videoUrl;
    private String content;

    public SafeVideo(String title,String content ,String imgUrl,String videoUrl){
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
