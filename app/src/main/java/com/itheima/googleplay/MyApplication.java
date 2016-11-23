package com.itheima.googleplay;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by acer on 2016/11/23.
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getmHandler() {
        return mHandler;
    }
}
