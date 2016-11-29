package com.example.dllo.yohomix.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.VideoAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class VideoFragment extends BaseFragment{


    private TabLayout videoTb;
    private ViewPager videoVp;
    private ArrayList<Fragment> mFragments;
    private VideoAdapter mVideoAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        videoTb = bindView(R.id.video_tb);
        videoVp = bindView(R.id.video_vp);
        mFragments = new ArrayList<>();
        mVideoAdapter = new VideoAdapter(getChildFragmentManager());
    }

    @Override
    protected void initData() {
        mFragments.add(new VideoVideoFragment());
        mFragments.add(new LiveFragment());
        mVideoAdapter.setFragments(mFragments);
        videoVp.setAdapter(mVideoAdapter);
        videoTb.setupWithViewPager(videoVp);

    }
}
