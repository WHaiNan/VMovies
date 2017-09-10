package com.whn.vmovies.bean.series;

import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */

public class SeriesDetailPostsBean {
    private String from_to;
    private List<SeriesDetailPosts> list;

    public String getFrom_to() {
        return from_to;
    }

    public void setFrom_to(String from_to) {
        this.from_to = from_to;
    }

    public List<SeriesDetailPosts> getList() {
        return list;
    }

    public void setList(List<SeriesDetailPosts> list) {
        this.list = list;
    }
}
