package com.xy.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.a520it.generalutils.R;
import com.xy.view.dialog.ESProgressDialog;

import org.w3c.dom.Text;

/**
 * Created by John on 2016/10/26.
 */

public class MsgManager{

    private Context context;

    public MsgManager(Context context){
        this.context = context;
    }
    //判断是否能被打开
    public boolean isCanOpen(){
        if (context instanceof Activity){
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.JELLY_BEAN_MR1){
                return !((Activity) context).isDestroyed();
            }else {
                return true;
            }
        }else {
            return true;
        }
    }

    /**
     * 等待对话框
     */
    private ESProgressDialog esProgressDialog;

    public void ShowLoading(boolean show){
        if (show){
            showESLoading(true);
        }else {
            stopESLoading();
        }
    }

    public void stopESLoading() {

    }

    public void showESLoading(boolean cancelable) {
        showESLoading(cancelable,300);
    }

    public void showESLoading(boolean cancelable,int delay){

    }

    /**
     * Toast.show()
     */
    private Toast toast;
    public void showToastMsg(String str){
       showToastMsg(str,Toast.LENGTH_SHORT);
    }

    public void showToastMsg(String str,int druation){
       showToastMsg(str,druation, Gravity.CENTER,0,0);
    }

    public void showToastMsg(String str,int gravity,int x,int y){
        showToastMsg(str,Toast.LENGTH_SHORT,gravity,x,y);
    }

    public void showToastMsg(String str,int druaction,int gravity,int x,int y){
        if (toast == null){
            toast = Toast.makeText(context,str,druaction);
        }else {
            toast.setDuration(druaction);
            toast.setText(str);
        }
        toast.setGravity(gravity,x,y);
        toast.show();
    }

    /**
     *shackBar.show();
     */
    private Snackbar snackbar;

    public void setSnackbar(Snackbar snackbar){
        this.snackbar = snackbar;
    }

    public void setSnackbar(int TextColor,int ActionTextColor){
        View snackbarView = snackbar.getView();
        TextView snackbarText = (TextView)snackbarView.findViewById(R.id.snackbar_text);
        snackbarText.setTextColor(TextColor);
        snackbar.setActionTextColor(ActionTextColor);
    }
    public void showSnackMsg(String str){
        showSnackMsg(str,Snackbar.LENGTH_SHORT);
    }
    public void setSnackAction(String actionText, View.OnClickListener listner){
        snackbar.setAction(actionText,listner);
    }
    public void showSnackMsg(String str,int duration){
        if (snackbar == null){
            if (context instanceof Activity){
                snackbar = Snackbar.make((((Activity) context).getWindow().getDecorView()),str,duration);
            }
        }else {
            snackbar.setText(str);
            snackbar.setDuration(duration);
        }
        snackbar.show();
    }
}
