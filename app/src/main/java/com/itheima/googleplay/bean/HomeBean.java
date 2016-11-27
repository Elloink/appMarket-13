package com.itheima.googleplay.bean;

import java.util.List;

/**
 * Created by acer on 2016/11/26.
 */

public class HomeBean {

    public List<String> picture;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 1525489
         * name : 黑马程序员
         * packageName : com.itheima.www
         * iconUrl : app/com.itheima.www/icon.jpg
         * stars : 5
         * size : 91767
         * downloadUrl : app/com.itheima.www/com.itheima.www.apk
         * des : 产品介绍：google市场app测试。
         */

        public int id;
        public String name;
        public String packageName;
        public String iconUrl;
        public float stars;
        public long size;
        public String downloadUrl;
        public String des;
    }
}
