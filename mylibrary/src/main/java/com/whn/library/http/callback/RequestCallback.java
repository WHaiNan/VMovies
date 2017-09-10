package com.whn.library.http.callback;


public interface RequestCallback {
    // 请求成功
    void onSucceed(String result);
    // 请求失败
    void onFailure(String msg);
    // 准备请求，通常用来显示加载进度条
    void onPrepare();
    // 加载结束
    void onLoadFinish();

}
