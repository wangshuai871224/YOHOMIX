package com.example.dllo.yohomix.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.bean.RecommendBean;
import com.example.dllo.yohomix.tools.CommonVH;
import com.example.dllo.yohomix.tools.URLValues;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by dllo on 16/11/23.
 */

public class RecommendListAdapter extends BaseAdapter{

    private Context mContext;
    private RecommendBean mBean;
    private List<RecommendBean.DataBean.ParamsBean> mBean1;


    public void setBean(RecommendBean bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    public RecommendListAdapter(Context mContext) {
        this.mContext = mContext;
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
    public int getItemViewType(int position) {

        return mBean.getData().get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 20;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CommonVH viewHolder = null;

        int type = getItemViewType(position);
        switch (type) {
            // 获取三个图片的数据
            case URLValues.RECOMMEND_TYPE_ZERO:
                viewHolder = CommonVH.listViewHolder(view, viewGroup, R.layout.item_list);
                mBean1 = mBean.getData().get(position).getParams();

                Date dateOne = new Date(Long.valueOf(mBean1.get(1).getCreate_time()));
                String timeOne = new SimpleDateFormat("MM.dd.yyyy").format(dateOne);
                Date dateTwo = new Date(Long.valueOf(mBean1.get(2).getCreate_time()));
                String timeTwo = new SimpleDateFormat("MM.dd.yyyy").format(dateTwo);
                Date dateZero = new Date(Long.valueOf(mBean1.get(0).getCreate_time()));
                String timeZero = new SimpleDateFormat("MM.dd.yyyy").format(dateZero);

                viewHolder.setImage(R.id.picture_zero, mBean1.get(0).getImage());
                viewHolder.setText(R.id.zero_title, mBean1.get(0).getTitle() + mBean1.get(0).getSubtitle());
                viewHolder.setText(R.id.zero_create_time,  timeZero);
                viewHolder.setText(R.id.zero_tag_name, "# " + mBean1.get(0).getTag().get(0).getTag_name());

                viewHolder.getView(R.id.zero_box).setVisibility(View.VISIBLE);
                // listView 复用, 每当设置GONE的时候, 下回用就是GONE, 所以每次用的时候必须让它显示(VISIBLE)
                if (mBean1.get(0).getImgNum() > 0 ) {
                    viewHolder.setText(R.id.zero_context_type, String.valueOf(mBean1.get(0).getImgNum()));
                }else {
                    viewHolder.getView(R.id.zero_box).setVisibility(View.GONE);
                }

                viewHolder.setImage(R.id.picture_one, mBean1.get(1).getImage());
                viewHolder.setText(R.id.one_title, mBean1.get(1).getTitle() + mBean1.get(1).getSubtitle());
                viewHolder.setText(R.id.one_create_time,  timeOne);
                viewHolder.setText(R.id.one_tag_name, "# " + mBean1.get(1).getTag().get(0).getTag_name());

                viewHolder.getView(R.id.one_box).setVisibility(View.VISIBLE);
                if (mBean1.get(1).getImgNum() > 0 ) {
                    viewHolder.setText(R.id.one_context_type, String.valueOf(mBean1.get(1).getImgNum()));
                } else {
                    viewHolder.getView(R.id.one_box).setVisibility(View.GONE);
                }

                viewHolder.setImage(R.id.picture_two, mBean1.get(2).getImage());
                viewHolder.setText(R.id.two_title, mBean1.get(2).getTitle() + mBean1.get(2).getSubtitle());
                viewHolder.setText(R.id.two_create_time,  timeTwo);
                viewHolder.setText(R.id.two_tag_name, "# " + mBean1.get(2).getTag().get(0).getTag_name());

                viewHolder.getView(R.id.two_box).setVisibility(View.VISIBLE);
                if (mBean1.get(2).getImgNum() > 0 ) {
                    viewHolder.setText(R.id.two_context_type, String.valueOf(mBean1.get(2).getImgNum()));
                }else {
                    viewHolder.getView(R.id.two_box).setVisibility(View.GONE);
                }

                break;

            // 获取一张图片(轮播图中的)的数据
            case URLValues.RECOMMEND_TYPE_TWO:
                viewHolder = CommonVH.listViewHolder(view, viewGroup, R.layout.item_banner_picture);
                mBean1 = mBean.getData().get(position).getParams();

                Date date = new Date(Long.valueOf(mBean1.get(0).getCreate_time()));
                String time = new SimpleDateFormat("MM.dd.yyyy").format(date);

                if (mBean1 != null){
                    viewHolder.setImage(R.id.banner_image, mBean1.get(0).getImage());
                    viewHolder.setText(R.id.banner_title, mBean1.get(0).getTitle() + mBean1.get(0).getSubtitle());
                    viewHolder.setText(R.id.banner_tag_name, "# " + mBean1.get(0).getTag().get(0).getTag_name());
                    viewHolder.setText(R.id.banner_create_time, mBean1.get(0).getCreate_time());
                }

                break;
//            case URLValues.RECOMMEND_TYPE_THREE:
//                viewHolder = CommonVH.listViewHolder(view, viewGroup,R.layout.item_magazine);
//
//                break;
            default:
                viewHolder = CommonVH.listViewHolder(view, viewGroup,R.layout.item_magazine);
                break;


        }
        return viewHolder.getItemView();
    }

}
