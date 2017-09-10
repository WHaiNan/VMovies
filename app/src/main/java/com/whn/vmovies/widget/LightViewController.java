package com.whn.vmovies.widget;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.whn.vmovies.R;


public class LightViewController {

    private static final String TAG = LightViewController.class.getSimpleName();
    private static Toast sToast;

    public static void showLight(Context context, int currentValue) {

        if (sToast == null) {
            sToast = new Toast(context);
            sToast.setDuration(Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.LEFT, 0, 0);

        }
        Log.e(TAG, "showLight: " + currentValue);
        View view = LayoutInflater.from(context).inflate(R.layout.light, null);
        LightView lightView = (LightView) view.findViewById(R.id.light_view);
        lightView.setCurrentValue(currentValue);
        sToast.setView(view);
        sToast.show();

    }


}
