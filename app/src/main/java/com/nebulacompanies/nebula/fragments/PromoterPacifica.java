package com.nebulacompanies.nebula.fragments;

import android.os.Bundle;
import android.os.Handler;
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
public class PromoterPacifica extends Fragment {

    private WebView pacificaWebView;
    ImageView pacificaImageView;
    MyTextView textView2, textView3, textView4, textView5;
    Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_promoter_pacifica, container, false);

        //pacificaWebView = (WebView) view.findViewById(R.id.webviewpacifica);
        pacificaImageView = (ImageView) view.findViewById(R.id.pacifica_img);

        textView2 = (MyTextView) view.findViewById(R.id.dirtext1);
        textView3 = (MyTextView) view.findViewById(R.id.shortdesc1);
        textView4 = (MyTextView) view.findViewById(R.id.exp1);
        textView5 = (MyTextView) view.findViewById(R.id.shortdesctext1);

        /*int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            pacificaImageView.setImageResource(R.drawable.pacifica_logo);
        } else {
            pacificaImageView.setImageResource(R.drawable.pacifica_logo_land);
        }*/

      /*  String text = "<html><body>"
                + "<p align=\"justify\">"
                + getString(R.string.pacifica_view)
                + "</p> "
                + "</body></html>";

        //pacificaWebView.getSettings();
        pacificaWebView.setBackgroundColor(Color.TRANSPARENT);
        pacificaWebView.loadData(text, "text/html", "utf-8");*/

        return view;
    }

}
