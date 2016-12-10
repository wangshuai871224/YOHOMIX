package com.example.dllo.yohomix.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.example.dllo.yohomix.R;

/**
 * Created by dllo on 16/12/7.
 */

public class LoadFootView extends ImageView implements SwipeTrigger, SwipeLoadMoreTrigger{
    public LoadFootView(Context context) {
        super(context);
    }

    public LoadFootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        this.setImageResource(R.drawable.anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b) {
            if (i >= getHeight()) {
                setImageResource(R.mipmap.loading00001);
            }else {

            }
        }else {
            setImageResource(R.mipmap.loading00022);
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {

    }
}
