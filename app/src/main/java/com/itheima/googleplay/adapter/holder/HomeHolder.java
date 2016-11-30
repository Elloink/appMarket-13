package com.itheima.googleplay.adapter.holder;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.bean.HomeBean;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.utils.StringUtils;
import com.itheima.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/11/26.
 */

public class HomeHolder extends BaseHolder {
    @Bind(R.id.item_appinfo_iv_icon)
    ImageView itemAppinfoIvIcon;
    @Bind(R.id.item_appinfo_tv_title)
    TextView itemAppinfoTvTitle;
    @Bind(R.id.item_appinfo_rb_stars)
    RatingBar itemAppinfoRbStars;
    @Bind(R.id.item_appinfo_tv_des)
    TextView itemAppinfoTvDes;
    @Bind(R.id.item_app_tv_size)
    TextView itemAppTvSize;


    @Override
    public void refreshHolderView(Object data) {
        HomeBean.ListBean bean = (HomeBean.ListBean) data;
        itemAppinfoTvTitle.setText(bean.name);
        itemAppinfoRbStars.setRating(bean.stars);
        itemAppTvSize.setText(StringUtils.formatFileSize(bean.size));
        itemAppinfoTvDes.setText(bean.des);

        Uri uri = Uri.parse(Constants.URLS.IMGBASEURL+bean.iconUrl);

        itemAppinfoIvIcon.setImageURI(uri);


    }

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
