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
public class PromoterFutura extends Fragment {

    WebView futuraWebView;
    ImageView futuraImageView;
    MyTextView textView1, textView2, textView3, textView4, textView5, textView6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_promoter_futura, container, false);

        //futuraWebView = (WebView) view.findViewById(R.id.webviewfutura);
        futuraImageView = (ImageView) view.findViewById(R.id.futura_img);

        textView2 = (MyTextView) view.findViewById(R.id.dirtext3);
        textView1 = (MyTextView) view.findViewById(R.id.dirtext4);
        textView6 = (MyTextView) view.findViewById(R.id.dirtext5);
        textView3 = (MyTextView) view.findViewById(R.id.shortdesc3);
        textView4 = (MyTextView) view.findViewById(R.id.exp3);
        textView5 = (MyTextView) view.findViewById(R.id.shortdesctext3);

       /* int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            futuraImageView.setImageResource(R.drawable.futura_logo);
        } else {
            futuraImageView.setImageResource (R.drawable.futura_logo_land);
        }*/

       /* String text = "<html><body>"
                + "<p align=\"justify\">"
                + getString(R.string.futura_view)
                + "</p> "
                + "</body></html>";

        //futuraWebView.getSettings();
        futuraWebView.setBackgroundColor(Color.TRANSPARENT);
        futuraWebView.loadData(text, "text/html", "utf-8");*/

        return view;
    }

}
