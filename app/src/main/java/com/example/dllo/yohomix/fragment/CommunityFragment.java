package com.example.dllo.yohomix.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.CommunityAdapter;
import com.example.dllo.yohomix.adapter.MyFancyCoverFlowAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.CommunityContentBean;
import com.example.dllo.yohomix.bean.CommunityCoverBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.MyImage;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class CommunityFragment extends BaseFragment{

    private ListView communityList;
    private CommunityAdapter mAdapter;
    private Banner mBanner;
    private FancyCoverFlow mFancyCoverFlow;
    private ArrayList<Integer> datas;
    private MyFancyCoverFlowAdapter mMyFancyCoverFlowAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView() {
        communityList = bindView(R.id.community_list);
        mAdapter = new CommunityAdapter();

        View viewOne = LayoutInflater.from(getActivity()).inflate(R.layout.community_head_banner, null);
        mBanner = (Banner) viewOne.findViewById(R.id.community_banner);
        mFancyCoverFlow = (FancyCoverFlow) viewOne.findViewById(R.id.fancy_cover_flow);
        communityList.addHeaderView(viewOne);
        datas = new ArrayList<>();

    }

    @Override
    protected void initData() {
        getListDetail();
        headBanner();
        fancyCoverFlow();

    }
    // 获取缩小放大图片滑动
    private void fancyCoverFlow() {
        VolleySingleton.MyRequest(URLValues.COMMUNITY_COVER_URL, CommunityCoverBean.class, new NetListener<CommunityCoverBean>() {
            @Override
            public void successListener(CommunityCoverBean response) {

                mMyFancyCoverFlowAdapter = new MyFancyCoverFlowAdapter();
                mMyFancyCoverFlowAdapter.setBean(response);
                mFancyCoverFlow.setAdapter(mMyFancyCoverFlowAdapter);

                mFancyCoverFlow.setUnselectedAlpha(0.3f);
                mFancyCoverFlow.setUnselectedSaturation(0.5f);
                mFancyCoverFlow.setUnselectedScale(0.5f);
                mFancyCoverFlow.setSpacing(-50);
                mFancyCoverFlow.setMaxRotation(0);
                mFancyCoverFlow.setScaleDownGravity(0.5f);
                mFancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    // banner轮播图
    private void headBanner() {
        datas.add(R.mipmap.yohocomtop);
        datas.add(R.mipmap.yohocomtop);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mBanner.setImageLoader(new MyImage());
        mBanner.setImages(datas);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(2000);
        mBanner.start();
    }

    // 获取listView的数据
    private void getListDetail() {
        VolleySingleton.MyRequest(URLValues.COMMUNITY_URL, CommunityContentBean.class, new NetListener<CommunityContentBean>() {
            @Override
            public void successListener(CommunityContentBean response) {

                mAdapter.setBean(response);
                communityList.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }


}
