package com.xy.mainp.main;

import android.app.Application;

/**
 * Created by John on 2016/10/19.
 */

public class MainPApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
