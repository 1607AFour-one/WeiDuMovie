package com.bw.movie.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bw.movie.R;

public class GlideUtils {


    /**
     * 默认加载图片方法
     *
     * @param mContext
     * @param mImageUrl
     * @param mImageView
     */
    public static void setDefaultImage(Context mContext, String mImageUrl, View mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.zhong)) //加载中 默认的加载图片
                .into ((ImageView) mImageView);
    }

    public static void setDefaultImage(Context mContext, Uri mImageUrl, View mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.zhong)) //加载中 默认的加载图片
                .into ((ImageView) mImageView);
    }

    /**
     * 强制加载静态图片方法
     *
     * @param mContext
     * @param mImageUrl
     * @param mImageView
     */
    public static void setStaticImage(Context mContext, String mImageUrl, View mImageView) {

        Glide.with (mContext)

                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.zhong)) //加载中 默认的加载图片
                .apply (RequestOptions.noAnimation ())//强制加载图片格式为静态图片   默认情况下都可以加载
                .into ((ImageView) mImageView);
    }

    public static void load(Context context, ImageView imageView, String url, int defaultImage,
                            int radius) {
        //RequestOptions 设置请求参数，通过apply方法设置
        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(defaultImage)
                .error(R.mipmap.ic_launcher)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new RoundedCorners(radius));
        // 图片加载库采用Glide框架
        Glide.with(context).load(url).apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);

    }


    public static void setStaticImage(Context mContext, Uri mImageUrl, View mImageView) {

        Glide.with (mContext)
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.zhong)) //加载中 默认的加载图片
                .apply (RequestOptions.noAnimation ())//强制加载图片格式为静态图片   默认情况下都可以加载
                .into ((ImageView) mImageView);
    }

    /**
     * 强制加载动态图片方法
     *
     * @param mContext
     * @param mImageUrl
     * @param mImageView
     */
    public static void setGifImage(Context mContext, String mImageUrl, View mImageView) {

        Glide.with (mContext)
                .asGif () //强制加载动态图片
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.zhong)) //加载中 默认的加载图片
                .into ((ImageView) mImageView);
    }

    public static void setGifImage(Context mContext, Uri mImageUrl, View mImageView) {

        Glide.with (mContext)
                .asGif () //强制加载动态图片
                .load (mImageUrl)
                .apply (RequestOptions.errorOf (R.mipmap.error))        //加载失败 默认的加载图片
                .apply (RequestOptions.placeholderOf (R.mipmap.zhong)) //加载中 默认的加载图片

                .into ((ImageView) mImageView);
    }



}
