package com.nebulacompanies.nebula;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;

/**
 * Created by Palak Mehta on 12/27/2016.
 */

public class Permissions {

    public static boolean isReadPhoneStatePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE},1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    public static boolean isReadStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    public static boolean isReadContactsPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_CONTACTS}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

     public static boolean isLocationPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 4);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    public static boolean isReadWriteStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 5);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    public static boolean isWriteStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 6);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    public static boolean isCameraPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA},7);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    public static boolean isSendSmsPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS},8);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    public static boolean isReadSmsPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_SMS},9);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    public static boolean isReceiveSmsPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECEIVE_SMS},10);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }

    /*public static boolean isReadPhonePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE}, 4);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }*/



    /*public static boolean isCallPhonePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.i("INFO","Permission is granted");
                return true;
            } else {
                Log.i("INFO","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.CALL_PHONE}, 4);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.i("INFO","Permission is granted");
            return true;
        }
    }*/
}
