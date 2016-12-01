package com.itheima.googleplay.adapter;

import com.itheima.googleplay.adapter.holder.SubjectHolder;
import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.base.SuperBaseAdapter;

import java.util.List;

/**
 * Created by acer on 2016/11/29.
 */

public class SubjectAdapter extends SuperBaseAdapter {

    public SubjectAdapter(List dataSets) {
        super(dataSets);
    }

    @Override
    public BaseHolder getSpecialBaseHolder(int position) {
        return new SubjectHolder();
    }
}
