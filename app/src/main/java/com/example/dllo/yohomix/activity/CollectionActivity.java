package com.example.dllo.yohomix.activity;

import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.CollectionAdapter;
import com.example.dllo.yohomix.base.BaseActivity;


/**
 * Created by dllo on 16/12/8.
 */
public class CollectionActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    private SwipeToLoadLayout mToLoadLayout;
    private ListView mListView;
    private CollectionAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.swipe_target);
        mToLoadLayout = bindView(R.id.collect_swipe);
        mAdapter = new CollectionAdapter();

    }

    @Override
    protected void initData() {

        mListView.setAdapter(mAdapter);
        mToLoadLayout.setOnRefreshListener(this);
        mToLoadLayout.setOnLoadMoreListener(this);

    }

    // 刷新
    @Override
    public void onRefresh() {
        mToLoadLayout.setRefreshing(false);

    }

    // 加载
    @Override
    public void onLoadMore() {
        mToLoadLayout.setLoadingMore(false);

    }
}
