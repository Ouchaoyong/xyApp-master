package com.xy.mainp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.xy.manager.MsgManager;

/**
 * Created by John on 2016/10/19.
 */

public class BaseActivity extends FragmentActivity {

    private MsgManager msgManager;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msgManager = new MsgManager(this);
    }

    public void showToastMsg(String str){
        msgManager.showToastMsg(str);
    }

    public void showToastMsg(String str,int druation){
        msgManager.showToastMsg(str,druation);
    }

    public void showSnackMsg(String str){
        msgManager.showSnackMsg(str);
    }

    public void showSnackMsg(String str,int duration){
      msgManager.showSnackMsg(str, duration);
    }

}
