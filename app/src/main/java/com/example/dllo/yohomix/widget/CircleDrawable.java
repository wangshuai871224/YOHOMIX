package com.example.dllo.yohomix.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.PixelCopy;
import android.view.View;

/**
 * Created by dllo on 16/11/29.
 */

public class CircleDrawable extends Drawable {

    private Bitmap mBitmap;
    private int r;
    private Paint mPaint;

    public CircleDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);// 抗锯齿
        // 核心代码                                         图片重复时使用的模式

        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        // 计算出半径   取最小值

        r = Math.min(mBitmap.getHeight()/2, mBitmap.getWidth()/ 2);
    }

    @Override
    public void draw(Canvas canvas) {
        // 画圆
        canvas.drawCircle(mBitmap.getWidth() / 2 , mBitmap.getHeight() / 2 , r, mPaint);
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
    // 负责告诉Drawable它的宽和高是多少   自动压缩
    @Override
    public int getIntrinsicWidth() {
        return 2*r;
    }

    @Override
    public int getIntrinsicHeight() {
        return 2*r;
    }
}
