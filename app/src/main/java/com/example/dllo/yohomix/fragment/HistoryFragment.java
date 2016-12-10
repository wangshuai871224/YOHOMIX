package com.example.dllo.yohomix.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.HistoryAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.SearchItemBean;
import com.example.dllo.yohomix.tools.DBTool;

import java.util.List;

/**
 * Created by dllo on 16/12/6.
 */
public class HistoryFragment extends BaseFragment {

    private GridView historyGV;
    private ImageView historyDelete;
    private List<SearchItemBean> mBeanList;
    private LinearLayout historyFrame;
    private HistoryAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initView() {

        historyGV = bindView(R.id.history_grid_view);
        historyDelete = bindView(R.id.history_delete);
        historyFrame = bindView(R.id.history_frame);
        mAdapter = new HistoryAdapter();
    }

    @Override
    protected void initData() {
        mBeanList = DBTool.getInstance().queryAll();
        historyFrame.setVisibility(View.VISIBLE);
        if (mBeanList == null) {
            historyFrame.setVisibility(View.GONE);
        }else {
            mAdapter.setBeanList(mBeanList);
            historyGV.setAdapter(mAdapter);
        }

    }
}
