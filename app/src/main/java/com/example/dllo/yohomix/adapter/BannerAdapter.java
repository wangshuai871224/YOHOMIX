package com.example.dllo.yohomix.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.example.dllo.yohomix.bean.BannerBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class BannerAdapter extends PagerAdapter {

    private BannerBean bannerBeans;

    public void setBannerBeans(BannerBean bannerBeans) {
        this.bannerBeans = bannerBeans;
    }

    @Override
    public int getCount() {
        return bannerBeans.getData() == null ? 0 : Integer.MAX_VALUE;
    }

    public int getImgCount() {
        return bannerBeans.getData().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        String file = bannerBeans.getData()
                .get(position % bannerBeans.getData().size())
                .getImage();
        Picasso.with(container.getContext()).load(file)
                .fit()
                .into(imageView);
        container.addView(imageView, ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }
    }
}
