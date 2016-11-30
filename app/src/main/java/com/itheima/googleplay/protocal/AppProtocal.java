package com.itheima.googleplay.protocal;

/**
 * Created by acer on 2016/11/27.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay.base.BaseProtocal;
import com.itheima.googleplay.bean.AppBean;

import java.util.List;

/**
 * 协议
 * 负责对homeFragment的网络部分进行封装
 */
public class AppProtocal extends BaseProtocal<List<AppBean>> {


    @Override
    protected List<AppBean> parseJson(String resJsonString) {
        Gson gson = new Gson();
        List<AppBean> list = gson.fromJson(resJsonString, new TypeToken<List<AppBean>>(){}.getType());
        return list;
    }
}
