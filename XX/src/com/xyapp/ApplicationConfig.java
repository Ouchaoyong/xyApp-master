package com.xyapp;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.xy.debug.LogUtils;

/**
 * @author ocy
 * @time 2016/10/20 23:44
 * @des ${TODU}
 */

public class ApplicationConfig {


    public static void initConfig(Context context){

        LogUtils.isLog = true;  //调试
        libsInit(context);
    }

    private static void libsInit(Context context){
    //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(context.getApplicationContext());
    }
}
