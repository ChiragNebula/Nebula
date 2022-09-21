package com.nebulacompanies.nebula.CustomerBooking.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class : UserAuthorization
 * Details: This Class for User Authorization & Token Access.
 * Create by : Jadav Chirag At NebulaApplication Infraspace LLP 23-11-2017
 * Modification by :
 */

public class UserAuthorization {

    private static SharedPreferences sharedpreferences;

    private final String KEY_PREFERENCES = "NebulaCustomerBooking";
    private final static String PRE_TOKEN = "ACCESS_TOKEN";
    private final static String PRE_AUTHORIZATION_DETAILS = "AUTHORIZATION_DETAILS";
    private final static String PRE_IS_LOGIN = "IS_LOGIN";
    private final static String PRE_FCM_TOKEN = "FCM_TOKEN";

    public UserAuthorization(Context mContext) {
        //  if(sharedpreferences!=null)
        sharedpreferences = mContext.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
    }

    /**
     * This Method get Access Token
     *
     * @return the authorization token.
     */
    public static String getAuthorizationToken() {
        return sharedpreferences.getString(PRE_TOKEN, "None");
    }

    public static void setAuthorizationToken(String token) {
        sharedpreferences.edit().putString(PRE_TOKEN, token).commit();
    }

    /**
     * This method for set the bunch of user details string
     *
     * @param userAuthorization
     */
    public static void setUserAuthorization(String userAuthorization) {
        sharedpreferences.edit().putString(PRE_AUTHORIZATION_DETAILS, userAuthorization).commit();
    }

    /**
     * This method for store user credential for re use.
     *
     * @param username The User name
     * @param pwd      The Encoded Password.
     */
    public static void setUserCredential(String username, String pwd, boolean isLogin) {
        sharedpreferences.edit().putString(Const.WEB_SERVICES_PARAM.KEY_USER_NAME, username).commit();
        sharedpreferences.edit().putString(Const.WEB_SERVICES_PARAM.KEY_PASSWORD, pwd).commit();
        sharedpreferences.edit().putBoolean(PRE_IS_LOGIN, isLogin).commit();
    }

    /**
     * This method get for
     *
     * @return True id user has login else return false.
     */
    public static boolean getUserLogin() {
        return sharedpreferences.getBoolean(PRE_IS_LOGIN, false);
    }

    /**
     * This method for getting the user name
     *
     * @return The user name.
     */
    public static String getUserName() {
        return sharedpreferences.getString(Const.WEB_SERVICES_PARAM.KEY_USER_NAME, "None");
    }



    /**
     * This method for getting the user pwd.
     *
     * @return The password.
     */
    public static String getPassword() {
        return sharedpreferences.getString(Const.WEB_SERVICES_PARAM.KEY_PASSWORD, "None");
    }


    public static void setUserProfileName(String strprofilename) {
        sharedpreferences.edit().putString(Const.WEB_SERVICES_PARAM.KEY_USER_PROFILE_NAME, strprofilename).commit();
    }


    /**
     * This method for getting the user pwd.
     *
     * @return The password.
     */
    public static String getUserProfileName() {
        return sharedpreferences.getString(Const.WEB_SERVICES_PARAM.KEY_USER_PROFILE_NAME, "None");
    }

}
