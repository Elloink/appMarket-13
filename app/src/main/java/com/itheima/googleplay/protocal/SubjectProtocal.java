package com.itheima.googleplay.protocal;

/**
 * Created by acer on 2016/11/27.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay.base.BaseProtocal;
import com.itheima.googleplay.bean.SubjectBean;

import java.util.List;

/**
 * 协议
 * 负责对homeFragment的网络部分进行封装
 */
public class SubjectProtocal extends BaseProtocal<List<SubjectBean>> {


    @Override
    protected List<SubjectBean> parseJson(String resJsonString) {
        Gson gson = new Gson();
        List<SubjectBean> list = gson.fromJson(resJsonString,new TypeToken<List<SubjectBean>>(){}.getType());
        return list;
    }
}
