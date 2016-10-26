package com.xy.mainp.main;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;

import com.xy.mainp.R;
import com.xy.mainp.base.BasePActivity;
import com.xy.mainp.databinding.ActivityMainBinding;
import com.xy.mainp.utils.StatusBarUtil;

/**
 * Created by John on 2016/10/19.
 */

public class MainActivity extends BasePActivity {

    private ActivityMainBinding binding;

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setImmersiveStatusBar(this,StatusBarUtil.FULL_SCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    public static void enter(Activity activity){
        Intent intent = new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        binding.mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        binding.mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        binding.mapView.onPause();
    }
}
