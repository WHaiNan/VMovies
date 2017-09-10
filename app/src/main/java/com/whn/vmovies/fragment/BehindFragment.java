package com.whn.vmovies.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.google.gson.Gson;
import com.whn.library.http.HttpUtil;
import com.whn.library.http.callback.RequestCallback;
import com.whn.vmovies.R;
import com.whn.vmovies.adapter.BehindVpFragmentAdapter;
import com.whn.vmovies.bean.MH;
import com.whn.vmovies.bean.MHList;
import com.whn.vmovies.constant.Api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/6.
 */

public class BehindFragment extends BaseFragment {
    public static final String Tag = BehindFragment.class.getName();
    private TabLayout tab;
    private ViewPager vp;
    private List<String> titles;
    private List<Fragment> fragments;
    private BehindVpFragmentAdapter adapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_behind;
    }

    @Override
    protected void initView() {
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.mh_vp);
        adapter = new BehindVpFragmentAdapter(getChildFragmentManager(), titles, fragments);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        initData();

    }

    private void initData() {
        HttpUtil.getStringAsync(Api.MHGUIDE, new RequestCallback() {
            @Override
            public void onSucceed(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    MHList list = gson.fromJson(result, MHList.class);
                    List<MH> data = list.getData();
                    for (int i = 0; i < data.size(); i++) {
                        String cateid = data.get(i).getCateid();
                        String catename = data.get(i).getCatename();
                        titles.add(catename);
                        CommFragment comm = new CommFragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("cateid",cateid);
                        comm.setArguments(bundle);
                        fragments.add(comm);
                    }
                    adapter.notifyDataSetChanged();
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


}
