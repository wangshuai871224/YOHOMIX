package com.example.dllo.yohomix.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.SearchActivity;
import com.example.dllo.yohomix.adapter.BannerAdapter;
import com.example.dllo.yohomix.adapter.RecommendListAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.BannerBean;
import com.example.dllo.yohomix.bean.RecommendBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.example.dllo.yohomix.LoginActivity;
import com.example.dllo.yohomix.widget.MyPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class RecommendFragment extends BaseFragment implements View.OnClickListener{
    private ImageView mineSet, searchContent;
    private ViewPager viewPager;
    private ListView listView;
    private BannerAdapter bannerAdapter;
    private RecommendListAdapter listAdapter;

    private Handler mHandler;
    private List<MyPoint> points;
    private LinearLayout pointLl;
    private DrawerLayout mDrawer;

    private TextView myCollect, myMagazine, myRequest, feedBack, evaluate, mySet,loginUser;
    private Intent mIntent;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        mineSet = bindView(R.id.mine_set);
        searchContent = bindView(R.id.search_content);
        listView = bindView(R.id.recommend_lv);
        bannerAdapter = new BannerAdapter();
        listAdapter = new RecommendListAdapter(getContext());

        // 抽屉的操作
        mDrawer = (DrawerLayout) getActivity().findViewById(R.id.mine_drawer);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        loginUser = (TextView) getActivity().findViewById(R.id.login_user);


        setClick(this, mineSet, searchContent, loginUser);

        addHeadView();
        timer();

    }

    @Override
    protected void initData() {

        requestBanner();
        requestList();

        // 图片与点 联动
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 当前显示的是第几个图
                int currentPage = position % bannerAdapter.getImgCount();

                for (MyPoint point : points) {
                    point.setSelected(false);// 不选中
                }
                points.get(currentPage).setSelected(true);// 当前选中
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    // 添加头布局
    public void addHeadView() {
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.carousel_picture, null);
        viewPager = (ViewPager) headerView.findViewById(R.id.banner_vp);
        pointLl = (LinearLayout) headerView.findViewById(R.id.point_ll);
        listView.addHeaderView(headerView);
    }

    // 定时循环
    public void timer() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (viewPager != null && msg.what == 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        };
    }

    // 请求推荐页面listView数据
    public void requestList() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.RECOMMEND_VALUE);

        VolleySingleton.MyRequest(URLValues.RECOMMEND_URL, RecommendBean.class, new NetListener<RecommendBean>() {
            @Override
            public void successListener(RecommendBean response) {
                listAdapter.setBean(response);

                listView.setAdapter(listAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    // 请求轮播图数据
    public void requestBanner() {

        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.BANNER_VALUE);
        VolleySingleton.MyRequest(URLValues.BANNER_URL, BannerBean.class, new NetListener<BannerBean>() {
            @Override
            public void successListener(BannerBean response) {
                Log.d("RecommendFragment", "response:" + response);
                bannerAdapter.setBannerBeans(response);
                viewPager.setAdapter(bannerAdapter);

                initPoints();
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    // 轮播图点
    private void initPoints() {
        points = new ArrayList<>();
        for (int i = 0; i < bannerAdapter.getImgCount(); i++) {
            MyPoint point = new MyPoint(getActivity());
            if (i == 0) {
                point.setSelected(true);
            }
            points.add(point);
            LinearLayout.LayoutParams layoutParams
                    = new LinearLayout.LayoutParams(30, 30);
            pointLl.addView(point, layoutParams);
        }
    }

    // 开始轮播
    @Override
    public void onStart() {
        super.onStart();
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    // 取消轮播
    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeMessages(1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_content:
                mIntent = new Intent(getActivity(), SearchActivity.class);
                startActivity(mIntent);
                break;
            case R.id.mine_set:
                mDrawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.login_user:
                mIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
