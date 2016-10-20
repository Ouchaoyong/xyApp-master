package com.xy.mainp.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;

import com.xy.mainp.ActivityWelComeBinding;
import com.xy.mainp.R;
import com.xy.mainp.base.BaseActivity;
import com.xy.mainp.utils.StatusBarUtil;
import com.xy.mainp.utils.SystemConfig;

/**
 * Created by John on 2016/10/19.
 */

public class WelcomeActivity extends BaseActivity {

    private ActivityWelComeBinding binding;
    private Animation mAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersiveStatusBar(this,StatusBarUtil.FULL_SCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        initView();
    }

    private void initView() {
        int appVersionCode = SystemConfig.getAppVersionCode(this);
        binding.tvVersionWelcome.setText(String.format(getString(R.string.version_code),appVersionCode+""));
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();

    }

}
