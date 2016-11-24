package com.itheima.googleplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.itheima.googleplay.R;
import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.factory.FragmentFactory;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fl_menu_main)
    FrameLayout flMenuMain;
    @Bind(R.id.vp_mian)
    ViewPager vpMian;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.strip_main)
    PagerSlidingTabStrip stripMain;
    private ActionBarDrawerToggle toggle;
    private BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initActionBar();
        initView();
        initActionBarToggle();
        initData();
        initLisenter();
    }

    private void initLisenter() {
        final ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LoadingPager loadingPager = fragment.getLoadingPager();
                loadingPager.triggerLoadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        stripMain.setOnPageChangeListener(onPageChangeListener);
        vpMian.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onPageChangeListener.onPageSelected(0);
                vpMian.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void initData() {
        //模拟数据集合

        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager());
        vpMian.setAdapter(adapter);
        stripMain.setViewPager(vpMian);
    }

    private class MainFragmentAdapter extends FragmentStatePagerAdapter {


        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            fragment = FragmentFactory.createFragment(position);

            return fragment;
        }


        @Override
        public int getCount() {
            return Constants.TABS.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Constants.TABS[position];
        }
    }

    private void initActionBarToggle() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        //同步状态方法 -->替换默认的默认效果
        toggle.syncState();

        //设置drawlayout的监听事件 --》跟随侧滑菜单
        drawerLayout.setDrawerListener(toggle);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggle.onOptionsItemSelected(item);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        //flMenuMain.setLayoutParams(new DrawerLayout.LayoutParams(DensityUtils.getWindowWidth()/2, ViewGroup.LayoutParams.MATCH_PARENT));
       // drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        //DrawerUtil.setDrawerLeftEdgeSize(this,drawerLayout,0.3f);
        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void initActionBar() {
        ActionBar supportActionBar = getSupportActionBar();

        //设置标题
        supportActionBar.setTitle("");
        //设置图标
        supportActionBar.setIcon(R.drawable.ic_launcher);
        //显示图标
        supportActionBar.setDisplayShowHomeEnabled(true);
        //显示回退键
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

}
