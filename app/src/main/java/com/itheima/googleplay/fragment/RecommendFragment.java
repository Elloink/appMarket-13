package com.itheima.googleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.protocal.RecommendProtocal;
import com.itheima.googleplay.utils.UIUtils;
import com.itheima.googleplay.view.flyinout.StellarMap;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by acer on 2016/11/23.
 */

public class RecommendFragment extends BaseFragment {
    private LoadingPager.DataResult result;
    private List<String> datas;

    @Override
    protected LoadingPager.DataResult baseInitData() {
        RecommendProtocal recommendProtocal = new RecommendProtocal();
        try {
            datas = recommendProtocal.loadData("recommend", 0);
            result = checkResult(datas);
        } catch (IOException e) {
            e.printStackTrace();
            result = LoadingPager.DataResult.STATE_ERROR;
        }
        return result;
    }

    @Override
    protected View BaseInitView() {
        //ScrollView scrollView = new ScrollView(UIUtils.getContext());
        StellarMap map = new StellarMap(UIUtils.getContext());
        map.setAdapter(new RecommendAdapter());
        //scrollView.addView(map);
        //首页未展示
        map.setGroup(0, true);
        //每一页的条目数目不统一
        map.setRegularity(15, 20);
        return map;
    }

    class RecommendAdapter implements StellarMap.Adapter {
        //每页多少个
        public static final int PAGESIZE = 15;
        @Override
        public int getGroupCount() {
            if (datas.size()%PAGESIZE ==0) {
                return datas.size()/PAGESIZE;
            }else{
                return datas.size()/PAGESIZE+1;
            }

        }

        @Override
        public int getCount(int group) {
            if (datas.size()%PAGESIZE == 0) {
                return PAGESIZE;
            }else{
                if (group == getGroupCount()-1) {
                    return datas.size()%PAGESIZE;
                }else {
                    return PAGESIZE;
                }
            }
        }

        @Override
        public View getView(int group, int position, View convertView) {
            //返回具体的孩子
            TextView tv = new TextView(UIUtils.getContext());
            //data
            int index = group*PAGESIZE+position;
            String data = datas.get(index);

            Random random = new Random();
            tv.setTextSize(random.nextInt(5) + 12);

            int alpha = 255;
            int red = random.nextInt(200) + 20;
            int green = random.nextInt(200) + 20;
            int blue = random.nextInt(200) + 20;
            tv.setTextColor(Color.argb(alpha, red, green, blue));
            tv.setText(data);
            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 0;
        }
    }
}
