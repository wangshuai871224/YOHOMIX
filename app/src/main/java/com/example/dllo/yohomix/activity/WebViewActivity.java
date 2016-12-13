package com.example.dllo.yohomix.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.bean.CollectWebBean;
import com.example.dllo.yohomix.tools.CommonDBTool;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/12/10.
 */
public class WebViewActivity extends BaseActivity implements View.OnClickListener{

    private String url, collectTitle, collectTag, collectImgUrl, collectTime;
    private WebView mWebView;
    private ImageView commentImg, comeBackImg, columnShare, collectImg;
    private EditText commentEdit;
    private TextView collectNum;
    private boolean collect = false;
    private boolean mExist;

    @Override
    protected int setLayout() {
        return R.layout.activity_web_view;

    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        findId();
        Intent intent = getIntent();
        url = intent.getStringExtra("webview");
        collectTitle = intent.getStringExtra("title");
        collectTime = intent.getStringExtra("createtime");
        collectTag = intent.getStringExtra("tagname");
        collectImgUrl = intent.getStringExtra("imgurl");

    }

    private void findId() {
        mWebView = bindView(R.id.column_web_view);
        commentImg = bindView(R.id.comment_image);
        comeBackImg = bindView(R.id.column_back);
        columnShare = bindView(R.id.column_share);
        collectImg = bindView(R.id.collect_image);
        commentEdit = bindView(R.id.column_comment);
        collectNum= bindView(R.id.column_collect_num);

        setClick(this, commentImg, comeBackImg, columnShare,collectImg, commentEdit,collectNum);
    }

    @Override
    protected void initData() {

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(url);
        mExist = CommonDBTool.getInstance().isHaveTitle(collectTitle);
        if (mExist) {
            collectImg.setImageResource(R.mipmap.heart_icon_h);
        }else {
            collectImg.setImageResource(R.mipmap.heart_icon);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comment_image:
                break;
            case R.id.column_share:
                showShare();
                break;
            case R.id.collect_image:
                CollectWebBean bean = new CollectWebBean();
                bean.setWebUrl(url);
                bean.setImgUrl(collectImgUrl);
                bean.setTagName(collectTag);
                bean.setTime(collectTime);
                bean.setTitle(collectTitle);
                bean.setType(1);

                collect = !collect;

                if (collect) {
                    collectImg.setImageResource(R.mipmap.heart_icon_h);
                    CommonDBTool.getInstance().insertWebBean(bean);
                }else {
                    collectImg.setImageResource(R.mipmap.heart_icon);
                    if (CommonDBTool.getInstance().isSave(bean)){
                        CommonDBTool.getInstance().deleteByTitle(bean.getTitle());
                    }
                }

                break;
            case R.id.column_back:
                finish();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }

}
