package com.alick.mvvmlearn.bindingadapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;

import com.alick.commonlibrary.utils.DensityUtils;
import com.alick.mvvmlearn.R;
import com.alick.mvvmlearn.glide.GlideCircleTransform;
import com.alick.mvvmlearn.glide.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * 功能:
 * 作者: 崔兴旺
 * 日期: 2018/11/10
 * 备注:
 */
public class GlideBindingAdapter {
    private static final float default_round =2;       //默认矩形圆角角度,单位:dp
    private static final float default_border_width=2; //默认圆形边框粗度,单位dp

    /**
     * 加载矩形圆角图片(用Glide)
     *
     * @param imageView
     * @param url
     */
    @BindingAdapter("roundImageUrl")
    public static void setRoundImageUrl(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .transform(new GlideRoundTransform(DensityUtils.dp2px(imageView.getContext(), default_round)))
                    .placeholder(R.drawable.default_avatar)
                    .error(R.drawable.default_avatar)
                    .priority(Priority.HIGH)
                    //.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(options)
                    .thumbnail(Glide.with(imageView.getContext()).load(url).apply(options))
                    .into(imageView);
        }else {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.drawable.ic_launcher);
        }
    }

    /**
     * 加载圆形图片(用Glide)
     * @param imageView         图片对象
     * @param circle_image_url  图片url
     */
    @BindingAdapter({"circle_image_url"})
    public static void setCircleImageUrl(ImageView imageView, String circle_image_url) {
        setCircleImageUrl(imageView,circle_image_url,0);
    }

    /**
     * 加载圆形图片(用Glide)
     *
     * @param imageView         图片对象
     * @param circle_image_url  图片url
     * @param border_width      边框粗度
     */
    @BindingAdapter({"circle_image_url","border_width"})
    public static void setCircleImageUrl(ImageView imageView, String circle_image_url,float border_width) {
        if (!TextUtils.isEmpty(circle_image_url)) {
            Context context = imageView.getContext();
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .transform(new GlideCircleTransform(border_width, Color.WHITE))
                    .placeholder(R.drawable.touxiang_default_circle)
                    .error(R.drawable.touxiang_default_circle)
                    .priority(Priority.HIGH)
                    //.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

            Glide.with(context)
                    .load(circle_image_url)
                    .apply(options)
                    .thumbnail(Glide.with(context).load(circle_image_url).apply(options))
                    .into(imageView);
        }else {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .transform(new GlideCircleTransform(border_width, Color.WHITE));

            Glide.with(imageView.getContext())
                    .load(R.drawable.touxiang_default_circle)
                    .apply(options)
                    .into(imageView);
        }
    }




}
