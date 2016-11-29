package com.example.dllo.yohomix.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.WallPaperBean;
import com.example.dllo.yohomix.tools.CommonVH;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/26.
 */

public class WallPaperAdapter extends BaseAdapter{
    private WallPaperBean mBean;
    private List<WallPaperBean.DataBean.WallpaperListBean> mListBeen;

    public void setBean(WallPaperBean bean) {
        mBean = bean;
        mListBeen = mBean.getData().getWallpaperList();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListBeen == null ? 0 : mListBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return mListBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CommonVH viewHolder = CommonVH.listViewHolder(view, viewGroup, R.layout.item_wall_paper);
        viewHolder.setText(R.id.wall_journal, mListBeen.get(position).getJournal());
        viewHolder.setText(R.id.wall_month, mListBeen.get(position).getMonth());

        viewHolder.setImage(R.id.wall_picture_one,mListBeen.get(position).getImages().get(0).getThumbImage());
        viewHolder.setImage(R.id.wall_picture_two,mListBeen.get(position).getImages().get(1).getThumbImage());
        viewHolder.setImage(R.id.wall_picture_three,mListBeen.get(position).getImages().get(2).getThumbImage());
        viewHolder.setImage(R.id.wall_picture_four,mListBeen.get(position).getImages().get(3).getThumbImage());

        return viewHolder.getItemView();
    }
}
