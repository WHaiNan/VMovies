package com.whn.vmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.whn.vmovies.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getContentLayoutId() {
        return R.layout.login;
    }

    @Override
    protected void initView() {
        ImageView back = (ImageView) findViewById(R.id.loading_top_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
