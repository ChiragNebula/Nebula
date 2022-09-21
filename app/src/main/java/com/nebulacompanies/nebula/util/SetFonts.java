package com.nebulacompanies.nebula.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.nebulacompanies.nebula.Config;

/**
 * Created by Palak Mehta on 8/9/2017.
 */

public class SetFonts {

    public static void setFonts(Context context, TextView textView) {
        Typeface tf1 = Typeface.createFromAsset(context.getAssets(), Config.FONT_STYLE);
        textView.setTypeface(tf1);
    }

    public static void setBoldFonts(Context context, TextView textView) {
        Typeface tf1 = Typeface.createFromAsset(context.getAssets(), Config.FONT_STYLE);
        textView.setTypeface(tf1, Typeface.BOLD);
    }

    public static void setFonts(Context context, TextInputLayout textInputLayout) {
        Typeface tf1 = Typeface.createFromAsset(context.getAssets(), Config.FONT_STYLE);
        textInputLayout.setTypeface(tf1);
    }

    public static void setFonts(Context context, View view){
        Typeface tf1 = Typeface.createFromAsset(context.getAssets(), Config.FONT_STYLE);
        ((TextView) view).setTypeface(tf1);
    }

    public static void setFontsLato(Context context, View view){
        Typeface tf1 = Typeface.createFromAsset(context.getAssets(), Config.FONT_STYLE_BOLD_LATO);
        ((TextView) view).setTypeface(tf1);
    }
    /*public static void setFonts(Context context, ShownEdittext view){
        Typeface tf1 = Typeface.createFromAsset(context.getAssets(), Config.FONT_STYLE);
        ((ShownEdittext) view).setTypeface(tf1);
    }*/
}
