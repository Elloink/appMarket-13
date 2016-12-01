package com.itheima.googleplay.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.bean.CategoryBean;
import com.itheima.googleplay.utils.DensityUtils;
import com.itheima.googleplay.utils.UIUtils;

/**
 * Created by acer on 2016/11/29.
 */

public class CategoryTitleHolder extends BaseHolder<CategoryBean> {

    private TextView mTvTitle;

    @Override
    public void refreshHolderView(CategoryBean data) {
        mTvTitle.setText(data.title);
    }

    /**
     * 决定对应holder所能提供的视图是啥
     */
    @Override
    public View initHolderView() {
        mTvTitle = new TextView(UIUtils.getContext());
        int padding = DensityUtils.dp2px(5);
        mTvTitle.setPadding(padding, padding, padding, padding);
        return mTvTitle;
    }

}
