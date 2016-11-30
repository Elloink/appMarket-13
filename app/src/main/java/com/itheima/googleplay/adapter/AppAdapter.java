package com.itheima.googleplay.adapter;

import com.itheima.googleplay.adapter.holder.AppHolder;
import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.base.SuperBaseAdapter;

import java.util.List;

/**
 * Created by acer on 2016/11/29.
 */

public class AppAdapter extends SuperBaseAdapter {

    public AppAdapter(List dataSets) {
        super(dataSets);
    }

    @Override
    public BaseHolder getSpecialBaseHolder() {
        return new AppHolder();
    }
}
