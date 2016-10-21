package com.xy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.xy.debug.SystemConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2016/10/21.
 */

public class TabBar extends HorizontalScrollView implements View.OnClickListener {

    private int onScreenCount = 4;  //默认屏幕内可见为4个
    private List<Item> views;
    private Context mContext;
    private LinearLayout cevView;

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
        mContext = context;
        views = new ArrayList<>();
        cevView = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(cevView,layoutParams);

        cevView.setOrientation(LinearLayout.HORIZONTAL);  //横向

    }

    public void addItem(View view){
        cevView.addView(view);
        Item item = new Item();
        item.view = view;
        item.index = views.size();
        view.setId(item.index);
        views.add(item);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        setItemClick(view.getId());
    }
    //设置子项的点击事件
    private void setItemClick(int id) {

    }

    private class Item{
        View view;
        int index;
    }

    public void setOnScreenCount(int count){
        onScreenCount = count;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int screenWidth = SystemConfig.getScreenSize(getContext())[0];
        int itemWidth = screenWidth / onScreenCount;
        for (Item item : views){
            MarginLayoutParams params = (MarginLayoutParams)item.view.getLayoutParams();
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            params.width = itemWidth;
            item.view.setLayoutParams(params);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
