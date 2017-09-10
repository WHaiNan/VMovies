package com.whn.library.cache;

import android.graphics.Bitmap;


public interface ImageCache {

    public void put(String url, Bitmap bitmap);

    public Bitmap get(String url);

}
