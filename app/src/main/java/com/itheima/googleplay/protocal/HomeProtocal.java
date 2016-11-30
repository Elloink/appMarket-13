package com.itheima.googleplay.protocal;

/**
 * Created by acer on 2016/11/27.
 */

import com.google.gson.Gson;
import com.itheima.googleplay.base.BaseProtocal;
import com.itheima.googleplay.bean.HomeBean;

/**
 * 协议
 * 负责对homeFragment的网络部分进行封装
 */
public class HomeProtocal extends BaseProtocal<HomeBean> {


    @Override
    protected HomeBean parseJson(String resJsonString) {
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(resJsonString,HomeBean.class);
        return homeBean;
    }
}
