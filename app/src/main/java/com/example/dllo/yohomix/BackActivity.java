package com.example.dllo.yohomix;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dllo.yohomix.base.BaseActivity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/12/13.
 */
public class BackActivity extends BaseActivity implements View.OnClickListener {

    private Button back, backMain;
    private PlatformActionListener platformActionListener;

    @Override
    protected int setLayout() {
        return R.layout.activity_back;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.login_back);
        backMain = bindView(R.id.back_main);
        setClick(this, back, backMain);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_main:
                Intent intent = new Intent(BackActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.login_back:
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    // isValid和removeAccount不开启线程，会直接返回。
                    platform.removeAccount(true);// 移除授权
                } else {
                    Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                }
                // 实现接口回调(login中的)
                platform.setPlatformActionListener(platformActionListener);
                // authorize与showUser单独调用一个即可
//        platform.authorize();//单独授权，OnComplete返回的hashmap是空的
//        platform.showUser(null);//授权并获取用户信息
                setResult(-1);
                finish();
                break;
        }

    }
}
