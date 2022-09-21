package com.nebulacompanies.nebula.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.nebulacompanies.nebula.Config;

@SuppressLint("AppCompatCustomView")
public class FontAwosmeText   extends AppCompatTextView {
    public FontAwosmeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public FontAwosmeText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FontAwosmeText(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "FontAwesome.ttf");
        setTypeface(font);
    }

}
