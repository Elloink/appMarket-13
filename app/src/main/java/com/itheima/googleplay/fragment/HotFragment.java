package com.itheima.googleplay.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.utils.UIUtils;

import java.util.Random;

/**
 * Created by acer on 2016/11/23.
 */

public class HotFragment extends BaseFragment {
    @Override
    protected LoadingPager.DataResult baseInitData() {
        LoadingPager.DataResult[] results = {LoadingPager.DataResult.STATE_ERROR, LoadingPager.DataResult.STATE_EMPTY,
                LoadingPager.DataResult.STATE_SUCCESS};
        Random random = new Random();
        int i = random.nextInt(3);
        return results[i];
    }

    @Override
    protected View BaseInitView() {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText(this.getClass().getSimpleName());
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
