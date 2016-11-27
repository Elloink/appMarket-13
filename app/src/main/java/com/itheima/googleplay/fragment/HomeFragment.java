package com.itheima.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.itheima.googleplay.adapter.HomeAdapter;
import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.bean.HomeBean;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.utils.UIUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by acer on 2016/11/23.
 */

public class HomeFragment extends BaseFragment {
    private Object dataFromServer;
    private String url = Constants.URLS.BASEURL + "home?index=0";
    private HomeBean datas;
    private List<HomeBean.ListBean> lists;
    private List<String> pictures;

    @Override
    protected LoadingPager.DataResult baseInitData() {
        datas = getDataFromServer();

        if (datas != null) {
            if (datas.list.size()>0) {
                return LoadingPager.DataResult.STATE_SUCCESS;
            }
        }
        return LoadingPager.DataResult.STATE_ERROR;
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

    public HomeBean getDataFromServer() {
        try {
            //打开浏览器
            OkHttpClient okHttpClient = new OkHttpClient();
            //创建请求
            Request request = new Request.Builder().url(url).get().build();
            //发送请求
            Call call = okHttpClient.newCall(request);
            //返回数据
            Response response = call.execute();
            if (response.isSuccessful()) {
                String body = response.body().string();
                return parseBody(body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HomeBean parseBody(String body) {
        Gson gson = new Gson();
        HomeBean datas = gson.fromJson(body, HomeBean.class);
        return datas;
    }
}
