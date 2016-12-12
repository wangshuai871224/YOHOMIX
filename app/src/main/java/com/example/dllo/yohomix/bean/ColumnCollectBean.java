package com.example.dllo.yohomix.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/12.
 */
@Entity
public class ColumnCollectBean {
    @Id
    private String id;
    private String url, imgurl;
    @Generated(hash = 1760292231)
    public ColumnCollectBean(String id, String url, String imgurl) {
        this.id = id;
        this.url = url;
        this.imgurl = imgurl;
    }
    @Generated(hash = 55542809)
    public ColumnCollectBean() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImgurl() {
        return this.imgurl;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
