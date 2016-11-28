package com.itheima.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.itheima.googleplay.adapter.HomeAdapter;
import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.bean.HomeBean;
import com.itheima.googleplay.protocal.HomeProtocal;
import com.itheima.googleplay.utils.UIUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by acer on 2016/11/23.
 */

public class HomeFragment extends BaseFragment {
    private String url ;
    private HomeBean datas;
    private List<HomeBean.ListBean> lists;
    private List<String> pictures;
    private LoadingPager.DataResult result;

    @Override
    protected LoadingPager.DataResult baseInitData() {
        HomeProtocal homeProtocal = new HomeProtocal();
        try {
            datas = homeProtocal.loadData("home",0);
            result = checkResult(datas);
        } catch (IOException e) {
            result = LoadingPager.DataResult.STATE_ERROR;
        }

        return result;
    }

    @Override
    protected View BaseInitView() {
        ListView listView = new ListView(UIUtils.getContext());
        if (datas != null) {
            lists = datas.list;
            pictures = datas.picture;
        }
        if (lists != null) {
        HomeAdapter adapter = new HomeAdapter(lists);
            listView.setAdapter(adapter);
        }

        return listView;
    }

    private HomeBean parseBody(String body) {
        Gson gson = new Gson();
        HomeBean datas = gson.fromJson(body, HomeBean.class);
        return datas;
    }
}
