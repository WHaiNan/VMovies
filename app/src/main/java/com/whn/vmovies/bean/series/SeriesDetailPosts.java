package com.whn.vmovies.bean.series;

/**
 * Created by admin on 2017/3/9.
 */

public class SeriesDetailPosts {
    /**
     * series_postid : 1792
     * number : 85
     * title : 番外篇-绿幕搭建和拍摄技巧 上海温哥华电影学院
     * addtime : 2016.06.28
     * duration : 1049
     * thumbnail : http://cs.vmoiver.com/Uploads/Series/2016-06-28/5772695e63d1a.jpeg
     * source_link : http://v.youku.com/v_show/id_XMTYyNDExOTkyNA==.html?f=26857029
     */

    private String series_postid;
    private String number;
    private String title;
    private String addtime;
    private String duration;
    private String thumbnail;
    private String source_link;

    public String getSeries_postid() {
        return series_postid;
    }

    public void setSeries_postid(String series_postid) {
        this.series_postid = series_postid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSource_link() {
        return source_link;
    }

    public void setSource_link(String source_link) {
        this.source_link = source_link;
    }
}
