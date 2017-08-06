package com.initishbhatt.loaders.type;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.initishbhatt.loaders.Loader;
import com.initishbhatt.loaders.LoaderInteractor;
import com.initishbhatt.loaders.R;

/**
 * @author nitishbhatt
 */

public class CircularLoader extends Loader implements LoaderInteractor {

    private int bigCircleRadius;
    private int numberOfDots = 8;
    private float[] yCorArray;
    private float[] xCorArray;

    public int getBigCircleRadius() {
        return bigCircleRadius;
    }

    public void setBigCircleRadius(int bigCircleRadius) {
        this.bigCircleRadius = bigCircleRadius;
        initCoordinates();
    }

    public CircularLoader(Context context) {
        super(context);
        initCoordinates();
        initDefaultCirclePaint();
        initSelectedCirclePaint();
        initShadowPaint();
    }

    public CircularLoader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs);
        initCoordinates();
        initDefaultCirclePaint();
        initSelectedCirclePaint();
        initShadowPaint();
    }

    public CircularLoader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs);
        initCoordinates();
        initDefaultCirclePaint();
        initSelectedCirclePaint();
        initShadowPaint();
    }

    @Override
    public void initCoordinates() {
        float sinVal = 0.7071f;
        float sin45Radius = sinVal * getBigCircleRadius();

        xCorArray = new float[numberOfDots];
        yCorArray = new float[numberOfDots];

        for (int i = 0; i < numberOfDots; i++) {
            yCorArray[i] = (getBigCircleRadius() + getRadius());
            xCorArray[i] = yCorArray[i];
        }

        xCorArray[1] = xCorArray[1] + sin45Radius;
        xCorArray[2] = xCorArray[2] + getBigCircleRadius();
        xCorArray[3] = xCorArray[3] + sin45Radius;

        xCorArray[5] = xCorArray[5] - sin45Radius;
        xCorArray[6] = xCorArray[6] - getBigCircleRadius();
        xCorArray[7] = xCorArray[7] - sin45Radius;

        yCorArray[0] = yCorArray[0] - getBigCircleRadius();
        yCorArray[1] = yCorArray[1] - sin45Radius;
        yCorArray[3] = yCorArray[3] + sin45Radius;

        yCorArray[4] = yCorArray[4] + getBigCircleRadius();
        yCorArray[5] = yCorArray[5] + sin45Radius;
        yCorArray[7] = yCorArray[7] - sin45Radius;
    }

    @Override
    public void initAttributes(AttributeSet attributeSet) {
        super.initAttributes(attributeSet);
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircularLoader, 0, 0);
        setBigCircleRadius(typedArray.getDimensionPixelSize(R.styleable.CircularLoader_big_circle_radius, 60));
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setCalculatedWidth(2 * getBigCircleRadius() + 2 * getRadius());
        setCalculatedHeight(getCalculatedWidth());
        setMeasuredDimension(getCalculatedWidth(), getCalculatedHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);

        if (isShouldAnimate()) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (System.currentTimeMillis() - getLogTime() >= getAnimationDuration()) {
                        setSelectedPosition(getSelectedPosition() + 1);

                        if (getSelectedPosition() > numberOfDots) {
                            setSelectedPosition(1);
                        }

                        invalidate();
                        setLogTime(System.currentTimeMillis());
                    }
                }
            }, getAnimationDuration());
        }
    }

    private void drawCircle(Canvas canvas) {
        int firstShadowPos;
        int secondShadowPos;
        if (getSelectedPosition() == 1) {
            firstShadowPos = 8;
        } else {
            firstShadowPos = getSelectedPosition() - 1;
        }

        if (firstShadowPos == 1) {
            secondShadowPos = 8;
        } else {
            secondShadowPos = firstShadowPos - 1;
        }

        for (int i = 0; i < numberOfDots; i++) {
            if (i + 1 == getSelectedPosition()) {
                canvas.drawCircle(xCorArray[i], yCorArray[i], getRadius(), getSelectedCirclePaint());
            } else if (isShowRunningShadow() && i + 1 == firstShadowPos) {
                canvas.drawCircle(xCorArray[i], yCorArray[i], getRadius(), getFirstShadowPaint());
            } else if (isShowRunningShadow() && i + 1 == secondShadowPos) {
                canvas.drawCircle(xCorArray[i], yCorArray[i], getRadius(), getSecondShadowPaint());
            } else {
                canvas.drawCircle(xCorArray[i], yCorArray[i], getRadius(), getDefaultCirclePaint());
            }

        }

    }
}
