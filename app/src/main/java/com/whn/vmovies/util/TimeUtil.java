package com.whn.vmovies.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 2017/3/12.
 */

public class TimeUtil {
    public static String parseTime(String time) {
        String formats = "MM-dd";
        Long timestamp = Long.parseLong(time) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
}
