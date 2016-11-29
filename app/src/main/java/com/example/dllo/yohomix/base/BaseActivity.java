package com.example.dllo.yohomix.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dllo.yohomix.MainActivity;
import com.example.dllo.yohomix.R;

/**
 * Created by dllo on 16/11/23.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();
    }

    // 绑定布局
    protected abstract int setLayout();
    // 初始化组件findViewById
    protected abstract void initView();
    // 初始化数据
    protected abstract void initData();

    // 祛除强转
    protected  <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    protected void setClick(MainActivity clickListener, View... views) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }
}
