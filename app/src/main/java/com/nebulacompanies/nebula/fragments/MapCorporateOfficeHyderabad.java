package com.nebulacompanies.nebula.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nebulacompanies.nebula.Base3Activity;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.MyTextView;

/**
 * Created by Palak Mehta on 4/13/2017.
 */

public class MapCorporateOfficeHyderabad extends Base3Activity implements View.OnClickListener {

    MyTextView title, address, email,txt_Phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_corporate_office_hyderabad, container, false);
        init(view);



        return view;
    }

    private void init(View view) {
        title = (MyTextView)view.findViewById(R.id.address_title);
        address= (MyTextView)view.findViewById(R.id.address_titlews_hyderabad);
        email=(MyTextView)view.findViewById(R.id.email1);
        email.setOnClickListener(this);
        txt_Phone = (MyTextView)view.findViewById(R.id.phone_number);
        txt_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent1 = new Intent(Intent.ACTION_DIAL);
                callIntent1.setData(Uri.parse("tel:+91-7227904567"));
                startActivity(callIntent1);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email1:
                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "info@nebulacompanies.com"});
                //need this to prompts email client only
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                break;
        }
    }
}
