package com.whn.vmovies.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.google.gson.Gson;

import com.whn.library.http.HttpUtil;
import com.whn.library.http.callback.RequestCallback;
import com.whn.vmovies.R;
import com.whn.vmovies.adapter.UpdateAdapter;
import com.whn.vmovies.bean.series.SeriesDetailData;
import com.whn.vmovies.bean.series.SeriesDetailList;
import com.whn.vmovies.bean.series.SeriesDetailPosts;
import com.whn.vmovies.bean.series.SeriesDetailPostsBean;
import com.whn.vmovies.constant.Api;

import java.util.List;

public class SeriesDetailActivity extends BaseActivity implements View.OnClickListener {
    private String seriesId;
    private TextView update_title;
    private TextView detail_title;
    private TextView update_weekly;
    private TextView update_to;
    private TextView tag_name;
    private TextView movies_content;
    private ListView listView;
    private UpdateAdapter updateAdapter;
    private TabLayout tab;
    private PopupWindow popupWindow;


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_series_detail;
    }

    @Override
    protected void initView() {
        ImageView back = (ImageView) findViewById(R.id.video_back);
        ImageView share = (ImageView) findViewById(R.id.video_share);
        share.setOnClickListener(this);
        LinearLayout share_layout = (LinearLayout) findViewById(R.id.layout_share);
        share_layout.setOnClickListener(this);
        View headView = LayoutInflater.from(this).inflate(R.layout.series_head_view, null);
        tab = (TabLayout) headView.findViewById(R.id.tab);
        update_title = (TextView) headView.findViewById(R.id.update_title);
        detail_title = (TextView) headView.findViewById(R.id.detail_title);
        update_weekly = (TextView) headView.findViewById(R.id.update_weekly);
        update_to = (TextView) headView.findViewById(R.id.update_to);
        tag_name = (TextView) headView.findViewById(R.id.tag_name);
        movies_content = (TextView) headView.findViewById(R.id.movies_content);
        listView = (ListView) findViewById(R.id.update_lv);
        listView.addHeaderView(headView);
        updateAdapter = new UpdateAdapter(this, null, R.layout.update_detail);
        listView.setAdapter(updateAdapter);
        back.setOnClickListener(this);
        Intent intent = getIntent();
        seriesId = intent.getStringExtra("seriesId");
        initData();
        initPopupWindow();
    }

    private void initData() {
        HttpUtil.getStringAsync(Api.SERIES_DETAIL + "?seriesid=" + seriesId, new RequestCallback() {
            @Override
            public void onSucceed(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    SeriesDetailList seriesDetailList = gson.fromJson(result, SeriesDetailList.class);
                    SeriesDetailData data = seriesDetailList.getData();
                    final List<SeriesDetailPostsBean> posts = data.getPosts();
                    update_title.setText("第" + data.getUpdate_to() + "集  " + posts.get(0).getList().get(0).getTitle());
                    detail_title.setText(data.getTitle());
                    update_weekly.setText("更新: " + data.getWeekly());
                    update_to.setText("集数: 更新至" + data.getUpdate_to() + "集");
                    tag_name.setText("类型: " + data.getTag_name());
                    movies_content.setText(data.getContent());
                    for (int i = 0; i < posts.size(); i++) {
                        tab.addTab(tab.newTab().setText(posts.get(i).getFrom_to()));
                    }
                    updateAdapter.addRes(posts.get(0).getList());
                    tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            List<SeriesDetailPosts> slist = posts.get(tab.getPosition()).getList();
                            updateAdapter.updateRes(slist);
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onPrepare() {

            }

            @Override
            public void onLoadFinish() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_back:
                finish();
            case R.id.video_share:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.layout_share:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.close_pop:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                break;
        }
    }

    private void initPopupWindow() {
        View pop = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);
        ImageView pop_close = (ImageView) pop.findViewById(R.id.close_pop);
        pop_close.setOnClickListener(this);
        popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT, 300);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
