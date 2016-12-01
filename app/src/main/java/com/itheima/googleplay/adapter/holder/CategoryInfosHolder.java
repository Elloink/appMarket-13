package com.itheima.googleplay.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.itheima.googleplay.R;
import com.itheima.googleplay.base.BaseHolder;
import com.itheima.googleplay.bean.CategoryBean;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016/11/29.
 */

public class CategoryInfosHolder extends BaseHolder<CategoryBean> {


    @Bind(R.id.item_category_icon_1)
    SimpleDraweeView itemCategoryIcon1;
    @Bind(R.id.item_category_name_1)
    TextView itemCategoryName1;
    @Bind(R.id.item_category_item_1)
    LinearLayout itemCategoryItem1;
    @Bind(R.id.item_category_icon_2)
    SimpleDraweeView itemCategoryIcon2;
    @Bind(R.id.item_category_name_2)
    TextView itemCategoryName2;
    @Bind(R.id.item_category_item_2)
    LinearLayout itemCategoryItem2;
    @Bind(R.id.item_category_icon_3)
    SimpleDraweeView itemCategoryIcon3;
    @Bind(R.id.item_category_name_3)
    TextView itemCategoryName3;
    @Bind(R.id.item_category_item_3)
    LinearLayout itemCategoryItem3;

    @Override
    public void refreshHolderView(CategoryBean data) {
        refreshUI(data.name1, data.url1, itemCategoryName1, itemCategoryIcon1);
        refreshUI(data.name2, data.url2, itemCategoryName2, itemCategoryIcon2);
        refreshUI(data.name3, data.url3, itemCategoryName3, itemCategoryIcon3);
    }

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_infos_category, null);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 为控件填上数据
     */
    public void refreshUI(final String name, final String url, TextView tvName, SimpleDraweeView ivIcon) {
        if (TextUtils.isEmpty(name)&&TextUtils.isEmpty(url)) {
            //如果某个位置没有数据，设置invisible占位
            ViewParent parent = tvName.getParent();
            ((ViewGroup) parent).setVisibility(View.INVISIBLE);
        }else{
            tvName.setText(name);
            ivIcon.setImageURI(Constants.URLS.IMGBASEURL + url);
            ViewParent parent = tvName.getParent();
            ((ViewGroup) parent).setVisibility(View.VISIBLE);
            ((ViewGroup) parent).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), ""+url, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
