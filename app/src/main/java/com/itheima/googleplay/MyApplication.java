package com.itheima.googleplay;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.LruCache;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by acer on 2016/11/23.
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;

    private static LruCache<String, String> mMemProtocalCacheMap = new LruCache<>(4*1024*1024);
    public static LruCache<String,String> getmMemProtocalCacheMap(){
        return mMemProtocalCacheMap;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();

        //初始化fresco
        Fresco.initialize(mContext);
    }




    public static Context getContext() {
        return mContext;
    }

    public static Handler getmHandler() {
        return mHandler;
    }
}
