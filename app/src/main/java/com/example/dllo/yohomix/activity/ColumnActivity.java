package com.example.dllo.yohomix.activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.ColumnTwoAdapter;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.bean.ColumnBean;
import com.example.dllo.yohomix.bean.ColumnTwoBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by dllo on 16/12/9.
 */
public class ColumnActivity extends BaseActivity {

    private HashMap<String, String> mMapSure;
    private ColumnBean.DataBean mDataBean;
    private ColumnTwoBean mBean;
    private ColumnTwoAdapter mTwoAdapter;
    private ListView mListView;
    private TextView columnTitle ;
    private ImageView columnBack, mImageView;
    private String mId,mHeadUrl,mHeadTitle, mHeadSum, mHeadNum;
    private View mView;


    @Override
    protected int setLayout() {
        return R.layout.activity_column;
    }

    @Override
    protected void initView() {
        columnTitle = bindView(R.id.column_two_title);
        columnBack = bindView(R.id.column_two_back);
        mListView = bindView(R.id.column_two_list);
        mTwoAdapter = new ColumnTwoAdapter();
        Intent intent = getIntent();
        mHeadUrl = intent.getStringExtra("headUrl");
        mHeadTitle = intent.getStringExtra("title");
        mHeadSum = intent.getStringExtra("sum");
        mHeadNum = intent.getStringExtra("num");
        mId = intent.getStringExtra("id");
        newMap(mId);
        addHeader();
        columnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    // 添加头布局
    private void addHeader() {
        mView = LayoutInflater.from(this).inflate(R.layout.column_two_header, null);
        mImageView = (ImageView) mView.findViewById(R.id.head_image);
        TextView headSummary= (TextView) mView.findViewById(R.id.column_head_summary);
        TextView headNum = (TextView) mView.findViewById(R.id.column_two_num);
        headSummary.setText(mHeadSum);
        headNum.setText("更新至" + mHeadNum + "篇");
        Picasso.with(this).load(mHeadUrl).into(mImageView);
        mListView.addHeaderView(mView);
    }

    // 拼接body体(post请求)
    private void newMap(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("page", "1");
        map.put("limit", "20");
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> mapnew = new HashMap<>();
        mapnew.put("udid", "00000000000000063aa461b71c4cfcf");
        Gson gson = new Gson();
        String a = gson.toJson(mapnew).toString();
        map.put("authInfo", a);
        String value = gson.toJson(map).toString();

        mMapSure = new HashMap<>();
        mMapSure.put("parameters", value);
    }


    @Override
    protected void initData() {

        columnTitle.setText(mHeadTitle);
        request();

    }

    // 请求数据及点击事件
    private void request() {
        VolleySingleton.MyRequest(URLValues.COLUMN_TWO_URL, ColumnTwoBean.class, new NetListener<ColumnTwoBean>() {
            @Override
            public void successListener(ColumnTwoBean response) {
                mBean = response;
                mTwoAdapter.setBean(response);
                mListView.setAdapter(mTwoAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, mMapSure);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ColumnActivity.this, WebViewActivity.class);
                if (i - 1 >= 0) {
                    intent.putExtra("webview", mBean.getData().getContent().get(i - 1).getPublishURL());
                }
                startActivity(intent);
            }
        });
    }

}
