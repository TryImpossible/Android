package com.barry.customcontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by bynn on 2018/3/6.
 */
public class PrecentView extends View {

    private final  static String TAG = PrecentView.class.getSimpleName();
    private Paint mPaint;
    private RectF oval;

    public PrecentView(Context context) {
        super(context);
        init();
    }

    public PrecentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PrecentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        oval = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getMode(heightMeasureSpec);
        Log.e(TAG, "onMeasure--widthMode-->" + widthMode);
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:

                break;
            case MeasureSpec.EXACTLY:

                break;
            case MeasureSpec.AT_MOST:

                break;
        }
        Log.e(TAG, "onMeasure--widthSize-->" + widthSize);
        Log.e(TAG, "onMeasure--widthMode-->" + heightMode);
        Log.e(TAG, "onMeasure--widthSize-->" + heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        //Fill填充，STROKE描邊，FILL_AND_STROKE填充和描邊
        int width = getWidth();
        int height = getHeight();
        Log.e(TAG, "onDraw--->" + width + "*" + height);
        float radius = width / 4;
        canvas.drawCircle(width / 2, width / 2, radius, mPaint);

        mPaint.setColor(Color.BLUE);
        oval.set(width / 2 - radius, width / 2 - radius, width / 2 + radius, width / 2 + radius); //用於定義圓弧的形狀和大小的界限
        canvas.drawArc(oval, 270, 10, true, mPaint); //根據進度畫圓弧
    }
}
