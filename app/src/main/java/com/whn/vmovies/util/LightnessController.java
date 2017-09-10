package com.whn.vmovies.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;

import com.whn.vmovies.widget.LightViewController;

/**
 *  系统亮度是存在我们系统中的一个配置
 *      想要操作的话 需要使用ContentResolver来获取以及设置值
 *
 *   在Activity.getContentResolver 来获取
 *
 */

public class LightnessController {

    private static final String TAG = LightnessController.class.getSimpleName();

    /**
     *  ① 获取当前的亮度
     *  亮度值的范围  [0-1]
     *  ②  计算变化值
     *  ③ 设置亮度值
     *
     *  yDelta 负值
     *
     */
    public static void turnUp(Activity activity,float yDelta,int width){
        ContentResolver contentResolver = activity.getContentResolver();
        try {
            int brightNess = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
            // 系统中存储亮度时的值 0 - 255 转换为页面中的亮度时 是 0 - 1
            float change = 255 * yDelta / width;
            // 计算操作之后的值
            float min =  Math.min(255, brightNess - change);
            // 将系统中存储的亮度值 转换为窗口理解的属性 设置上来
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            // 参数中 存在一个亮度控制亮度的字段
            attributes.screenBrightness = min / 255;
            Log.e(TAG, "turnUp: " + attributes.screenBrightness );
            // 设置改变后的值
            activity.getWindow().setAttributes(attributes);
            // 将值存到系统中
            Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, (int) min);
            LightViewController.showLight(activity, ((int) (min / 2.55)));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "turnUp: 更改亮度失败:"+e.getCause());
        }


    }

    public static void turnDown(Activity activity,float yDelta,int width){
        ContentResolver contentResolver = activity.getContentResolver();
        try {
            int brightNess = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
            // 系统中存储亮度时的值 0 - 255 转换为页面中的亮度时 是 0 - 1
            float change = 255 * yDelta / width;
            // 计算操作之后的值
            float max =  Math.max(0, brightNess - change);
            // 将系统中存储的亮度值 转换为窗口理解的属性 设置上来
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            // 参数中 存在一个亮度控制亮度的字段
            attributes.screenBrightness = max / 255;
            Log.e(TAG, "turnDown: " + attributes.screenBrightness );
            activity.getWindow().setAttributes(attributes);
            // 将值存到系统中
            Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, (int) max);
            LightViewController.showLight(activity, ((int) (max / 2.55)));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "turnUp: 更改亮度失败:"+e.getCause());
        }
    }

}
