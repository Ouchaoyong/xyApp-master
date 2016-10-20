package com.xy.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.xy.debug.LogUtils;

/**
 * @author ocy
 * @time 2016/10/20 23:06
 * @des 判断网络类型以及是否联网
 */

public class NetWrokUtils {

    private static NetWrokUtils instance;
    private static Context mContext;
    private static ConnectivityManager mManager;
    public static final int NETWORK_4G = 0x0004;
    public static final int NETWORK_3G = 0x0003;
    public static final int NETWORK_2G = 0x0002;
    public static final int NETWORK_WIFI = 0x0001;
    public static final int NETWORK_NOT = -1;

    public static NetWrokUtils getInstance(Context context) {
        if(instance == null) {
            synchronized (NetWrokUtils.class){
                if(instance == null) {
                    instance =  new NetWrokUtils();
                    mContext = context;
                }
            }
        }
        return instance;
    }

    private NetWrokUtils(){
        //获取系统服务，网络状态管理器
        mManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @return true 表示网络可用
     */
    public static boolean isNetWorkConnet() {
        if (mManager != null) {
            NetworkInfo info = mManager.getActiveNetworkInfo();//活动网络信息
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTING) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断网络类型
     */
    public static int getNetWrokType(){
        int type = NETWORK_NOT;
        //假如网络状态是连接的
        if(isNetWorkConnet()) {
            NetworkInfo info = mManager.getActiveNetworkInfo();//活动网络信息
            if(info.getType() == ConnectivityManager.TYPE_WIFI){
                type = NETWORK_WIFI;
            }else if(info.getType() == ConnectivityManager.TYPE_MOBILE) {
                int newWorkType = info.getSubtype();  //网络类型
                switch (newWorkType){
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN : //api<8: replace by 11
                        type = NETWORK_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        type = NETWORK_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<1 : replace by 13
                        type = NETWORK_4G;
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (info.getSubtypeName().equalsIgnoreCase("TD-SCDMA")
                                || info.getSubtypeName().equalsIgnoreCase("WCDMA")
                                || info.getSubtypeName().equalsIgnoreCase("CDMA2000"))
                        {
                            type = NETWORK_3G;
                        }
                        else
                        {
                            LogUtils.i("netWorkType",info.getSubtypeName());
                        }

                        break;
                }

            }
        }
        return  type;
    }

}
