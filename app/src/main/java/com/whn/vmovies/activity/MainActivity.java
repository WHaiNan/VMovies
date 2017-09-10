package com.whn.vmovies.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.whn.vmovies.R;
import com.whn.vmovies.fragment.BehindFragment;
import com.whn.vmovies.fragment.FristPageFragment;
import com.whn.vmovies.fragment.MovieListFragment;
import com.whn.vmovies.fragment.SeriesFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 1 确认需求
 * 2 确认架构
 * 3 确认实现方案（mvc mvp mvvm 动态管理模板）
 * 4 封装通用模块 封装基类
 * 5 书写离线功能
 * 6 加载网络数据 测试联调
 * 7 打包 上线
 */

public class MainActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, MovieListFragment.TimeChangedListener {

    View mCover;
    RadioButton mHome;
    RadioButton mSeries;
    RadioButton mBehind;
    ImageView open, close;
    RadioGroup group;
    private Fragment mShowFragment;
    private boolean isExit;
    TextView title;
    View first_pager_title;
    TextView title_movies;
    TextView title_channel;
    private OnTitleClickListener onTitleClickListener;
    View indicator;
    private ImageView home_search;

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mCover = findViewById(R.id.main_cover);
        open = (ImageView) findViewById(R.id.main_open_cover);
        close = (ImageView) findViewById(R.id.main_close_cover);
        group = (RadioGroup) findViewById(R.id.main_cover_controller);
        mHome = (RadioButton) findViewById(R.id.cover_home);
        ImageView login = (ImageView) findViewById(R.id.login);
        login.setOnClickListener(this);
        mHome.setOnClickListener(this);
        mSeries = (RadioButton) findViewById(R.id.cover_series);
        mSeries.setOnClickListener(this);
        mBehind = (RadioButton) findViewById(R.id.cover_behind);
        mBehind.setOnClickListener(this);
        home_search = (ImageView) findViewById(R.id.home_search);
        home_search.setOnClickListener(this);
        open.setOnClickListener(this);
        close.setOnClickListener(this);
        group.setOnCheckedChangeListener(this);

        title = (TextView) findViewById(R.id.top_title);
        first_pager_title = findViewById(R.id.first_page_title);
        indicator = findViewById(R.id.main_first_page_indicator);

        title_movies = (TextView) findViewById(R.id.title_movies_list);
        title_channel = (TextView) findViewById(R.id.title_channel_list);
        title_movies.setOnClickListener(this);
        title_channel.setOnClickListener(this);
        mHome.setChecked(true);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_open_cover:
                showCover();
                break;
            case R.id.main_close_cover:
                closeCover();
                break;
            case R.id.cover_home:
            case R.id.cover_series:
            case R.id.cover_behind:
                hideCover();
                break;
            case R.id.title_movies_list:
                title_channel.setTextColor(Color.GRAY);
                title_movies.setTextColor(Color.WHITE);
                if (onTitleClickListener != null) {
                    onTitleClickListener.onMovieListTitleClick();
                }
                break;
            case R.id.title_channel_list:
                title_movies.setTextColor(Color.GRAY);
                title_channel.setTextColor(Color.WHITE);
                if (onTitleClickListener != null) {
                    onTitleClickListener.onChannelListTitleClick();
                }
                break;
            case R.id.home_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }

    }

    private void hideCover() {
        if (mCover.getVisibility() == View.VISIBLE) {
            mCover.setVisibility(View.GONE);
        }
    }

    private void showCover() {
        mCover.setVisibility(View.VISIBLE);

        mHome.setAlpha(0);
        mSeries.setAlpha(0);
        mBehind.setAlpha(0);

        //菜单动画
        ObjectAnimator home = ObjectAnimator.ofFloat(mHome, "alpha", 0, 1);
        home.setDuration(200);
        ObjectAnimator series = ObjectAnimator.ofFloat(mSeries, "alpha", 0, 1);
        series.setDuration(200);
        ObjectAnimator behind = ObjectAnimator.ofFloat(mBehind, "alpha", 0, 1);
        behind.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(home);
        animatorSet.play(series).after(200);
        animatorSet.play(behind).after(400);
        animatorSet.start();

        //关闭按钮动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(close, "scaleX", 0, 1.2f, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(close, "scaleY", 0, 1.2f, 1);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.play(scaleX).with(scaleY);
        set.start();

    }

    private void closeCover() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(close, "scaleX", 0, 1.2f, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(close, "scaleY", 0, 1.2f, 1);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.play(scaleX).with(scaleY);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mCover.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.cover_home:
                switchPager(FristPageFragment.Tag);
                title.setVisibility(View.GONE);
                first_pager_title.setVisibility(View.VISIBLE);
                break;
            case R.id.cover_series:
                switchPager(SeriesFragment.Tag);
                title.setVisibility(View.VISIBLE);
                first_pager_title.setVisibility(View.GONE);
                title.setText("系列");
                break;
            case R.id.cover_behind:
                switchPager(BehindFragment.Tag);
                title.setVisibility(View.VISIBLE);
                first_pager_title.setVisibility(View.GONE);
                title.setText("幕后文章");
                break;
        }
        mCover.setVisibility(View.GONE);
    }

    private void switchPager(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mShowFragment != null) {
            transaction.hide(mShowFragment);
        }
        mShowFragment = manager.findFragmentByTag(tag);
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            try {
                mShowFragment = (Fragment) Class.forName(tag).newInstance();
                transaction.add(R.id.main_container, mShowFragment, tag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mCover.getVisibility() == View.VISIBLE) {
                mCover.setVisibility(View.GONE);
                return true;
            }
            if (!isExit) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isExit = true;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * offset  0-1 [0,1]
     */
    public void moveTitleIndicator(float offset) {
        int width = first_pager_title.getWidth();
        indicator.setTranslationX(offset * width / 2);
    }

    @Override
    public void timeCallBack(String time) {
        title_movies.setText(time);
    }

    /**
     * 接口回调实现传值
     * ① 定义接口，定义接口中的方法
     * ② 在数据产生的地方，持有接口的引用，在数据产生的时候调用接口中的方法
     * ③ 在需要处理数据的地方，实现接口，并将引用传递到数据产生的地方
     */

    public static interface OnTitleClickListener {
        void onMovieListTitleClick();

        void onChannelListTitleClick();
    }
}
