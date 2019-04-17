package com.alick.mvvmlearn.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class GlideCircleTransform extends BitmapTransformation {
    private Paint strokePaint;
    private float borderWidth;
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);

    public GlideCircleTransform() {

    }

    public GlideCircleTransform(float borderWidth,int borderCorlor) {
        if(borderWidth>0){
            this.borderWidth=borderWidth;
            strokePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
            strokePaint.setColor(borderCorlor);
            strokePaint.setStrokeWidth(borderWidth);
            strokePaint.setStyle(Paint.Style.STROKE);
        }
    }

    @Override
    public Bitmap transform(BitmapPool pool, Bitmap toTransform,
                            int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        // TODO this could be acquired from the pool too
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        if(strokePaint!=null){
            canvas.drawCircle(r, r, r-borderWidth/2, strokePaint);
        }

        return result;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}  
