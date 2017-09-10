package com.whn.vmovies.adapter;

import android.content.Context;
import android.service.voice.VoiceInteractionService;
import android.view.View;
import android.widget.ImageView;

import com.whn.library.ImageLoader;
import com.whn.library.adapters.TeachBaseAdapter;
import com.whn.vmovies.R;
import com.whn.vmovies.bean.series.SeriesDetailPosts;

import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */

public class UpdateAdapter extends TeachBaseAdapter<SeriesDetailPosts> {
    public UpdateAdapter(Context context, List<SeriesDetailPosts> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, SeriesDetailPosts item, int position) {
        holder.setText(R.id.update_title,"第"+item.getNumber()+"集 "+item.getTitle());
        holder.setText(R.id.update_date,item.getAddtime());
        ImageView view = (ImageView) holder.findView(R.id.update_img);
        ImageLoader.display(view,item.getThumbnail());
    }
}
