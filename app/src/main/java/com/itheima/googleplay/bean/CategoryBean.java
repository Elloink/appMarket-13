package com.itheima.googleplay.bean;

/**
 * Created by acer on 2016/11/30.
 */

public class CategoryBean {

    /**
     * infos : [{"name1":"休闲","name2":"棋牌","name3":"益智","url1":"image/category_game_0.jpg","url2":"image/category_game_1.jpg","url3":"image/category_game_2.jpg"},{"name1":"射击","name2":"体育","name3":"儿童","url1":"image/category_game_3.jpg","url2":"image/category_game_4.jpg","url3":"image/category_game_5.jpg"},{"name1":"网游","name2":"角色","name3":"策略","url1":"image/category_game_6.jpg","url2":"image/category_game_7.jpg","url3":"image/category_game_8.jpg"},{"name1":"经营","name2":"竞速","name3":"","url1":"image/category_game_9.jpg","url2":"image/category_game_10.jpg","url3":""}]
     * title : 游戏
     */

    public String title;

    public String name1;
    public String name2;
    public String name3;
    public String url1;
    public String url2;
    public String url3;

    public boolean isTitle;

    @Override
    public String toString() {
        return "CategoryBean{" +
                "title='" + title + '\'' +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", name3='" + name3 + '\'' +
                ", url1='" + url1 + '\'' +
                ", url2='" + url2 + '\'' +
                ", url3='" + url3 + '\'' +
                ", isTitle=" + isTitle +
                '}';
    }
}
