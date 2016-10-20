package com.xy.mainp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by John on 2016/10/19.
 */

public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void showToastMsg(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    public void showToastMsg(String str,int druation){
        Toast.makeText(this,str,druation).show();
    }

    public void showSnackMsg(String str){
        Snackbar.make(getWindow().getDecorView(),str,Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackMsg(String str,int duration){
        Snackbar.make(getWindow().getDecorView(),str,duration).show();
    }

}
