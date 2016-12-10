package com.example.dllo.yohomix.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.SearchItemBean;
import com.example.dllo.yohomix.tools.CommonVH;

import java.util.List;

/**
 * Created by dllo on 16/12/8.
 */
public class GridViewAdapter extends BaseAdapter{

    private List<SearchItemBean> mList;

    public void setList(List<SearchItemBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup, R.layout.item_my_magazine);
        commonVH.setImage(R.id.my_magazine_item_image, mList.get(i).getUrl());
        commonVH.setText(R.id.my_magazine_item_text, mList.get(i).getBody());

        return commonVH.getItemView();
    }
}
