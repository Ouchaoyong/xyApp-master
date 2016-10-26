package com.xy.mainp.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xy.mainp.R;
import com.xy.mainp.base.BasePActivity;
import com.xy.mainp.utils.StatusBarUtil;

/**
 * Created by John on 2016/10/19.
 */

public class MainActivity extends BasePActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setImmersiveStatusBar(this);
        setContentView(R.layout.activity_main);
        showSnackMsg("hahahaha");
    }


    public static void enter(Activity activity){
        Intent intent = new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }
}
