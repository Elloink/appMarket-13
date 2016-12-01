package com.itheima.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.itheima.googleplay.adapter.holder.CategoryInfosHolder;
import com.itheima.googleplay.adapter.holder.CategoryTitleHolder;
import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.base.SuperBaseAdapter;
import com.itheima.googleplay.bean.CategoryBean;
import com.itheima.googleplay.protocal.CategoryProtocal;
import com.itheima.googleplay.utils.UIUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by acer on 2016/11/23.
 */

public class CategoryFragment extends BaseFragment {
    private LoadingPager.DataResult result;
    private List<CategoryBean> datas;

    @Override
    protected LoadingPager.DataResult baseInitData() {
        try {
            datas = new CategoryProtocal().loadData("category", 0);
            result = checkResult(datas);
        } catch (IOException e) {
            e.printStackTrace();
            result = LoadingPager.DataResult.STATE_ERROR;
        }

        return result;
    }

    @Override
    protected View BaseInitView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new Category(datas));
        return listView;
    }

    private class Category extends SuperBaseAdapter<CategoryBean> {
        public Category(List<CategoryBean> dataSets) {
            super(dataSets);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (datas.get(position).isTitle) {
                return 0;
            }else{
                return 1;
            }
        }

        @Override
        public BaseHolder getSpecialBaseHolder(int position) {
            if (datas.get(position).isTitle) {
                return new CategoryTitleHolder();
            }else{
                return new CategoryInfosHolder();
            }
        }
    }

}
