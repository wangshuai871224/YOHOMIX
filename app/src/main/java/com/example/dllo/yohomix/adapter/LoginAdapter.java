package com.example.dllo.yohomix.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/6.
 */
public class LoginAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> mFragments;

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }

    public LoginAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}
