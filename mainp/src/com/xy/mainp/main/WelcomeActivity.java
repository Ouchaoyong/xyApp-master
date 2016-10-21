package com.xy.mainp.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xy.mainp.ActivityWelComeBinding;
import com.xy.mainp.R;
import com.xy.mainp.base.BaseActivity;
import com.xy.mainp.utils.StatusBarUtil;
import com.xy.mainp.utils.SystemConfig;
import com.xy.net.NetWrokUtils;

/**
 * Created by John on 2016/10/19.
 */

public class WelcomeActivity extends BaseActivity {

    private ActivityWelComeBinding binding;
    private boolean mConnetNet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersiveStatusBar(this,StatusBarUtil.FULL_SCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        initView();
    }

    private void initView() {
        String appVersionName = SystemConfig.getAppVersionName(this);
        binding.tvVersionWelcome.setText(String.format(getString(R.string.version_code),appVersionName));
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        isConnetNet();
    }

    public void isConnetNet() {
        if(NetWrokUtils.getInstance(this).isNetWorkConnet()) {
           MainActivity.enter(this);
        }else {
            showToastMsg("未连接网络，请打开网络连接");
        }
    }
}
