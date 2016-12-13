package com.example.dllo.yohomix.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.CollectWebBean;
import com.example.dllo.yohomix.tools.CommonDBTool;
import com.example.dllo.yohomix.tools.CommonVH;
import com.example.dllo.yohomix.tools.TimeInstead;

import java.util.List;

/**
 * Created by dllo on 16/12/9.
 */
public class CollectionAdapter extends BaseAdapter{

    private boolean flag = false;

    private List<CollectWebBean> mBean;

    @Override
    public int getCount() {
        return mBean == null ? 0 : mBean.size();
    }

    @Override
    public Object getItem(int i) {
        return mBean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup, R.layout.item_my_collect);

        commonVH.getView(R.id.collect_play).setVisibility(View.GONE);
        if ( 1 != mBean.get(i).getType()) {
            commonVH.getView(R.id.collect_play).setVisibility(View.VISIBLE);
        }else {
            commonVH.getView(R.id.collect_play).setVisibility(View.GONE);
        }

        String time = TimeInstead.timeString(mBean.get(i).getTime());
        commonVH.setImage(R.id.my_collect_image, mBean.get(i).getImgUrl());
        commonVH.setText(R.id.collect_title, mBean.get(i).getTitle());
        commonVH.setText(R.id.collect_create_time, time);
        commonVH.setText(R.id.collect_tag_name, mBean.get(i).getTagName());

        if (flag) {
            commonVH.getView(R.id.delete_background_image).setBackgroundColor(R.color.alpha);
            commonVH.getView(R.id.delete_collect).setVisibility(View.VISIBLE);
        }else {
            commonVH.getView(R.id.delete_background_image).setBackgroundColor(Color.TRANSPARENT);
            commonVH.getView(R.id.delete_collect).setVisibility(View.GONE);
        }

        commonVH.getView(R.id.delete_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonDBTool.getInstance().deleteByTitle(mBean.get(i).getTitle());
                mBean.remove(i);
                notifyDataSetChanged();
            }
        });

        return commonVH.getItemView();
    }

    public void setBean(List<CollectWebBean> bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    public void showX(boolean show) {
        this.flag = show;
        notifyDataSetChanged();
    }
}
