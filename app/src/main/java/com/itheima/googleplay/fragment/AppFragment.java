package com.itheima.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.itheima.googleplay.adapter.AppAdapter;
import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.bean.AppBean;
import com.itheima.googleplay.footer.Appfooter;
import com.itheima.googleplay.protocal.AppProtocal;
import com.itheima.googleplay.utils.LogUtils;
import com.itheima.googleplay.utils.UIUtils;

import java.util.List;

/**
 * Created by acer on 2016/11/23.
 */

public class AppFragment extends BaseFragment {

    private LoadingPager.DataResult result;
    private List<AppBean> list;
    private AppProtocal appProtocal;
    private View headerView;

    @Override
    protected LoadingPager.DataResult baseInitData() {
        AppProtocal appProtocal = new AppProtocal();
        try {
            list = appProtocal.loadData("app", 0);
            result = checkResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("应用页加载数据失败");
            result = LoadingPager.DataResult.STATE_ERROR;
        }
        return result;
    }

    @Override
    protected View BaseInitView() {
        ListView listView = new ListView(UIUtils.getContext());
        if (list != null) {
            final AppAdapter adapter = new AppAdapter(list);
            listView.setAdapter(adapter);

            //添加点击加载更多
            Appfooter appfooter = null;
            if (appProtocal == null) {
                appProtocal = new AppProtocal();
            }
            appfooter = new Appfooter(appProtocal, "app", 1, list, adapter);
            View view = appfooter.getView();
            listView.addFooterView(view);


        }

        return listView;
    }


}
