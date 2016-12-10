package com.example.dllo.yohomix.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by dllo on 16/12/6.
 */

@Entity
public class SearchItemBean {
    @Id
    // Long  切记L大写!!!!!!
    private Long id;
    private String body, url;
    private int count;
    @Transient
    // 添加Transient这个注解, 下面的内容不会加入数据库
    // 就是一个普通的成员变量
    private String say;

    @Generated(hash = 1464615205)
    public SearchItemBean(Long id, String body, String url, int count) {
        this.id = id;
        this.body = body;
        this.url = url;
        this.count = count;
    }
    @Generated(hash = 1193441901)
    public SearchItemBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    

}
