package com.example.dllo.yohomix.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.ColumnTwoBean;
import com.example.dllo.yohomix.tools.CommonVH;
import com.example.dllo.yohomix.tools.TimeInstead;

import java.sql.Time;

/**
 * Created by dllo on 16/12/9.
 */

public class ColumnTwoAdapter extends BaseAdapter{

    private ColumnTwoBean mBean;

    public void setBean(ColumnTwoBean bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBean == null ? 0 : mBean.getData().getContent().size();
    }

    @Override
    public Object getItem(int i) {
        return mBean.getData().getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup , R.layout.item_column_two_list);
        String time = TimeInstead.timeString(mBean.getData().getContent().get(i).getCreate_time());
        commonVH.setText(R.id.column_two_time, time);
        commonVH.setText(R.id.column_two_tag, "#" + mBean.getData().getContent().get(i).getTag().get(0).getTag_name());
        commonVH.setText(R.id.column_two_item_title, mBean.getData().getContent().get(i).getTitle());

        commonVH.setImage(R.id.column_two_image, mBean.getData().getContent().get(i).getImage());
        return commonVH.getItemView();
    }
}
