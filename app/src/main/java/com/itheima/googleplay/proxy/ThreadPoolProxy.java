package com.itheima.googleplay.proxy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by acer on 2016/11/26.
 */

public class ThreadPoolProxy {
    private ThreadPoolExecutor poolExecutor;
    private int corePoolSize;
    private int maxPoolSize;

    public ThreadPoolProxy(int corePoolSize,int maxPoolSize) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
    }

    public void inintThreadPool() {//双重枷锁检查
        if (poolExecutor == null || poolExecutor.isShutdown() || poolExecutor.isTerminated()) {
            synchronized (ThreadPoolProxy.class) {
                if (poolExecutor == null || poolExecutor.isShutdown() || poolExecutor.isTerminated()){
                    long keepAliveTime = 0;
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue<Runnable> workQueue =new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

                    poolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
                }
            }
        }
    }

    public Future submit(Runnable task) {
        inintThreadPool();
        Future<?> future = poolExecutor.submit(task);
        return future;
    }

    public void execte(Runnable task) {
        inintThreadPool();
        poolExecutor.execute(task);
    }

    public void remove(Runnable task) {
        inintThreadPool();
        poolExecutor.remove(task);
    }
}
