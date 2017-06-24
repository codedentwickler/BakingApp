package com.example.codedentwickler.bakingapp.customview;

import android.content.Context;
import android.util.AttributeSet;

public class ProportionalImageView extends android.support.v7.widget.AppCompatImageView {

    private static final float ASPECT_RATIO = 1.2f;

    public ProportionalImageView(Context context) {
        super(context);
    }

    public ProportionalImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProportionalImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = Math.round(width * ASPECT_RATIO);
        setMeasuredDimension(width, height);
    }
}
