package com.itheima.googleplay.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.itheima.googleplay.R;
import com.itheima.googleplay.utils.LogUtils;
import com.itheima.googleplay.utils.ThreadUtil;
import com.itheima.googleplay.utils.UIUtils;

/**
 * @des 该类主要是为fragment在oncreatview时提供view对象，该类直接继承自framelayout，本身就是一个view直接把自己返回就可以了
 * @des 在显示加载的view时是有四种情况的（加载中视图，错误视图，空视图，成功视图）
 * @des 加载数据
 * @des 数据和视图的绑定 这个类就是mvc中的controller
 */

public abstract class LoadingPager extends FrameLayout {
    public static final int STATE_LOADING = 0;
    public static final int STATE_ERROR = 1;
    public static final int STATE_EMPTY = 2;
    public static final int STATE_SUCCESS = 3;
    public int currentState = STATE_LOADING; //默认的加载状态是正在加载
    private View pagerLoading;
    private View pagerError;
    private View pagerEmptey;
    private View pagerSuccess;
    private ReLoadData reLoadDataTask;


    public LoadingPager(Context context) {
        super(context);
        /**
         * 为什么要在构造方法里初始化view呢，因为在fragment里获取view是通过直接返回这个类的自己来实现的，所以在构造方法里
         * 就要完成三个静态view的初始化
         */
        initCommonView();
    }

    /**
     * 包括正在加载，加载错误，空视图
     */
    private void initCommonView() {
        //加载中的视图
        pagerLoading = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
        this.addView(pagerLoading);
        //加载出错
        pagerError = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
        this.addView(pagerError);
        //加载空视图
        pagerEmptey = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
        this.addView(pagerEmptey);

        //找到错误视图，添加点击事件
        ImageButton bt_er = (ImageButton) pagerError.findViewById(R.id.iv_error);
        bt_er.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerLoadData();
            }
        });

        refreshViewByState();
    }

    /**
     * @des 根据状态刷新ui(决定LoadingPager到底提供四种视图中的哪一种)
     * @called 1.LoadingPager创建的时候
     * @called 2.外界调用了triggerLoadData加载数据, 数据加载之前
     * @called 3.外界调用了triggerLoadData加载数据, 而且数据加载完成了
     */
    private void refreshViewByState() {
        if (currentState == STATE_LOADING) {
            pagerLoading.setVisibility(View.VISIBLE);
        } else {
            pagerLoading.setVisibility(View.GONE);
        }

        if (currentState == STATE_ERROR) {
            pagerError.setVisibility(View.VISIBLE);
        } else {
            pagerError.setVisibility(View.GONE);
        }

        if (currentState == STATE_EMPTY) {
            pagerEmptey.setVisibility(View.VISIBLE);
        } else {
            pagerEmptey.setVisibility(View.GONE);
        }

        if (pagerSuccess == null && currentState == STATE_SUCCESS) {
            pagerSuccess = initSuccessView();
            this.addView(pagerSuccess);
        }

        if (pagerSuccess != null) {
            if (currentState == STATE_SUCCESS) {
                pagerSuccess.setVisibility(View.VISIBLE);
            } else {
                pagerSuccess.setVisibility(View.GONE);
            }
        }


    }

    /**
     * 因为成功的视图只有在子类中才能处理所以抽象
     *
     * @return
     */
    public abstract View initSuccessView();


    public abstract DataResult initData();

    /**
     * 触发加载数据
     */
    public void triggerLoadData() {

        /**
         * 如果当前状态时加载成功或者正在加载，则不回加载
         */
        if (currentState != STATE_SUCCESS) {
            LogUtils.e("触发数据加载。。。triggerLoadData");
            //重新加载数据是耗时任务需要在子线程里实现
            // TODO: 2016/11/24
            if (reLoadDataTask == null) {
                //控制数据加载之前的视图
                currentState = STATE_LOADING;
                refreshViewByState();

                reLoadDataTask = new ReLoadData();
                ThreadUtil.runInThread(reLoadDataTask);
            }

        }


    }

    private class ReLoadData  implements Runnable  {

        @Override
        public void run() {
                DataResult dataResult = initData();

                currentState = dataResult.getState();

                //让主线程去更新ui
                ThreadUtil.runInUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshViewByState();
                    }
                });

                reLoadDataTask = null;
            }
    }

        public enum DataResult {
       /* public static final int STATE_ERROR = 1;
        public static final int STATE_EMPTY = 2;
        public static final int STATE_SUCCESS = 3;*/

            STATE_SUCCESS(3), STATE_ERROR(1), STATE_EMPTY(2);

            public int getState() {
                return state;
            }

            private int state;

            DataResult(int i) {
                this.state = i;
            }
        }


    }
