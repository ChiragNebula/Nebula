package com.nebulacompanies.nebula.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Palak Mehta on 3/29/2016.
 */
public class ConnectionDetector {

    private Context _context;

    public ConnectionDetector(Context context) {
        this._context = context;
    }

    public boolean isConnectingToInternet(){
        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                //Toast.makeText(_context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            }
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                //Toast.makeText(_context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return true;
            }
            else {
                //Toast.makeText(_context, "No Network ", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            // not connected to the internet
            return false;
        }
    }

    public boolean isConnectingToInternet1(){
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (connectivity != null) {
            if (wifi.isConnectedOrConnecting()) {
                //Toast.makeText(_context, "Wifi", Toast.LENGTH_LONG).show();
                return true;
            } else if (mobile.isConnectedOrConnecting()) {
                //Toast.makeText(_context, "Mobile 3G ", Toast.LENGTH_LONG).show();
                return true;
            } else {
                //Toast.makeText(_context, "No Network ", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else {
            return false;
        }

            /*if (connectivity != null)
            {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        {
                            return true;
                        }
            }*/
        //return false;
    }
}
