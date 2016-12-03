package com.example.dllo.yohomix.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.SearchBean;
import com.example.dllo.yohomix.tools.CommonVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/3.
 */

public class ReuseAdapter extends BaseAdapter{

    private SearchBean mBean;
    private List<SearchBean.DataBean.ContentBean> beans;

    public void setBean(SearchBean bean) {
        mBean = bean;
        beans = mBean.getData().getContent();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
                                // 对象.方法
        CommonVH viewHolder = CommonVH.listViewHolder(view, viewGroup, R.layout.item_search);
        viewHolder.setText(R.id.search_item_title, beans.get(i).getTitle());
        viewHolder.setText(R.id.search_item_tag_name, "# " + beans.get(i).getTag().get(0).getTag_name());
        viewHolder.setText(R.id.search_item_create_time, String.valueOf(beans.get(i).getCreate_time()));
        viewHolder.setImage(R.id.search_item_image, beans.get(i).getImage());

        viewHolder.getView(R.id.all_image_num).setVisibility(View.VISIBLE);
        if (beans.get(i).getImgNum() > 0){
            viewHolder.setText(R.id.search_item_img_num, String.valueOf(beans.get(i).getImgNum()));
        }else {
            viewHolder.getView(R.id.all_image_num).setVisibility(View.GONE);
        }
        return viewHolder.getItemView();
    }
}
