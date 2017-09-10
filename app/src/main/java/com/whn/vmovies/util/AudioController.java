package com.whn.vmovies.util;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by admin on 2017/3/13.
 */

public class AudioController {
    /**
     * @param context
     * @param yDelta  <0
     * @param width
     */
    public static void turnUp(Context context, float yDelta, int width) {
        AudioManager systemService = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int current = systemService.getStreamVolume(AudioManager.STREAM_MUSIC);
        int streamMaxVolume = systemService.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int v = (int) (streamMaxVolume * yDelta / width);
        int min = Math.min(streamMaxVolume, current - v);
        systemService.setStreamVolume(AudioManager.STREAM_MUSIC, min, AudioManager.FLAG_SHOW_UI);
    }

    public static void turnDown(Context context, float yDelta, int width) {
        AudioManager systemService = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int current = systemService.getStreamVolume(AudioManager.STREAM_MUSIC);
        int streamMaxVolume = systemService.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int v = (int) (streamMaxVolume * yDelta / width);
        int max = Math.max(0, current - v);
        systemService.setStreamVolume(AudioManager.STREAM_MUSIC, max, AudioManager.FLAG_SHOW_UI);
    }
}
