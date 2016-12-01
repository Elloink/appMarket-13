package com.itheima.googleplay.protocal;

/**
 * Created by acer on 2016/11/27.
 */

import com.itheima.googleplay.base.BaseProtocal;
import com.itheima.googleplay.bean.CategoryBean;
import com.itheima.googleplay.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 协议
 * 负责对homeFragment的网络部分进行封装
 */
public class CategoryProtocal extends BaseProtocal<List<CategoryBean>> {


    @Override
    protected List<CategoryBean> parseJson(String resJsonString) {
        List<CategoryBean> datas = new ArrayList<>();

        try {
            JSONArray rootJsonArray = new JSONArray(resJsonString);
            //遍历集合
            for (int i = 0; i < rootJsonArray.length(); i++) {
                JSONObject itemJsonObjet = rootJsonArray.getJSONObject(i);
                //去取title
                String title = itemJsonObjet.getString("title");
                CategoryBean titleCategoryInfoBean = new CategoryBean();
                titleCategoryInfoBean.title = title;
                titleCategoryInfoBean.isTitle = true;
                //加入集合中
                datas.add(titleCategoryInfoBean);
                JSONArray infosJsonArray = itemJsonObjet.getJSONArray("infos");
                //遍历集合
                for (int i1 = 0; i1 < infosJsonArray.length(); i1++) {
                    JSONObject infoJsonObject = infosJsonArray.getJSONObject(i1);
                    String name1 = infoJsonObject.getString("name1");
                    String name2 = infoJsonObject.getString("name2");
                    String name3 = infoJsonObject.getString("name3");
                    String url1 = infoJsonObject.getString("url1");
                    String url2 = infoJsonObject.getString("url2");
                    String url3 = infoJsonObject.getString("url3");

                    CategoryBean categoryInfoBean = new CategoryBean();
                    categoryInfoBean.name1 = name1;
                    categoryInfoBean.name2 = name2;
                    categoryInfoBean.name3 = name3;
                    categoryInfoBean.url1 = url1;
                    categoryInfoBean.url2 = url2;
                    categoryInfoBean.url3 = url3;

                    //加入集合
                    datas.add(categoryInfoBean);
                }
            }
            LogUtils.e(datas+"");
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
