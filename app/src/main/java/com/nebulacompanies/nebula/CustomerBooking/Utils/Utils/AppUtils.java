package com.nebulacompanies.nebula.CustomerBooking.Utils.Utils;

import static com.nebulacompanies.nebula.CustomerBooking.Utils.Utils.DateConverter.toDate;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateUtils;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class : AppUtils
 * Details: This Class for Declare Application Variables and Method
 * Create by : Jadav Chirag At NebulaApplication Infraspace LLP 08-09-2017
 * Modification by :
 */

public class AppUtils {

    public static String DisplayDate(long dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        return dateFormat.format(toDate(dateTime));
    }

}