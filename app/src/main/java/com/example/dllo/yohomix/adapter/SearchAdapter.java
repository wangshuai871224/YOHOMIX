package com.example.dllo.yohomix.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.dllo.yohomix.bean.ReuseBean;
import com.example.dllo.yohomix.fragment.ReuseFragment;

/**
 * Created by dllo on 16/12/3.
 */

public class SearchAdapter extends FragmentStatePagerAdapter{
    private ReuseBean mBean;// 不用静态方法,实体类就不会是静态的,  实体类不用静态,避免不容易回收(以后会讲)********

    public void setBean(ReuseBean bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    public SearchAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String id = getMessage(position);
        return ReuseFragment.getInstance(id);
    }

    @Override
    public int getCount() {
        return mBean == null ? 0 : mBean.getData().get(0).getSubNav().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBean.getData().get(0).getSubNav().get(position).getChannel_name_cn();
    }

    public String getMessage(int pos) {

        return mBean.getData().get(0).getSubNav().get(pos).getId();
    }
}
