package com.example.dllo.yohomix.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.LoginAdapter;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.fragment.LoginFragment;
import com.example.dllo.yohomix.fragment.RegisterFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/2.
 */
public class LoginActivity extends BaseActivity{

    public static ViewPager loginVp;
    private ImageView loginReturn;
    private LoginAdapter mAdapter;
    private TextView loginRegister;
    private ArrayList<Fragment> mFragments;
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginReturn = bindView(R.id.login_return);
        loginVp = bindView(R.id.login_vp);
        loginRegister = bindView(R.id.login_register);
        mFragments = new ArrayList<>();
        mAdapter = new LoginAdapter(getSupportFragmentManager());
        loginReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        mFragments.add(new LoginFragment());
        mFragments.add(new RegisterFragment());
        mAdapter.setFragments(mFragments);
        loginVp.setAdapter(mAdapter);

        loginVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (1 == position) {
                    loginRegister.setVisibility(View.VISIBLE);
                }else {
                    loginRegister.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
