package com.nebulacompanies.nebula.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private OTPReceiveListener otpReceiveListener;

    public SmsBroadcastReceiver() {
    }

    public void init(OTPReceiveListener otpReceiveListener) {
        this.otpReceiveListener = otpReceiveListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            String message1 = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
            Log.e("OTP:", message1);
            if (extras != null) {
                Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
                if (status != null)
                    switch (status.getStatusCode()) {
                        case CommonStatusCodes.SUCCESS:
                            // Get SMS message contents
                            String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                            if (message != null) {
                                Pattern pattern = Pattern.compile("(\\d{6})");
                                //   \d is for a digit
                                //   {} is the number of digits here 4.
                                Matcher matcher = pattern.matcher(message);
                                String val = "";
                                if (matcher.find()) {
                                    val = matcher.group(0);  // 4 digit number
                                    if (this.otpReceiveListener != null)
                                        this.otpReceiveListener.onOTPReceived(val);
                                } else {
                                    if (this.otpReceiveListener != null)
                                        this.otpReceiveListener.onOTPReceived(null);
                                }
                            }
                            break;
                        case CommonStatusCodes.TIMEOUT:
                            if (this.otpReceiveListener != null)
                                this.otpReceiveListener.onOTPTimeOut();
                            break;
                    }
            }
        }
    }

    public interface OTPReceiveListener {
        void onOTPReceived(String otp);

        void onOTPTimeOut();
    }
}
