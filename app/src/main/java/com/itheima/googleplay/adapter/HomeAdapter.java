package com.itheima.googleplay.adapter;

import com.itheima.googleplay.adapter.holder.HomeHolder;
import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.base.SuperBaseAdapter;

import java.util.List;

/**
 * Created by acer on 2016/11/26.
 */

public class HomeAdapter extends SuperBaseAdapter {


    public HomeAdapter(List dataSets) {
        super(dataSets);
    }

    @Override
    public BaseHolder getSpecialBaseHolder() {
        return new HomeHolder();
    }

}
