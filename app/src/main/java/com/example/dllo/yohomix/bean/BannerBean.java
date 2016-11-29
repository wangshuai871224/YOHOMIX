package com.example.dllo.yohomix.bean;

import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class BannerBean {

    /**
     * status : 0
     * code : 200000
     * data : [{"link":"12882","frame":"1","magazine":"0","feature":"0","app":"1","delay":"4","image":"http://img01.yohoboys.com/staticimg/2016/11/23/16/01d10932bc47346c345b933a32a2fd2f3a.jpg","contentType":0,"linkType":1,"mergeId":"22020","rid":"22020","cid":"12882","id":"22020"},{"link":"12861","frame":"2","magazine":"0","feature":"0","app":"1","delay":"4","image":"http://img02.yohoboys.com/staticimg/2016/11/22/17/0236a4e797cb49144075f98fc67dd81be1.jpg","contentType":0,"linkType":1,"mergeId":"21998","rid":"21998","cid":"12861","id":"21998"},{"link":"12855","frame":"3","magazine":"0","feature":"0","app":"1","delay":"4","image":"http://img02.yohoboys.com/staticimg/2016/11/22/18/02bc4388e7bacf9b92ceb791fdf4a1c836.jpg","contentType":0,"linkType":1,"mergeId":"21992","rid":"21992","cid":"12855","id":"21992"},{"link":"http://new.yohoboys.com/news/index-12385-0-0.html","frame":"4","magazine":"0","feature":"0","app":"0","delay":"4","image":"http://img02.yohoboys.com/staticimg/2016/11/23/09/0212d491632eb7e5576910cc58d28fd8c4.jpg","linkType":3},{"link":"http://new.yohoboys.com/news/index-12678-0-0.html","frame":"5","magazine":"0","feature":"0","app":"0","delay":"4","image":"http://img02.yohoboys.com/staticimg/2016/11/23/09/02cd17efb5c1177128ddf3f43f99e93054.jpg","linkType":3},{"link":"12842","frame":"6","magazine":"0","feature":"0","app":"1","delay":"4","image":"http://img02.yohoboys.com/staticimg/2016/11/21/21/0253e83893f25fc432008ca721af15fa4b.jpg","contentType":0,"linkType":1,"mergeId":"21979","rid":"21979","cid":"12842","id":"21979"},{"link":"12763","frame":"7","magazine":"0","feature":"0","app":"1","delay":"4","image":"http://img02.yohoboys.com/staticimg/2016/11/20/12/025d62aa17f2124d1780ac6b1b99d717b4.jpg","contentType":0,"linkType":1,"mergeId":"21896","rid":"21896","cid":"12763","id":"21896"},{"link":"12778","frame":"8","magazine":"0","feature":"0","app":"1","delay":"4","image":"http://img01.yohoboys.com/staticimg/2016/11/18/20/01181c20ab5c2e0696fcce79d00c3d9a22.jpg","contentType":0,"linkType":1,"mergeId":"21911","rid":"21911","cid":"12778","id":"21911"},{"link":"12738","frame":"9","magazine":"0","feature":"0","app":"1","delay":"4","image":"http://img01.yohoboys.com/staticimg/2016/11/19/13/01cfb9e95d32db4eab33b1c84d9b4a0504.jpg","contentType":0,"linkType":1,"mergeId":"21870","rid":"21870","cid":"12738","id":"21870"},{"link":"12739","frame":"10","magazine":"0","feature":"0","app":"1","delay":"4","image":"http://img02.yohoboys.com/staticimg/2016/11/17/18/0237c4d30a2608263c55ba1e99fd2ff9a4.jpg","contentType":0,"linkType":1,"mergeId":"21871","rid":"21871","cid":"12739","id":"21871"}]
     * message :
     */

    private String status;
    private int code;
    private String message;
    /**
     * link : 12882
     * frame : 1
     * magazine : 0
     * feature : 0
     * app : 1
     * delay : 4
     * image : http://img01.yohoboys.com/staticimg/2016/11/23/16/01d10932bc47346c345b933a32a2fd2f3a.jpg
     * contentType : 0
     * linkType : 1
     * mergeId : 22020
     * rid : 22020
     * cid : 12882
     * id : 22020
     */

    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String link;
        private String frame;
        private String magazine;
        private String feature;
        private String app;
        private String delay;
        private String image;
        private int contentType;
        private int linkType;
        private String mergeId;
        private String rid;
        private String cid;
        private String id;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getFrame() {
            return frame;
        }

        public void setFrame(String frame) {
            this.frame = frame;
        }

        public String getMagazine() {
            return magazine;
        }

        public void setMagazine(String magazine) {
            this.magazine = magazine;
        }

        public String getFeature() {
            return feature;
        }

        public void setFeature(String feature) {
            this.feature = feature;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getDelay() {
            return delay;
        }

        public void setDelay(String delay) {
            this.delay = delay;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getContentType() {
            return contentType;
        }

        public void setContentType(int contentType) {
            this.contentType = contentType;
        }

        public int getLinkType() {
            return linkType;
        }

        public void setLinkType(int linkType) {
            this.linkType = linkType;
        }

        public String getMergeId() {
            return mergeId;
        }

        public void setMergeId(String mergeId) {
            this.mergeId = mergeId;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
