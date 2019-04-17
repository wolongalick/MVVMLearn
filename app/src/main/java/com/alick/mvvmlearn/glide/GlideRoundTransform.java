package com.alick.mvvmlearn.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * desc:
 * 2018/9/19
 * mc
 */
public class GlideRoundTransform extends BitmapTransformation {
    private float radius = 0f;

    public GlideRoundTransform() {
        super();
    }

    public GlideRoundTransform(int px) {
        super();
        this.radius = px;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
         Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
        return roundCrop(pool, bitmap);
    }

//    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
//        if (source == null) return null;
//
//        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//        if (result == null) {
//            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//        }
//
//        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
//        paint.setAntiAlias(true);
//        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
//        canvas.drawRoundRect(rectF, radius, radius, paint);
//        return result;
//    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
