package com.xy.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.a520it.generalutils.R;
import com.xy.debug.SystemConfig;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by John on 2016/10/26.
 */

public class ESProgressDialog extends Dialog {
    public ESProgressDialog(Context context) {
        this(context, R.style.Dialog_Transparent);
        init(context);
    }

    public ESProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected ESProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        int size = SystemConfig.dp2px(context, 32);
        GifImageView bar = (GifImageView) LayoutInflater.from(context).inflate(R.layout.dialog_progressbar, null);
        setContentView(bar, new ViewGroup.LayoutParams(size, size));
    }
}
