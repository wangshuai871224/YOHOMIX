package com.example.dllo.yohomix;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.yohomix.activity.CollectionActivity;
import com.example.dllo.yohomix.activity.LoginActivity;
import com.example.dllo.yohomix.activity.MagazineActivity;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.fragment.ColumnFragment;
import com.example.dllo.yohomix.fragment.CommunityFragment;
import com.example.dllo.yohomix.fragment.LoginFragment;
import com.example.dllo.yohomix.fragment.MagazineFragment;
import com.example.dllo.yohomix.fragment.RecommendFragment;
import com.example.dllo.yohomix.fragment.VideoFragment;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton recommendBtn, columnBtn, communityBtn, videoBtn, magazineBtn;
    private FragmentManager manager;
    private DrawerLayout mDrawerLayout;
    private TextView myCollect, myMagazine, myQuest;
    private Intent mIntent;
    private LinearLayout share;
    private TextView userName;
    private ImageView userIcon;
    private String name;
    private String icon;
    public static final int REQUEST_CODE = 1;

    private PlatformActionListener platformActionListener;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        recommendBtn = bindView(R.id.recommend_btn);
        columnBtn = bindView(R.id.column_btn);
        communityBtn = bindView(R.id.community_btn);
        videoBtn = bindView(R.id.video_btn);
        magazineBtn = bindView(R.id.magazine_btn);
        share = bindView(R.id.share);
        userName = bindView(R.id.user_name);
        userIcon = bindView(R.id.user_image);



        myCollect = bindView(R.id.my_collect);
        myMagazine = bindView(R.id.my_magazine);
        myQuest = bindView(R.id.my_quest);
        setClick(this, recommendBtn, columnBtn, communityBtn, videoBtn, magazineBtn
                , myCollect, myMagazine, myQuest, share, userName, userIcon);
        mDrawerLayout = bindView(R.id.mine_drawer);

    }

    @Override
    protected void initData() {
        login();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_frame, new RecommendFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View view) {

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.recommend_btn:
                transaction.replace(R.id.replace_frame, new RecommendFragment());
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case R.id.column_btn:
                transaction.replace(R.id.replace_frame, new ColumnFragment());
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.community_btn:
                transaction.replace(R.id.replace_frame, new CommunityFragment());
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.video_btn:
                transaction.replace(R.id.replace_frame, new VideoFragment());
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.magazine_btn:
                transaction.replace(R.id.replace_frame, new MagazineFragment());
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;

            case R.id.my_collect:
                mIntent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(mIntent);

                break;
            case R.id.my_magazine:
                mIntent = new Intent(MainActivity.this, MagazineActivity.class);
                startActivity(mIntent);
                break;

            case R.id.user_name:
                mIntent = new Intent(this, LoginActivity.class);
                startActivityForResult(mIntent, REQUEST_CODE);
                break;

            case R.id.user_image:
                if (!TextUtils.isEmpty(name)) {
                    mIntent = new Intent(this, BackActivity.class);
                    startActivityForResult(mIntent, REQUEST_CODE);
                }else {
                    mIntent = new Intent(this, LoginActivity.class);
                    startActivityForResult(mIntent, REQUEST_CODE);
                }
                break;

            case R.id.my_quest:
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    // isValid和removeAccount不开启线程，会直接返回。
                    platform.removeAccount(true);// 移除授权
                    userName.setText("登录");
                    userIcon.setImageResource(R.mipmap.default_head);
                    name = null;
                } else {
                    Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                }
                // 实现接口回调(login中的)
                platform.setPlatformActionListener(platformActionListener);
                // authorize与showUser单独调用一个即可
//                platform.authorize();//单独授权，OnComplete返回的hashmap是空的
//                platform.showUser(null);//授权并获取用户信息
                setResult(-1);
                break;

            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            // 退出登录
            userName.setText("登录");
            userIcon.setImageResource(R.mipmap.default_head);
            name= "";
            icon= "";
            return;
        }

        if (requestCode == REQUEST_CODE && LoginFragment.RESULT == resultCode && data != null) {
            // 登入成功
            name = data.getStringExtra("name");
            icon = data.getStringExtra("icon");
            userName.setText(name);
            Glide.with(this).load(icon).bitmapTransform(new CropCircleTransformation(this)).into(userIcon);

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
                Glide.with(this).load(icon).bitmapTransform(new CropCircleTransformation(this)).into(userIcon);
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
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
            }
        };
    }

}
