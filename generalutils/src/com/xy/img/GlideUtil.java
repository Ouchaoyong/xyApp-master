package com.xy.img;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.xy.debug.LogUtils;


/**
 * 下载图片的工具类 为了统一用法固定参数
 */
public class GlideUtil {
    private static final String TAG = "GlideUtil";

    //圆角ImageView用
    public static void loadImageR(String url, ImageView imageView, int srcId) {
        loadImage(url, imageView, false, srcId, null);
    }

    public static void loadImageKeepSize(String url, ImageView imageView) {
        loadImageKeepSize(url, imageView, false, 0, null);
    }


    public static void loadImageKeepSize(String url, ImageView imageView, boolean anim, int id, RequestListener<String, GlideDrawable> requestListener) {
        loadImage(url, imageView, anim, 0, 0, id, requestListener);
    }

    public static void loadImage(Activity activity, String url, int id) {
        View v = activity.findViewById(id);
        if (v == null) {
            return;
        }
        loadImage(url, (ImageView) v);
    }


    public static void loadImage(View viewGroup, String url, int id) {
        View v = viewGroup.findViewById(id);
        if (v == null) {
            return;
        }
        loadImage(url, (ImageView) v);
    }

    public static void loadImage(View viewGroup, String url, int id, int srcId) {
        View v = viewGroup.findViewById(id);
        if (v == null) {
            return;
        }
        loadImage(url, (ImageView) v, true, srcId, null);
    }

    public static void loadImageNoAnim(String url, ImageView imageView) {
        loadImage(url, imageView, false, 0, null);
    }

    public static void loadImage(String url, ImageView imageView) {
        loadImage(url, imageView, true, 0, null);
    }

    public static void loadImage(String url, ImageView imageView, int srcId) {
        loadImage(url, imageView, true, srcId, null);
    }

    public static void loadImage(String url, ImageView imageView, RequestListener<String, GlideDrawable> requestListener) {
        loadImage(url, imageView, true, 0, requestListener);
    }


    public static void loadImage(String url, ImageView imageView, boolean anim, int srcId, RequestListener<String, GlideDrawable> requestListener) {
        try {
            Context context = imageView.getContext();
            DrawableRequestBuilder builder = Glide.with(context).load(url).listener(requestListener);
            if (anim) {
                builder.crossFade();
            } else {
                builder.dontAnimate();
            }
            if (srcId != 0) {
                builder.placeholder(srcId);
            }
            builder.into(imageView);
        } catch (Exception e) {

        }
    }

    public static void loadImage(String url, ImageView imageView, boolean anim, int imageWidth, int imageHeight, int srcId, RequestListener<String, GlideDrawable> requestListener) {
        LogUtils.i(TAG, "image width:" + imageWidth + "----------image height:" + imageHeight);
        if (imageWidth <= 0 || imageHeight <= 0) {
            imageWidth = imageHeight = Target.SIZE_ORIGINAL;
        }
        try {
            Context context = imageView.getContext();
            DrawableRequestBuilder builder = Glide.with(context).load(url).override(imageWidth, imageHeight).listener(requestListener);
            if (anim) {
                builder.crossFade();
            } else {
                builder.dontAnimate();
            }
            if (srcId != 0) {
                builder.placeholder(srcId);
            }
            builder.into(imageView);
        } catch (Exception e) {

        }
    }

    public static void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    public static void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

}
