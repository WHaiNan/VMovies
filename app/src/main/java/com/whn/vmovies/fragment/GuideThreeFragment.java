package com.whn.vmovies.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.whn.vmovies.R;
import com.whn.vmovies.activity.MainActivity;

/**
 * Created by admin on 2017/3/6.
 */

public class GuideThreeFragment extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_guide_three;
    }

    @Override
    protected void initView() {
        ImageView guide = (ImageView) findViewById(R.id.iv_guide);
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
}
