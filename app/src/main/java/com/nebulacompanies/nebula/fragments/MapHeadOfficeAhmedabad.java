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

public class MapHeadOfficeAhmedabad extends Base3Activity implements View.OnClickListener {

    MyTextView title, address, fax, phonenumber1, phonenumber2, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_head_office_ahmedabad, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        title = (MyTextView) view.findViewById(R.id.address_title);
        address = (MyTextView) view.findViewById(R.id.address_titlews);
        fax = (MyTextView) view.findViewById(R.id.output_fax);
        phonenumber1 = (MyTextView) view.findViewById(R.id.phone_number1);
        phonenumber2 = (MyTextView) view.findViewById(R.id.phone_number2);
        email = (MyTextView) view.findViewById(R.id.email);
        phonenumber1.setOnClickListener(this);
        phonenumber2.setOnClickListener(this);
        email.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.phone_number1:
                Intent callIntent1 = new Intent(Intent.ACTION_DIAL);
                callIntent1.setData(Uri.parse("tel:+91 79 6621 2222"));
                startActivity(callIntent1);
                break;
            case R.id.phone_number2:
                Intent callIntent2 = new Intent(Intent.ACTION_DIAL);
                callIntent2.setData(Uri.parse("tel:1800 419 2299"));
                startActivity(callIntent2);
                break;
            case R.id.email:
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
