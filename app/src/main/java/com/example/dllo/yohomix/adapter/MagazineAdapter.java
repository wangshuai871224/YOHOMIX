package com.example.dllo.yohomix.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/25.
 */

public class MagazineAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> mFragments;
    private String[] title = {"杂志", "壁纸"};

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }

    public MagazineAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
