package com.whn.vmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        initView();
    }

    protected abstract int getContentLayoutId();

    protected abstract void initView();
}
