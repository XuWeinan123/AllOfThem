package com.aaronxu.wolfeatsheep;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

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

    public Bitmap mWolfMeadow;
    public Bitmap mSheepMeadow;
    public Bitmap mMeadow;

    public BlankView(Context context) {
        this(context,null);
    }

    public BlankView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BlankView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = getContext();
//        mPhoneWidth = 1080;
//        mPhoneHeight = 1920;
        Display d = ((Activity)mContext).getWindowManager().getDefaultDisplay();
        mPhoneWidth = d.getWidth();
        mPhoneHeight = d.getHeight();

        initBitmap();

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

    private void initBitmap() {
        mWolfMeadow = BitmapFactory.decodeResource(getResources(),R.mipmap.wolf_meadow);
        mSheepMeadow = BitmapFactory.decodeResource(getResources(),R.mipmap.sheep_meadow);
        mMeadow = BitmapFactory.decodeResource(getResources(),R.mipmap.meadow);

        Log.d(TAG, "缩放前: " + mWolfMeadow.getWidth());

        Matrix matrix = new Matrix();
        float mFloat = (float) ((mPhoneWidth*1.0/8)/mWolfMeadow.getWidth());
        matrix.postScale(mFloat,mFloat);
        Log.d(TAG, "缩放比: "+mFloat);
        mWolfMeadow = Bitmap.createBitmap(mWolfMeadow,0,0,mWolfMeadow.getWidth(),mWolfMeadow.getHeight(),matrix,true);
        Log.d(TAG, "缩放后: " + mWolfMeadow.getWidth());
        mSheepMeadow = Bitmap.createBitmap(mSheepMeadow,0,0,mSheepMeadow.getWidth(),mSheepMeadow.getHeight(),matrix,true);
        mMeadow = Bitmap.createBitmap(mMeadow,0,0,mMeadow.getWidth(),mMeadow.getHeight(),matrix,true);
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
            canvas.drawBitmap(mWolfMeadow,0,0,null);
        }else if (mWolfOrSheep ==2){
            canvas.drawBitmap(mSheepMeadow,0,0,null);
        }else if(mWolfOrSheep == 0){
            canvas.drawBitmap(mMeadow,0,0,null);
        }

//        canvas.drawLine(                 0,                  0,getMeasuredWidth(),                  0,mFramePaint);
//        canvas.drawLine(getMeasuredWidth(),                  0,getMeasuredWidth(),getMeasuredHeight(),mFramePaint);
//        canvas.drawLine(getMeasuredWidth(),getMeasuredHeight(),                 0,getMeasuredHeight(),mFramePaint);
//        canvas.drawLine(                 0,getMeasuredHeight(),                 0,                  0,mFramePaint);
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
