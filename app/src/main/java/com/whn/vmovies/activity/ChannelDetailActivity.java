package com.whn.vmovies.activity;


import android.app.FragmentBreadCrumbs;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import com.whn.library.http.HttpUtil;
import com.whn.library.http.callback.RequestCallback;
import com.whn.vmovies.R;
import com.whn.vmovies.adapter.MoviesAdapter;
import com.whn.vmovies.bean.Movies;
import com.whn.vmovies.bean.MoviesList;
import com.whn.vmovies.constant.Api;
import com.whn.vmovies.fragment.MovieListFragment;

import java.util.List;

public class ChannelDetailActivity extends BaseActivity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ListView listView;
    private MoviesAdapter moviesAdapter;
    String path = Api.CHANNEL_DETAIL;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int index = 1;
    private boolean isBottom;
    String cateId;
    private ImageView back;
    private TextView cateTitle;
    private View footView;


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.movies_list);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.re_top_layout);
        layout.setVisibility(View.VISIBLE);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        cateTitle = (TextView) findViewById(R.id.cate_title);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        moviesAdapter = new MoviesAdapter(this, null, R.layout.movie_list);
        footView = LayoutInflater.from(this).inflate(R.layout.footer_view, null);
        //listView.addFooterView(footView);
        listView.setAdapter(moviesAdapter);
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        Intent intent = getIntent();
        cateId = intent.getStringExtra("cateId");
        String cateName = intent.getStringExtra("cateName");
        cateTitle.setText(cateName);
        initData(Stated.START, path + "?cateid=" + cateId);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movies item = moviesAdapter.getItem(position);
        Intent intent = new Intent(this, MoviesDetailActivity.class);
        intent.putExtra("title", item.getWx_small_app_title());
        intent.putExtra("like_num", item.getLike_num());
        intent.putExtra("share_num", item.getShare_num());
        intent.putExtra("url", item.getRequest_url());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onRefresh() {
        initData(Stated.START, path + "?cateid=" + cateId);
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
                    MoviesList moviesList = gson.fromJson(result, MoviesList.class);
                    List<Movies> data = moviesList.getData();
                    switch (stated) {
                        case START:
                            moviesAdapter.updateRes(data);
                            break;
                        case END:
                            moviesAdapter.addRes(data);
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
                    footView.setVisibility(View.VISIBLE);
                    index++;
                    String path = this.path + "?p=" + index + "&size=10" + "&cateid=" + cateId;
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
