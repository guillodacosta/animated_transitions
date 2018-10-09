package co.com.ceiba.heisenberglab.animated_transitions;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


import com.airbnb.lottie.LottieAnimationView;

/**
 * TODO: document your custom view class.
 */
public class NumberSlider extends LottieAnimationView {
    private static final float DEFAULT_END = 2f;
    private static final float DEFAULT_START = 0f;
    private Float end; // TODO: use a default from R.string...
    private Float start; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    GestureListener gestureListener;

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
        this.setOnTouchListener(gestureListener);
    }

    public NumberSlider(Context context) {
        super(context);
        init(null, 0);
    }

    public NumberSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public NumberSlider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        gestureListener = new GestureListener(this);
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.NumberSlider, defStyle, 0);

        end = a.getFloat(
                R.styleable.NumberSlider_end, DEFAULT_END);
        start = a.getFloat(
                R.styleable.NumberSlider_end, DEFAULT_START);
        mExampleColor = a.getColor(
                R.styleable.NumberSlider_backgroundColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.NumberSlider_size,
                mExampleDimension);

        if (a.hasValue(R.styleable.NumberSlider_animationSource)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.NumberSlider_animationSource);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();

    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        // mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
        // canvas.drawText(mExampleString,
        //         paddingLeft + (contentWidth - mTextWidth) / 2,
//                 paddingTop + (contentHeight + mTextHeight) / 2,
        //              mTextPaint);

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();

    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
        Context context;
        GestureDetector gestureDetector;
        LottieAnimationView lottieAnimationView;

        public GestureListener(LottieAnimationView lottieAnimationView) {
            this(lottieAnimationView.getContext(), null);
            this.lottieAnimationView = lottieAnimationView;
        }

        public GestureListener(Context context, GestureDetector _gestureDetector) {
            if(_gestureDetector == null)
                _gestureDetector = new GestureDetector(context, this);
            this.context = context;
            this.gestureDetector = _gestureDetector;
        }


        public GestureDetector getDetector() {
            return gestureDetector;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            lottieAnimationView.setProgress(0.4f);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            lottieAnimationView.setProgress(0.4f);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }
}
