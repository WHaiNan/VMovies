package com.whn.vmovies.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.whn.vmovies.R;

public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    ImageView splash;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        splash = (ImageView) findViewById(R.id.splash_bg);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.1f, 1, 1.1f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setAnimationListener(this);
        splash.startAnimation(scaleAnimation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        SharedPreferences sharedPreferences = getSharedPreferences("is_First", MODE_PRIVATE);
        boolean first = sharedPreferences.getBoolean("first", true);
        if (first) {
            startActivity(new Intent(this, GuideActivity.class));
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("first", false);
            edit.apply();
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
