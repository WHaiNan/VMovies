package com.whn.vmovies.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2017/3/6.
 */

public abstract class BaseFragment extends Fragment {
    protected View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layout == null) {
            layout = inflater.inflate(getLayoutResId(), container, false);
        }
        initView();

        return layout;
    }

    protected abstract void initView();

    protected abstract int getLayoutResId();

    protected View findViewById(int id) {
        return layout.findViewById(id);
    }
}
