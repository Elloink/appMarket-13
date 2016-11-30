package com.itheima.googleplay.footer;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.AppAdapter;
import com.itheima.googleplay.base.BaseProtocal;
import com.itheima.googleplay.bean.AppBean;
import com.itheima.googleplay.protocal.AppProtocal;
import com.itheima.googleplay.utils.ThreadUtil;
import com.itheima.googleplay.utils.UIUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by acer on 2016/11/29.
 */

public class Appfooter {
    private AppProtocal appProtocal;
    private int index;
    private List<AppBean> list;
    private List<AppBean> list2;
    private String tab;
    private AppAdapter adapter;

    public Appfooter(AppProtocal appProtocal, String tab, int index, List<AppBean> list,AppAdapter adapter) {
        this.appProtocal = appProtocal;
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

                    list2 = appProtocal.loadDataFromNet(tab, index);
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

