package com.nebulacompanies.nebula.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Palak Mehta on 8/9/2016.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    ConnectionDetector cd;
    static public Boolean isInternetPresent = false;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        System.out.println("NetworkChangeReceiver onReceive");
        cd = new ConnectionDetector(context.getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        /*Toast toast1 = Toast.makeText(context, "NetworkChangeReceiver : onReceive : " + isInternetPresent , Toast.LENGTH_SHORT);
        toast1.show();*/
    }

}
