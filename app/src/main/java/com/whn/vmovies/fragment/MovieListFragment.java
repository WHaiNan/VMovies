package com.whn.vmovies.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;

import com.whn.library.http.HttpUtil;
import com.whn.library.http.callback.RequestCallback;
import com.whn.vmovies.R;
import com.whn.vmovies.activity.MoviesDetailActivity;
import com.whn.vmovies.adapter.BannerAdapter;
import com.whn.vmovies.adapter.MoviesAdapter;
import com.whn.vmovies.bean.Banner;
import com.whn.vmovies.bean.BannerList;
import com.whn.vmovies.bean.Movies;
import com.whn.vmovies.bean.MoviesList;
import com.whn.vmovies.constant.Api;
import com.whn.vmovies.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MovieListFragment extends BaseFragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener, ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener, Handler.Callback, View.OnTouchListener {

    public static final String TAG = MovieListFragment.class.getName();
    private static final int FIRST_PAGE = 1;
    private ListView listView;
    MoviesAdapter adapter;
    BannerAdapter banAdapter;
    private boolean isBottom;
    private int index = 1;
    private int position = 0;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewPager vp_movies;
    LinearLayout layout;
    private List<Banner> data;
    private Timer timer;
    private final int WHAT_NEXT_PAGE = 1;
    private int currentPosition;
    private View footView;
    TimeChangedListener changedListener;
    private boolean mIsChanged = false;
    private int mCurrentPagePosition;
    private int mCurrentIndex;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changedListener = (TimeChangedListener) getActivity();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        listView = (ListView) findViewById(R.id.movies_list);
        adapter = new MoviesAdapter(getActivity(), null, R.layout.movie_list);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.vp_headview, null);
        vp_movies = (ViewPager) headView.findViewById(R.id.vp_movies);
        layout = (LinearLayout) headView.findViewById(R.id.ll_layout);
        //footView = LayoutInflater.from(getActivity()).inflate(R.layout.footer_view, null);
        listView.addHeaderView(headView);
        //listView.addFooterView(footView);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        initData(Stated.START, Api.NEW_LIST);
        banAdapter = new BannerAdapter(null, getActivity());
        initBanner();
        vp_movies.setAdapter(banAdapter);
        vp_movies.addOnPageChangeListener(this);
        listView.setOnItemClickListener(this);
        vp_movies.setOnTouchListener(this);
    }


    private void initBanner() {
        HttpUtil.getStringAsync(Api.BANNER, new RequestCallback() {


            @Override
            public void onSucceed(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    BannerList bannerList = gson.fromJson(result, BannerList.class);
                    data = bannerList.getData();
                    layout.removeAllViews();
                    for (int i = 0; i < data.size(); i++) {
                        ImageView imageView = new ImageView(getActivity());
                        if (i == 0) {
                            imageView.setImageResource(R.mipmap.news_advert_dot_p);
                        } else {
                            imageView.setImageResource(R.mipmap.news_advert_dot_d);
                        }

                        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(40, 40);
                        mParams.setMargins(10, 10, 10, 10);
                        imageView.setLayoutParams(mParams);
                        layout.addView(imageView);

                    }
                    Log.e(TAG, "onSucceed: " + data.size());
                    banAdapter.addRes(data);
                    vp_movies.setCurrentItem(1);
                    mHandler.sendEmptyMessageDelayed(FIRST_PAGE, 5000);
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
    public void onRefresh() {
        initData(Stated.START, Api.NEW_LIST);
    }

    private Handler mHandler = new Handler(this);

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case FIRST_PAGE:
                //position++;
                //vp_movies.setCurrentItem(position);
                vp_movies.setCurrentItem(vp_movies.getCurrentItem() + 1);
                mHandler.sendEmptyMessageDelayed(FIRST_PAGE, 5000);
                break;
        }
        return true;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (ViewPager.SCROLL_STATE_IDLE == state) {
            if (mIsChanged) {
                mIsChanged = false;
                vp_movies.setCurrentItem(mCurrentPagePosition, false);
            }
        }
//        if (state == ViewPager.SCROLL_STATE_IDLE) {
//            int arg0 = vp_movies.getCurrentItem();
//            if (arg0 == vp_movies.getAdapter().getCount() - 1) {
//                vp_movies.setCurrentItem(1, false);
//            } else if (arg0 == 0) {
//                vp_movies.setCurrentItem(vp_movies.getAdapter().getCount() - 2, false);
//            }
//        }

    }

    private void setCurrentDot(int positon) {
        //0 1 2 3 4
        //  0 1 2
        // 界面实际显示的序号是第1, 2, 3。而点的序号应该是0, 1, 2.所以减1.
        positon = positon - 1;
        if (positon < 0 || positon > adapter.getCount() - 1 || mCurrentIndex == positon) {
            return;
        }
        ((ImageView) layout.getChildAt(positon)).setImageResource(R.mipmap.news_advert_dot_p);
        ((ImageView) layout.getChildAt(mCurrentIndex)).setImageResource(R.mipmap.news_advert_dot_d);
        mCurrentIndex = positon;
    }

    @Override
    public void onPageSelected(int position) {
        mIsChanged = true;
        if (position > data.size()) {
            mCurrentPagePosition = 1;
        } else if (position < 1) {
            mCurrentPagePosition = data.size();
        } else {
            mCurrentPagePosition = position;
        }
        setCurrentDot(mCurrentPagePosition);
        Log.e(TAG, "onPageSelected: " + position);
//        for (int i = 0; i < layout.getChildCount(); i++) {
//            if (i == position % data.size()) {
//                ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.news_advert_dot_p);
//            } else {
//                ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.news_advert_dot_d);
//            }
//        }
//        this.position = position;

//        if (position == vp_movies.getAdapter().getCount() - 1) {// 最后一个，同第二个相同
//            // 设置ViewPager下面的LinearLayout 跟随显示标记(显示在第一项)
//            for (int i = 0; i < layout.getChildCount(); i++) {
//                if (i == 0) {
//                    ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.news_advert_dot_p);
//                } else {
//                    ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.news_advert_dot_d);
//                }
//            }
//        } else if (position == 0) {// 第一个同倒数第二个相同
//            // 设置ViewPager下面的LinearLayout 跟随显示标记(显示在最后一项)
//            for (int i = 0; i < layout.getChildCount(); i++) {
//                if (i == layout.getChildCount() - 1) {
//                    ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.news_advert_dot_p);
//                } else {
//                    ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.news_advert_dot_d);
//                }
//            }
//        } else {
//            // 设置ViewPager下面的LinearLayout 跟随显示标记(显示在对应图片)
//            for (int i = 0; i < layout.getChildCount(); i++) {
//                if (i == position - 1) {
//                    ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.news_advert_dot_p);
//                } else {
//                    ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.news_advert_dot_d);
//                }
//            }
//        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movies item = adapter.getItem(position - 1);
        Intent intent = new Intent(getActivity(), MoviesDetailActivity.class);
        intent.putExtra("title", item.getWx_small_app_title());
        intent.putExtra("like_num", item.getLike_num());
        intent.putExtra("share_num", item.getShare_num());
        intent.putExtra("url", item.getRequest_url());
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeMessages(FIRST_PAGE);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(FIRST_PAGE, 5000);
                break;
        }
        return false;
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
                            adapter.updateRes(data);
                            break;
                        case END:
                            adapter.addRes(data);
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
                    String path = Api.NEW_LIST + "?p=" + index + "&size=10";
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
        if (changedListener != null) {
            String publish_time;
            if (firstVisibleItem == 0) {
                publish_time = "最新";
            } else {
                publish_time = TimeUtil.parseTime(adapter.getItem(firstVisibleItem - 1).getPublish_time());
            }
            changedListener.timeCallBack(publish_time);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(FIRST_PAGE);
    }

    public interface TimeChangedListener {
        void timeCallBack(String time);
    }
}
