package com.whn.vmovies;

import android.app.Application;

import com.whn.library.ImageLoader;


/**
 * Created by admin on 2017/3/6.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.init(this);
    }
}
