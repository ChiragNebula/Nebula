package com.nebulacompanies.nebula.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Palak Mehta on 10/19/2016.
 */

public class Session {

    private SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String LOGIN_ID = "idKey";
    public static final String RANK_ID = "RanKID";
    public static final String Login = "false";
    public static final String Logout = "false";
    public static final String USER_NAME = "nameKey";
    //public static final String PASSWORD = "passwordKey";

    private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String REFRESH_TOKEN = "REFRESH_TOKEN";



    public Session(Context context) {
        // TODO Auto-generated constructor stub
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void setLoginID(String loginID) {
        editor.putString(LOGIN_ID, loginID);
        editor.commit();
    }

    public String getLoginID() {
        String login_id = sharedpreferences.getString(LOGIN_ID, "");
        return login_id;
    }

    public void setRankID(String rankID) {
        editor.putString(RANK_ID, rankID);
        editor.commit();
    }

    public String getRankID() {
        String rank_id = sharedpreferences.getString(RANK_ID, "");
        return rank_id;
    }

    public void setUserName(String userName) {
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public String getUserName() {
        String user_name = sharedpreferences.getString(USER_NAME, "");
        return user_name;
    }

    public void setLogin(Boolean aBoolean) {
        editor.putBoolean(Login, aBoolean);
        editor.commit();
    }

    public Boolean getLogin() {
        Boolean login = sharedpreferences.getBoolean(Login, false);
        return login;
    }

    public void setLogout(Boolean bBoolean) {
        editor.putBoolean(Logout, bBoolean);
        editor.commit();
    }

    public Boolean getLogout() {
        Boolean logout = sharedpreferences.getBoolean(Logout, false);
        return logout;
    }

    public void setUserCredential(String username, String pwd) {
        sharedpreferences.edit().putString(Constants.WEB_SERVICES_PARAM.KEY_USER_NAME, username).commit();
        sharedpreferences.edit().putString(Constants.WEB_SERVICES_PARAM.KEY_PASSWORD, pwd).commit();
    }

    /**
     * This method get for
     *
     * @return
     */
    /*public boolean getUserLogin() {
        //return sharedpreferences.getBoolean(IS_LOGIN, false);
    }*/

    /**
     * This Method get Access Token
     *
     * @return the authorization token.
     */
    public String getAccessToken() {
        return sharedpreferences.getString(ACCESS_TOKEN, "None");
    }

    public void setAccessToken(String token) {
        sharedpreferences.edit().putString(ACCESS_TOKEN, token).commit();
    }

    /**
     * This Method get Access Token
     *
     * @return the authorization token.
     */
    public String getRefreshToken() {
        return sharedpreferences.getString(REFRESH_TOKEN, "None");
    }

    public void setRefreshToken(String refreshToken) {
        sharedpreferences.edit().putString(REFRESH_TOKEN, refreshToken).commit();
    }

    public void clear() {
        editor.remove(LOGIN_ID).commit();
        editor.remove(ACCESS_TOKEN).commit();
        editor.remove(REFRESH_TOKEN).commit();
        editor.remove(Constants.WEB_SERVICES_PARAM.KEY_USER_NAME).commit();
        editor.remove(Constants.WEB_SERVICES_PARAM.KEY_PASSWORD).commit();
        //editor.clear();
        editor.commit();
    }
    // Added By Jadav Chirag Holiday User Login Details


}
