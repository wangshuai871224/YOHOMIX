package com.example.dllo.yohomix.activity;

import android.widget.GridView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.GridViewAdapter;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.bean.SearchItemBean;
import com.example.dllo.yohomix.tools.DBTool;

import java.util.List;

/**
 * Created by dllo on 16/12/8.
 */
public class MagazineActivity extends BaseActivity{

    private GridView myGridView;
    private GridViewAdapter mAdapter;
    private List<SearchItemBean> beans;

    @Override
    protected int setLayout() {
        return R.layout.activity_magazine;
    }

    @Override
    protected void initView() {
        myGridView = bindView(R.id.magazine_grid_view);
        mAdapter = new GridViewAdapter();

    }

    @Override
    protected void initData() {
        beans = DBTool.getInstance().queryAll();
        mAdapter.setList(beans);
        myGridView.setAdapter(mAdapter);
    }
}
