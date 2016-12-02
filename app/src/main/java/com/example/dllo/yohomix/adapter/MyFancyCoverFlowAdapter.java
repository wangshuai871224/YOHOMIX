package com.example.dllo.yohomix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.dalong.francyconverflow.FancyCoverFlowAdapter;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.CommunityCoverBean;
import com.example.dllo.yohomix.tools.StaticMethod;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/12/1.
 */

public class MyFancyCoverFlowAdapter extends FancyCoverFlowAdapter{

    private CommunityCoverBean mBean;

    public void setBean(CommunityCoverBean bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    @Override
    public View getCoverFlowItem(int position, View reusableView, ViewGroup parent) {
        Context context = parent.getContext();
        ViewHolder viewHolder;
        if (reusableView == null) {
            reusableView = LayoutInflater.from(context).inflate(R.layout.item_head_hl, parent, false);
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth()+600;
            reusableView.setLayoutParams(new FancyCoverFlow.LayoutParams(width * 2 / 5 ,FancyCoverFlow.LayoutParams.WRAP_CONTENT));
            viewHolder = new ViewHolder(reusableView);
            reusableView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) reusableView.getTag();
        }

        if (position == 0){
            viewHolder.imageBack.setBackgroundResource(R.mipmap.yohocommid1);
        }else if (position == mBean.getData().getForumInfo().size() + 1){
            viewHolder.imageBack.setBackgroundResource(R.mipmap.yohocommid1);
        }else {
            Glide.with(context).load(mBean.getData().getForumInfo().get(position - 1).getForumPic()).into(viewHolder.forumPic);
            Glide.with(context).load(StaticMethod.getSubstring(mBean.getData().getForumInfo().get(position - 1).getHotPost().getUser().getHeadIcon())).bitmapTransform(new CropCircleTransformation(context)).into(viewHolder.hotHeadIcon);
            Glide.with(context).load(StaticMethod.getSubstring(mBean.getData().getForumInfo().get(position - 1).getNewPost().getUser().getHeadIcon())).bitmapTransform(new CropCircleTransformation(context)).into(viewHolder.newHeadIcon);
            viewHolder.forumName.setText(mBean.getData().getForumInfo().get(position - 1).getForumName());
            viewHolder.postNum.setText("帖子 " + String.valueOf(mBean.getData().getForumInfo().get(position - 1).getPostsNum()));
            viewHolder.commentNum.setText("| 回复 " + String.valueOf(mBean.getData().getForumInfo().get(position - 1).getCommentsNum()));
            viewHolder.praiseNum.setText("| 赞" + String.valueOf(mBean.getData().getForumInfo().get(position - 1).getPraiseNum()));
            viewHolder.hotPostTitle.setText(mBean.getData().getForumInfo().get(position - 1).getHotPost().getPostsTitle());
            viewHolder.newPostTitle.setText(mBean.getData().getForumInfo().get(position - 1).getNewPost().getPostsTitle());
            viewHolder.addNum.setText(String.valueOf(mBean.getData().getForumInfo().get(position - 1).getOneDayAddNum() + "条更新> "));
        }
            return reusableView;
    }

    @Override
    public int getCount() {
        return mBean.getData().getForumInfo().size()+2;
    }

    @Override
    public Object getItem(int i) {
        return mBean.getData().getForumInfo().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{

        private ImageView forumPic, hotHeadIcon, newHeadIcon;
        private TextView commentNum, postNum, praiseNum, hotPostTitle, newPostTitle, addNum, forumName;
        private LinearLayout imageBack;
        public ViewHolder(View reusableView) {
            forumName = (TextView) reusableView.findViewById(R.id.forum_name_item);
            commentNum = (TextView) reusableView.findViewById(R.id.comment_num);
            postNum = (TextView) reusableView.findViewById(R.id.post_num);
            praiseNum = (TextView) reusableView.findViewById(R.id.praise_num);
            hotPostTitle = (TextView) reusableView.findViewById(R.id.hot_post_title);
            newPostTitle = (TextView) reusableView.findViewById(R.id.new_post_title);
            addNum = (TextView) reusableView.findViewById(R.id.add_num);

            forumPic = (ImageView) reusableView.findViewById(R.id.forum_pic);
            hotHeadIcon = (ImageView) reusableView.findViewById(R.id.hot_head_icon);
            newHeadIcon = (ImageView) reusableView.findViewById(R.id.new_head_icon);
            imageBack = (LinearLayout) reusableView.findViewById(R.id.image_ll);

        }
    }
}
