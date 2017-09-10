package com.whn.vmovies.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.whn.library.ImageLoader;
import com.whn.library.adapters.TeachBaseAdapter;
import com.whn.vmovies.R;
import com.whn.vmovies.bean.Movies;

import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */

public class MoviesAdapter extends TeachBaseAdapter<Movies> {
    public MoviesAdapter(Context context, List data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, Movies item, int position) {
        holder.setText(R.id.app_title, item.getWx_small_app_title());
        holder.setText(R.id.catename, item.getCates().get(0).getCatename());
        int t = Integer.valueOf(item.getDuration());
        int min = t / 60;
        int miu = t % 60;
        if (miu < 10) {
            holder.setText(R.id.duration, min + "'0" + miu + "''");
        } else {
            holder.setText(R.id.duration, min + "'" + miu + "''");
        }
        ImageView view = (ImageView) holder.findView(R.id.movies);
        ImageLoader.display(view, item.getImage());
    }
}
