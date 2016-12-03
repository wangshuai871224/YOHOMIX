package com.example.dllo.yohomix.bean;

import java.util.List;

/**
 * Created by dllo on 16/12/3.
 */

public class ReuseBean {

    /**
     * status : 0
     * code : 200000
     * data : [{"id":"1-16","channel_name_cn":"最新","channel_name_en":"NEW","subNav":[{"id":"1-16","app":"1","parent_id":"0","channel_name_cn":"主页","channel_name_en":"Home","current":true,"md5":"c7763203e20a64b270352752d6a1e7c6"},{"id":"2-17","app":"1","parent_id":"0","channel_name_cn":"时尚","channel_name_en":"Fashion","md5":"c7763203e20a64b270352752d6a1e7c6"},{"id":"3","app":"1","parent_id":"0","channel_name_cn":"球鞋","channel_name_en":"Sneaker","md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"5-19","app":"1","parent_id":"0","channel_name_cn":"生活","channel_name_en":"LifeStyle","md5":"c7763203e20a64b270352752d6a1e7c6"},{"id":"6-20","app":"1","parent_id":"0","channel_name_cn":"运动","channel_name_en":"Sports","md5":"c7763203e20a64b270352752d6a1e7c6"},{"id":"22","app":"2","parent_id":"0","channel_name_cn":"美容","channel_name_en":"Beauty","beautyTry":true,"md5":"cfcd208495d565ef66e7dff9f98764da"}],"md5":"c07adbfd3b66f3ae2ef605c8c0c73ae2","isUpdate":0,"zineString":""},{"id":"1","channel_name_cn":"男生资讯","channel_name_en":"BOYS","subNav":[{"id":"1","app":"1","parent_id":"0","channel_name_cn":"主页","channel_name_en":"Home","current":true,"md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"2","app":"1","parent_id":"0","channel_name_cn":"时尚","channel_name_en":"Fashion","md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"3","app":"1","parent_id":"0","channel_name_cn":"球鞋","channel_name_en":"Sneaker","md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"5","app":"1","parent_id":"0","channel_name_cn":"生活","channel_name_en":"LifeStyle","md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"6","app":"1","parent_id":"0","channel_name_cn":"运动","channel_name_en":"Sports","md5":"cfcd208495d565ef66e7dff9f98764da"}],"md5":"d1d7196bbade351e4677798994c46460","isUpdate":0,"zineString":""},{"id":"16","channel_name_cn":"女生资讯","channel_name_en":"GIRLS","subNav":[{"id":"16","app":"2","parent_id":"0","channel_name_cn":"主页","channel_name_en":"Home","current":true,"md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"17","app":"2","parent_id":"0","channel_name_cn":"时尚","channel_name_en":"Fashion","md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"22","app":"2","parent_id":"0","channel_name_cn":"美容","channel_name_en":"Beauty","beautyTry":true,"md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"19","app":"2","parent_id":"0","channel_name_cn":"生活","channel_name_en":"LifeStyle","md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"20","app":"2","parent_id":"0","channel_name_cn":"运动","channel_name_en":"Sports","md5":"cfcd208495d565ef66e7dff9f98764da"}],"md5":"d1d7196bbade351e4677798994c46460","isUpdate":0,"zineString":""},{"id":"7-21","channel_name_cn":"视频","channel_name_en":"VIDEO","isUpdate":0,"zineString":""},{"id":"28-29","channel_name_cn":"专题","channel_name_en":"FEATURE","isUpdate":0,"zineString":""},{"id":"903","channel_name_cn":"杂志","channel_name_en":"MAGAZINE","isUpdate":1,"zineString":"YOHO!BOY 170"},{"id":"904","channel_name_cn":"壁纸","channel_name_en":"WALLPAPER","isUpdate":1,"zineString":"305"}]
     * message :
     */

    private String status;
    private int code;
    private String message;
    /**
     * id : 1-16
     * channel_name_cn : 最新
     * channel_name_en : NEW
     * subNav : [{"id":"1-16","app":"1","parent_id":"0","channel_name_cn":"主页","channel_name_en":"Home","current":true,"md5":"c7763203e20a64b270352752d6a1e7c6"},{"id":"2-17","app":"1","parent_id":"0","channel_name_cn":"时尚","channel_name_en":"Fashion","md5":"c7763203e20a64b270352752d6a1e7c6"},{"id":"3","app":"1","parent_id":"0","channel_name_cn":"球鞋","channel_name_en":"Sneaker","md5":"cfcd208495d565ef66e7dff9f98764da"},{"id":"5-19","app":"1","parent_id":"0","channel_name_cn":"生活","channel_name_en":"LifeStyle","md5":"c7763203e20a64b270352752d6a1e7c6"},{"id":"6-20","app":"1","parent_id":"0","channel_name_cn":"运动","channel_name_en":"Sports","md5":"c7763203e20a64b270352752d6a1e7c6"},{"id":"22","app":"2","parent_id":"0","channel_name_cn":"美容","channel_name_en":"Beauty","beautyTry":true,"md5":"cfcd208495d565ef66e7dff9f98764da"}]
     * md5 : c07adbfd3b66f3ae2ef605c8c0c73ae2
     * isUpdate : 0
     * zineString :
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
        private String id;
        private String channel_name_cn;
        private String channel_name_en;
        private String md5;
        private int isUpdate;
        private String zineString;
        /**
         * id : 1-16
         * app : 1
         * parent_id : 0
         * channel_name_cn : 主页
         * channel_name_en : Home
         * current : true
         * md5 : c7763203e20a64b270352752d6a1e7c6
         */

        private List<SubNavBean> subNav;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getChannel_name_cn() {
            return channel_name_cn;
        }

        public void setChannel_name_cn(String channel_name_cn) {
            this.channel_name_cn = channel_name_cn;
        }

        public String getChannel_name_en() {
            return channel_name_en;
        }

        public void setChannel_name_en(String channel_name_en) {
            this.channel_name_en = channel_name_en;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(int isUpdate) {
            this.isUpdate = isUpdate;
        }

        public String getZineString() {
            return zineString;
        }

        public void setZineString(String zineString) {
            this.zineString = zineString;
        }

        public List<SubNavBean> getSubNav() {
            return subNav;
        }

        public void setSubNav(List<SubNavBean> subNav) {
            this.subNav = subNav;
        }

        public static class SubNavBean {
            private String id;
            private String app;
            private String parent_id;
            private String channel_name_cn;
            private String channel_name_en;
            private boolean current;
            private String md5;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getChannel_name_cn() {
                return channel_name_cn;
            }

            public void setChannel_name_cn(String channel_name_cn) {
                this.channel_name_cn = channel_name_cn;
            }

            public String getChannel_name_en() {
                return channel_name_en;
            }

            public void setChannel_name_en(String channel_name_en) {
                this.channel_name_en = channel_name_en;
            }

            public boolean isCurrent() {
                return current;
            }

            public void setCurrent(boolean current) {
                this.current = current;
            }

            public String getMd5() {
                return md5;
            }

            public void setMd5(String md5) {
                this.md5 = md5;
            }
        }
    }
}
