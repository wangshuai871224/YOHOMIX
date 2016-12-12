package com.example.dllo.yohomix;

import android.content.Intent;
import android.os.CountDownTimer;

import com.example.dllo.yohomix.base.BaseActivity;

/**
 * Created by dllo on 16/12/12.
 */

public class WelcomeActivity extends BaseActivity{

    private CountDownTimer mTimer;
    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        mTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    protected void initData() {
        mTimer.start();
    }
}
