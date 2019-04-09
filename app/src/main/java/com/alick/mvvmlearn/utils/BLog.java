package com.alick.mvvmlearn.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author 崔兴旺
 * @package com.alick.googlelifecycle
 * @title:
 * @description: TODO
 * @date 2019/4/3 17:16
 */
public class BLog {
    private static final String TAG = "alick";


    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        Log.e(tag, msg);
    }


}
