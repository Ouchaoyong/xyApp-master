package com.xy.debug;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by John on 2016/10/19.
 */

public class SystemConfig {

    //返回版本号
    public static int getAppVersionCode(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return  1;
        }
    }

    //返回版本名称
    public static String getAppVersionName(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return  "";
        }
    }

    //获取屏幕高度
    public static int getScreenHeight(Context context){
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
    //获取屏幕尺寸
    public static int[] getScreenSize(Context context) {
        Display mDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        mDisplay.getMetrics(metrics);
        int[] size = new int[2];
        size[0] = metrics.widthPixels;
        size[1] = metrics.heightPixels;
        return size;
    }

    //dpTopx
    public static int dp2px(Context context,int dp){
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(density * dp + 0.5f);
    }

    public static int px2dp(Context context,int px){
        float density = context.getResources().getDisplayMetrics().density;
        return (int)( px / density + 0.5f);
    }

}
