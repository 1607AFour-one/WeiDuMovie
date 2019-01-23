package com.bw.movie.utils;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
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
