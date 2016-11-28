package com.itheima.googleplay.protocal;

/**
 * Created by acer on 2016/11/27.
 */

import com.google.gson.Gson;
import com.itheima.googleplay.bean.HomeBean;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.utils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 协议
 * 负责对homeFragment的网络部分进行封装
 */
public abstract class HomeProtocalBackUp<BEANTYPE> {

    public HomeBean loadDataFromNet() throws IOException {
        //创建客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        //输入url
        Map<String, Object> params = new HashMap<>();
        params.put("index", 0);
        String url = Constants.URLS.BASEURL+"home"+"?"+ HttpUtils.getUrlParamsByMap(params);
        //创建请求
        Request request = new Request.Builder().get().url(url).build();
        //放入浏览器并打开
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String json = response.body().string();
            Gson gson = new Gson();
            return gson.fromJson(json, HomeBean.class);
        }else{
            //打开链接失败
            return null;
        }
    }
}
