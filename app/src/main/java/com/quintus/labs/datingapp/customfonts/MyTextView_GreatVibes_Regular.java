package com.quintus.labs.datingapp.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Quintus Labs on 17-Dec-2018.
 * www.quintuslabs.com
 */

public class MyTextView_GreatVibes_Regular extends TextView {

    public MyTextView_GreatVibes_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextView_GreatVibes_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView_GreatVibes_Regular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/GreatVibes-Regular.ttf");
            setTypeface(tf);
        }
    }

}