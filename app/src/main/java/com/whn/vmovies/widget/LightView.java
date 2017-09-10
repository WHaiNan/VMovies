package com.whn.vmovies.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class LightView extends View {

    private int currentValue = 0;
    private Paint mPaint;

    public LightView(Context context) {
        this(context, null);
    }

    public LightView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //  构造一支画笔
        mPaint = new Paint();
        // 设置抗锯齿
        mPaint.setAntiAlias(true);
        //
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int startY = measuredHeight / 2 + measuredHeight / 3;
        int endY = startY - measuredHeight * 2 / 3 * currentValue / 100;

        canvas.drawLine(measuredWidth / 2 - 5, startY, measuredWidth / 2 - 5, endY, mPaint);

    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        invalidate();
    }

}
