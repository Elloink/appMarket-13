package com.itheima.googleplay.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.protocal.HotProtocal;
import com.itheima.googleplay.utils.UIUtils;
import com.itheima.googleplay.view.FlowLayout;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by acer on 2016/11/23.
 */

public class HotFragment extends BaseFragment {
    private LoadingPager.DataResult result;
    private List<String> datas;

    @Override
    protected LoadingPager.DataResult baseInitData() {
        HotProtocal hotProtocal = new HotProtocal();
        try {
            datas = hotProtocal.loadData("hot", 0 );
            result = checkResult(datas);
        } catch (IOException e) {
            e.printStackTrace();
            result = LoadingPager.DataResult.STATE_ERROR;
        }

        return result;

    }


    @Override
    protected View BaseInitView() {
        ScrollView scrollView = new ScrollView(UIUtils.getContext());
        FlowLayout flowLayout = new FlowLayout(UIUtils.getContext());
        Random random = new Random();
        for (int i = 0; i < datas.size(); i++) {
            TextView textView = new TextView(UIUtils.getContext());
            textView.setText(datas.get(i));
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(10,10,10,10);

            int alpha = 255;
            int red = random.nextInt(170)+30;
            int green = random.nextInt(170)+30;
            int blue = random.nextInt(170)+30;
            //创建背景
            GradientDrawable nomorlBg = new GradientDrawable();
            nomorlBg.setCornerRadius(10);
            nomorlBg.setColor(Color.argb(alpha, red, green, blue));
            //创建一个按下去的背景
            GradientDrawable pressBg = new GradientDrawable();
            pressBg.setCornerRadius(10);
            pressBg.setColor(Color.DKGRAY);

            //创建一个带状态的背景
            StateListDrawable selectorBg = new StateListDrawable();
            selectorBg.addState(new int[]{android.R.attr.state_pressed}, pressBg);
            selectorBg.addState(new int[]{},nomorlBg);
            textView.setBackground(selectorBg);

            textView.setClickable(true);
            flowLayout.addView(textView);
        }
        scrollView.addView(flowLayout);
        return scrollView;
    }
}
