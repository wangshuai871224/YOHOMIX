package com.example.dllo.yohomix;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.yohomix.activity.CollectionActivity;
import com.example.dllo.yohomix.activity.MagazineActivity;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.fragment.ColumnFragment;
import com.example.dllo.yohomix.fragment.CommunityFragment;
import com.example.dllo.yohomix.fragment.MagazineFragment;
import com.example.dllo.yohomix.fragment.RecommendFragment;
import com.example.dllo.yohomix.fragment.VideoFragment;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton recommendBtn, columnBtn, communityBtn, videoBtn, magazineBtn;
    private FragmentManager manager;
    private DrawerLayout mDrawerLayout;
    private TextView myCollect, myMagazine, myQuest;
    private Intent mIntent;
    private LinearLayout share;

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

        myCollect = bindView(R.id.my_collect);
        myMagazine = bindView(R.id.my_magazine);
        myQuest = bindView(R.id.my_quest);
        setClick(this, recommendBtn, columnBtn, communityBtn, videoBtn, magazineBtn
                , myCollect, myMagazine, myQuest, share);
        mDrawerLayout = bindView(R.id.mine_drawer);

    }

    @Override
    protected void initData() {
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
            case R.id.my_quest:
                Toast.makeText(this, "点我了", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        transaction.commit();
    }

}
