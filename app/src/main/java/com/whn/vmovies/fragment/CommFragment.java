package com.whn.vmovies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.whn.library.http.HttpUtil;
import com.whn.library.http.callback.RequestCallback;
import com.whn.vmovies.R;
import com.whn.vmovies.activity.MHActivity;
import com.whn.vmovies.adapter.CommAdapter;
import com.whn.vmovies.bean.BehindDetail;
import com.whn.vmovies.bean.BehindList;

import com.whn.vmovies.constant.Api;

import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */

public class CommFragment extends BaseFragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private int index = 1;
    private boolean isBottom;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String path = Api.MHCONTENT + "?cateid=";
    private CommAdapter commAdapter;
    String cateid;
    private View footView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_behind_content;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        cateid = bundle.getString("cateid");
        listView = (ListView) findViewById(R.id.behind_lv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_behind);
        commAdapter = new CommAdapter(getActivity(), null, R.layout.fragment_behind_content_detail);
        footView = LayoutInflater.from(getActivity()).inflate(R.layout.footer_view, null);
        //listView.addFooterView(footView);
        listView.setAdapter(commAdapter);
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        initData(Stated.START, path + cateid);
    }

    @Override
    public void onRefresh() {
        initData(Stated.START, path + cateid);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BehindDetail item = commAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), MHActivity.class);
        intent.putExtra("like_num", item.getLike_num());
        intent.putExtra("share_num", item.getShare_num());
        intent.putExtra("mhtitle", item.getWx_small_app_title());
        intent.putExtra("postId", item.getPostid());
        startActivity(intent);

    }


    enum Stated {
        START, END
    }

    private void initData(final Stated stated, String url) {
        HttpUtil.getStringAsync(url, new RequestCallback() {
            @Override
            public void onSucceed(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    BehindList behindList = gson.fromJson(result, BehindList.class);
                    List<BehindDetail> data = behindList.getData();
                    switch (stated) {
                        case START:
                            commAdapter.updateRes(data);
                            break;
                        case END:
                            commAdapter.addRes(data);
                            break;
                    }
                    swipeRefreshLayout.setRefreshing(false);

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
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                if (isBottom) {
                    index++;
                    initData(Stated.END, path + cateid + "&p=" + index + "&size=10");
                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount == firstVisibleItem + visibleItemCount) {
            isBottom = true;
        } else {
            isBottom = false;
        }
    }


}
