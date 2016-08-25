package com.aaronxu.allofthem.RandomNine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.aaronxu.allofthem.R;
import com.aaronxu.allofthem.RandomNine.utill.DensityUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by woshi on 2016-08-10.
 */
public class CustomTitleView extends View {
    private static final String TAG = "CustomTitleView";
    /**
     * 文本
     */
    private String mTitleText = "0000";
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context) {
        this(context,null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView,defStyleAttr,0);
        int n = a.getIndexCount();
        mTitleText = randomText();
        for(int i = 0;i<n;i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.CustomTitleView_thisText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_thisTextColor:
                    // 默认颜色设置为黑色
                    mTitleTextColor = a.getColor(attr, 0xffffffff);
                    break;
                case R.styleable.CustomTitleView_thisTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        // mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        mPaint.setColor(0xff3692c5);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        if (mTitleTextColor == 0) mTitleTextColor = 0xffffffff;
        mPaint.setColor(mTitleTextColor);
        Log.d(TAG, String.valueOf(mTitleTextColor));
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int padding = DensityUtil.dip2px(getContext(),8);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        }else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) ( padding+ textWidth + padding);
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (padding + textHeight + padding);
            height = desired;
        }
//        Log.d(TAG, "width="+width+",height="+height);
        setMeasuredDimension(DensityUtil.dip2px(getContext(),100), height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, String.valueOf(event.getAction()));
        if(event.getAction()== MotionEvent.ACTION_DOWN){
            mTitleText = randomText();
            postInvalidate();
        }
        return super.onTouchEvent(event);
    }

    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 3)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }
    public void changeNumber(){
        mTitleText = randomText();
        invalidate();
    }
}
