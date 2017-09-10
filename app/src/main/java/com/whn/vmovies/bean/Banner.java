package com.whn.vmovies.bean;

/**
 * Created by admin on 2017/3/7.
 */

public class Banner {
    /**
     * bannerid : 1572
     * type : 7
     * object_id : 0
     * title :
     * url : /
     * image : http://cs.vmoiver.com/Uploads/Banner/2017/03/06/58bd793041f30.jpg
     * description :
     * userid : 927555
     * addtime : 1488812336
     * uptime : 1488812336
     * orderid : 7
     * cateid : 0
     * count_click : 0
     * status : 1
     * start_time : 1488816000
     * end_time : 1488902340
     * extra : {"app_banner_type":"1","app_banner_param":"http:\/\/www.vmovier.com\/fan203?inner_app=1","app_show_type":null}
     * extra_data : {"app_banner_type":"1","app_banner_param":"http://www.vmovier.com/fan203?inner_app=1","app_show_type":""}
     */

    private String bannerid;
    private String type;
    private String object_id;
    private String title;
    private String url;
    private String image;
    private String description;
    private String userid;
    private String addtime;
    private String uptime;
    private String orderid;
    private String cateid;
    private String count_click;
    private String status;
    private String start_time;
    private String end_time;
    private String extra;
    private ExtraDataBean extra_data;

    public String getBannerid() {
        return bannerid;
    }

    public void setBannerid(String bannerid) {
        this.bannerid = bannerid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getCount_click() {
        return count_click;
    }

    public void setCount_click(String count_click) {
        this.count_click = count_click;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public ExtraDataBean getExtra_data() {
        return extra_data;
    }

    public void setExtra_data(ExtraDataBean extra_data) {
        this.extra_data = extra_data;
    }
}
