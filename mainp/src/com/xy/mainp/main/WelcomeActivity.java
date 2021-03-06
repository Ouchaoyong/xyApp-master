package com.xy.mainp.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.xy.mainp.ActivityWelComeBinding;
import com.xy.mainp.R;
import com.xy.mainp.base.BasePActivity;
import com.xy.mainp.utils.StatusBarUtil;
import com.xy.debug.SystemConfig;
import com.xy.net.NetWrokUtils;

/**
 * Created by John on 2016/10/19.
 */

public class WelcomeActivity extends BasePActivity {

    private ActivityWelComeBinding binding;
    private Handler handler = new Handler();

    @Override
    protected void loadData() {
        isConnetNet();
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setImmersiveStatusBar(this, StatusBarUtil.FULL_SCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        String appVersionName = SystemConfig.getAppVersionName(this);
        binding.tvVersionWelcome.setText(String.format(getString(R.string.version_code), appVersionName));
    }

    private Runnable runable = new Runnable() {
        @Override
        public void run() {
            MainActivity.enter(WelcomeActivity.this);
            finish();
        }
    };

    public void isConnetNet() {
        if (NetWrokUtils.getInstance(this).isNetWorkConnet()) {
            handler.postDelayed(runable, 1300);

        } else {
            showToastMsg("未连接网络，请打开网络连接");
        }
    }
}
