package com.whn.vmovies.bean;

import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */

public class BannerList {
    /**
     * status : 0
     * msg : OK
     * data : [{"bannerid":"1572","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/03/06/58bd793041f30.jpg","description":"","userid":"927555","addtime":"1488812336","uptime":"1488812336","orderid":"7","cateid":"0","count_click":"0","status":"1","start_time":"1488816000","end_time":"1488902340","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"http:\\/\\/www.vmovier.com\\/fan203?inner_app=1\",\"app_show_type\":null}","extra_data":{"app_banner_type":"1","app_banner_param":"http://www.vmovier.com/fan203?inner_app=1","app_show_type":""}},{"bannerid":"1574","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/03/07/58bdfcfbbd69f.jpg","description":"","userid":"919390","addtime":"1488846075","uptime":"1488846075","orderid":"6","cateid":"0","count_click":"0","status":"1","start_time":"1488846075","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"51272\",\"app_show_type\":\"all\"}","extra_data":{"app_banner_type":"2","app_banner_param":"51272","app_show_type":"all","is_album":"0"}},{"bannerid":"1566","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/03/03/58b8e01a4ad50.jpg","description":"","userid":"927555","addtime":"1488511002","uptime":"1488511002","orderid":"5","cateid":"0","count_click":"593","status":"1","start_time":"1488556800","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"51237\",\"app_show_type\":null}","extra_data":{"app_banner_type":"2","app_banner_param":"51237","app_show_type":"","is_album":"0"}},{"bannerid":"1546","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/02/24/58afada3ec5de.jpg","description":"","userid":"927555","addtime":"1487908260","uptime":"1487908260","orderid":"4","cateid":"0","count_click":"1254","status":"1","start_time":"1487908259","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"51158\",\"app_show_type\":null}","extra_data":{"app_banner_type":"2","app_banner_param":"51158","app_show_type":"","is_album":"0"}},{"bannerid":"1548","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/02/24/58afce12296e3.jpg","description":"","userid":"927555","addtime":"1487916562","uptime":"1487916562","orderid":"3","cateid":"0","count_click":"4358","status":"1","start_time":"1487909700","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"51150\",\"app_show_type\":\"all\"}","extra_data":{"app_banner_type":"2","app_banner_param":"51150","app_show_type":"all","is_album":"0"}},{"bannerid":"1318","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/12/27/5862369ddd867.jpg","description":"","userid":"551913","addtime":"1482831518","uptime":"1482831518","orderid":"2","cateid":"0","count_click":"42361","status":"1","start_time":"1478841300","end_time":"0","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"http:\\/\\/school.xinpianchang.com\\/film-room\\/application\\/app_m.html?inner_app=1\"}","extra_data":{"app_banner_type":"1","app_banner_param":"http://school.xinpianchang.com/film-room/application/app_m.html?inner_app=1"}},{"bannerid":"1535","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/02/23/58aec3829fe91.jpg","description":"","userid":"551913","addtime":"1487848322","uptime":"1487848322","orderid":"1","cateid":"0","count_click":"2341","status":"1","start_time":"1487558100","end_time":"0","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"http:\\/\\/www.xinpianchang.com\\/activity\\/independence\\/ts-travel2\",\"app_show_type\":\"all\"}","extra_data":{"app_banner_type":"1","app_banner_param":"http://www.xinpianchang.com/activity/independence/ts-travel2","app_show_type":"all"}}]
     */

    private String status;
    private String msg;
    private List<Banner> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Banner> getData() {
        return data;
    }

    public void setData(List<Banner> data) {
        this.data = data;
    }
}
