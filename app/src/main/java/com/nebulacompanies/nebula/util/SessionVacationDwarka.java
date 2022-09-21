package com.nebulacompanies.nebula.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SessionVacationDwarka {

    private SharedPreferences customer_sharedpreferences;
    SharedPreferences.Editor customer_editor;
    public static final String VACATION_PREFERENCES = "VacationPrefs";
    public static final String Customer_Login = "false";
    public static final String Customer_Logout = "false";

    public static final String CUSTOMER_LOGIN_ID = "CustIdKey";
    public static final String PASSWORD = "PasswordKey";
    public static final String CUSTOMER_NAME = "CustomerNameKey";
    public static final String MEMBER_ID = "MemberIdKey";

    public static final String PACKAGE_NAME = "PackageNameKey";
    public static final String PACKAGE_DATE = "PackageDateKey";
    public static final String PACKAGE_AMOUNT = "PackageAmountKey";
    public static final String BOOKING_DATE = "BookingDateKey";
    public static final String ENTRY_NO = "EntryNoKey";
    public static final String PACKAGE_ID = "PackageIdKey";
    public static final String CUSTOMER_ADDRESS = "CustomerAddressKey";
    public static final String PAYMENT_DATE = "PaymentDateKey";
    public static final String GENDER = "Gender";
    public static final String PRODUCT_SALE_ID = "ProductSaleID";

    private final static String ACCESS_TOKEN_HOLIDAY = "ACCESS_TOKEN";
    private final static String LOGIN_HOLIDAY = "ACCESS_LOGIN";

    public SessionVacationDwarka(Context context) {
        // TODO Auto-generated constructor stub
        customer_sharedpreferences = context.getSharedPreferences(VACATION_PREFERENCES, Context.MODE_PRIVATE);
        customer_editor = customer_sharedpreferences.edit();
    }

    public void setCustomerLoginID(String loginID) {
        customer_editor.putString(CUSTOMER_LOGIN_ID, loginID);
        customer_editor.commit();
    }

    public String getCustomerLoginID() {
        String login_id = customer_sharedpreferences.getString(CUSTOMER_LOGIN_ID, "");
        return login_id;
    }

    public void setPassword(String pwd) {
        customer_editor.putString(PASSWORD, pwd);
        customer_editor.commit();
    }

    public String getPassword() {
        String password = customer_sharedpreferences.getString(PASSWORD, "");
        return password;
    }

    public void setCustomerName(String customerName) {
        customer_editor.putString(CUSTOMER_NAME, customerName);
        customer_editor.commit();
    }

    public String getCustomerName() {
        String customer_name = customer_sharedpreferences.getString(CUSTOMER_NAME, "");
        return customer_name;
    }

    public void setMemberId(String memberId) {
        customer_editor.putString(MEMBER_ID, memberId);
        customer_editor.commit();
    }

    public String getMemberId() {
        String member_id = customer_sharedpreferences.getString(MEMBER_ID, "");
        return member_id;
    }

    public void setCustomerLogin(Boolean aBoolean) {
        customer_editor.putBoolean(Customer_Login, aBoolean);
        customer_editor.commit();
    }

    public Boolean getCustomerLogin() {
        Boolean login = customer_sharedpreferences.getBoolean(Customer_Login, false);
        return login;
    }

    public void setCustomerLogout(Boolean bBoolean) {
        customer_editor.putBoolean(Customer_Logout, bBoolean);
        customer_editor.commit();
    }

    public Boolean getCustomerLogout() {
        Boolean logout = customer_sharedpreferences.getBoolean(Customer_Logout, false);
        return logout;
    }

    public void setPaymentDate(String paymentDate) {
        customer_editor.putString(PAYMENT_DATE, paymentDate);
        customer_editor.commit();
    }

    public String getPaymentDate() {
        String payment_date = customer_sharedpreferences.getString(PAYMENT_DATE, "");
        return payment_date;
    }

    public void setPackageDate(String packageDate) {
        customer_editor.putString(PACKAGE_DATE, packageDate);
        customer_editor.commit();
    }

    public String getPackageDate() {
        String package_date = customer_sharedpreferences.getString(PACKAGE_DATE, "");
        return package_date;
    }

    public void setPackageAmount(String packageAmount) {
        customer_editor.putString(PACKAGE_AMOUNT, packageAmount);
        customer_editor.commit();
    }

    public String getPackageAmount() {
        String package_amount = customer_sharedpreferences.getString(PACKAGE_AMOUNT, "");
        return package_amount;
    }

    public void setBookingDate(String bookingDate) {
        customer_editor.putString(BOOKING_DATE, bookingDate);
        customer_editor.commit();
    }

    public String getBookingDate() {
        String booking_date = customer_sharedpreferences.getString(BOOKING_DATE, "");
        return booking_date;
    }

    public void setEntryNo(String entryNo) {
        customer_editor.putString(ENTRY_NO, entryNo);
        customer_editor.commit();
    }

    public String getEntryNo() {
        String entry_no = customer_sharedpreferences.getString(ENTRY_NO, "");
        return entry_no;
    }

    public void setPackageId(String packageId) {
        customer_editor.putString(PACKAGE_ID, packageId);
        customer_editor.commit();
    }

    public String getPackageId() {
        String package_id = customer_sharedpreferences.getString(PACKAGE_ID, "");
        return package_id;
    }

    public void setCustomerAddress(String customerAddress) {
        customer_editor.putString(CUSTOMER_ADDRESS, customerAddress);
        customer_editor.commit();
    }

    public String getCustomerAddress() {
        String customer_address = customer_sharedpreferences.getString(CUSTOMER_ADDRESS, "");
        return customer_address;
    }

    public void setPackageName(String packageName) {
        customer_editor.putString(PACKAGE_NAME, packageName);
        customer_editor.commit();
    }

    public String getPackageName() {
        String package_name = customer_sharedpreferences.getString(PACKAGE_NAME, "");
        return package_name;
    }

    public void setGender(String Gender) {
        customer_editor.putString(GENDER, Gender);
        customer_editor.commit();
    }

    public String getGender() {
        String gender = customer_sharedpreferences.getString(GENDER, "");
        return gender;
    }

    public void setProductSaleId(String productSaleId) {
        customer_editor.putString(PRODUCT_SALE_ID, productSaleId);
        customer_editor.commit();
    }

    public String getProductSaleId() {
        String saleId = customer_sharedpreferences.getString(PRODUCT_SALE_ID, "");
        return saleId;
    }

    public void clear() {
        customer_editor.remove(CUSTOMER_LOGIN_ID).commit();
        customer_editor.remove(PACKAGE_NAME).commit();
        customer_editor.remove(PACKAGE_DATE).commit();
        customer_editor.remove(PACKAGE_AMOUNT).commit();
        customer_editor.remove(BOOKING_DATE).commit();
        customer_editor.remove(ENTRY_NO).commit();
        customer_editor.remove(PACKAGE_ID).commit();
        customer_editor.remove(CUSTOMER_NAME).commit();
        customer_editor.remove(CUSTOMER_ADDRESS).commit();
        customer_editor.remove(PAYMENT_DATE).commit();
        customer_editor.remove(GENDER).commit();
        //customer_editor.clear();
        customer_editor.commit();
    }


    public void setHolidayUserCredential(String username, String pwd) {
        customer_sharedpreferences.edit().putString(Constants.WEB_SERVICES_PARAM.KEY_USER_NAME_HOLIDAY, username).commit();
        customer_sharedpreferences.edit().putString(Constants.WEB_SERVICES_PARAM.KEY_PASSWORD_HOLIDAY, pwd).commit();
    }

    public String getcustomerCredential()
    {
        return customer_sharedpreferences.getString(Constants.WEB_SERVICES_PARAM.KEY_USER_NAME_HOLIDAY,null);
    }
    /**
     * This Method get Access Token
     *
     * @return the authorization token.
     */
    public String getAccessTokenHoliday() {
        return customer_sharedpreferences.getString(ACCESS_TOKEN_HOLIDAY, "None");
    }

    public void setAccessTokenHoliday(String token) {
        customer_sharedpreferences.edit().putString(ACCESS_TOKEN_HOLIDAY, token).commit();
    }

    // Added By Sagar Virvani
    public void setCustomerDetails(String jsonString) {
        customer_sharedpreferences.edit().putString("CustomerDetails", jsonString).commit();
    }

    public void setCustomerConfirmationMessage(String Message) {
        customer_sharedpreferences.edit().putString("CustomerConfirmationMessage", Message).commit();
    }

    public String getCustomerConfirmationMessage() {
        return customer_sharedpreferences.getString("CustomerConfirmationMessage", null);
    }


    public void sharePreferenceUserRole(String mRole) {
        customer_sharedpreferences.edit().putString("USER_ROLE", mRole).commit();
    }

    public String getSharePreferenceUserRole() {
        return customer_sharedpreferences.getString("USER_ROLE", null);
    }

}
