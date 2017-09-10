package com.whn.vmovies.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;

import com.whn.library.http.HttpUtil;
import com.whn.library.http.callback.RequestCallback;
import com.whn.vmovies.R;
import com.whn.vmovies.activity.ChannelDetailActivity;
import com.whn.vmovies.adapter.ChannelAdapter;
import com.whn.vmovies.bean.Channel;
import com.whn.vmovies.bean.ChannelList;
import com.whn.vmovies.constant.Api;

import java.util.List;

public class ChannelListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public static final String TAG = ChannelListFragment.class.getName();
    private GridView grid;
    private ChannelAdapter channelAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_channel_list;
    }

    @Override
    protected void initView() {
        grid = (GridView) findViewById(R.id.channel_grid);
        channelAdapter = new ChannelAdapter(getActivity(), null, R.layout.fragment_channel_detail);
        grid.setAdapter(channelAdapter);
        initData();
        grid.setOnItemClickListener(this);
    }

    private void initData() {
        HttpUtil.getStringAsync(Api.CHANNEL, new RequestCallback() {
            @Override
            public void onSucceed(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    ChannelList channelList = gson.fromJson(result, ChannelList.class);
                    List<Channel> data = channelList.getData();
                    channelAdapter.addRes(data);
                }
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onPrepare() {

            }

            @Override
            public void onLoadFinish() {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Channel item = channelAdapter.getItem(position);
        String cateId = item.getCateid();
        Intent intent = new Intent(getActivity(), ChannelDetailActivity.class);
        intent.putExtra("cateId", cateId);
        intent.putExtra("cateName",item.getCatename());
        startActivity(intent);
    }
}
