package com.whn.vmovies.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by admin on 2017/3/11.
 */

public class CustomWebView extends WebView {
    private OnScrollChangedCallBack onScrollChangedCallBack;

    public void setOnScrollChangedCallBack(OnScrollChangedCallBack onScrollChangedCallBack) {
        this.onScrollChangedCallBack = onScrollChangedCallBack;
    }

    public CustomWebView(Context context) {
        this(context, null);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float webcontent = getContentHeight() * getScale();// webview的高度
        float webnow = getHeight() + getScrollY();// 当前webview的高度
        if (onScrollChangedCallBack != null) {
            if (Math.abs(webcontent - webnow) < 1) {
                // 已经处于底端
                // Log.i("TAG1", "已经处于底端");
            } else if (getScrollY() == 0) {
                // Log.i("TAG1", "已经处于顶端");
                onScrollChangedCallBack.scrollTop();
            } else {
                onScrollChangedCallBack.scrollChanged();
            }
        }

    }


    public interface OnScrollChangedCallBack {
        void scrollTop();

        void scrollChanged();

        void scrollBottom();

    }
}