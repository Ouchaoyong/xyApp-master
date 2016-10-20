package com.xy.debug;

import android.util.Log;

/**
 * @author ocy
 * @time 2016/10/20 23:45
 * @des ${TODU}
 */

public class LogUtils {
    public static String LOGTAG="xyApp";
    public static boolean isLog = false;

    public static void i(String str){
        if(isLog) {
            Log.i(LOGTAG,str);
        }
    }

    public static void i(String tag,String str){
        if(isLog) {
            Log.i(tag,str);
        }
    }

    public static void d(String str){
        if(isLog) {
            Log.d(LOGTAG,str);
        }
    }
    public static void e(String str){
        if(isLog) {
            Log.e(LOGTAG,str);
        }

    }
    public static void v(String str){
        if(isLog) {
            Log.v(LOGTAG, str);
        }
    }
    public static void w(String str){
        if(isLog) {

            Log.w(LOGTAG,str);
        }
    }
    public static void wtf(String str){
        if(isLog) {

            Log.wtf(LOGTAG,str);
        }
    }
}
