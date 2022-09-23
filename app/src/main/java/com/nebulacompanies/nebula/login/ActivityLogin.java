package com.nebulacompanies.nebula.login;

import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nebulacompanies.nebula.Model.Login.getLoginResonse;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.util.Session;
import com.nebulacompanies.nebula.util.Uttils;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends Activity {

    ActivityLogin obj_Login;

    EditText edt_BlockNo, edt_FlatNo;
    Button  btn_Continue;
    String str_Block, str_Flat,str_Username,str_Password,str_OTP;

    private APIInterface mAPIInterface;

    TextView txt_NeedHelp;

    // Shared Prefrences
    SharedPreferences sharedPreferences;
    //Shared Preferences Variables
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences.Editor editor;
    String lang;

    TextView txt_Login;

    Session session;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(this);
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        obj_Login = this;
        findById();
        setAction();
        setLangRecreate();
    }


    public void setLangRecreate() {
        String language = session.getLanguage();
        if (language.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(language);//Set Selected Locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        Log.e("Locale", config.locale.toString());
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
        //recreate();
        updateText();
    }

    private void updateText() {
        txt_Login.setText(R.string.login);
    }

    private void dialougNeedHelp() {
        LayoutInflater mLayoutInflater = (LayoutInflater)getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final Dialog dialog = new Dialog(obj_Login);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View convertView = mLayoutInflater.inflate(R.layout.dialoug_need_help, null);
        dialog.setContentView(convertView);
        dialog.setCancelable(true);


        ImageView iv_Close = (ImageView)dialog.findViewById(R.id.iv_close);
        iv_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        DisplayMetrics displaymetrics = new DisplayMetrics();
         getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (int) ((int)displaymetrics.widthPixels * 0.9);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width,  WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setAttributes(lp);

        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();
    }


    private void findById() {
        edt_BlockNo = (EditText) findViewById(R.id.edtBlockId);
        edt_FlatNo = (EditText) findViewById(R.id.edtFlatNo);
        
      //  btn_Cancel = (Button) findViewById(R.id.btnCancel);
        btn_Continue = (Button) findViewById(R.id.btnLogin);

      // edt_BlockNo.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        txt_NeedHelp = findViewById(R.id.txt_NeedHelp);

        txt_Login = (TextView) findViewById(R.id.txt_Login);
    }

    private void setAction() {
//        btn_Cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        txt_NeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialougNeedHelp();
            }
        });

        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_Block = edt_BlockNo.getText().toString();
                str_Flat = edt_FlatNo.getText().toString();

                if (str_Block == null || str_Block.equals("")){
                  //  Uttils.showToast(obj_Login,"Please Enter Block");
                    edt_BlockNo.setError(getString(R.string.validationblocks));
                    edt_BlockNo.requestFocus();
                }else if (str_Flat == null || str_Flat.equals("")){
                    edt_FlatNo.setError(getString(R.string.validationflat));
                    edt_FlatNo.requestFocus();
                    //Uttils.showToast(obj_Login,"Please Enter Flat No.");
                }
                else{
                    callLoginResponse();
                }

            }
        });
    }

    private void callLoginResponse() {
        if (isInternetPresent) {
            Uttils.showProgressDialoug(obj_Login);
            Call<getLoginResonse> wsCallingSiteProgress = mAPIInterface.getUserLogin(str_Block,str_Flat);
            wsCallingSiteProgress.enqueue(new Callback<getLoginResonse>() {
                @Override
                public void onResponse(Call<getLoginResonse> call, Response<getLoginResonse> response) {
                    Uttils.hideProgressDialoug();
                    if(response.isSuccessful()){

                        if (response.body().getStatusCode() == 1){
                          str_Username = response.body().getData().getUsername();
                          str_Password = response.body().getData().getPassword();
                          str_OTP = response.body().getData().getOTp().toString();

                          Bundle b = new Bundle();
                          b.putString("Username",str_Username);
                          b.putString("Password",str_Password);
                          b.putString("Otp",str_OTP);
                          b.putString("Block",str_Block);
                          b.putString("Flat",str_Flat);
                          Intent i_Otp = new Intent(obj_Login,ActivityOTP.class);
                          i_Otp.putExtras(b);
                          startActivity(i_Otp);

                        }
                        else {
                            String str_Message = response.body().getMessage();
                            if(str_Message.equals(getResources().getString(R.string.invalid_block))){
                                edt_BlockNo.setError(response.body().getMessage());
                                edt_BlockNo.requestFocus();
                            }
                            else if (str_Message.equals(getResources().getString(R.string.invalid_flat))){
                                edt_FlatNo.setError(response.body().getMessage());
                                edt_FlatNo.requestFocus();
                            }
                            else {
                                Uttils.showToast(obj_Login,response.body().getMessage());
                            }

                        }
                    }else {
                        Uttils.showToast(obj_Login,"Incorrect block or flat no!");
                    }

                }

                @Override
                public void onFailure(Call<getLoginResonse> call, Throwable t) {
                    Log.e("ResponseCode",t.getMessage());
                    Uttils.hideProgressDialoug();
                    Uttils.showToast(obj_Login,t.getMessage());
                }
            });
        }
    }

}
