package com.example.dllo.yohomix;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.bean.StartBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.MyApp;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/12.
 */

public class StartActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mImageView;
    private Button mTextView;
    private CountDownTimer mTimer;
    private Intent mIntent;

    @Override
    protected int setLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        mImageView = bindView(R.id.start_time_image);
        mTextView = bindView(R.id.start_time);
        setClick(this, mTextView);
        mIntent = new Intent(StartActivity.this, MainActivity.class);
    }

    @Override
    protected void initData() {

        VolleySingleton.MyRequest(URLValues.START_PAGE_URL, StartBean.class, new NetListener<StartBean>() {
            @Override
            public void successListener(StartBean response) {
                
                Picasso.with(MyApp.getContext()).load(response.getData().getPic()).into(mImageView);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

        time();

    }

    // 倒计时
    private void time() {
        mTimer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {
                String time = l / 1000 + "";
                mTextView.setText(time);
            }

            @Override
            public void onFinish() {
                startActivity(mIntent);
                finish();
            }
        }.start();
    }

    // 跳转并取消倒计时
    @Override
    public void onClick(View view) {
        startActivity(mIntent);
        mTimer.cancel();
        finish();
    }
}
