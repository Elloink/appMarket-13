package com.itheima.googleplay.utils;

import android.os.Handler;

import com.itheima.googleplay.MyApplication;

/**
 * Created by acer on 2016/11/24.
 */

public class ThreadUtil {
    public static void runInThread(Runnable r) {
        new Thread(r).start();
    }

    private static Handler handler = MyApplication.getmHandler();

    public static void runInUiThread(Runnable r) {
        handler.post(r);
    }
}
