package com.example.dllo.yohomix.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.MagazineAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import java.util.ArrayList;


/**
 * Created by dllo on 16/11/23.
 */

public class MagazineFragment extends BaseFragment{

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MagazineAdapter mAdapter;
    private ArrayList<Fragment> mFragments;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;

    }

    @Override
    protected void initView() {
        mTabLayout = bindView(R.id.video_tb);
        mViewPager = bindView(R.id.video_vp);
        mFragments = new ArrayList<>();
        mAdapter = new MagazineAdapter(getChildFragmentManager());
    }

    @Override
    protected void initData() {
        mFragments.add(new JournalFragment());
        mFragments.add(new WallPaperFragment());
        mAdapter.setFragments(mFragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
