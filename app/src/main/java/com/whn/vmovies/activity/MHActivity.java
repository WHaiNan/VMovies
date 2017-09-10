package com.whn.vmovies.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whn.vmovies.R;
import com.whn.vmovies.constant.Api;

public class MHActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow popupWindow;

    @Override
    protected int getContentLayoutId() {
        return R.layout.behind_detail;
    }

    @Override
    protected void initView() {
        final WebView webView = (WebView) findViewById(R.id.behind_webview);
        ImageView back = (ImageView) findViewById(R.id.back);
        ImageView share = (ImageView) findViewById(R.id.mh_share);
        LinearLayout layout = (LinearLayout) findViewById(R.id.share_layout);
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        layout.setOnClickListener(this);
        TextView tv_like = (TextView) findViewById(R.id.movies_like_num);
        TextView tv_share = (TextView) findViewById(R.id.movies_share_num);
        Intent intent = getIntent();
        String postId = intent.getStringExtra("postId");
        tv_like.setText(intent.getStringExtra("like_num"));
        tv_share.setText(intent.getStringExtra("share_num"));
        initPopupWindow();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        final String path = Api.BASE_URL + "/" + postId + "?qingapp=app_new";
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(path);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.mh_share:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.share_layout:
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
