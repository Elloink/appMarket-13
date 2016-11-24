package com.itheima.googleplay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.googleplay.utils.UIUtils;

/**
 * Created by acer on 2016/11/24.
 * @des 从六个fragment抽取基类，这个
 */

public abstract class BaseFragment extends Fragment {


    public LoadingPager getLoadingPager() {
        return loadingPager;
    }

    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPager = new LoadingPager(UIUtils.getContext()) {
            @Override
            public View initSuccessView() {
                return BaseInitView();
            }

            @Override
            public DataResult initData() {
                return baseInitData();
            }
        };
        return loadingPager;
    }

    protected abstract LoadingPager.DataResult baseInitData();

    protected abstract View BaseInitView();


}
