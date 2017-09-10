package com.whn.vmovies.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.whn.library.ImageLoader;
import com.whn.vmovies.bean.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */

public class BannerAdapter extends PagerAdapter {
    private List<Banner> dlist;
    private Context context;

    public BannerAdapter(List<Banner> dlist, Context context) {
        if (dlist != null) {
            this.dlist = dlist;
        } else {
            this.dlist = new ArrayList<>();
        }
        this.context = context;
    }


    public void addRes(List<Banner> data) {
        if (data != null) {
            dlist.clear();
            dlist.add(0, data.get(data.size() - 1));
            dlist.addAll(data);
            dlist.add(data.size() + 1, data.get(0));
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        //return (dlist != null && dlist.size() > 0) ? Integer.MAX_VALUE : 0;
        return dlist != null ? dlist.size() : 0;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(img);
        ImageLoader.display(img, dlist.get(position).getImage());
        //ImageLoader.display(img, dlist.get(position % dlist.size()).getImage());
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
