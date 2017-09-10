package com.whn.vmovies.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.whn.library.adapters.CommonFragmentPagerAdapter;
import com.whn.vmovies.R;
import com.whn.vmovies.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/6.
 */

public class FristPageFragment extends BaseFragment implements ViewPager.OnPageChangeListener, MainActivity.OnTitleClickListener {
    public static final String Tag = FristPageFragment.class.getName();
    ViewPager vp;
    MainActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mActivity = (MainActivity) context;
            mActivity.setOnTitleClickListener(this);
        }
    }

    @Override
    protected void initView() {
        vp = (ViewPager) findViewById(R.id.first_page_viewpager);
        vp.setAdapter(new CommonFragmentPagerAdapter(getChildFragmentManager(), getFragments()));
        vp.addOnPageChangeListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_firstpage;
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MovieListFragment());
        fragments.add(new ChannelListFragment());
        return fragments;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mActivity != null) {
            mActivity.moveTitleIndicator(position + positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onMovieListTitleClick() {
        vp.setCurrentItem(0);
    }

    @Override
    public void onChannelListTitleClick() {
        vp.setCurrentItem(1);
    }
}
