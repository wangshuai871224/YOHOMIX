package com.example.dllo.yohomix.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.CommunityContentBean;
import com.example.dllo.yohomix.tools.CommonVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/30.
 */

public class CommunityAdapter extends BaseAdapter {
    private static final int PIC_SIZE_ONE = 1;
    private static final int PIC_SIZE_TWO = 2;
    private static final int PIC_SIZE_ELSE = 3;

    private CommunityContentBean mBean;
    private List<CommunityContentBean.DataBean.ListBean> mListBeen;
    private String mOne;
    private String mOneUrl;
    private String mTwo;
    private String mTwoUrl;
    private String mThree;
    private String mThreeUrl;
    private ArrayList<String> urls;

    public void setBean(CommunityContentBean bean) {
        mBean = bean;
        mListBeen = mBean.getData().getList();
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
        urls = new ArrayList<String>();
        CommonVH viewHolder = CommonVH.listViewHolder(view, viewGroup, R.layout.item_community);
        int type = mListBeen.get(position).getBlocks().size();
//        viewHolder.setImage(R.id.head_icon, getSubstring(mListBeen.get(position).getAuthorInfo().getHeadIcon()));
        if (mListBeen.get(position).getPostsTitle() != null) {
            viewHolder.setText(R.id.post_title, mListBeen.get(position).getPostsTitle());
        }
        viewHolder.setText(R.id.community_time, String.valueOf(mListBeen.get(position).getCreateTime()));
        viewHolder.setText(R.id.comment, String.valueOf(mListBeen.get(position).getComment()));
        viewHolder.setText(R.id.praise, String.valueOf(mListBeen.get(position).getPraise()));
        viewHolder.setText(R.id.nick_name, mListBeen.get(position).getAuthorInfo().getNickName());
        viewHolder.setText(R.id.forum_name, mListBeen.get(position).getForumName());

        for (int i = 0; i < type; i++) {
            if (mListBeen.get(position).getBlocks().get(i).getTemplateKey().equals("text")){
                if (mListBeen.get(position).getBlocks().get(i).getContentData() != null) {
                    viewHolder.setText(R.id.content_data, mListBeen.get(position).getBlocks().get(i).getContentData());
                }
            }else {

                urls.add(getSubstring(mListBeen.get(position).getBlocks().get(i).getContentData()));
            }
        }
        if (urls.size() == PIC_SIZE_ONE) {
            mOneUrl = urls.get(0);
            viewHolder.setImage(R.id.community_image_one, mOneUrl);
        }else if (urls.size() == PIC_SIZE_TWO) {
            mOneUrl = urls.get(0);
            mTwoUrl = urls.get(1);
            viewHolder.setImage(R.id.community_image_one, mOneUrl);
            viewHolder.setImage(R.id.community_image_two, mTwoUrl);
        }else {
            mOneUrl = urls.get(0);
            mTwoUrl = urls.get(1);
            mThreeUrl = urls.get(2);
            viewHolder.setImage(R.id.community_image_one, mOneUrl);
            viewHolder.setImage(R.id.community_image_two, mTwoUrl);
            viewHolder.setImage(R.id.community_image_three, mThreeUrl);

            viewHolder.getView(R.id.pic_num_ll).setVisibility(View.VISIBLE);
            if (urls.size() > 3) {
                TextView textView =  viewHolder.getView(R.id.pic_num);
                textView.setText(String.valueOf(urls.size()));
            }else {
                viewHolder.getView(R.id.pic_num_ll).setVisibility(View.GONE);
            }
        }

//        if (mListBeen.get(position).getBlocks().get(0).getTemplateKey().equals("text")){
//            viewHolder.setText(R.id.content_data, mListBeen.get(position).getBlocks().get(0).getContentData());
//            if ((type - 1) == PIC_SIZE_ONE) {
//                mOne = mListBeen.get(position).getBlocks().get(1).getContentData();
//                mOneUrl = getSubstring(mOne);
//
//                viewHolder.setImage(R.id.community_image_one, mOneUrl);
//            }else if ((type - 1) == PIC_SIZE_TWO) {
//                mOne = mListBeen.get(position).getBlocks().get(1).getContentData();
//                mOneUrl = getSubstring(mOne);
//                mTwo = mListBeen.get(position).getBlocks().get(2).getContentData();
//                mTwoUrl = getSubstring(mTwo);
//
//                viewHolder.setImage(R.id.community_image_one, mOneUrl);
//                viewHolder.setImage(R.id.community_image_two, mTwoUrl);
//            }else {
//                mOne = mListBeen.get(position).getBlocks().get(1).getContentData();
//                mOneUrl = getSubstring(mOne);
//                mTwo = mListBeen.get(position).getBlocks().get(2).getContentData();
//                mTwoUrl = getSubstring(mTwo);
//                mThree = mListBeen.get(position).getBlocks().get(3).getContentData();
//                mThreeUrl = getSubstring(mThree);
//
//                viewHolder.setImage(R.id.community_image_one, mOneUrl);
//                viewHolder.setImage(R.id.community_image_two, mTwoUrl);
//                viewHolder.setImage(R.id.community_image_three, mThreeUrl);
//
//                viewHolder.getView(R.id.pic_num_ll).setVisibility(View.VISIBLE);
//                if (type - 1 > 3) {
//                    TextView textView =  viewHolder.getView(R.id.pic_num);
//                    textView.setText(String.valueOf(type - 1));
//                }else {
//                    viewHolder.getView(R.id.pic_num_ll).setVisibility(View.GONE);
//                }
//            }
//        }else {
//            viewHolder.setText(R.id.content_data, "");
//            if (type == PIC_SIZE_ONE){
//                mOne = mListBeen.get(position).getBlocks().get(0).getContentData();
//                mOneUrl = getSubstring(mOne);
//
//                viewHolder.setImage(R.id.community_image_one, mOneUrl);
//            }else if (type == PIC_SIZE_TWO) {
//                mOne = mListBeen.get(position).getBlocks().get(0).getContentData();
//                mOneUrl = getSubstring(mOne);
//                mTwo = mListBeen.get(position).getBlocks().get(1).getContentData();
//                mTwoUrl = getSubstring(mTwo);
//
//                viewHolder.setImage(R.id.community_image_one, mOneUrl);
//                viewHolder.setImage(R.id.community_image_two, mTwoUrl);
//            }else {
//                mOne = mListBeen.get(position).getBlocks().get(0).getContentData();
//                mOneUrl = getSubstring(mOne);
//                mTwo = mListBeen.get(position).getBlocks().get(1).getContentData();
//                mTwoUrl = getSubstring(mTwo);
//                mThree = mListBeen.get(position).getBlocks().get(2).getContentData();
//                mThreeUrl = getSubstring(mThree);
//
//                viewHolder.setImage(R.id.community_image_one, mOneUrl);
//                viewHolder.setImage(R.id.community_image_two, mTwoUrl);
//                viewHolder.setImage(R.id.community_image_three, mThreeUrl);
//
//                viewHolder.getView(R.id.pic_num_ll).setVisibility(View.VISIBLE);
//                if (type > 3) {
//                    TextView textView =  viewHolder.getView(R.id.pic_num);
//                    textView.setText(String.valueOf(type));
//                }else {
//                    viewHolder.getView(R.id.pic_num_ll).setVisibility(View.GONE);
//                }
//            }
//        }

        return viewHolder.getItemView();
    }

    public String getSubstring(String url ) {
        String a = url.substring(0, url.indexOf("?"));
        return a;
    }
}
