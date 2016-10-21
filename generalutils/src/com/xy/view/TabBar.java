package com.xy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by John on 2016/10/21.
 */

public class TabBar extends HorizontalScrollView {
    public TabBar(Context context) {
        super(context);
        init(context);
    }


    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
