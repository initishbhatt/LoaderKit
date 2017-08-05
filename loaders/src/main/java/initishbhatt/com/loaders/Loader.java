package initishbhatt.com.loaders;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import initishbhatt.com.loaders.util.Helper;

/**
 * @author nitishbhatt
 */

public abstract class Loader extends View {


    public int animationDuration = 500;

    public float[] xCorArray;

    public Paint defaultCirclePaint;
    public Paint selectedCirclePaint;

    public Paint firstShadowPaint;
    public Paint secondShadowPaint;

    private boolean isShadowColorSet = false;

    public boolean shouldAnimate = true;

    public int selectedPosition = 1;

    public long logTime = 0;

    public int calcuatedHeight = 0;

    public int calculatedWidth = 0;
    private int defaultColor;
    private int selectedColor;
    public int radius;
    public boolean showRunningShadow;

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = ContextCompat.getColor(getContext(), defaultColor);
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = ContextCompat.getColor(getContext(), selectedColor);
        initShadowPaint();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        initCoordinates();
    }

    public boolean isShowRunningShadow() {
        return showRunningShadow;
    }

    public void setShowRunningShadow(boolean showRunningShadow) {
        this.showRunningShadow = showRunningShadow;
    }

    public int getFirstShadowColor() {
        return firstShadowColor;
    }

    public void setFirstShadowColor(int firstShadowColor) {
        this.firstShadowColor = firstShadowColor;
        initShadowPaint();
    }

    public int getSecondShadowColor() {
        return secondShadowColor;
    }

    public void setSecondShadowColor(int secondShadowColor) {
        this.secondShadowColor = secondShadowColor;
        initShadowPaint();
    }

    private int firstShadowColor;
    private int secondShadowColor;

    public Loader(Context context) {
        super(context);
    }

    public Loader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Loader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initAttributes(AttributeSet attributeSet) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.Loader, 0, 0);

        this.defaultColor = typedArray.getColor(R.styleable.Loader_default_color,
                ContextCompat.getColor(getContext(), R.color.loader_default));
        this.selectedColor = typedArray.getColor(R.styleable.Loader_selected_color,
                ContextCompat.getColor(getContext(), R.color.loader_selected));

        this.radius = typedArray.getDimensionPixelSize(R.styleable.Loader_circle_radius, 30);

        this.animationDuration = typedArray.getInt(R.styleable.Loader_anim_dur, 500);

        this.showRunningShadow = typedArray.getBoolean(R.styleable.Loader_show_running_shadow, true);

        this.firstShadowColor = typedArray.getColor(R.styleable.Loader_first_shadow_color, 0);
        this.secondShadowColor = typedArray.getColor(R.styleable.Loader_second_shadow_color, 0);

        typedArray.recycle();
    }

    public abstract void initCoordinates();

    public void initSelectedCirclePaint() {
        selectedCirclePaint = new Paint();
        selectedCirclePaint.setAntiAlias(true);
        selectedCirclePaint.setStyle(Paint.Style.FILL);
        selectedCirclePaint.setColor(selectedColor);
    }

    public void initDefaultCirclePaint() {
        defaultCirclePaint = new Paint();
        defaultCirclePaint.setAntiAlias(true);
        defaultCirclePaint.setStyle(Paint.Style.FILL);
        defaultCirclePaint.setColor(defaultColor);
    }

    public void initShadowPaint() {
        if (showRunningShadow) {
            if (!isShadowColorSet) {
                firstShadowColor = Helper.adjustAlpha(selectedColor, 0.7f);
                secondShadowColor = Helper.adjustAlpha(selectedColor, 0.5f);
                isShadowColorSet = true;
            }

            initFirstShadowPaint();

            initSecondShadowpaint();
        }
    }

    private void initSecondShadowpaint() {
        secondShadowPaint = new Paint();
        secondShadowPaint.setAntiAlias(true);
        secondShadowPaint.setStyle(Paint.Style.FILL);
        secondShadowPaint.setColor(secondShadowColor);
    }

    private void initFirstShadowPaint() {
        firstShadowPaint = new Paint();
        firstShadowPaint.setAntiAlias(true);
        firstShadowPaint.setStyle(Paint.Style.FILL);
        firstShadowPaint.setColor(firstShadowColor);
    }

    public void startAnimation() {
        shouldAnimate = true;
        invalidate();
    }

    public void stopAnimation() {
        shouldAnimate = false;
        invalidate();
    }


}
