package com.itheima.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.itheima.googleplay.adapter.SubjectAdapter;
import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.bean.SubjectBean;
import com.itheima.googleplay.protocal.SubjectProtocal;
import com.itheima.googleplay.utils.LogUtils;
import com.itheima.googleplay.utils.UIUtils;

import java.util.List;

/**
 * Created by acer on 2016/11/23.
 */

public class SubjectFragment extends BaseFragment {

    private List<SubjectBean> list;
    private LoadingPager.DataResult result;

    @Override
    protected LoadingPager.DataResult baseInitData() {
        SubjectProtocal subProtocal = new SubjectProtocal();
        try {
            list = subProtocal.loadData("subject", 0);
            result = checkResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("主题页加载数据失败");
            result = LoadingPager.DataResult.STATE_ERROR;
        }
        return result;
    }

    @Override
    protected View BaseInitView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new SubjectAdapter(list));
        return listView;
    }
}
