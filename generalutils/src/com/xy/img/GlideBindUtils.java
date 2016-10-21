package com.xy.img;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.xy.debug.LogUtils;


/**
 * Created by John on 2016/10/13.
 */

public class GlideBindUtils {
    private static final String TAG = "GlideBindUtil";


    //圆角ImageView用
    @BindingAdapter({"imageUrl","srcDrawable"})
    public static void loadImageR(ImageView imageView, String imageUrl, Drawable srcDrawable) {
        loadImage(imageView,imageUrl,false,srcDrawable,null);
    }

    @BindingAdapter({"KeepSizeimageUrl"})
    public static void loadImageKeepSize(ImageView imageView, String KeepSizeimageUrl) {
        loadImageKeepSize(imageView, KeepSizeimageUrl, false, null, null);
    }

    @BindingAdapter({"KeepSizeimageUrl","anim","drawableRes","requestListener"})
    public static void loadImageKeepSize(ImageView imageView, String KeepSizeimageUrl, boolean anim, Drawable drawableRes, RequestListener<String, GlideDrawable> requestListener) {
        loadImage(imageView, KeepSizeimageUrl, anim, 0, 0, drawableRes, requestListener);
    }

    public static void loadImage(Activity activity, String url, int id) {
        View v = activity.findViewById(id);
        if (v == null) {
            return;
        }
        loadImage((ImageView) v, url);
    }


    public static void loadImage(View viewGroup, String url, int id) {
        View v = viewGroup.findViewById(id);
        if (v == null) {
            return;
        }
        loadImage((ImageView) v, url);
    }

    public static void loadImage(View viewGroup, String url, int id, Drawable srcDrawable) {
        View v = viewGroup.findViewById(id);
        if (v == null) {
            return;
        }
        loadImage((ImageView) v, url, true, srcDrawable, null);
    }
    @BindingAdapter({"noAnimalImageUrl"})
    public static void loadImageNoAnim(ImageView imageView, String noAnimalImageUrl) {
        loadImage(imageView, noAnimalImageUrl, false, null, null);
    }
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        loadImage(imageView, url, true, null, null);
    }
    @BindingAdapter({"imageUrl","srcDrawable"})
    public static void loadImage(ImageView imageView, String url, Drawable srcDrawable) {
        loadImage(imageView, url, true, srcDrawable, null);
    }
    @BindingAdapter({"imageUrl","requestListener"})
    public static void loadImage(ImageView imageView, String imageUrl, RequestListener<String, GlideDrawable> requestListener) {
        loadImage(imageView, imageUrl, true, null, requestListener);
    }
    @BindingAdapter({"imageUrl","anim","srcDrawable","requestListener"})
    public static void loadImage(ImageView imageView, String imageUrl, boolean anim, Drawable srcDrawable, RequestListener<String, GlideDrawable> requestListener) {
        try {
            Context context = imageView.getContext();
            DrawableRequestBuilder builder = Glide.with(context).load(imageUrl).listener(requestListener);
            if (anim) {
                builder.crossFade();
            } else {
                builder.dontAnimate();
            }
            if (srcDrawable != null) {
                builder.placeholder(srcDrawable);
            }
            builder.into(imageView);
        } catch (Exception e) {

        }
    }
    @BindingAdapter({"imageUrl","anim","drawableRes","imageWidth","imageHeight","requestListener"})
    public static void loadImage(ImageView imageView, String imageUrl, boolean anim, int imageWidth, int imageHeight, Drawable drawableRes, RequestListener<String, GlideDrawable> requestListener) {
        LogUtils.i(TAG, "image width:" + imageWidth + "----------image height:" + imageHeight);
        if (imageWidth <= 0 || imageHeight <= 0) {
            imageWidth = imageHeight = Target.SIZE_ORIGINAL;
        }
        try {
            Context context = imageView.getContext();
            DrawableRequestBuilder builder = Glide.with(context).load(imageUrl).override(imageWidth, imageHeight).listener(requestListener);
            if (anim) {
                builder.crossFade();
            } else {
                builder.dontAnimate();
            }
            if (drawableRes != null) {
                builder.placeholder(drawableRes);
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
