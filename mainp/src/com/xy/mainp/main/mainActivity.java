package com.xy.mainp.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xy.mainp.R;
import com.xy.mainp.base.BaseActivity;
import com.xy.mainp.utils.StatusBarUtil;

/**
 * Created by John on 2016/10/19.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        StatusBarUtil.setImmersiveStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void enter(Activity activity){
        Intent intent = new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }
}
