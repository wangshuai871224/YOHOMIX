package com.example.dllo.yohomix.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/25.
 */

public class VideoAdapter extends FragmentStatePagerAdapter{

    private ArrayList<Fragment> mFragments;
    private String[] title=  {"VIDEO", "直播"};

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }

    public VideoAdapter(FragmentManager fm) {
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
