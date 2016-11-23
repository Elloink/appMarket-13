package com.itheima.googleplay.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;

import static com.itheima.googleplay.utils.UIUtils.getResources;

/**
 * Created by acer on 2016/11/23.
 */

public class DensityUtils {
    public static int dp2px(int dp){
        return  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,dp,
                getResources().getDisplayMetrics());
    }
    public static int sp2px(int sp){
        return  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,sp,
                getResources().getDisplayMetrics());
    }

    public static int getWindowHeight() {
        WindowManager wm = (WindowManager) UIUtils.getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
    public static int getWindowWidth() {
        WindowManager wm = (WindowManager) UIUtils.getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return width;
    }

}
