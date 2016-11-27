package com.itheima.googleplay.base;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by acer on 2016/11/26.
 */

public abstract class SuperBaseAdapter<T> extends MyBaseAdapter {

    public SuperBaseAdapter(List<T> dataSets) {
        super(dataSets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;
        if (convertView == null) {
            //创建holder对象
            holder = getSpecialBaseHolder();
        }else {
            holder = (BaseHolder) convertView.getTag();
        }

        Object data = mDatasSets.get(position);
        holder.setDataAndRefreshHolderView(data);
        return holder.mHolderView;
    }

    public abstract BaseHolder getSpecialBaseHolder();
}
