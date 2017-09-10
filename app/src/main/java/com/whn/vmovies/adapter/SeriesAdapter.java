package com.whn.vmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import com.whn.library.ImageLoader;
import com.whn.library.adapters.TeachBaseAdapter;
import com.whn.vmovies.R;
import com.whn.vmovies.activity.SeriesDetailActivity;
import com.whn.vmovies.bean.Series;
import com.whn.vmovies.bean.SeriesData;
import com.whn.vmovies.constant.Api;

import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */

public class SeriesAdapter extends TeachBaseAdapter<Series> {
    private Context context;

    public SeriesAdapter(Context context, List<Series> data, int layoutResId) {
        super(context, data, layoutResId);
        this.context = context;
    }

    @Override
    protected void bindData(ViewHolder holder, final Series item, int position) {
        holder.setText(R.id.series_list_item_title, item.getTitle());
        holder.setText(R.id.series_list_item_weekly_and_follower_num, "以更新至" + item.getUpdate_to() + "集   " + item.getFollower_num() + "人已订阅");
        ImageView view = (ImageView) holder.findView(R.id.series_list_item_image);
        ImageLoader.display(view, item.getImage());
        holder.setText(R.id.series_list_item_content, item.getContent());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SeriesDetailActivity.class);
                intent.putExtra("seriesId", item.getSeriesid());
                context.startActivity(intent);
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.series_list_item_image:
//                Log.e("flag", "onClick: " + v.getTag());
//                Integer tag = (Integer) v.getTag();
//                Log.e("tag", "onClick: " + tag);
//                String id = this.getItem(tag).getSeriesid();
//                Log.e("id", "onClick: " + tag+"   " + id);
//                final Intent intent = new Intent(context, SeriesDetailActivity.class);
//                intent.putExtra("seriesId", id);
//                context.startActivity(intent);
//                break;
//        }
//
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.series_list_item_image:
//                Log.e("test", "onClick: "+seriesId);
////                Log.e("flag", "onClick: " + v.getTag());
////                Integer tag = (Integer) v.getTag();
////                Log.e("tag", "onClick: " + tag);
////                String id = this.getItem(tag).getSeriesid();
////                Log.e("id", "onClick: " + tag+"   " + id);
//                final Intent intent = new Intent(context, SeriesDetailActivity.class);
//                intent.putExtra("seriesId", seriesId);
//                context.startActivity(intent);
//                break;
//        }


}
