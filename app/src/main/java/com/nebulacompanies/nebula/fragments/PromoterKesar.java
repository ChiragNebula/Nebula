package com.nebulacompanies.nebula.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.MyTextView;

/**
 * Created by Palak Mehta on 7/30/2016.
 */
public class PromoterKesar extends Fragment {

    WebView kesarWebView;
    ImageView kesarImageView;
    MyTextView textView2, textView3, textView4, textView5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_promoter_kesar, container, false);

        //kesarWebView = (WebView) view.findViewById(R.id.webviewkesar);
        kesarImageView = (ImageView) view.findViewById(R.id.kesar_img);

        textView2 = (MyTextView) view.findViewById(R.id.dirtext2);
        textView3 = (MyTextView) view.findViewById(R.id.shortdesc2);
        textView4 = (MyTextView) view.findViewById(R.id.exp2);
        textView5 = (MyTextView) view.findViewById(R.id.shortdesctext2);

       /* int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            kesarImageView.setImageResource(R.drawable.kesar_logo);
        } else {
            kesarImageView.setImageResource (R.drawable.kesar_logo_land);
        }*/

       /* String text = "<html><body>"
                + "<p align=\"justify\">"
                + getString(R.string.kesar_view)
                + "</p> "
                + "</body></html>";

        //kesarWebView.getSettings();
        kesarWebView.setBackgroundColor(Color.TRANSPARENT);
        kesarWebView.loadData(text, "text/html", "utf-8");*/

        return view;
    }
}