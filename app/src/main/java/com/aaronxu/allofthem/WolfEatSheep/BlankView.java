package com.aaronxu.allofthem.WolfEatSheep;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.aaronxu.allofthem.R;
import com.aaronxu.allofthem.RandomNine.utill.DensityUtil;

/**
 * Created by woshi on 2016-08-24.
 */
public class BlankView extends View {
    private static final String TAG = "BlankView";
    public static final int NOTHING = 0;
    public static final int WOLF = 1;
    public static final int SHEEP = 2;
    private boolean mClickable;
    private int mWolfOrSheep;
    private Context mContext;
    private Paint mRectPaint;//矩形的画笔
    private Paint mFramePaint;//矩形的边框
    private int mStrokeWidth;
    private int mPhoneWidth;
    private int mPhoneHeight;

    public BlankView(Context context) {
        this(context,null);
    }

    public BlankView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BlankView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = getContext();

//        Display d = ((Activity)mContext).getWindowManager().getDefaultDisplay();
//        mPhoneWidth = d.getWidth();
//        mPhoneHeight = d.getHeight();
        mPhoneWidth = 1080;
        mPhoneHeight = 1920;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BlankView);
        mClickable = a.getBoolean(R.styleable.BlankView_clickable,false);
        mWolfOrSheep = a.getInteger(R.styleable.BlankView_wolf_of_sheep,0);
//        int n = a.getIndexCount();
//        for (int i =0;i<n;i++){
//            int attr = a.getIndex(i);
//            switch (attr){
//                case R.styleable.BlankView_clickable:
//                    mClickable = a.getBoolean(attr,false);
//                    break;
//                case R.styleable.BlankView_wolf_of_sheep:
//                    mWolfOrSheep = a.getInt(attr,0);
//                    break;
//            }
//        }
        a.recycle();
        mRectPaint = new Paint();
        mRectPaint.setColor(0xffeeeeee);
        mFramePaint = new Paint();
        mFramePaint.setColor(0xFF171717);
        mStrokeWidth = DensityUtil.dip2px(mContext,2);
        mFramePaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.d(TAG,        MeasureSpec.getMode(widthMeasureSpec)+"/"+MeasureSpec.getSize(widthMeasureSpec));
        int width = mPhoneWidth/8;
        int height;
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        Log.d(TAG, "onDraw: "+"是否可以点击：" + mClickable + "\n状态：" + mWolfOrSheep);
        if (mWolfOrSheep==1){
            mRectPaint.setColor(0xffff0000);
        }else if (mWolfOrSheep ==2){
            mRectPaint.setColor(0xff00ff00);
        }else if(mWolfOrSheep == 0){
            mRectPaint.setColor(0xffeeeeee);
        }
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mRectPaint);

        canvas.drawLine(                 0,                  0,getMeasuredWidth(),                  0,mFramePaint);
        canvas.drawLine(getMeasuredWidth(),                  0,getMeasuredWidth(),getMeasuredHeight(),mFramePaint);
        canvas.drawLine(getMeasuredWidth(),getMeasuredHeight(),                 0,getMeasuredHeight(),mFramePaint);
        canvas.drawLine(                 0,getMeasuredHeight(),                 0,                  0,mFramePaint);
        super.onDraw(canvas);
    }
    public int setmWolfOrSheep(int status){
        int preStatus = mWolfOrSheep;
        mWolfOrSheep = status;
        invalidate();
        return preStatus;
    }

    public int getmWolfOrSheep() {
        return mWolfOrSheep;
    }

    public boolean ismClickable() {
        return mClickable;
    }

    public void setmClickable(boolean mClickable) {
        this.mClickable = mClickable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!mClickable) return true;
        int e = event.getAction();
        Log.d(TAG, "onTouchEvent: "+e);
        switch (e){
            case 0:
                mWolfOrSheep = (mWolfOrSheep+1)%3;
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }
}
