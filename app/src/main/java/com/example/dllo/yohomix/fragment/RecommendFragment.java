package com.example.dllo.yohomix.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.activity.SearchActivity;
import com.example.dllo.yohomix.adapter.BannerAdapter;
import com.example.dllo.yohomix.adapter.RecommendListAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.BannerBean;
import com.example.dllo.yohomix.bean.RecommendBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.example.dllo.yohomix.activity.LoginActivity;
import com.example.dllo.yohomix.widget.MyPoint;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/11/23.
 */

public class RecommendFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener {
    private ImageView mineSet, searchContent;
    private ViewPager viewPager;
    private ListView listView;
    private BannerAdapter bannerAdapter;
    private RecommendListAdapter listAdapter;

    private Handler mHandler;
    private List<MyPoint> points;
    private LinearLayout pointLl;
    private DrawerLayout mDrawer;

    private SwipeToLoadLayout mSwipeToLoadLayout;
    private HashMap<String, String> map;
    private HashMap<String, String> newMap;
    private Gson gson;
    private String value;
    private int num = 0;
    private int q = 1;
    private RecommendBean mBean;

    private TextView myCollect, myMagazine, feedBack, evaluate, mySet, userName, myQuest;
    private ImageView userIcon;
    private Intent mIntent;

    private String name;
    private String icon;
    private TextView myRequest;
    public static final int RESULT = 0;
    private PlatformActionListener platformActionListener;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        mineSet = bindView(R.id.mine_set);
        searchContent = bindView(R.id.search_content);
        listView = bindView(R.id.swipe_target);
        mSwipeToLoadLayout = bindView(R.id.recommend_swipe);
        bannerAdapter = new BannerAdapter();
        listAdapter = new RecommendListAdapter(getContext());

        // 抽屉的操作
        mDrawer = (DrawerLayout) getActivity().findViewById(R.id.mine_drawer);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        userName = (TextView) getActivity().findViewById(R.id.user_name);
        userIcon = (ImageView) getActivity().findViewById(R.id.user_image);
        myQuest = (TextView) getActivity().findViewById(R.id.my_quest);


        setClick(this, mineSet, searchContent, userName, userIcon, myQuest);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        addHeadView();
        timer();

    }

    @Override
    protected void initData() {
        login();
        recommendMap();
        requestBanner();
        requestList();
        viewPagerChange();

    }

    // post map
    public void recommendMap() {
        map = new HashMap<>();
        map.put("newsEndtime", "0");
        map.put("otherEndTime", "0");
        map.put("magazineType", "3");
        map.put("WallpaperType", "3");
        map.put("scale", "2");
        map.put("num", String.valueOf(num));
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> mm = new HashMap<>();
        mm.put("udid", "00000000000000063aa461b71c4cfcf");
        String a = new Gson().toJson(mm).toString();
        map.put("authInfo", a);
        gson = new Gson();
        value = gson.toJson(map).toString();
        newMap = new HashMap<>();
        newMap.put("parameters", value);
    }

    // 图片与点 联动
    private void viewPagerChange() {
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
                mBean = response;
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
            case R.id.user_name:
                mIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(mIntent);
                break;
            case R.id.user_image:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息

                // 回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        final String name = platformDb.getUserName();
                        final String icon = platformDb.getUserIcon();
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("icon", icon);
//                        getActivity().setResult(RESULT, intent);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                userName.setText(name);
                                Picasso.with(getActivity()).load(icon).into(userIcon);
                            }
                        });


                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });

                break;
            case R.id.my_quest:
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    platform.removeAccount(true);
                }
                else {
                    Toast.makeText(getActivity(), "退出登录", Toast.LENGTH_SHORT).show();
                }
                platform.setPlatformActionListener(platformActionListener);
                // authorize与showUser单独调用一个即可
                platform.authorize();//单独授权，OnComplete返回的hashmap是空的
//                platform.showUser(null);//授权并获取用户信息
                // isValid和removeAccount不开启线程，会直接返回。
                // qq.removeAccount(true);
                getActivity().setResult(-1);
                break;
        }
    }
    private void login() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行

                try {
                    PlatformDb platformDb = qq.getDb();
                    name = platformDb.getUserName();
                    icon = platformDb.getUserIcon();

                    if (!TextUtils.isEmpty(name)) {
                        userName.setText(name);
                        Picasso.with(getActivity()).load(icon).into(userIcon);
                    }
                } catch (NullPointerException e) {

                }

        platformActionListener = new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                //输出所有授权信息
                arg0.getDb().exportData();
                userName.setText("登录");
                userIcon.setImageResource(R.mipmap.default_head);
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {

            }
        };
        //authorize与showUser单独调用一个即可
//        qq.authorize();//单独授权,OnComplete返回的hashmap是空的
//        qq.showUser(null);//授权并获取用户信息
        // 移除授权
        // qq.removeAccount(true);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data == null) {
//            // 退出登录
//            userName.setText("登录");
//            userIcon.setImageResource(R.mipmap.default_head);
//            return;
//        }
//
//        if (requestCode == 1 && RESULT == resultCode && data != null) {
//            // 登入成功
//            name = data.getStringExtra("name");
//            icon = data.getStringExtra("icon");
//
//            userName.setText(name);
//            Picasso.with(getActivity()).load(icon).into(userIcon);
//
//        }
//
//    }

    // 下啦刷新
    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
                requestList();
            }
        }, 2000);
    }

    // 上啦加载
    @Override
    public void onLoadMore() {

        num += 16;
        map.put("num", String.valueOf(num));
        String aa = gson.toJson(map).toString();
        newMap.put("parameters", aa);

        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                mSwipeToLoadLayout.setLoadingMore(false);
                VolleySingleton.MyRequest(URLValues.RECOMMEND_URL, RecommendBean.class, new NetListener<RecommendBean>() {
                    @Override
                    public void successListener(RecommendBean response) {
                          listAdapter.addBean(response);
                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                }, newMap);
            }
        }, 2000);

    }


}
