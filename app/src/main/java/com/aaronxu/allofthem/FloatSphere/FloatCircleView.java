package com.aaronxu.allofthem.FloatSphere;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.aaronxu.allofthem.R;

/**
 * Created by woshi on 2016-08-21.
 */
public class FloatCircleView extends View{
    public static final int LEFT_AND_NO_DRAG =0;
    public static final int MIDDLE_AND_DRAG =1;
    public static final int RIGHT_AND_NO_DRAG =2;
    public int width = 200;
    public int height = 200;
    Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.woman),width,height,true);
    Bitmap bitmapWhenStayLeft = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.woman_left),width,height,true);
    Bitmap bitmapWhenStayRight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.woman_right),width,height,true);
    private Paint circlePaint;
    private Paint textPaint;
    private String text = "50%";
    private int isDraging = LEFT_AND_NO_DRAG;

    public FloatCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }


    private void initPaints() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.GRAY);
        circlePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setTextSize(25);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);
    }

    public FloatCircleView(Context context) {
        this(context,null);
    }

    public FloatCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawCircle(width/2,height/2,width/2,circlePaint);
//        float textwidth = textPaint.measureText(text);
//        float x = width/2 -textwidth/2;
//        Paint.FontMetrics metrics = textPaint.getFontMetrics();
//        float dy = -(metrics.descent+metrics.ascent)/2;
//        float y = height/2+dy;
//        canvas.drawText(text,x,y,textPaint);
        if(isDraging==LEFT_AND_NO_DRAG){
            canvas.drawBitmap(bitmapWhenStayLeft, 0, 0, null);
        }else if(isDraging==MIDDLE_AND_DRAG){
            canvas.drawBitmap(bitmap, 0, 0, null);
        }else if(isDraging == RIGHT_AND_NO_DRAG){
            canvas.drawBitmap(bitmapWhenStayRight,0,0,null);
        }
    }

    public void setDragState(int isDraging) {
        this.isDraging = isDraging;
        invalidate();
    }
}
