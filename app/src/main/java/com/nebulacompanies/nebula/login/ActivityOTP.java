package com.nebulacompanies.nebula.login;

import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Const;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UI.Activity.CustomerBookingNavigationActivity;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UserAuthorization;
import com.nebulacompanies.nebula.Model.Login.getLoginResonse;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.broadcast.AppSignatureHashHelper;
import com.nebulacompanies.nebula.broadcast.SMSReceiver;
import com.nebulacompanies.nebula.util.Uttils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityOTP extends Activity implements
        SMSReceiver.OTPReceiveListener{

    public static final String TAG = ActivityOTP.class.getSimpleName();

    private SMSReceiver smsReceiver;
    BroadcastReceiver receiver;
    EditText edt_Otp1, edt_Otp2,edt_Otp3,edt_Otp4;
    String str_Username,str_Password,str_OTP,str_VerfityOTP,str_Block,str_Flat;
    //Button btn_Edit ,btn_Verify;
    ActivityOTP obj_OTP;

    private static APIInterface mAPIInterface;
    private UserAuthorization mUserAuthorization;

    TextView txt_Resend;
    final CountDownTimer[] cTimer = new CountDownTimer[1];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        obj_OTP = this;
        mUserAuthorization = new UserAuthorization(obj_OTP);
        mAPIInterface = APIClient.getClient(obj_OTP).create(APIInterface.class);
        AppSignatureHashHelper appSignatureHashHelper = new AppSignatureHashHelper(this);
        Log.i(TAG, "HashKey: " + appSignatureHashHelper.getAppSignatures().get(0));

        findById();
        getBundleData();
        setAction();
        checkAndRequestPermissions();
        startSMSListener();

//        receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.e("OTP", "OTP");
//                if (intent.getAction().equalsIgnoreCase("otp")) {
//                    final String message = intent.getStringExtra("message");
//                    final String sender = intent.getStringExtra("Sender");
//                    Log.e("sender", sender);
//                    if (sender.equals("QP-NEBULA")){
//                        Pattern pattern = Pattern.compile("(\\d{4})");
//                        Matcher matcher = pattern.matcher(message);
//                        String otp = "";
//                        if (matcher.find()) {
//                            otp = matcher.group(0);  // 4 digit number
//                        } else {
//
//                        }
//                        setOtp(otp);
//                    }
//
//                }
//            }
//        };
        startTimer();


    }

    public void startTimer() {
        if (cTimer[0] != null){
            cTimer[0].cancel();
        }
        cTimer[0] = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                txt_Resend.setText( getResources().getString(R.string.resendotp) +String.valueOf(millisUntilFinished/1000) + "s");
                txt_Resend.setEnabled(false);
                txt_Resend.setFocusable(false);
                txt_Resend.setClickable(false);
            }
            public void onFinish() {
                String text = getResources().getString(R.string.didnt_receive);
                SpannableString spannableString = new SpannableString(text);
                ForegroundColorSpan foregroundColorSpanRed = new ForegroundColorSpan(Color.BLACK);
                ForegroundColorSpan foregroundColorSpanGreen = new ForegroundColorSpan(Color.RED);
                spannableString.setSpan(foregroundColorSpanRed, 0, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(foregroundColorSpanGreen, 23, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                txt_Resend.setText(spannableString);
                txt_Resend.setEnabled(true);
                txt_Resend.setFocusable(true);
                txt_Resend.setClickable(true);
            }
        };

        cTimer[0].start();
    }

    private void setOtp(String otp) {
        if (otp !=null){
            edt_Otp1.setText(""+otp.charAt(0));
            edt_Otp2.setText(""+otp.charAt(1));
            edt_Otp3.setText(""+otp.charAt(2));
            edt_Otp4.setText(""+otp.charAt(3));

            checkOTP();
           // callLoginAPI(str_Username,str_Password);
        }
    }

    private void setAction() {
//       btn_Edit.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               finish();
//           }
//       });
//       btn_Verify.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//
//           }
//       });

        txt_Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLoginResponse();
            }
        });
    }


    private void getBundleData() {
        Bundle b = getIntent().getExtras();
        if (b !=null){
          str_Username = b.getString("Username");
          str_Password = b.getString("Password");
          str_OTP = b.getString("Otp");
          str_Block = b.getString("Block");
          str_Flat = b.getString("Flat");
        }
    }

    private void findById() {
        edt_Otp1 = (EditText) findViewById(R.id.edtOTP1) ;
        edt_Otp2 = (EditText) findViewById(R.id.edtOTP2) ;
        edt_Otp3 = (EditText) findViewById(R.id.edtOTP3) ;
        edt_Otp4 = (EditText) findViewById(R.id.edtOTP4) ;

       // btn_Edit = (Button) findViewById(R.id.btnCancel);
       // btn_Verify = (Button) findViewById(R.id.btnLogin);

        edt_Otp1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (edt_Otp1.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    edt_Otp2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void afterTextChanged(Editable s) {

            }
        });

        edt_Otp2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (edt_Otp2.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    edt_Otp3.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void afterTextChanged(Editable s) {
                if(edt_Otp1.getText().toString().length()> 0 && edt_Otp2.getText().toString().length() == 0){
                    edt_Otp1.requestFocus();
                }
            }
        });

        edt_Otp3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (edt_Otp3.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    edt_Otp4.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void afterTextChanged(Editable s) {
               // edt_Otp2.requestFocus();

                if(edt_Otp2.getText().toString().length()> 0 && edt_Otp3.getText().toString().length() == 0){
                    edt_Otp2.requestFocus();
                }
            }
        });

        edt_Otp4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (edt_Otp4.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                      hideKeyboardFrom(ActivityOTP.this,edt_Otp4);
                      checkOTP();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void afterTextChanged(Editable s) {
              //  edt_Otp3.requestFocus();
              if(edt_Otp3.getText().toString().length()> 0 && edt_Otp4.getText().toString().length() == 0){
                  edt_Otp3.requestFocus();
              }
            }
        });


//        edt_Otp2.setOnKeyListener(new View.OnKeyListener() {
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                // TODO Auto-generated method stub
//                edt_Otp1.requestFocus();
//                return false;
//            }
//        });

//        edt_Otp3.setOnKeyListener(new View.OnKeyListener() {
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                // TODO Auto-generated method stub
//                edt_Otp2.requestFocus();
//                return false;
//            }
//        });

//        edt_Otp4.setOnKeyListener(new View.OnKeyListener() {
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                // TODO Auto-generated method stub
//                edt_Otp3.requestFocus();
//                return false;
//            }
//        });

        txt_Resend = (TextView) findViewById(R.id.txt_Resend);
    }


    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    /**
     * Starts SmsRetriever, which waits for ONE matching SMS message until timeout
     * (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
     * action SmsRetriever#SMS_RETRIEVED_ACTION.
     */
    private void startSMSListener() {

        smsReceiver = new SMSReceiver();
        smsReceiver.setOTPListener(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        this.registerReceiver(smsReceiver, intentFilter);

        SmsRetrieverClient client = SmsRetriever.getClient(this);

        Task<Void> task = client.startSmsRetriever();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // API successfully started
                Log.e("OTP","Success");
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Fail to start API
                Log.e("OTP","Fail");
            }
        });

        try {

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error",e.getMessage());
        }
    }


    @Override
    public void onOTPReceived(String otp) {
        showToast("OTP Received: " + otp);
        Log.e("OTP","OTP Received:"  + otp);
        Pattern pattern = Pattern.compile("(\\d{4})");
         Matcher matcher = pattern.matcher(otp);
        String otp1 = "";
       if (matcher.find()) {
           otp1 = matcher.group(0);  // 4 digit number
        } else {

         }
       setOtp(otp1);
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
            smsReceiver = null;
        }
    }

    @Override
    public void onOTPTimeOut() {
        showToast("OTP Time out");
    }

    @Override
    public void onOTPReceivedError(String error) {
        showToast(error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
        }
    }


    private void showToast(String msg) {
    }


    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            int receiveSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
            int readSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.RECEIVE_SMS);
            }
            if (readSMS != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_SMS);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
                return false;
            }
            return true;
        }
        return true;

    }


    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private void callLoginAPI(String str_username, String str_password) {

        if (AppUtils.isNetworkAvailable(obj_OTP)) {
            Uttils.showProgressDialoug(obj_OTP);
            Call<JsonObject> callRequestUpdate = mAPIInterface.getUpdateToken(str_username, str_password, Const.WEB_SERVICES_PARAM.KEY_PASSWORD);
            callRequestUpdate.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Uttils.hideProgressDialoug();

                    if (response.isSuccessful()) {
                        if (response.code() == 200 && response.body() != null) {

                            try {
                                String jsonString = response.body().toString();
                                JSONObject jsonObject = new JSONObject(jsonString);

                                if (jsonObject.has(Const.WEB_SERVICES_PARAM.KEY_TOKEN_TYPE) && jsonObject.has(Const.WEB_SERVICES_PARAM.KEY_ACCESS_TOKEN)) {
                                    String token = jsonObject.getString(Const.WEB_SERVICES_PARAM.KEY_TOKEN_TYPE) + " " + jsonObject.getString(Const.WEB_SERVICES_PARAM.KEY_ACCESS_TOKEN);
                                    mUserAuthorization.setAuthorizationToken(token);
                                    mUserAuthorization.setUserCredential(str_username, str_password, true);
                                    Intent va = new Intent(obj_OTP, CustomerBookingNavigationActivity.class);
                                    va.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(va);
                                    finish();
                                } else
                                    Toast.makeText(obj_OTP, "The ic_user name or password is incorrect.", Toast.LENGTH_LONG).show();

                                Uttils.hideProgressDialoug();
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Uttils.hideProgressDialoug();
                                AppUtils.displayErrorMessage(obj_OTP, jsonObject.getString("error_description"));
                            } catch (Exception error) {
                                Uttils.hideProgressDialoug();
                                Log.e(getClass().getSimpleName(), "ERROR " + error.getMessage());
                            }
                        }

                    } else
                        Toast.makeText(obj_OTP, "The ic_user name or password is incorrect.", Toast.LENGTH_LONG).show();//DisplayEmptyDialog(getActivity(), -1);

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Uttils.hideProgressDialoug();
                }
            });

        } else {
            Uttils.hideProgressDialoug();
            Toast.makeText(obj_OTP, R.string.error_msg_network, Toast.LENGTH_SHORT).show();
            //displayAlertErrorNetwork(CustomerBookingNavigationActivity.this);
        }
    }


    public void   checkOTP(){
     str_VerfityOTP = edt_Otp1.getText().toString() + edt_Otp2.getText().toString() + edt_Otp3.getText().toString() + edt_Otp4.getText().toString();

     if (str_VerfityOTP == null || str_VerfityOTP.equals("")){
         Uttils.showToast(obj_OTP,"Please Enter OTP!");
     }else if (str_VerfityOTP.length() <4){
         Uttils.showToast(obj_OTP,"Please Enter 4 Digit OTP!");
     }
     else {
         if (!str_VerfityOTP.equals(str_OTP)){
             Uttils.showToast(obj_OTP,"The OTP entered is incorrect. Please enter correct OTP or try regenerating the OTP");
         }
         else {
             callLoginAPI(str_Username,str_Password);
         }
     }
    }

    private void callLoginResponse() {
        if (isInternetPresent) {
            Uttils.showProgressDialoug(obj_OTP);
            Call<getLoginResonse> wsCallingSiteProgress = mAPIInterface.getUserLogin(str_Block,str_Flat);
            wsCallingSiteProgress.enqueue(new Callback<getLoginResonse>() {
                @Override
                public void onResponse(Call<getLoginResonse> call, Response<getLoginResonse> response) {
                    Uttils.hideProgressDialoug();
                    if(response.isSuccessful()){

                        if (response.body().getStatusCode() == 1){
                            Uttils.showToast(obj_OTP,"Resend OTP successfully!");
                            str_Username = response.body().getData().getUsername();
                            str_Password = response.body().getData().getPassword();
                            str_OTP = response.body().getData().getOTp().toString();
                             startTimer();
                        }
                        else {
                            Uttils.showToast(obj_OTP,response.body().getMessage());
                        }
                    }else {
                        Uttils.showToast(obj_OTP,"Incorrect block or flat no!");
                    }

                }

                @Override
                public void onFailure(Call<getLoginResonse> call, Throwable t) {
                    Log.e("ResponseCode",t.getMessage());
                    Uttils.hideProgressDialoug();
                    Uttils.showToast(obj_OTP,t.getMessage());
                }
            });
        }
    }
}
