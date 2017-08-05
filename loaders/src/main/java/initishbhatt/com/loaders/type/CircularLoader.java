package initishbhatt.com.loaders.type;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import initishbhatt.com.loaders.Loader;
import initishbhatt.com.loaders.R;

/**
 * @author nitishbhatt
 */

public class CircularLoader extends Loader {

    private int bigCircleRadius;
    private int numberOfDots = 8;
    private float[] yCorArray;
    private float sinVal = 0.7071f;

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
        float sin45Radius = sinVal * this.bigCircleRadius;

        xCorArray = new float[numberOfDots];
        yCorArray = new float[numberOfDots];

        for (int i = 0; i < numberOfDots - 1; i++) {
            yCorArray[i] = (this.bigCircleRadius + radius);
            xCorArray[i] = yCorArray[i];
        }

        xCorArray[1] = xCorArray[1] + sin45Radius;
        xCorArray[2] = xCorArray[2] + this.bigCircleRadius;
        xCorArray[3] = xCorArray[3] + sin45Radius;

        xCorArray[5] = xCorArray[5] - sin45Radius;
        xCorArray[6] = xCorArray[6] - this.bigCircleRadius;
        xCorArray[7] = xCorArray[7] - sin45Radius;

        yCorArray[0] = yCorArray[0] - this.bigCircleRadius;
        yCorArray[1] = yCorArray[1] - sin45Radius;
        yCorArray[3] = yCorArray[3] + sin45Radius;

        yCorArray[4] = yCorArray[4] + this.bigCircleRadius;
        yCorArray[5] = yCorArray[5] + sin45Radius;
        yCorArray[7] = yCorArray[7] - sin45Radius;
    }

    @Override
    public void initAttributes(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircularLoader, 0, 0);
        this.bigCircleRadius = typedArray.getDimensionPixelSize(R.styleable.CircularLoader_big_circle_radius, 60);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculatedWidth = 2 * this.bigCircleRadius + 2 * radius;
        calcuatedHeight = calculatedWidth;

        setMeasuredDimension(calculatedWidth, calcuatedHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);

        if (shouldAnimate) {


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (System.currentTimeMillis() - logTime >= animationDuration) {

                        selectedPosition++;

                        if (selectedPosition > numberOfDots) {
                            selectedPosition = 1;
                        }

                        invalidate();
                        logTime = System.currentTimeMillis();
                    }
                }
            }, animationDuration);
        }
    }

    private void drawCircle(Canvas canvas) {
        int firstShadowPos;
        int secondShadowPos;
        if (selectedPosition == 1) {
            firstShadowPos = 8;
        } else {
            firstShadowPos = selectedPosition - 1;
        }

        if (firstShadowPos == 1) {
            secondShadowPos = 8;
        } else {
            secondShadowPos = firstShadowPos - 1;
        }

        for (int i = 0; i < numberOfDots - 1; i++) {
            if (i + 1 == selectedPosition) {
                canvas.drawCircle(xCorArray[i], yCorArray[i], radius, selectedCirclePaint);
            } else if (this.showRunningShadow && i + 1 == firstShadowPos) {
                canvas.drawCircle(xCorArray[i], yCorArray[i], radius, firstShadowPaint);
            } else if (this.showRunningShadow && i + 1 == secondShadowPos) {
                canvas.drawCircle(xCorArray[i], yCorArray[i], radius, secondShadowPaint);
            } else {
                canvas.drawCircle(xCorArray[i], yCorArray[i], radius, defaultCirclePaint);
            }

        }

    }
}
