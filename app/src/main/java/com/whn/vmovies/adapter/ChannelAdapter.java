package com.whn.vmovies.adapter;

import android.content.Context;
import android.widget.ImageView;


import com.whn.library.ImageLoader;
import com.whn.library.adapters.TeachBaseAdapter;
import com.whn.vmovies.R;
import com.whn.vmovies.bean.Channel;

import java.util.List;

/**
 * Created by admin on 2017/3/7.
 */

public class ChannelAdapter extends TeachBaseAdapter<Channel> {
    public ChannelAdapter(Context context, List<Channel> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, Channel item, int position) {
        holder.setText(R.id.channel_title, "#" + item.getCatename() + "#");
        ImageView view = (ImageView) holder.findView(R.id.channel_detail_img);
        ImageLoader.display(view, item.getIcon());

    }
}
