package com.itheima.googleplay.constant;

import static com.itheima.googleplay.utils.LogUtils.LEVEL_ALL;

/**
 * Created by acer on 2016/11/23.
 */
public class Constants {

    public static final int DEBUGLEVEL = LEVEL_ALL;

    public static final String [] TABS = {"首页","应用","游戏","专题","推荐","分类","排行"};

    public static final class URLS {
        public static final String BASEURL = "http://10.0.2.2:8080/GooglePlayServer/";
        public static final String IMGBASEURL = BASEURL + "image?name=";
    }

}
