package com.itheima.googleplay.adapter.holder;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itheima.googleplay.R;
import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.bean.AppBean;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.utils.StringUtils;
import com.itheima.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/11/29.
 */

public class AppHolder extends BaseHolder<AppBean> {
    @Bind(R.id.item_appinfo_iv_icon)
    SimpleDraweeView itemAppinfoIvIcon;
    @Bind(R.id.item_appinfo_tv_title)
    TextView itemAppinfoTvTitle;
    @Bind(R.id.item_appinfo_rb_stars)
    RatingBar itemAppinfoRbStars;
    @Bind(R.id.item_app_tv_size)
    TextView itemAppTvSize;
    @Bind(R.id.item_appinfo_tv_des)
    TextView itemAppinfoTvDes;

    @Override
    public void refreshHolderView(AppBean data) {
        String url = Constants.URLS.IMGBASEURL+data.iconUrl;
        itemAppinfoIvIcon.setImageURI(url);
        itemAppinfoTvTitle.setText(data.name);
        itemAppinfoRbStars.setRating(data.stars);
        itemAppTvSize.setText(StringUtils.formatFileSize(data.size));
        itemAppinfoTvDes.setText(data.des);

    }

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
