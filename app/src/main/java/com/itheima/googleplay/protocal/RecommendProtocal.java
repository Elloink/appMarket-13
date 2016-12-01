package com.itheima.googleplay.protocal;

/**
 * Created by acer on 2016/11/27.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay.base.BaseProtocal;

import java.util.List;

/**
 * 协议
 * 负责对homeFragment的网络部分进行封装
 */
public class RecommendProtocal extends BaseProtocal<List<String>> {


    @Override
    protected List<String> parseJson(String resJsonString) {
//        List<String> datas = new ArrayList<>();
//        try {
//            JSONArray jsons = new JSONArray(resJsonString);
//            for (int i = 0; i < jsons.length(); i++) {
//                String appName = (String) jsons.get(i);
//                datas.add(appName);
//            }
//            return datas;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
        return new Gson().fromJson(resJsonString, new TypeToken<List<String>>() {
        }.getType());
    }
}
