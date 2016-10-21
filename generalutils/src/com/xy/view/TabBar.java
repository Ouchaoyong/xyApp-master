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
    private int index = -1;  //index 代表被选中的子项序号
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
    /*
    * 设置被选中的tab
    */
    private OnClickChange onClickChange;

    public void setOnClickChange(OnClickChange onClickChange) {
        this.onClickChange = onClickChange;
    }

    private void setItemClick(int position) {
        if (index != position && position>=0 && position < views.size()){
            boolean isCut  = false;
            if (onClickChange != null){
                isCut = onClickChange.onClicked(position);
            }
            if (isCut) return;
            views.get(position).view.setSelected(true);
            if (index>=0&&position<views.size()){
                views.get(index).view.setSelected(false);
            }
            if (onClickChange != null){
                onClickChange.onChange(position,index);
            }
            index = position;
        }
    }

    public interface OnClickChange{
        boolean onClicked(int newIndex);  //可以跳转到其它的界面
        void onChange(int newIndex,int oldIndex);  //可以在这里写入相关界面的切换
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
