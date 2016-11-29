package com.example.dllo.yohomix.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.VideoBean;
import com.example.dllo.yohomix.tools.CommonVH;

/**
 * Created by dllo on 16/11/25.
 */

public class VideoVideoAdapter extends BaseAdapter {

    private VideoBean mVideoBean;

    public void setVideoBean(VideoBean videoBean) {
        mVideoBean = videoBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mVideoBean.getData().getContent() == null ? 0 : mVideoBean.getData().getContent().size();
    }

    @Override
    public Object getItem(int i) {
        return mVideoBean.getData().getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CommonVH viewHolder = CommonVH.listViewHolder(view, viewGroup, R.layout.item_video);
        VideoBean.DataBean.ContentBean  bean = mVideoBean.getData().getContent().get(position);

//        viewHolder.setTextLeftDrawable(R.id.online_number,);
        viewHolder.setImage(R.id.video_image, bean.getImage());
        viewHolder.setText(R.id.video_tag_name, "# " + bean.getTag().get(0).getTag_name());
        viewHolder.setText(R.id.video_time, String.valueOf(bean.getCreate_time()));
        viewHolder.setText(R.id.video_title, bean.getTitle());
        viewHolder.getView(R.id.online_number).setVisibility(View.GONE);
        return viewHolder.getItemView();
    }
}
