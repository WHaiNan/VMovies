package com.whn.vmovies.bean;

/**
 * Created by admin on 2017/3/7.
 */

public class ExtraDataBean {
    /**
     * app_banner_type : 1
     * app_banner_param : http://www.vmovier.com/fan203?inner_app=1
     * app_show_type :
     */

    private String app_banner_type;
    private String app_banner_param;
    private String app_show_type;

    public String getApp_banner_type() {
        return app_banner_type;
    }

    public void setApp_banner_type(String app_banner_type) {
        this.app_banner_type = app_banner_type;
    }

    public String getApp_banner_param() {
        return app_banner_param;
    }

    public void setApp_banner_param(String app_banner_param) {
        this.app_banner_param = app_banner_param;
    }

    public String getApp_show_type() {
        return app_show_type;
    }

    public void setApp_show_type(String app_show_type) {
        this.app_show_type = app_show_type;
    }
}
