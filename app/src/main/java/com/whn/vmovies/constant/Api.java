package com.whn.vmovies.constant;

/**
 * Created by admin on 2017/3/7.
 */

public class Api {
    public static final String BASE_URL = "http://app.vmoiver.com";
    //首页轮播图
    public static final String BANNER = BASE_URL + "/apiv3/index/getBanner";
    //最新电影列表
    public static final String NEW_LIST = BASE_URL + "/apiv3/post/getPostByTab";
    //频道
    public static final String CHANNEL = BASE_URL + "/apiv3/cate/getList";
    //频道详情
    public static final String CHANNEL_DETAIL = BASE_URL + "/apiv3/post/getPostInCate";
    //系列
    public static final String SERIES = BASE_URL + "/apiv3/series/getList";
    //系列详情页面
    public static final String SERIES_DETAIL = BASE_URL + "/apiv3/series/view";
    //幕后顶部导航
    public static final String MHGUIDE = BASE_URL + "/apiv3/backstage/getCate";
    //幕后内容区
    public static final String MHCONTENT = BASE_URL + "/apiv3/backstage/getPostByCate";
    //电影列表详情
    public static final String MOVIE_BASE_URL = "http://apiv2.vmovier.com";


    /**电影列表详情
     * request_url": "http://app.vmoiver.com/51258?qingapp=app_new"
     * host:   apiv2.vmovier.com
     path:   /api/post/view
     web :   app.vmoiver.com/postid?qingapp=app_new
     */

}
