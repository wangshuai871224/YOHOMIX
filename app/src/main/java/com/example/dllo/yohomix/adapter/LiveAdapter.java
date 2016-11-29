package com.example.dllo.yohomix.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.LiveBean;
import com.example.dllo.yohomix.bean.VideoBean;
import com.example.dllo.yohomix.tools.CommonVH;

import static android.graphics.Color.BLACK;

/**
 * Created by dllo on 16/11/25.
 */

public class LiveAdapter extends BaseAdapter{

    private LiveBean mLiveBean;

    public void setLiveBean(LiveBean liveBean) {
        mLiveBean = liveBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mLiveBean.getData().getContent() == null ? 0 : mLiveBean.getData().getContent().size();
    }

    @Override
    public Object getItem(int i) {
        return mLiveBean.getData().getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CommonVH viewHolder = CommonVH.listViewHolder(view, viewGroup, R.layout.item_video);
        viewHolder.setImage(R.id.video_image, mLiveBean.getData().getContent().get(position).getImage());
        viewHolder.setText(R.id.video_tag_name, "# " + mLiveBean.getData().getContent().get(position).getTag().get(0).getTag_name());
        viewHolder.setText(R.id.video_time, String.valueOf(mLiveBean.getData().getContent().get(position).getCreate_time()));
        viewHolder.setText(R.id.video_title, mLiveBean.getData().getContent().get(position).getTitle());
        TextView backLook = viewHolder.getView(R.id.back_look);
        backLook.setText("回看");
        backLook.setTextColor(Color.WHITE);
        backLook.setBackgroundColor(Color.BLACK);

        viewHolder.getView(R.id.online_number).setVisibility(View.VISIBLE);
        if (mLiveBean.getData().getContent().get(position).getOnlineNum() > 0) {
            viewHolder.setText(R.id.online_number, String.valueOf(mLiveBean.getData().getContent().get(position).getOnlineNum()));
        }else {
             viewHolder.getView(R.id.online_number).setVisibility(View.GONE);
        }
            return viewHolder.getItemView();
    }
}
