package com.itheima.googleplay.base;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by acer on 2016/11/26.
 */

/**
 * 需要数据集合datas类，
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public List<T> mDatasSets;
    public MyBaseAdapter(List<T> dataSets) {
        mDatasSets = dataSets;
    }

    @Override
    public int getCount() {
        if (mDatasSets != null) {
            return mDatasSets.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDatasSets != null) {
            return mDatasSets.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
