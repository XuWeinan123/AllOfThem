package com.aaronxu.allofthem.FloatSphere;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by woshi on 2016-08-21.
 */
public class FloatViewManager {
    private static final String TAG = "FloatViewManager";
    private  Context context;
    private FloatCircleView circleView;
    private float y0;
    private float x0;
    private View.OnTouchListener circleviewTouchListener = new View.OnTouchListener() {

        private float starty;
        private float startx;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startx = motionEvent.getRawX();
                    x0 = motionEvent.getRawX();
                    starty = motionEvent.getRawY();
                    y0 = motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float x = motionEvent.getRawX();
                    float y = motionEvent.getRawY();
                    float dx = x- startx;
                    float dy = y- starty;
                    params.x += dx;
                    params.y += dy;
                    circleView.setDragState(1);
                    wm.updateViewLayout(circleView,params);
                    startx=x;
                    starty=y;
                    break;
                case MotionEvent.ACTION_UP:
                    float x1= motionEvent.getRawX();
                    if(x1>getScreenWidth()/2){
                        circleView.setDragState(2);
                        Log.d(TAG, "屏幕宽度是："+getScreenWidth()+"\n"+"view宽度是："+circleView.width);
                        params.x = getScreenWidth()-circleView.width;
                    }else{
                        circleView.setDragState(0);
                        params.x = 0;
                    }

                    wm.updateViewLayout(circleView,params);
                    if(Math.abs(x1-x0)>6){
                        return true;
                    }else {
                        return false;
                    }
                default:
                    break;
            }
            return false;
        }
    };
    private final DialogView floatMenuView;

    public int getScreenWidth(){
        return wm.getDefaultDisplay().getWidth();
    }
    public int getScreenHeight(){
        return wm.getDefaultDisplay().getHeight();
    }
    public int getStatusHeight(){
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (int) field.get(o);
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            return 0;
        }
    }
    WindowManager wm;//通过这个windowmanager来操控浮窗体的显示和隐藏以及位置的改变
    private WindowManager.LayoutParams params;

    private FloatViewManager(final Context context){
        this.context = context;
        wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        circleView = new FloatCircleView(context);
        circleView.setOnTouchListener(circleviewTouchListener);
        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"支付宝密码拿来",Toast.LENGTH_SHORT).show();
                
                wm.removeView(circleView);
                showDialogView();
            }
        });
        floatMenuView = new  DialogView(context);
    }

    protected void showDialogView() {
        params = new WindowManager.LayoutParams();
        params.width = getScreenWidth();
        params.height = getScreenHeight()-getStatusHeight();
        params.x = 0;
        params.y = 0;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.format = PixelFormat.RGBA_8888;

        wm.addView(floatMenuView, params);
    }

    private static FloatViewManager instance;
    public static FloatViewManager getInstance(Context context){
        if(instance == null){
            synchronized (FloatViewManager.class){
                if (instance == null) {
                    instance = new FloatViewManager(context);
                }
            }
        }
        return instance;
    }
    /*展示浮窗小球到窗口上*/
    public void showFloatCircleView(){
        params = new WindowManager.LayoutParams();
        params.width = circleView.width;
        params.height = circleView.height;
        params.gravity = Gravity.CENTER_VERTICAL|Gravity.LEFT;
        params.x = 0;
        params.y = 0;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.format = PixelFormat.RGBA_8888;

        wm.addView(circleView, params);
    }
}
