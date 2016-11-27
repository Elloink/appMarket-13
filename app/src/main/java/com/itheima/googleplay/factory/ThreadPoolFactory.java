package com.itheima.googleplay.factory;

import com.itheima.googleplay.proxy.ThreadPoolProxy;

import java.util.concurrent.ThreadFactory;

/**
 * Created by acer on 2016/11/26.
 */

public class ThreadPoolFactory {
    static ThreadPoolProxy mCommonThreadPool;
    static ThreadPoolProxy mDownloadThreadPool;

    /**
     * 创建普普通线程 核心池大小
     * @return
     */
    public static ThreadPoolProxy createCommonThread() {
        if (mCommonThreadPool == null) {
            synchronized (ThreadFactory.class) {
                if (mCommonThreadPool == null) {
                    mCommonThreadPool = new ThreadPoolProxy(5, 5);
                }
            }
        }
        return mCommonThreadPool;
    }

    /**
     * 创建下载线程
     */
    public static ThreadPoolProxy createDownloadThread() {
        if (mDownloadThreadPool == null) {
            synchronized (ThreadFactory.class) {
                if (mDownloadThreadPool == null) {
                    mDownloadThreadPool = new ThreadPoolProxy(5, 5);
                }
            }
        }
        return mDownloadThreadPool;
    }
}
