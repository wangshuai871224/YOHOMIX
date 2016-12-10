package com.example.dllo.yohomix.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.ColumnBean;
import com.example.dllo.yohomix.tools.CommonVH;

/**
 * Created by dllo on 16/11/25.
 */

public class ColumnAdapter extends BaseAdapter{

    private ColumnBean mBean;

    public void setBean(ColumnBean bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    public void addBean(ColumnBean bean){
        mBean.getData().addAll(bean.getData());
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return mBean.getData() == null ? 0 : mBean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return mBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CommonVH viewHolder = CommonVH.listViewHolder(view, viewGroup, R.layout.item_column);
        viewHolder.setImage(R.id.column_cover, mBean.getData().get(position).getCover());
        viewHolder.setText(R.id.column_total, "更新至" + mBean.getData().get(position).getTotal() + "篇");
        viewHolder.setText(R.id.column_summary, mBean.getData().get(position).getSummary());
        viewHolder.setText(R.id.column_title, mBean.getData().get(position).getTitle());
        return viewHolder.getItemView();
    }
}
