package com.example.dllo.yohomix.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.yohomix.widget.CircleDrawable;
import com.squareup.picasso.Picasso;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/11/24.
 */

public class CommonVH extends RecyclerView.ViewHolder{
    private View mView;
    private SparseArray<View> views;// 存放视图的数组, 类似map,  key值固定int类型
    private Context mContext;

    private CommonVH(View itemView,Context context) {
        super(itemView);
        mView = itemView;
        mContext = context;
        views = new SparseArray<>();
    }

    public <T extends View> T getView(int layoutId) {
        View view = views.get(layoutId);
        if (view == null) {
            view = mView.findViewById(layoutId);
            views.put(layoutId, view);
        }
        return (T) view;
    }

    // 对外提供静态方法
    public static CommonVH createViewHolder(Context context, ViewGroup parent, int id) {
        View itemView = LayoutInflater.from(context).inflate(id,parent,false);
        CommonVH viewHolder = new CommonVH(itemView, context);
        return viewHolder;
    }

    // listView的holder
    public static CommonVH listViewHolder(View itemView, ViewGroup parent, int layoutId) {
        CommonVH commonVH = null;
        if (itemView == null) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            commonVH = new CommonVH(itemView,parent.getContext());
            itemView.setTag(commonVH);
        }else {
            commonVH = (CommonVH) itemView.getTag();
        }
        return commonVH;
    }

    public CommonVH setText(int id, String s){
        TextView textView = getView(id);
        if (s != null){
            textView.setText(s);
        }
        return this;
    }

    public CommonVH setImage(int id, String s){
        ImageView imageView = getView(id);
        if (s != null){
            Picasso.with(mContext).load(s).into(imageView);
        }
        return this;
    }

    public CommonVH setGlideImage(int id, String s){
        ImageView imageView = getView(id);
        if (s != null){
            Glide.with(mContext).load(s).bitmapTransform(new CropCircleTransformation(mContext)).into(imageView);
        }
        return this;
    }



    public View getItemView() {
        return mView;
    }

    public CommonVH setCircleBitmap(int id, Bitmap bitmap) {
        ImageView imageView = getView(id);
        CircleDrawable circleDrawable = new CircleDrawable(bitmap);
        imageView.setImageDrawable(circleDrawable);
        return this;
    }

}
