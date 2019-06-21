package com.plotalong.android.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kbhakade on 11/7/17.
 */

public class DateUtil {
    private static final String TAG = DateUtil.class.getSimpleName().concat(GlobalConstant.STRING_PlotAlong);

    public static String getCurrentTimeStamp() {
        Log.e(TAG, "getCurrentTimeStamp: ");
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }


    public static String getCurrentFormatDateAndTime() {
        Log.e(TAG, "getCurrentFormatDateAndTime: ");
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(c.getTime());
    }
}
