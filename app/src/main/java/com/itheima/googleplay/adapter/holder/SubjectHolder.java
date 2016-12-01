package com.itheima.googleplay.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itheima.googleplay.R;
import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.bean.SubjectBean;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/11/29.
 */

public class SubjectHolder extends BaseHolder<SubjectBean> {


    @Bind(R.id.item_subject_tv_title)
    TextView itemSubjectTvTitle;
    @Bind(R.id.item_subject_iv)
    SimpleDraweeView itemSubjectIv;

    @Override
    public void refreshHolderView(SubjectBean data) {
        String url = Constants.URLS.IMGBASEURL + data.url;
        itemSubjectIv.setImageURI(url);

        itemSubjectTvTitle.setText(data.des);
    }

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_subject, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
