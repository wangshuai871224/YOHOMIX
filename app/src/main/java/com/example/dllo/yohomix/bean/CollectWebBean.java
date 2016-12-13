package com.example.dllo.yohomix.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/13.
 */

@Entity
public class CollectWebBean {
    @Id(autoincrement = true)
    private Long id;
    private String imgUrl, title, webUrl, time, tagName, videoUrl;
    private int type;
    @Generated(hash = 1428389012)
    public CollectWebBean(Long id, String imgUrl, String title, String webUrl,
            String time, String tagName, String videoUrl, int type) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.title = title;
        this.webUrl = webUrl;
        this.time = time;
        this.tagName = tagName;
        this.videoUrl = videoUrl;
        this.type = type;
    }
    @Generated(hash = 666517011)
    public CollectWebBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getWebUrl() {
        return this.webUrl;
    }
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTagName() {
        return this.tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public String getVideoUrl() {
        return this.videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
}