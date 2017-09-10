package com.whn.vmovies.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;


import com.whn.library.ImageLoader;
import com.whn.library.adapters.TeachBaseAdapter;
import com.whn.vmovies.R;
import com.whn.vmovies.bean.BehindDetail;

import java.util.List;

/**
 * Created by admin on 2017/3/8.
 */

public class CommAdapter extends TeachBaseAdapter<BehindDetail> {
    public CommAdapter(Context context, List<BehindDetail> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, BehindDetail item, int position) {
        holder.setText(R.id.behind_title, item.getTitle());
        holder.setText(R.id.behind_like, item.getLike_num());
        holder.setText(R.id.behind_share, item.getShare_num());
        ImageView view = (ImageView) holder.findView(R.id.behind_img);
        ImageLoader.display(view, item.getImage());
    }
}
