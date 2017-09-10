package com.whn.vmovies.activity;


import android.view.View;
import android.widget.TextView;

import com.whn.vmovies.R;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        TextView cancle = (TextView) findViewById(R.id.cancle_search);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
