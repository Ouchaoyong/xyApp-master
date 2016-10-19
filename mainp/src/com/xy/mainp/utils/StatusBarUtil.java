package com.xy.mainp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ocy
 * @time 2016/9/7 21:30
 * @des 状态栏工具类
 *
 */
public class StatusBarUtil {

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context){
        //通过系统资源文件R参数调用  android.R.dimen.status_bar_height
        int result = 0;
        int resourseId = context.getResources().getIdentifier("status_bar_height","dimen","android");
        if(resourseId > 0) {
            result = context.getResources().getDimensionPixelOffset(resourseId);
        }
        return result;
    }

    /**
     * 设置沉浸式状态栏
     */
    public static final String FULL_SCREEN  = "full_screen";  //全屏模式
    public static final String COLOR_STATUS_BAR  = "color_status_bar";  //着色模式
    private static final int UNSET_COLOR = -1;
    public static void setImmersiveStatusBar(Activity activity){
        setImmersiveStatusBar(activity,0x66000000,COLOR_STATUS_BAR);  //默认着色模式
    }

    public static void setImmersiveStatusBar(Activity activity,int color){  //设置状态栏颜色
        setImmersiveStatusBar(activity,color,COLOR_STATUS_BAR);  //默认着色模式
    }

    public static void setImmersiveStatusBar(Activity activity,String mode){  //设置状态栏颜色
        setImmersiveStatusBar(activity,UNSET_COLOR,mode);
    }

    public static void setImmersiveStatusBar(Activity activity,int color, String Mode){
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup)activity.findViewById(Window.ID_ANDROID_CONTENT);
        if("full_screen".equalsIgnoreCase(Mode)) {
            setFullScreen( window,mContentView,color);
        }else if("color_status_bar".equalsIgnoreCase(Mode)) {
            setColorStatusBar(window,mContentView,color);
        }

    }

    private static boolean hasDarkMode = true;

    /**
     * 改变状态栏字体颜色  暂时只支持安卓6.0以及小米魅族4.4以上
     *
     * @return
     */
    public static void setStatusBarLightMode(Activity activity, boolean darkmode) {
        Window window = activity.getWindow();
        if (hasDarkMode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && OSUtils.getOSName().equals(OSUtils.TYPE_NONE)) {
                //6.0通过此代码改为亮色背景的深色字体
                window.getDecorView().setSystemUiVisibility(darkmode ? View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                return;
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && OSUtils.getOSName().equals(OSUtils.TYPE_FLYME)) {
                if (window != null) {
                    try {
                        WindowManager.LayoutParams lp = window.getAttributes();
                        Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                        Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                        darkFlag.setAccessible(true);
                        meizuFlags.setAccessible(true);
                        int bit = darkFlag.getInt(null);
                        int value = meizuFlags.getInt(lp);
                        if (darkmode) {
                            value |= bit;
                        } else {
                            value &= ~bit;
                        }
                        meizuFlags.setInt(lp, value);
                        window.setAttributes(lp);
                        return;
                    } catch (Exception e) {
                        hasDarkMode = false;
                    }
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && OSUtils.getOSName().equals(OSUtils.TYPE_MIUI)) {
                if (window != null) {
                    Class clazz = window.getClass();
                    try {
                        int darkModeFlag = 0;
                        Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                        Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                        darkModeFlag = field.getInt(layoutParams);
                        Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                        extraFlagField.invoke(window, darkmode ? darkModeFlag : 0, darkModeFlag);
                        return;
                    } catch (Exception e) {
                        hasDarkMode = false;
                    }
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !OSUtils.getOSName().equals(OSUtils.TYPE_EMUI)) {
            window.setStatusBarColor(darkmode ? 0x33000000 : 0x00000000);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup viewGroup = (ViewGroup) window.getDecorView();
            if (darkmode) {
                View view = new View(activity);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity)));
                view.setBackgroundColor(0x33000000);
                view.setTag("StatusBarColor");
                viewGroup.addView(view);
            } else {
                View view = viewGroup.findViewWithTag("StatusBarColor");
                viewGroup.removeView(view);
            }
        }
    }

    /**
     * 4.5 可以直接调用API设置状态栏颜色
     * 4.4-5.0因为没有直接的 API 可以调用,需要自己兼容处理,
     * 网上的解决方法基本都是创建一下高度为状态栏的 View ,通过设置这个 View 的背景色来模拟状态栏.
     *
     *
     * @param window
     * @param mContentView
     * @param color
     */

    private static void setColorStatusBar(Window window, ViewGroup mContentView,int color) {
        View mChlidView = mContentView.getChildAt(0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(color == UNSET_COLOR) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                color = Color.TRANSPARENT;
            }else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);  //设置状态栏颜色
            if(mChlidView != null) {
                ViewCompat.setFitsSystemWindows(mChlidView,true); //为系统View留出空间
            }
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View mChildView = mContentView.getChildAt(0);
            int statusBarHeight = getStatusBarHeight(window.getContext());
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
                if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                    //不预留系统空间
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                    lp.topMargin += statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
            }

            View statusBarView = mContentView.getChildAt(0);
            if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
                //避免重复调用时多次添加 View
                statusBarView.setBackgroundColor(color);
                return;
            }
            statusBarView = new View(window.getContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            statusBarView.setBackgroundColor(color);
            //向 ContentView 中添加假 View
            mContentView.addView(statusBarView, 0, lp);
        }
    }

    private static void setFullScreen(Window window,ViewGroup mContentView,int color) {

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //设置透明状态栏,这样才能让 ContentView 向上
        //全屏模式都不为系统View预留空间
        View mChildView = mContentView.getChildAt(0);
        //获取mContentView的第一个子控件，并将其设置为fitSystemWindows
        if(mChildView != null) {
           if(color == UNSET_COLOR) {
               //默认情况下设置此属性，不去特意设置颜色则与大背景的背景色相同
               ViewCompat.setFitsSystemWindows(mChildView,true); //使其为系统 View 预留空间.
           }else {
               ViewCompat.setFitsSystemWindows(mChildView,false); //使其不为系统 View 预留空间.
           }
        }
        //大于5.0的处理方式
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(color);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){  //大于4.4的处理方式
            int statusBarHeight = getStatusBarHeight(window.getContext());
            //避免多次调用该方法时,多次移除了 View
            if(mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height == statusBarHeight) {
                //移除假的 View
                mContentView.removeView(mChildView);
                mChildView = mContentView.getChildAt(0);
            }
            if(mChildView != null) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //清除 ChildView 的 marginTop 属性
                if (params != null && params.topMargin >= statusBarHeight) {
                    params.topMargin -= statusBarHeight;
                    mChildView.setLayoutParams(params);
                }
            }
        }

    }


}
