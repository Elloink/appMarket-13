package com.itheima.googleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.itheima.googleplay.MyApplication;
import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.HomeAdapter;
import com.itheima.googleplay.base.BaseFooter;
import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.bean.HomeBean;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.protocal.HomeProtocal;
import com.itheima.googleplay.utils.UIUtils;
import com.itheima.googleplay.view.TopNewsViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.util.List;

/**
 * Created by acer on 2016/11/23.
 */

public class HomeFragment extends BaseFragment {
    private HomeBean datas;
    private List<HomeBean.ListBean> lists;
    private List<String> pictures;
    private LoadingPager.DataResult result;
    private HomeProtocal homeProtocal;
    private TopNewsViewPager viewPager;
    private int positon;
    private AutoScrollTask mAutoScrollTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected LoadingPager.DataResult baseInitData() {
        homeProtocal = new HomeProtocal();
        try {
            datas = homeProtocal.loadData("home", 0);
            result = checkResult(datas);
        } catch (IOException e) {
            result = LoadingPager.DataResult.STATE_ERROR;
        }

        return result;
    }

    @Override
    protected View BaseInitView() {
        ListView listView = new ListView(UIUtils.getContext());
        if (datas != null) {
            lists = datas.list;
            pictures = datas.picture;
        }
        if (lists != null) {
            final HomeAdapter adapter = new HomeAdapter(lists);
            listView.setAdapter(adapter);

            BaseFooter footer = new BaseFooter(homeProtocal, "home", 1,lists,adapter);
            View view = footer.getView();
            listView.addFooterView(view);

            //添加头部的轮播条
            listView.addHeaderView(getHeaderView());
        }


        return listView;
    }



    private HomeBean parseBody(String body) {
        Gson gson = new Gson();
        HomeBean datas = gson.fromJson(body, HomeBean.class);
        return datas;
    }

    public View getHeaderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.header_recommend, null);
        viewPager = (TopNewsViewPager) view.findViewById(R.id.vp_header_recommend);
        CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator_header);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pictures.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                position = position%pictures.size();
                String url = Constants.URLS.IMGBASEURL+pictures.get(position);
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(UIUtils.getContext());
                ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
                layoutParams.height = ViewPager.LayoutParams.WRAP_CONTENT;
                layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
                simpleDraweeView.setLayoutParams(layoutParams);
                simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_START);
                simpleDraweeView.setImageURI(url);
                container.addView(simpleDraweeView);
                return simpleDraweeView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }


        });
        indicator.setViewPager(viewPager);


        //实现自动轮播
        if (mAutoScrollTask == null) {
            mAutoScrollTask = new AutoScrollTask();
            mAutoScrollTask.start();
        }
        //按下去的时候停止轮播
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mAutoScrollTask.stop();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mAutoScrollTask.stop();
                        break;
                    case MotionEvent.ACTION_UP:
                        mAutoScrollTask.start();
                        break;
                }
                return false;
            }
        });

        return view;
    }

    private class AutoScrollTask implements Runnable{
        /**
         * 开始滚动
         */

        public void start() {
            stop();
            MyApplication.getmHandler().postDelayed(this, 3000);
        }

        /**
         * 结束滚动
         */
        public void stop() {
            MyApplication.getmHandler().removeCallbacks(this);
        }

        @Override
        public void run() {
            positon = viewPager.getCurrentItem();
            if (positon == pictures.size()-1) {
                positon = -1;
            }
            positon++;
            viewPager.setCurrentItem(positon);

            start();
        }
    }

}
