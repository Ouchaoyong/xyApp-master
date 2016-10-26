package com.xyapp;

import com.xy.debug.LogUtils;
import com.xy.mainp.main.MainPApplication;

/**
 * Created by John on 2016/10/19.
 */

public class MainApplication extends MainPApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationConfig.initConfig(getApplicationContext());
    }
}
