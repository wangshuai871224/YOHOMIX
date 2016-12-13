package com.example.dllo.yohomix.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.CollectionAdapter;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.bean.CollectWebBean;
import com.example.dllo.yohomix.tools.CommonDBTool;

import java.util.List;


/**
 * Created by dllo on 16/12/8.
 */
public class CollectionActivity extends BaseActivity implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener {
    private SwipeToLoadLayout mToLoadLayout;
    private ListView mListView;
    private CollectionAdapter mAdapter;
    private List<CollectWebBean> mBean;
    private TextView edit;
    private ImageView quit;
    private boolean isShow = false;
    private Intent intent;

    @Override
    protected int setLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.swipe_target);
        mToLoadLayout = bindView(R.id.collect_swipe);
        edit = bindView(R.id.my_edit);
        quit = bindView(R.id.collect_quit);
        setClick(this, edit, quit);
        mAdapter = new CollectionAdapter();

    }

    @Override
    protected void initData() {
        mBean = CommonDBTool.getInstance().queryAll();
        mAdapter.setBean(mBean);
        mListView.setAdapter(mAdapter);
        mToLoadLayout.setOnRefreshListener(this);
        mToLoadLayout.setOnLoadMoreListener(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                intent = new Intent(CollectionActivity.this, WebViewActivity.class);
                intent.putExtra("webview", mBean.get(position).getWebUrl());
                intent.putExtra("title", mBean.get(position).getTitle());
                intent.putExtra("createtime", mBean.get(position).getTime());
                intent.putExtra("tagname", mBean.get(position).getTagName());
                intent.putExtra("imgurl", mBean.get(position).getImgUrl());
                startActivity(intent);
            }
        });
    }

    // 刷新
    @Override
    public void onRefresh() {
        mToLoadLayout.setRefreshing(false);
        mToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBean = CommonDBTool.getInstance().queryAll();
                mAdapter.setBean(mBean);
            }
        }, 1000);

    }

    // 加载
    @Override
    public void onLoadMore() {
        mToLoadLayout.setLoadingMore(false);
        Toast.makeText(this, "没有加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.my_edit:
                Toast.makeText(this, "点我了", Toast.LENGTH_SHORT).show();
                isShow = !isShow;
                mAdapter.showX(isShow);
                if (isShow) {
                    edit.setText("取消");
                }else {
                    edit.setText("编辑");
                }
                break;
            case R.id.collect_quit:
                finish();
                break;

        }
    }
}
