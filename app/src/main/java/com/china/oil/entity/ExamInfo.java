package com.china.oil.entity;

import java.io.Serializable;

public class ExamInfo implements Serializable {
    private String headerUrl;
    private String title;
    private String content;
    private String time;

    public ExamInfo(String title,String content ,String headerUrl,String time){
        this.title = title;
        this.content = content;
        this.headerUrl = headerUrl;
        this.time = time;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
