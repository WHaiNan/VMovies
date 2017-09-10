package com.whn.vmovies.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.whn.library.http.HttpUtil;
import com.whn.library.http.callback.RequestCallback;
import com.whn.vmovies.R;
import com.whn.vmovies.adapter.SeriesAdapter;
import com.whn.vmovies.bean.Series;
import com.whn.vmovies.bean.SeriesList;
import com.whn.vmovies.constant.Api;

import java.util.List;

/**
 * Created by admin on 2017/3/6.
 */

public class SeriesFragment extends BaseFragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String Tag = SeriesFragment.class.getName();
    private ListView listView;
    private SeriesAdapter seriesAdapter;
    private boolean isBottom;
    private int index = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View footView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_series;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.series_list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        seriesAdapter = new SeriesAdapter(getActivity(), null, R.layout.fragment_series_list);
        footView = LayoutInflater.from(getActivity()).inflate(R.layout.footer_view, null);
        //listView.addFooterView(footView);
        listView.setAdapter(seriesAdapter);
        listView.setOnScrollListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        initData(Stated.START, Api.SERIES);
    }

    @Override
    public void onRefresh() {
        initData(Stated.START, Api.SERIES);
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
                    SeriesList list = gson.fromJson(result, SeriesList.class);
                    List<Series> data = list.getData();
                    switch (stated) {
                        case START:
                            seriesAdapter.updateRes(data);
                            break;
                        case END:
                            seriesAdapter.addRes(data);
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
                    String path = Api.SERIES + "?p=" + index + "&size=10";
                    initData(Stated.END, path);
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
