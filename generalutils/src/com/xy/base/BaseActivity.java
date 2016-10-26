package com.xy.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xy.manager.MsgManager;

/**
 * Created by John on 2016/10/26.
 */

public abstract class BaseActivity extends FragmentActivity {
    private MsgManager msgManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msgManager = new MsgManager(this);
        initData();
        initView(savedInstanceState);
        loadData();
    }

    /**
     * 初始化数据
     */
    protected  void initData(){

    };
    /**
     * 加载网络数据
     */
    protected  void loadData(){

    };
    /**
     * 加载网络数据
     */
    protected abstract void initView(Bundle savedInstanceState);

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
