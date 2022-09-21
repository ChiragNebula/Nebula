package com.nebulacompanies.nebula.CustomerBooking.Utils;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Toast;


import com.nebulacompanies.nebula.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Class : AppUtils
 * Details: This Class for Declare Application Variables and Method
 * Create by : Jadav Chirag At NebulaApplication Infraspace LLP 08-09-2017
 * Modification by :
 */

public class AppUtils {

    public static String FCM_TOKEN = "";

    public static int NOTIFICATION_COUNT = 0;
    public static String FONT_STYLE = "fonts/Calibri.ttf";

    public static DecimalFormat IndianCurrencyFormat = new DecimalFormat("##,##,###");

    public static String DEVICE_COUNTRY_ISO = "+91 - ";



    public static String titleOfFirstApplicant = "Mr.", titleOfSecondApplicant = "Mr.";



    public static ProgressDialog progressDialog;
    public static ArrayList<String> arrayListEmailSuggestion = new ArrayList<>();
    //public static String[] arrayCity;
    //public static ArrayList<IndianStateCityDetails> arrayListStateCity = new ArrayList<>();
    public static String[] arrayResidentialStatus;

    // All world country state and cities

    public static String[] arrayState = {" Gujarat", "Andhra Pradesh",
            "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Haryana", "Himachal Pradesh",
            "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur",
            "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu",
            "Telangana", "Tripura", "Uttar Pradesh", " Uttarakhand", "West Bengal"};

    public static String[] arrayCountry = {"India", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
            "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

    /**
     * This method for Display error message.
     *
     * @param mContext The current class context.
     * @param mMessage The display message.
     */
    public static final void displayErrorMessage(final Context mContext, final String mMessage) {
        Toast.makeText(mContext, mMessage, Toast.LENGTH_LONG).show();
    }

    public static void displayErrorMessageInternalServer(Context mContext) {
        Toast.makeText(mContext, "Internal Server Error.Please try again", Toast.LENGTH_LONG).show();
    }

    /**
     * This method getting custom font type face.
     *
     * @param mContext The Current Class context object.
     * @return The Typeface Object.
     */
    public static Typeface getTypeface(final Context mContext) {
        Typeface tf1 = Typeface.createFromAsset(mContext.getAssets(), "fonts/tt0140m_2.ttf");
        return tf1;
    }

    /**
     * This method for check valid Aadhar no or not.
     *
     * @param AadharNo AadharNo
     * @return True if Enter AadharNo is valid Else return false.
     */
    public static boolean validAadharNo(String AadharNo) {
        Pattern pattern = Pattern.compile("[0-9]{4}[ ]{1}[0-9]{4}[ ]{1}[0-9]{4}");

        Matcher matcher = pattern.matcher(AadharNo);
        if (matcher.matches()) {
            return true;
        } else
            return false;
    }


    public static void displayAlertErrorNetwork(final Context mContext) {

//
//
//        FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
//                //.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_cloud_off_black_24dp))
//                .setImageResource(R.drawable.ic_cloud_off_black_24dp)
//                .setTextTitle("Oops.!")
//                .setBackgroundColor(R.color.white)
//                .setTextSubTitle("No internet connection.")
//                .setBody("No internet connection. We can notify you when you can view this page.")
//                .setNegativeColor(R.color.colorNegative)
//                .setPositiveButtonText("Ok")
//                .setPositiveColor(R.color.colorPositive)
//                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
//                    @Override
//                    public void OnClick(View view, Dialog dialog) {
//                        dialog.dismiss();
//                    }
//                })
//                /* .setAutoHide(true)*/
//                .build();
//        alert.show();
    }

    /**
     * This method check enter PAN Number is valid or not
     *
     * @param panNumber PAN Number
     * @return True if Enter PAN Number is Valid Else return False.
     */
    public static boolean validPANNo(String panNumber) {
        Pattern pattern = Pattern.compile("[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}");

        Matcher matcher = pattern.matcher(panNumber);
        if (matcher.matches()) {
            return true;
        } else
            return false;
    }

    /**
     * This method for check Driving License number is valid format or not.
     *
     * @param DrivingLicense Driving License number
     * @return True if enter Driving License number is valid Else return False.
     */
    public static boolean validDrivingLicense(String DrivingLicense) {
        Pattern pattern = Pattern.compile("[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}");
        Matcher matcher = pattern.matcher(DrivingLicense);
        if (matcher.matches()) {
            return true;
        } else
            return false;
    }

    /**
     * This method for check Passport number is valid or not.
     *
     * @param passportNumber
     * @return True if enter passport number format is valid else return False.
     */
    public static boolean validPassport(String passportNumber) {
        String panPattern = "/[a-zA-Z]{2}[0-9]{7}/";
        if (passportNumber.matches(panPattern) && passportNumber.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * THis method for check enter mobile number is valid on Indian mobile number format.
     *
     * @param MobileNumber Mobile Number
     * @return True if mobile number is valid else return false.
     */
    public static boolean validMobile(String MobileNumber) {
        if (MobileNumber.length() != 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method for check internet connection is connected or not.
     *
     * @param context The Current Class context.
     * @return True if internet is connected else return false.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method for display process dialog.
     *
     * @param context
     */
    public static void displayProcess(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("please wait.");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * This method for convert Date object into time Distance String
     *
     * @param notificationDate
     * @return The String value for distance Date.
     */
    public static String toTimeDistance(Date notificationDate) {

        String relativeTime = "";
        DateFormat timeFormatDisplay = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEE");
        SimpleDateFormat monthNameFormat = new SimpleDateFormat("dd MMM");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date convertedDate = null;
        try {
            long now = System.currentTimeMillis();
            String datetime1 = notificationDate.toString();
            convertedDate = dateFormat.parse(datetime1);
            CharSequence relavetime1 = DateUtils.getRelativeTimeSpanString(
                    convertedDate.getTime(),
                    now,
                    DateUtils.SECOND_IN_MILLIS,
                    DateUtils.FORMAT_NUMERIC_DATE);


            String CurrentString = relavetime1.toString();
            String[] separated = CurrentString.split(" ");
            if (separated != null)

            //TODO : Display Week Day With the Name
            if (relavetime1.toString().equalsIgnoreCase("0 seconds ago"))
                relativeTime = "Just now";
            else if (separated != null && separated[1].equalsIgnoreCase("hours") && Integer.parseInt(separated[0]) > 11)
                relativeTime = "Yesterday at " + timeFormatDisplay.format(convertedDate);
            else if (separated != null && separated[1].equalsIgnoreCase("days") && Integer.parseInt(separated[0]) > 1 && Integer.parseInt(separated[0]) <= 7)
                relativeTime = dayNameFormat.format(convertedDate) + " at " + timeFormatDisplay.format(convertedDate);
            else if (separated != null && separated[1].equalsIgnoreCase("seconds") && Integer.parseInt(separated[0]) > 0 && Integer.parseInt(separated[0]) <= 60)
                relativeTime = relavetime1.toString();
            else if (separated != null && separated[1].equalsIgnoreCase("minutes") && Integer.parseInt(separated[0]) > 0 && Integer.parseInt(separated[0]) <= 60)
                relativeTime = relavetime1.toString();
            else
                relativeTime = monthNameFormat.format(convertedDate) + " at " + timeFormatDisplay.format(convertedDate);

            return relativeTime;
        } catch (Exception e) {
            //Log.e("TAG", "ERROR " + e.getMessage());
            relativeTime = monthNameFormat.format(convertedDate) + " at " + timeFormatDisplay.format(convertedDate);
            return relativeTime;
        }
    }



}