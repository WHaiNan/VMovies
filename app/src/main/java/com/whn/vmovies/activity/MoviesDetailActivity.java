package com.whn.vmovies.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.whn.vmovies.R;
import com.whn.vmovies.constant.Api;
import com.whn.vmovies.util.AudioController;
import com.whn.vmovies.util.LightnessController;
import com.whn.vmovies.widget.CustomVideoView;
import com.whn.vmovies.widget.CustomWebView;
import com.whn.vmovies.widget.MyVideoView;

public class MoviesDetailActivity extends BaseActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, CustomWebView.OnScrollChangedCallBack, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, Handler.Callback, MediaPlayer.OnErrorListener, GestureDetector.OnGestureListener {

    private static final int UPDATE_PROGRESS = 100;
    private static final String TAG = MoviesDetailActivity.class.getSimpleName();
    private CustomWebView webview;
    private PopupWindow popupWindow;
    private MyVideoView videoView;
    //private ImageView video_play, video_pause;
    private RelativeLayout layout_video;
    private TextView play_now;
    private RelativeLayout top;
    private CheckBox video_play;
    private Handler mHandler;
    private TextView current_time;
    private TextView total_time;
    private SeekBar seekBar_progress;
    private CheckBox screen;
    private FrameLayout frame_container;
    private int height;
    private boolean isLandScape;
    private float mFirstX;
    private float mFirstY;
    private float mLastX;
    private float mLastY;
    private int mLinjie = 2;//临界值,
    private int widthPixels;
    private int heightPixels;
    private LinearLayout layout_bottom;
    private GestureDetector mGestureDetector;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_update_detail;
    }

    @Override
    protected void initView() {
        webview = (CustomWebView) findViewById(R.id.movies_webview);
        ImageView back = (ImageView) findViewById(R.id.video_back);
        ImageView share = (ImageView) findViewById(R.id.video_share);
        //layout_video = (RelativeLayout) findViewById(R.id.layout_video);
        layout_bottom = (LinearLayout) findViewById(R.id.detail_update_bottom);
        top = (RelativeLayout) findViewById(R.id.top);
        videoView = (MyVideoView) findViewById(R.id.video);
        play_now = (TextView) findViewById(R.id.play_now);
        play_now.setOnClickListener(this);
        initVideoView();
//        video_play = (ImageView) findViewById(R.id.video_play);
//        video_pause = (ImageView) findViewById(R.id.video_pause);
//        video_pause.setOnClickListener(this);
        mHandler = new Handler(this);
        video_play = (CheckBox) findViewById(R.id.video_play);
        current_time = (TextView) findViewById(R.id.video_current);
        total_time = (TextView) findViewById(R.id.video_total);
        seekBar_progress = (SeekBar) findViewById(R.id.video_progress);
        screen = (CheckBox) findViewById(R.id.video_full_screen);
        frame_container = (FrameLayout) findViewById(R.id.video_container);
        video_play.setOnCheckedChangeListener(this);
        screen.setOnCheckedChangeListener(this);
        seekBar_progress.setOnSeekBarChangeListener(this);
        LinearLayout share_movies = (LinearLayout) findViewById(R.id.layout_share_movies);
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        share_movies.setOnClickListener(this);
        TextView tv_likenum = (TextView) findViewById(R.id.like_num);
        TextView tv_sharenum = (TextView) findViewById(R.id.share_num);
        Intent intent = getIntent();
        String path = intent.getStringExtra("url");
        String likenum = intent.getStringExtra("like_num");
        String sharenum = intent.getStringExtra("share_num");
        tv_likenum.setText(likenum);
        tv_sharenum.setText(sharenum);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl(path);
        webview.setOnScrollChangedCallBack(this);
        initPopupWindow();
        //记录屏幕宽高，横屏时  宽是高，高是宽
        widthPixels = getResources().getDisplayMetrics().widthPixels;
        heightPixels = getResources().getDisplayMetrics().heightPixels;
        mGestureDetector = new GestureDetector(this, this);
    }

    private void initVideoView() {
        videoView.setVideoURI(Uri.parse("http://7rflo2.com2.z0.glb.qiniucdn.com/5714b0b53c958.mp4"));
//        MediaController controller = new MediaController(this);
//        videoView.setMediaController(controller);
//        videoView.requestFocus();
        videoView.setOnPreparedListener(this);
        videoView.setOnCompletionListener(this);
        videoView.setOnErrorListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_back:
                finish();
                break;
            case R.id.video_share:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.layout_share_movies:
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
//            case R.id.video_pause:
//                if (videoView != null) {
//                    videoView.seekTo(0);
//                    videoView.start();
//                    video_pause.setVisibility(View.GONE);
//                }
//                break;
//            case R.id.video_play:
//                break;
            case R.id.play_now:
                if (!videoView.isPlaying()) {
                    videoView.start();
                    //layout_video.setVisibility(View.VISIBLE);
                    frame_container.setVisibility(View.VISIBLE);
                    play_now.setVisibility(View.GONE);
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
    public void onPrepared(MediaPlayer mp) {
        video_play.setChecked(true);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //video_pause.setVisibility(View.VISIBLE);
    }

    //屏幕旋转时回调
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandScape = true;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            isLandScape = false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isLandScape) {
            screen.setChecked(false);
            return true;
        } else {
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        if (videoView != null && videoView.isPlaying()) {
            videoView.stopPlayback();
        }

    }


    @Override
    public void scrollTop() {
        //layout_video.setVisibility(View.VISIBLE);
        frame_container.setVisibility(View.VISIBLE);
        play_now.setVisibility(View.GONE);
    }

    @Override
    public void scrollChanged() {
        if (videoView != null && !videoView.isPlaying()) {
            //layout_video.setVisibility(View.GONE);
            frame_container.setVisibility(View.GONE);
            play_now.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void scrollBottom() {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.video_play:
                if (isChecked) {
                    videoView.start();
                    mHandler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 1000);
                } else {
                    videoView.pause();
                    mHandler.removeMessages(UPDATE_PROGRESS);
                }
                break;
            case R.id.video_full_screen:
                if (isChecked) {
                    layout_bottom.setVisibility(View.GONE);
                    //添加全屏标记
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //记录原来的高度
                    height = frame_container.getLayoutParams().height;
                    frame_container.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                } else {
                    layout_bottom.setVisibility(View.VISIBLE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    frame_container.getLayoutParams().height = height;
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            videoView.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isLandScape) {
            return mGestureDetector.onTouchEvent(event) && super.onTouchEvent(event);
        }
        return false;
    }

    /**
     * 快进
     */
    public void fastForward(float xDelta) {
        int currentPosition = videoView.getCurrentPosition();
        int duration = videoView.getDuration();
        int v = (int) (duration * xDelta / heightPixels);
        int min = Math.min(v + currentPosition, duration);
        videoView.seekTo(min);
    }

    /**
     * 快退
     */
    public void forward(float xDelta) {
        int currentPosition = videoView.getCurrentPosition();
        int duration = videoView.getDuration();
        int v = (int) (duration * xDelta / heightPixels);
        int max = Math.max(v + currentPosition, 0);
        videoView.seekTo(max);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case UPDATE_PROGRESS:
                int currentPosition = videoView.getCurrentPosition();
                CharSequence format = DateFormat.format("mm:ss", currentPosition);
                current_time.setText(format);
                int duration = videoView.getDuration();
                total_time.setText(DateFormat.format("mm:ss", duration));
                seekBar_progress.setMax(duration);
                seekBar_progress.setProgress(currentPosition);
                mHandler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 1000);
                break;
        }
        return true;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (Math.abs(distanceX) > Math.abs(distanceY)) {
            //横向变化多
            Log.e(TAG, "onScroll: " + distanceX);
            if (e2.getX() - e1.getX() > 0) {
                //快进
                Log.e(TAG, "onTouchEvent: 快进");
                fastForward(e2.getX() - e1.getX());
            } else {
                //快退
                Log.e(TAG, "onTouchEvent: 快退");
                forward(e2.getX() - e1.getX());
            }
        } else if (Math.abs(distanceX) < Math.abs(distanceY)) {
            //纵向变化多
            if (e2.getX() < heightPixels / 2) {
                //亮度
                if (e2.getY() - e1.getY() > 0) {
                    //亮度调低
                    Log.e(TAG, "onTouchEvent: 亮度调低");
                    LightnessController.turnDown(this, e2.getY() - e1.getY(), widthPixels);
                } else {
                    Log.e(TAG, "onTouchEvent: 亮度调高");
                    LightnessController.turnUp(this, e2.getY() - e1.getY(), widthPixels);
                }
            } else {
                if (e2.getY() - e1.getY() > 0) {
                    //声音调低
                    Log.e(TAG, "onTouchEvent: 声音调低");
                    AudioController.turnDown(this, e2.getY() - e1.getY(), widthPixels);
                } else {
                    Log.e(TAG, "onTouchEvent: 声音调高");
                    AudioController.turnUp(this, e2.getY() - e1.getY(), widthPixels);
                }
            }
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
