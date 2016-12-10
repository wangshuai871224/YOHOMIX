package com.example.dllo.yohomix.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.SearchItemBean;
import com.example.dllo.yohomix.tools.CommonVH;

import java.util.List;

/**
 * Created by dllo on 16/12/9.
 */

public class HistoryAdapter extends BaseAdapter{

    private List<SearchItemBean> mBeanList;

    public void setBeanList(List<SearchItemBean> beanList) {
        mBeanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBeanList == null ? 0 : mBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return mBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.listViewHolder(view,viewGroup,R.layout.item_history_search);
        commonVH.setText(R.id.history_text, mBeanList.get(i).getBody());
        return commonVH.getItemView();
    }
}
