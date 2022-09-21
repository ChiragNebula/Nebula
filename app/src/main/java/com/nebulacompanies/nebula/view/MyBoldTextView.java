package com.nebulacompanies.nebula.view;

import static com.nebulacompanies.nebula.util.SetFonts.setBoldFonts;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by Palak Mehta on 9/25/2017.
 */

public class MyBoldTextView extends AppCompatTextView {

    public MyBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyBoldTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        setBoldFonts(getContext(), this);
    }
}