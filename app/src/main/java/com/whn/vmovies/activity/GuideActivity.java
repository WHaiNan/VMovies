package com.whn.vmovies.activity;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.whn.library.adapters.CommonFragmentPagerAdapter;
import com.whn.vmovies.R;
import com.whn.vmovies.fragment.GuideOneFragment;
import com.whn.vmovies.fragment.GuideThreeFragment;
import com.whn.vmovies.fragment.GuideTwoFragment;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    LinearLayout layout;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_guide);
        viewPager.setAdapter(new CommonFragmentPagerAdapter(getSupportFragmentManager(), getFragments()));
        viewPager.addOnPageChangeListener(this);
        layout = (LinearLayout) findViewById(R.id.ll_guide);
        initDot();
    }

    private void initDot() {
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.mipmap.pointer_);
            } else {
                imageView.setImageResource(R.mipmap.pointer);
            }

            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(10, 10);
            mParams.setMargins(10, 10, 10, 10);//设置margin,也就是外边距。
            imageView.setLayoutParams(mParams);//传入参数params设置宽和高
            layout.addView(imageView, mParams);

        }
    }

    private List<Fragment> getFragments() {
        List<Fragment> flist = new ArrayList<>();
        flist.add(new GuideOneFragment());
        flist.add(new GuideTwoFragment());
        flist.add(new GuideThreeFragment());
        return flist;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            if (i == position) {
                ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.pointer_);
            } else {
                ((ImageView) layout.getChildAt(i)).setImageResource(R.mipmap.pointer);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
