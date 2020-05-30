package com.china.oil.entity;

import java.io.Serializable;

public class SafeVideo implements Serializable {
    private String title;
    private String imgUrl;

    public SafeVideo(String title, String imgUrl){
        this.title = title;
        this.imgUrl = imgUrl;
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
}
