package com.itheima.googleplay.base;

import android.view.View;

/**
 * Created by acer on 2016/11/26.
 */

public abstract class BaseHolder<HOLDERVIEWTYPE> {
    public View mHolderView;
    public HOLDERVIEWTYPE mData;

    public BaseHolder() {
        //初始化根视图
        mHolderView = initHolderView();
       //找到一个符合条件的holder，绑定在自己的身上
        mHolderView.setTag(this);
    }

    public void setDataAndRefreshHolderView(HOLDERVIEWTYPE data) {
        mData = data;
        refreshHolderView(data);
    }


    public abstract void refreshHolderView(HOLDERVIEWTYPE data) ;

    public abstract View initHolderView();
}
