package com.itheima.googleplay.base;

import android.support.annotation.NonNull;
import android.view.View;

import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.HomeAdapter;
import com.itheima.googleplay.bean.HomeBean;
import com.itheima.googleplay.protocal.HomeProtocal;
import com.itheima.googleplay.utils.ThreadUtil;
import com.itheima.googleplay.utils.UIUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by acer on 2016/11/29.
 */

public class BaseFooter {
    private HomeProtocal homeProtocal;
    private int index;
    private List<HomeBean.ListBean> list;
    private List<HomeBean.ListBean> list2;
    private String tab;
    private HomeAdapter adapter;

    public BaseFooter(HomeProtocal homeProtocal, String tab, int index, List<HomeBean.ListBean> list,HomeAdapter adapter) {
        this.homeProtocal = homeProtocal;
        this.index = index;
        this.list = list;
        this.tab = tab;
        this.adapter = adapter;
    }

    @NonNull
    public View getView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.footer_loadmore, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMoreTask();
            }
        });
        return view;
    }

    private void loadMoreTask() {
        ThreadUtil.runInThread(new Runnable() {
            @Override
            public void run() {
                try {
                    list2 = homeProtocal.loadDataFromNet(tab, index).list;
                    list.addAll(list2);
                    index++;
                    ThreadUtil.runInUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

