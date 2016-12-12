package com.example.dllo.yohomix.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.bean.VideoVideoBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by dllo on 16/12/10.
 */
public class VideoPlayActivity extends BaseActivity{
    private Intent mIntent;
    private WebView mWebView;
    private VideoView mVideoView;
    private HashMap<String, String> newMap;
    private ImageView videoBack, videoShare;
    private WebSettings webSettings;

    @Override
    protected int setLayout() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initView() {
        mIntent = getIntent();
        mVideoView = bindView(R.id.video_play);
        mWebView = bindView(R.id.video_web);
        newMap();


        webSettings = mWebView.getSettings();
        //设置webview属性, 能够运行javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 加载需要显示的网页
        mWebView.setWebViewClient(new WebViewClient());
        // 设置web视图
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //  webSettings.setMinimumFontSize(18);
    }

    // 拼接后的map
    private void newMap() {
        HashMap<String,String> map = new HashMap<>();
        map.put("cid",mIntent.getStringExtra("cidvideo"));
        map.put("id",mIntent.getStringExtra("video"));
        map.put("app","1");
        map.put("platform","4");
        map.put("locale","zh-Hans");
        map.put("language","zh-Hans");
        map.put("udid","00000000000000063aa461b71c4cfcf");
        map.put("curVersion","5.0.4");
        HashMap<String,String> mm = new HashMap<>();
        mm.put("udid","00000000000000063aa461b71c4cfcf");
        Gson gson = new Gson();
        String a = gson.toJson(mm).toString();
        map.put("authInfo",a);
        newMap= new HashMap<>();
        String value = gson.toJson(map).toString();
        newMap.put("parameters",value);

    }

    @Override
    protected void initData() {
        requestVideoDetail();

    }

    private void requestVideoDetail() {

        VolleySingleton.MyRequest(URLValues.VIDEO_VIDEO_URL, VideoVideoBean.class, new NetListener<VideoVideoBean>() {
            @Override
            public void successListener(VideoVideoBean response) {

                //视频
                mVideoView.setMediaController(new MediaController(VideoPlayActivity.this));
                mVideoView.setVideoURI(Uri.parse(response.getData().getContents().getVideoUrl()));
                mVideoView.start();

//                tvTop.setText(response.getData().getContents().getTitle());
//                tvLeft.setText(response.getData().getContents().getTag().get(0).getTag_name());
//                tvRight.setText(Tolls.intoTime(response.getData().getContents().getCreate_time()));

                // tvView.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
                //  tvView.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接可以打开网页
                //  tvView.setText(Html.fromHtml(response.getData().getContents().getContent()));
                mWebView.loadDataWithBaseURL("about:blank",response.getData().getContents().getContent(),"text/html"
                        ,"utf-8",null);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, newMap);

    }
}
