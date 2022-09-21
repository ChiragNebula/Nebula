package com.nebulacompanies.nebula;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Const;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UI.Activity.CustomerBookingNavigationActivity;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UserAuthorization;
import com.nebulacompanies.nebula.CustomerBooking.Utils.widget.ShownEdittext;
import com.nebulacompanies.nebula.Model.Guest.VersionCheck;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.gui.CompanyEvents;
import com.nebulacompanies.nebula.gui.CompanyProfile;
import com.nebulacompanies.nebula.gui.CompanyVideos;
import com.nebulacompanies.nebula.gui.ContactUs;
import com.nebulacompanies.nebula.gui.EDocuments;
import com.nebulacompanies.nebula.gui.NebulaActivity;
import com.nebulacompanies.nebula.gui.Notifications;
import com.nebulacompanies.nebula.gui.Products;
import com.nebulacompanies.nebula.gui.SiteProducts;
import com.nebulacompanies.nebula.login.ActivityLogin;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.util.Session;
import com.nebulacompanies.nebula.util.SessionVacation;
import com.nebulacompanies.nebula.util.Uttils;
import com.nebulacompanies.nebula.view.MyTextView;
import com.nebulacompanies.nebula.view.NebulaEditText;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by Sagar Virvani on 15-02-2018.
 */

public class GuestActivity extends Base2Activity implements View.OnClickListener {


    LinearLayout companyProfileLayout, productsLayout, facebookFeedsLayout, eventsLayout, videosLayout, logoLayout, contactUsLayout, notificationsLayout, /*newslettersLayout,*/
            siteprogressLayout, languageLayout,   customerLoginLayout;//vacationLayout,iboLoginLayout
    MyTextView companyProfileTextView, productsTextView, facebookFeedsTextView, eventsTextView, videosTextView, welcomeTextView, inviteToJoinTextView, distributorsTextView, contactUsTextView, notificationsTextView, newslettersTextView, siteprogressTextView, languageTextview, languageTitleTextView,
            customerLoginTextView;
    //vacationTextView
    //iboTextView
    //static BadgeView badgeView;
    static MyTextView badgeView;
    NotificationManager manager;

    AlertDialog alertDialog;
    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";
    RadioGroup radioGroup;
    RadioButton englishRadioButton, hindiRadioButton, gujaratiRadioButton, teluguRadioButton;
    RadioButton checkedRadioButton, savedCheckedRadioButton;
    Button cancelButton;
    String lang;
    float pixels;
    String version_name;
    int version_code;
    ConnectionDetector cd;
    //VersionChecker versionChecker = null;
    ViewPager pager;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    static public GoogleApiClient mGoogleApiClient;

    public static String IMEINumber;
    String Device_Serial_Number;
    String updatedDate = "";
    String vfile, path;
    //Date sentDate;
    Date newDate;
    ArrayList vCard;
    Cursor cursor = null;
    BroadcastReceiver mNetworkChangeReceiver;
    SharedPreferences sharedPreferences;
    Session session;
    //SessionVacation sessionVacation;
    ProgressDialog progressDialog;

    String TokenKey, DeviceId, IMEI1, IMEI2;
    private static APIInterface mAPIInterface;
    public static final String TAG = "GuestActivity";

    // private ShowcaseView showcaseView;
    private int counter = 0;
    TextPaint paint, title;

    //Shared Preferences Variables
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences.Editor editor;


    private UserAuthorization mUserAuthorization;
    Dialog  dialogs,dialogOtp;
    NebulaEditText loginEditText, edtmid, edtNewPassword, edtUserName, edtConfirmPassword;
    RadioButton radioBtnEmail, radioBtnSms;

    //  Username
    String str_Username = "", str_Password = "";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_area);
        mUserAuthorization = new UserAuthorization(GuestActivity.this);
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        session = new Session(this);
        //sessionVacation = new SessionVacation(this);
        mAPIInterface = APIClient.getClient(GuestActivity.this).create(APIInterface.class);
        DetachScreen();
        getIDs();
        init();
        checkVersion();
        checkAndRequestPermissions();
        SetLanguage();
        loadLocale();
        //focusView(iboLoginLayout);
        /*Typeface typeface = Typeface.createFromAsset(getAssets(), Config.FONT_STYLE);
        TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setTypeface(typeface);
        paint.setTextAlign(Paint.Align.LEFT);
*/

       /* showcaseView = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(findViewById(R.id.ibo_layout)))
                .setContentTitle("IBO Login")
                .setContentText("Please click here to Login")
                .withMaterialShowcase()
                .setStyle(R.style.CustomShowcaseTheme2)
                .setOnClickListener(this)
                .build();*/

        /*showcaseView = new ShowcaseView.Builder(this)
                .withMaterialShowcase()
                .setTarget(new ViewTarget(findViewById(R.id.ibo_layout)))
                .setStyle(R.style.CustomShowcaseTheme2)
                .setContentText("Here's how to highlight items on a toolbar")
                .build();

        showcaseView.setButtonText(getString(R.string.next));*/

     /*   paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(getResources().getDimension(R.dimen.abc_text_size_body_1_material));
        //paint.setStrikeThruText(true);
        paint.setColor(Color.RED);
        //paint.setTypeface(Typeface.createFromAsset(getAssets(), "RobotoSlab-Regular.ttf"));

        title = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        title.setTextSize(getResources().getDimension(R.dimen.abc_text_size_headline_material));
        title.setUnderlineText(true);
        title.setColor(Color.YELLOW);
        //title.setTypeface(Typeface.createFromAsset(getAssets(), "RobotoSlab-Regular.ttf"));

        showcaseView = new ShowcaseView.Builder(this)
                //.withNewStyleShowcase()
                //.withMaterialShowcase()
                .withHoloShowcase()
                .setTarget(new ViewTarget(R.id.ibo_layout, this))
                .setContentTextPaint(paint)
                .setContentTitle("IBO Login")
                .setContentText("Please click here to Login")
                .setContentTitlePaint(title)
                .setStyle(R.style.CustomShowcaseTheme2)
                .setOnClickListener(this)
                .build();

        //setAlpha(0.8f, showcaseView);
        showcaseView.setDetailTextAlignment(Layout.Alignment.ALIGN_CENTER);
        showcaseView.setTitleTextAlignment(Layout.Alignment.ALIGN_CENTER);
        //showcaseView.forceTextPosition(ShowcaseView.BELOW_SHOWCASE);
        showcaseView.forceTextPosition(ShowcaseView.BELOW_SHOWCASE);
        showcaseView.setButtonText(getString(R.string.next));*/
        //showcaseView.setAlpha(0.8f);



        if ( mUserAuthorization.getUserProfileName() !=null &&  !mUserAuthorization.getUserProfileName().equals("") &&  !mUserAuthorization.getUserProfileName().equals("None")){
            customerLoginTextView.setText("Hi, "+mUserAuthorization.getUserProfileName());
        }
        else {
            customerLoginTextView.setText(getString(R.string.cust_login));
        }
    }

    public void focusView(View view) {
      /*  new FancyShowCaseView.Builder(this)
                .focusOn(view)
                .title("Click here to Login")
                .titleSize(50, 0)
                .titleStyle(R.style.TextStyle, Gravity.CENTER)
                .build()
                .show();*/
    }

    private void init() {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        LocalBroadcastManager.getInstance(this).registerReceiver(mNetworkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
    }

    private boolean checkAndRequestPermissions() {
        int read_phone_state = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int read_contacts = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        int read_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int write_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read_sms = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        int receive_sms = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        int camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (read_phone_state != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (read_contacts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (read_storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (write_storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (read_sms != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (receive_sms != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_SMS);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 11);
            return false;
        } else {
            /*if(isInternetPresent) {
             *//* if (NebulaApplication.getGoogleApiHelper().isConnected()) {
                    //Get google api client
                    mGoogleApiClient = NebulaApplication.getGoogleApiHelper().getGoogleApiClient();*//*
                    //if(isLocationPermissionGranted(this)) {
                    new LocationSender(this, mGoogleApiClient, "").execute();
                //}
            }*/
            //if(isReadPhoneStatePermissionGranted(this)) {
            //}
            // getDeviceInfo();
        }
        return true;
    }

    private void getIDs() {
        companyProfileLayout = (LinearLayout) findViewById(R.id.company_profile);
        productsLayout = (LinearLayout) findViewById(R.id.products_);
        facebookFeedsLayout = (LinearLayout) findViewById(R.id.facebook_feeds);
        eventsLayout = (LinearLayout) findViewById(R.id.events);
        videosLayout = (LinearLayout) findViewById(R.id.videos);
        logoLayout = (LinearLayout) findViewById(R.id.nebula_logo);
        contactUsLayout = (LinearLayout) findViewById(R.id.contact_us);
        notificationsLayout = (LinearLayout) findViewById(R.id.notifications);
        //newslettersLayout = (LinearLayout) findViewById(R.id.newsletters);
        siteprogressLayout = (LinearLayout) findViewById(R.id.siteprogress);
        languageLayout = (LinearLayout) findViewById(R.id.multi_langs);
//        vacationLayout = (LinearLayout) findViewById(R.id.vacation_thailand);
        //      iboLoginLayout = (LinearLayout) findViewById(R.id.ibo_layout);
        customerLoginLayout = (LinearLayout) findViewById(R.id.customer_booking_layout);


        companyProfileLayout.setOnClickListener(this);
        productsLayout.setOnClickListener(this);
        facebookFeedsLayout.setOnClickListener(this);
        eventsLayout.setOnClickListener(this);
        videosLayout.setOnClickListener(this);
        logoLayout.setOnClickListener(this);
        contactUsLayout.setOnClickListener(this);
        notificationsLayout.setOnClickListener(this);
        //newslettersLayout.setOnClickListener(this);
        siteprogressLayout.setOnClickListener(this);
        languageLayout.setOnClickListener(this);
        //vacationLayout.setOnClickListener(this);
        //iboLoginLayout.setOnClickListener(this);
        customerLoginLayout.setOnClickListener(this);

        //TextView
        companyProfileTextView = (MyTextView) findViewById(R.id.com);
        productsTextView = (MyTextView) findViewById(R.id.pro);
        facebookFeedsTextView = (MyTextView) findViewById(R.id.fb);
        eventsTextView = (MyTextView) findViewById(R.id.eve);
        videosTextView = (MyTextView) findViewById(R.id.vid);
        contactUsTextView = (MyTextView) findViewById(R.id.cu);
        notificationsTextView = (MyTextView) findViewById(R.id.noti);
        siteprogressTextView = (MyTextView) findViewById(R.id.site);
        languageTextview = (MyTextView) findViewById(R.id.fb_new);
        // vacationTextView = (MyTextView) findViewById(R.id.thai_text);
        badgeView = (MyTextView) findViewById(R.id.badge);
        //iboTextView = (MyTextView) findViewById(R.id.ibo_text);
        customerLoginTextView = (MyTextView) findViewById(R.id.cust_text);


        if ( mUserAuthorization.getUserProfileName() !=null &&  !mUserAuthorization.getUserProfileName().equals("") &&  !mUserAuthorization.getUserProfileName().equals("None")){
            customerLoginTextView.setText("Hi, "+mUserAuthorization.getUserProfileName());
        }
        else {
            customerLoginTextView.setText(getString(R.string.cust_login));
        }

        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (Config.NOTIFICATION_COUNT == 0) {
                    badgeView.setVisibility(View.INVISIBLE);
                   /* LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(45, 0, 0.85f);
                    ImageView imageView = (ImageView) getActivity().findViewById(R.id.ten);
                    imageView.setLayoutParams(params1);

                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0.15f);
                    //params.weight = 0.85f;
                    notificationsTextView.setLayoutParams(params2);*/
                } else {
                    badgeView.setVisibility(View.VISIBLE);
                }
                badgeView.setText(String.valueOf(Config.NOTIFICATION_COUNT));
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.post(runnable);
    }

    @SuppressLint("WrongConstant")
    private void SetLanguage() {
        lang = Locale.getDefault().getDisplayLanguage();
        if (lang.equals("தமிழ்")) {
            companyProfileTextView.setTextSize(pixels);
            productsTextView.setTextSize(pixels);
            facebookFeedsTextView.setTextSize(pixels);
            eventsTextView.setTextSize(pixels);
            videosTextView.setTextSize(pixels);
            contactUsTextView.setTextSize(pixels);
            notificationsTextView.setTextSize(pixels);
            //newslettersTextView.setTextSize(pixels);
            siteprogressTextView.setTextSize(pixels);
            languageTextview.setTextSize(pixels);
            //vacationTextView.setTextSize(pixels);
            companyProfileTextView.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            contactUsTextView.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            companyProfileTextView.setGravity(Gravity.CENTER);
            contactUsTextView.setGravity(Gravity.CENTER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                companyProfileTextView.setTextAlignment(Gravity.CENTER);
                contactUsTextView.setTextAlignment(Gravity.CENTER);
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.company_profile:
                Intent cp = new Intent(GuestActivity.this, CompanyProfile.class);
                startActivity(cp);
                break;
            case R.id.products_:
                Intent pro = new Intent(GuestActivity.this, Products.class);
                startActivity(pro);
                break;
            case R.id.facebook_feeds:
                /*Intent fb = new Intent(getActivity(), FacebookFeeds.class);
                startActivity(fb);*/

                Intent eb = new Intent(GuestActivity.this, EDocuments.class);
                startActivity(eb);

                break;
            case R.id.events:
                Intent eve = new Intent(GuestActivity.this, CompanyEvents.class);
                startActivity(eve);
                break;
            case R.id.videos:
               /* Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/channel/UCV240ViGsOswxb56m6XPjUA"));
                startActivity(intent);*/

                Intent vid = new Intent(GuestActivity.this, CompanyVideos.class);
                startActivity(vid);

               /* Intent vid = new Intent(getActivity(), VideoDownload.class);
                startActivity(vid);*/

               /* Intent vid = new Intent(getActivity(), YouTubeVideo.class);
                startActivity(vid);*/
                /*String url = "http://www.youtube.com/channel/" + CHANNEL_ID;
                Intent intent=null;
                try {
                    intent =new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.google.android.youtube");
                    intent.setData(Uri.parse(url));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }*/
                /*Intent intent = YouTubeIntents.createChannelIntent(getActivity(), CHANNEL_ID);
                startActivity(intent);*/
                break;
            case R.id.nebula_logo:
                Intent neb = new Intent(GuestActivity.this, NebulaActivity.class);
                startActivity(neb);
                break;
            case R.id.contact_us:
                Intent map = new Intent(GuestActivity.this, ContactUs.class);
                startActivity(map);
                break;
            case R.id.notifications:
//                Config.NOTIFICATION_COUNT = 0;
//                manager.cancelAll();
                Intent noti = new Intent(GuestActivity.this, Notifications.class);
               /* if(object != null) {
                    noti.putExtra("jsonObject", object.toString());
                }*/
                startActivity(noti);
                break;
            case R.id.siteprogress:
                Intent sp = new Intent(GuestActivity.this, SiteProducts.class);
                startActivity(sp);
                break;
            case R.id.multi_langs:
                if (Build.VERSION.SDK_INT <= 19) {
                    Toast.makeText(GuestActivity.this, "This device does not support multi-language.", Toast.LENGTH_SHORT).show();
                } else {
                    changeLanguage();
                }
                break;

        /*    case R.id.vacation_thailand:
                Intent vac = new Intent(GuestActivity.this, HolidayListActivity.class);
                startActivity(vac);
                break;*/

            case R.id.customer_booking_layout:


                if (!mUserAuthorization.getUserLogin()) {
                   // popupDialogFlatLogin();

                    Intent login = new Intent(GuestActivity.this, ActivityLogin.class);
                    startActivity(login);

                }
                else{
                    Intent va = new Intent(GuestActivity.this, CustomerBookingNavigationActivity.class);
                    startActivity(va);
                    finish();
                }

                break;
            // case R.id.ibo_layout:
               /* if (session.getLogin()) {
                    Intent dash = new Intent(GuestActivity.this, DashboardActivity.class);
                    startActivity(dash);
                }*/
                /*else if(sessionVacation.getCustomerLogin() && !session.getLogin()){
                    askHolidayLogout();
                }*/
            /* else {
             *//*progressDialog = new ProgressDialog(GuestActivity.this, R.style.MyTheme);
                    try {
                        progressDialog.show();
                    } catch (Error e) {
                    }
                    progressDialog.setContentView(R.layout.progressdialog);
                    progressDialog.setCancelable(true);
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));*//*

                    Intent login = new Intent(GuestActivity.this, LoginActivity.class);
                    startActivity(login);
                }*/


              /*  Intent login = new Intent(GuestActivity.this, CustomerLogin.class);
                startActivity(login);*/

               /* if (session.getLogin()) {
                    Intent dash = new Intent(GuestActivity.this, DashboardActivity.class);
                    startActivity(dash);
                }

                else {

                    Intent login = new Intent(GuestActivity.this, LoginActivity.class);
                    startActivity(login);
                }*/
                /*if (session.getLogin()) {
                    Intent dash = new Intent(GuestActivity.this, DashboardActivity.class);
                    dash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dash);
                    //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    //transitionTo(dash);
                } else {

                    Intent login = new Intent(GuestActivity.this, LoginActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(login);
                    // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    //transitionTo(login);
                }
                break;*/

            /*case R.id.showcase_button:

                switch (counter) {
                    case 0:
                        showcaseView.setShowcase(new ViewTarget(customerLoginLayout), true);
                        showcaseView.setContentTitle("Customer Login");
                        showcaseView.setContentText("Please click here to Login");
                        showcaseView.setStyle(R.style.CustomShowcaseTheme2);
                        showcaseView.forceTextPosition(ShowcaseView.BELOW_SHOWCASE);
                        showcaseView.setButtonText("Next");
                        //setAlpha(0.8f, showcaseView);
                        break;

                    case 1:
                        showcaseView.setShowcase(new ViewTarget(vacationLayout), true);
                        showcaseView.setContentTitle("Holiday Login");
                        showcaseView.setContentText("Please click here to Login");
                        showcaseView.setStyle(R.style.CustomShowcaseTheme2);
                        showcaseView.forceTextPosition(ShowcaseView.ABOVE_SHOWCASE);

                        //setAlpha(0.8f, showcaseView);
                        break;

                  *//*  case 2:
                        showcaseView.setTarget(Target.NONE);
                        showcaseView.setContentTitle("Check it out");
                        showcaseView.setContentText("You don't always need a target to showcase");
                        showcaseView.setButtonText("Close");
                        setAlpha(0.4f, iboLoginLayout, customerLoginLayout, vacationLayout);
                        break;*//*

                    case 2:
                        showcaseView.hide();
                        setAlpha(1.0f, iboLoginLayout, customerLoginLayout, vacationLayout);
                        break;
                }
                counter++;

                break;*/
        }
    }

    private void changeLanguage() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(GuestActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.language_settings, null);
        dialogBuilder.setView(dialogView);

        radioGroup = (RadioGroup) dialogView.findViewById(R.id.radiogroup);
        englishRadioButton = (RadioButton) dialogView.findViewById(R.id.option1);
        hindiRadioButton = (RadioButton) dialogView.findViewById(R.id.option2);
        teluguRadioButton = (RadioButton) dialogView.findViewById(R.id.option3);
        gujaratiRadioButton = (RadioButton) dialogView.findViewById(R.id.option4);
        cancelButton = (Button) dialogView.findViewById(R.id.cancel_for_lang);
        languageTitleTextView = (MyTextView) dialogView.findViewById(R.id.textViewtitle);

        LoadPreferences();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedRadioButton = (RadioButton) radioGroup.findViewById(checkedId);
                int checkedIndex = radioGroup.indexOfChild(checkedRadioButton);
                switch (checkedIndex) {
                    case 0: //English
                        PreferenceManager.getDefaultSharedPreferences(GuestActivity.this).edit().putString("LANG", "en").commit();
                        setLangRecreate("en");
                        SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                        alertDialog.dismiss();
                        return;
                    case 1: //Hindi
                        PreferenceManager.getDefaultSharedPreferences(GuestActivity.this).edit().putString("LANG", "hi").commit();
                        setLangRecreate("hi");
                        SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                        alertDialog.dismiss();
                        return;
                    case 2: //Telugu
                        PreferenceManager.getDefaultSharedPreferences(GuestActivity.this).edit().putString("LANG", "te").commit();
                        setLangRecreate("te");
                        SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                        alertDialog.dismiss();
                        return;
                    case 3: //Gujarati
                        PreferenceManager.getDefaultSharedPreferences(GuestActivity.this).edit().putString("LANG", "gu").commit();
                        setLangRecreate("gu");
                        SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);
                        alertDialog.dismiss();
                        return;
                    case 4: //Tamil
                        PreferenceManager.getDefaultSharedPreferences(GuestActivity.this).edit().putString("LANG", "ta").commit();
                        setLangRecreate("ta");
                        SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex);

                        alertDialog.dismiss();
                        return;
                    default: //By default set to english
                        PreferenceManager.getDefaultSharedPreferences(GuestActivity.this).edit().putString("LANG", "en").commit();
                        setLangRecreate("en");
                        SavePreferences(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
                        alertDialog.dismiss();
                        return;
                }
            }

        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        Typeface tf1 = Typeface.createFromAsset(getAssets(), Config.FONT_STYLE);
        cancelButton.setTypeface(tf1);
        englishRadioButton.setTypeface(tf1);
        hindiRadioButton.setTypeface(tf1);
        teluguRadioButton.setTypeface(tf1);
        gujaratiRadioButton.setTypeface(tf1);
        languageTitleTextView.setTypeface(tf1);

        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


    private void setAlpha(float alpha, View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }


    /**
     * This Method for login and show ic_user profile.
     *
     * @param
     */
    @SuppressLint("NewApi")
    private void popupDialogLogin() {
        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final Dialog dialog = new Dialog(GuestActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View convertView = mLayoutInflater.inflate(R.layout.dialog_customer_booking_login, null);
        dialog.setContentView(convertView);
        dialog.setCancelable(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final CardView cardLogin;
        final EditText txtUserName;
        final ShownEdittext txtPassword;
        final TextView txtForgotPwd;
        final Button btnCancel, btnLogin;
        final FloatingActionButton fab;



        //TODO: Initialization Control

        cardLogin = (CardView) convertView.findViewById(R.id.cardLogin);
        fab = (FloatingActionButton) convertView.findViewById(R.id.fab);
        txtUserName = (EditText) convertView.findViewById(R.id.txtUserName);
        txtPassword = (ShownEdittext) convertView.findViewById(R.id.txtPassword);
        txtForgotPwd = (TextView) convertView.findViewById(R.id.txtForgotPwd);
        btnCancel = (Button) convertView.findViewById(R.id.btnCancel);
        btnLogin = (Button) convertView.findViewById(R.id.btnLogin);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtUserName.setError(null);
                txtPassword.getEditText().setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        txtUserName.addTextChangedListener(textWatcher);
        txtPassword.getEditText().addTextChangedListener(textWatcher);

        SpannableString content = new SpannableString(getString(R.string.forgot_your_password));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        txtForgotPwd.setText(content);
        txtForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Modify Sagar Virvani
                dialogs = new Dialog(new android.view.ContextThemeWrapper(GuestActivity.this, R.style.Dialog));
                dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogs.setContentView(R.layout.forgot_password);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialogs.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialogs.getWindow().setAttributes(lp);

                edtmid = (NebulaEditText) dialogs.findViewById(R.id.edtmid);
                edtmid.setHint("Enter Your Customer ID");
                edtmid.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                edtmid.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                radioBtnEmail = (RadioButton) dialogs.findViewById(R.id.forgot_rd_mail);
                radioBtnSms = (RadioButton) dialogs.findViewById(R.id.forgot_rd_sms);
                Button dialogok = (Button) dialogs.findViewById(R.id.btnget);
                dialogok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        if (isInternetPresent) {
                            if (edtmid.getText().toString().length() == 0) {
                                edtmid.setError("Enter Your Customer ID");
                            }
                            //if (isSendSmsPermissionGranted(CustomerBookingNavigationActivity.this)&&isReadSmsPermissionGranted(CustomerBookingNavigationActivity.this)&&isReceiveSmsPermissionGranted(CustomerBookingNavigationActivity.this))
                            // forgotIBOPassword(edtmid.getText().toString());
                       /*else {
                           Toast.makeText(CustomerBookingNavigationActivity.this, R.string.give_sms_permission, Toast.LENGTH_SHORT).show();
                       }*/
                        } else {
                            Toast toast1 = Toast.makeText(GuestActivity.this, R.string.Network_is_not_available, Toast.LENGTH_SHORT);
                            toast1.show();
                        }
                    }
                });
                dialogs.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator mAnimator = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mAnimator = ViewAnimationUtils.createCircularReveal(cardLogin, cardLogin.getWidth() / 2, 0, cardLogin.getHeight(), fab.getWidth() / 2);
                }
                mAnimator.setDuration(500);
                mAnimator.setInterpolator(new AccelerateInterpolator());
                mAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cardLogin.setVisibility(View.INVISIBLE);
                        super.onAnimationEnd(animation);
                        dialog.dismiss();
                        //  navigationView.setCheckedItem(R.id.nav_dashboard);
                        //   getSupportActionBar().setTitle(getResources().getString(R.string.nav_dashboard));
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                });
                mAnimator.start();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtUserName.getText().toString().trim().isEmpty()) {
                    txtUserName.setError(getString(R.string.error_user_name));
                    txtUserName.requestFocus();
                } else if (txtPassword.getText().toString().trim().isEmpty()) {
                    txtPassword.getEditText().setError(getString(R.string.error_password));
                    txtPassword.requestFocus();
                } else {

                }
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
            getWindow().setSharedElementEnterTransition(transition);

            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    cardLogin.setVisibility(View.GONE);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                    Animator mAnimator = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mAnimator = ViewAnimationUtils.createCircularReveal(cardLogin, cardLogin.getWidth() / 2, 0, fab.getWidth() / 2, cardLogin.getHeight());
                    }
                    mAnimator.setDuration(500);
                    mAnimator.setInterpolator(new AccelerateInterpolator());
                    mAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            cardLogin.setVisibility(View.VISIBLE);
                            super.onAnimationStart(animation);
                        }
                    });
                    mAnimator.start();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }

            });
        }
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();
    }




    /**
     * This Method for login and show ic_user profile.
     *
     * @param
     */
    @SuppressLint("NewApi")
    private void popupDialogFlatLogin() {
        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final Dialog dialog = new Dialog(GuestActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View convertView = mLayoutInflater.inflate(R.layout.dialoug_flat_login, null);
        dialog.setContentView(convertView);
        dialog.setCancelable(false);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final CardView cardLogin;
        final EditText edt_FlatNo, edt_BlockNo, edt_OTP;
        final Button btnCancel, btnLogin;
        final FloatingActionButton fab;
        LinearLayout ll_Flat, ll_OTP;
        TextView txt_Resend;
        final CountDownTimer[] cTimer = new CountDownTimer[1];

        //TODO: Initialization Control

         ll_Flat = (LinearLayout)convertView.findViewById(R.id.ll_Flat);
        ll_OTP = (LinearLayout)convertView.findViewById(R.id.ll_OTP);
        cardLogin = (CardView) convertView.findViewById(R.id.cardLogin);
        fab = (FloatingActionButton) convertView.findViewById(R.id.fab);
        edt_FlatNo = (EditText) convertView.findViewById(R.id.edtFlatNo);
        edt_BlockNo = (EditText) convertView.findViewById(R.id.edtBlockId);
        edt_OTP = (EditText) convertView.findViewById(R.id.edtOTP);
        btnCancel = (Button) convertView.findViewById(R.id.btnCancel);
        btnLogin = (Button) convertView.findViewById(R.id.btnLogin);

        txt_Resend = (TextView)convertView. findViewById(R.id.txt_Resend);

        ll_Flat.setVisibility(View.VISIBLE);
        ll_OTP.setVisibility(View.GONE);

        txt_Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ll_Flat.getVisibility() == View.GONE){
                    ll_Flat.setVisibility(View.VISIBLE);
                    ll_OTP.setVisibility(View.GONE);
                    btnCancel.setText(getResources().getString(R.string.cancel));
                    return;
                }

                Animator mAnimator = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mAnimator = ViewAnimationUtils.createCircularReveal(cardLogin, cardLogin.getWidth() / 2, 0, cardLogin.getHeight(), fab.getWidth() / 2);
                }
                mAnimator.setDuration(500);
                mAnimator.setInterpolator(new AccelerateInterpolator());
                mAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cardLogin.setVisibility(View.INVISIBLE);
                        super.onAnimationEnd(animation);
                        dialog.dismiss();
                        //  navigationView.setCheckedItem(R.id.nav_dashboard);
                        //   getSupportActionBar().setTitle(getResources().getString(R.string.nav_dashboard));
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                });
                mAnimator.start();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_Flat.getVisibility() == View.VISIBLE){
                    if (edt_BlockNo.getText().toString().trim().isEmpty()) {
                        edt_BlockNo.setError(getString(R.string.error_block_no));
                        edt_BlockNo.requestFocus();
                    } else if (edt_FlatNo.getText().toString().trim().isEmpty()) {
                        edt_FlatNo.setError(getString(R.string.error_password));
                        edt_FlatNo.requestFocus();
                    }
                    else {
                        ll_Flat.setVisibility(View.GONE);
                        ll_OTP.setVisibility(View.VISIBLE);
                        btnCancel.setText("Edit");
                        startTimer();
                    }
                }
                else {
                    if (edt_OTP.getText().toString().trim().isEmpty()) {
                        edt_OTP.setError(getString(R.string.error_otp));
                        edt_OTP.requestFocus();
                    }
                    else {
                        dialog.dismiss();
                        str_Username = "KA1688";
                        str_Password = "b26895";
                        callLoginAPI(str_Username,str_Password);
                    }
                }

            }

            public void startTimer() {
                if (cTimer[0] != null){
                     cTimer[0].cancel();
                 }
             cTimer[0] = new CountDownTimer(30000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        txt_Resend.setText("seconds remaining: " +String.valueOf(millisUntilFinished/1000));
                        txt_Resend.setEnabled(false);
                        txt_Resend.setFocusable(false);
                        txt_Resend.setClickable(false);
                    }
                    public void onFinish() {
                        String text = "Didn't receive the OTP? RESEND";
                        SpannableString spannableString = new SpannableString(text);
                        ForegroundColorSpan foregroundColorSpanRed = new ForegroundColorSpan(Color.BLACK);
                        ForegroundColorSpan foregroundColorSpanGreen = new ForegroundColorSpan(Color.GREEN);
                        spannableString.setSpan(foregroundColorSpanRed, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spannableString.setSpan(foregroundColorSpanGreen, 11, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        txt_Resend.setText(spannableString);
                        txt_Resend.setEnabled(true);
                        txt_Resend.setFocusable(true);
                        txt_Resend.setClickable(true);
                    }
                };

                cTimer[0].start();
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
            getWindow().setSharedElementEnterTransition(transition);

            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    cardLogin.setVisibility(View.GONE);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    transition.removeListener(this);
                    Animator mAnimator = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mAnimator = ViewAnimationUtils.createCircularReveal(cardLogin, cardLogin.getWidth() / 2, 0, fab.getWidth() / 2, cardLogin.getHeight());
                    }
                    mAnimator.setDuration(500);
                    mAnimator.setInterpolator(new AccelerateInterpolator());
                    mAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            cardLogin.setVisibility(View.VISIBLE);
                            super.onAnimationStart(animation);
                        }
                    });
                    mAnimator.start();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }

            });
        }
        
        
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();
    }

    private void callLoginAPI(String str_username, String str_password) {

        if (AppUtils.isNetworkAvailable(GuestActivity.this)) {
            Uttils.showProgressDialoug(GuestActivity.this);
            Call<JsonObject> callRequestUpdate = mAPIInterface.getUpdateToken(str_username, str_password, Const.WEB_SERVICES_PARAM.KEY_PASSWORD);
            callRequestUpdate.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    if (response.isSuccessful()) {
                        if (response.code() == 200 && response.body() != null) {

                            try {
                                String jsonString = response.body().toString();
                                JSONObject jsonObject = new JSONObject(jsonString);

                                if (jsonObject.has(Const.WEB_SERVICES_PARAM.KEY_TOKEN_TYPE) && jsonObject.has(Const.WEB_SERVICES_PARAM.KEY_ACCESS_TOKEN)) {
                                    String token = jsonObject.getString(Const.WEB_SERVICES_PARAM.KEY_TOKEN_TYPE) + " " + jsonObject.getString(Const.WEB_SERVICES_PARAM.KEY_ACCESS_TOKEN);
                                    mUserAuthorization.setAuthorizationToken(token);
                                    mUserAuthorization.setUserCredential(str_username, str_password, true);
                                    Intent va = new Intent(GuestActivity.this, CustomerBookingNavigationActivity.class);
                                    startActivity(va);
                                    finish();
                                } else
                                    Toast.makeText(GuestActivity.this, "The ic_user name or password is incorrect.", Toast.LENGTH_LONG).show();

                                Uttils.hideProgressDialoug();
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Uttils.hideProgressDialoug();
                                AppUtils.displayErrorMessage(GuestActivity.this, jsonObject.getString("error_description"));
                            } catch (Exception error) {
                                Uttils.hideProgressDialoug();
                                Log.e(getClass().getSimpleName(), "ERROR " + error.getMessage());
                            }
                        }

                    } else
                        Toast.makeText(GuestActivity.this, "The ic_user name or password is incorrect.", Toast.LENGTH_LONG).show();//DisplayEmptyDialog(getActivity(), -1);

                    Uttils.hideProgressDialoug();
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Uttils.hideProgressDialoug();
                }
            });

        } else {
            Uttils.hideProgressDialoug();
            Toast.makeText(GuestActivity.this, R.string.error_msg_network, Toast.LENGTH_SHORT).show();
            //displayAlertErrorNetwork(CustomerBookingNavigationActivity.this);
        }
    }



    /*void askHolidayLogout(){
        new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog)
                //.setTitle(R.string.logout)
                .setMessage(R.string.holiday_logout_message)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        progressDialog = new ProgressDialog(GuestActivity.this, R.style.MyTheme);
                        try {
                            progressDialog.show();
                        } catch (Error e) {
                        }
                        progressDialog.setContentView(R.layout.progressdialog);
                        progressDialog.setCancelable(false);
                        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        sessionVacation.setCustomerLogout(true);
                        sessionVacation.setCustomerLogin(false);
                        startActivity(i);

                        sessionVacation.clear();
                        Config.CUSTOMER_LOGIN_ID = "";
                    }
                }).create().show();
    }*/


    private void SavePreferences(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void LoadPreferences() {
        int savedRadioIndex = sharedPreferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        savedCheckedRadioButton = (RadioButton) radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }

    public void setLangRecreate(String langval) {
        if (langval.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(langval);//Set Selected Locale
        saveLocale(langval);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
        //recreate();
        updateText();
    }

    private void updateText() {
        companyProfileTextView.setText(R.string.company_profile);
        productsTextView.setText(R.string.projects_);
        languageTextview.setText(R.string.language);
        eventsTextView.setText(R.string.events);
        videosTextView.setText(R.string.videos);
        facebookFeedsTextView.setText(R.string.edocs);
        customerLoginTextView.setText(R.string.cust_login);
        contactUsTextView.setText(R.string.contact_us);
        notificationsTextView.setText(R.string.notifications);
        siteprogressTextView.setText(R.string.site_progress);
    }

    //Save locale method in preferences
    public void saveLocale(String lang) {
        editor.putString(Locale_KeyValue, lang);
        editor.commit();
    }

    //Get locale method in preferences
    public void loadLocale() {
        String language = sharedPreferences.getString(Locale_KeyValue, "");
        setLangRecreate(language);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mNetworkChangeReceiver);
        super.onPause();
        if ((alertDialog != null) && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    private void checkVersion() {
        PackageManager manager = getApplicationContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        version_code = info.versionCode;
        version_name = info.versionName;
        Log.i("Info", "version_number : " + version_code);
        Log.i("Info", "version_name : " + version_name);

        if (Config.isFirstTime) {
            if (isInternetPresent) {
                try {
                   /* versionChecker = new  VersionChecker(version_name);
                    versionChecker.execute();*/
                    Call<VersionCheck> wsCallingversionChecker = mAPIInterface.getVersion();
                    wsCallingversionChecker.enqueue(new Callback<VersionCheck>() {
                        @Override
                        public void onResponse(Call<VersionCheck> call, Response<VersionCheck> response) {
                            if (response.isSuccessful()) {
                                if (response.code() == 200) {
                                    try {
                                        String latestVersions = response.body().getData();
                                        if (latestVersions != null && !latestVersions.equals(version_name)) {
                                            System.out.println("The two strings are not the same.");
                                            @SuppressLint("RestrictedApi") AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(GuestActivity.this, R.style.Update_Dialog));
                                            builder.setPositiveButton(R.string.update_now, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                                    } catch (android.content.ActivityNotFoundException anfe) {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                                    }
                                                }
                                            });
                                            builder.setNegativeButton(R.string.update_later, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            LayoutInflater inflater = getLayoutInflater();
                                            View dialoglayout = inflater.inflate(R.layout.app_update_dialog, null);
                                            //TextView txt = (TextView) dialoglayout.findViewById(R.id.showtext);
                                            builder.setView(dialoglayout);
                                            if (!isFinishing() && builder != null) {
                                                builder.show();
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<VersionCheck> call, Throwable t) {

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Config.isFirstTime = false;
            }
        }
    }

    /*public class VersionChecker extends AsyncTask<String, String, String> {
        String newVersion = null;
        String version;
        public VersionChecker(String versionName) {
            this.version = versionName;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=com.nebulacompanies.nebula&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div[itemprop=softwareVersion]")
                        .first()
                        .ownText();
                return newVersion;
            } catch (Exception e) {
                e.printStackTrace();
                return newVersion;
            }
            *//*catch (RuntimeException e) {
                e.printStackTrace();
                return newVersion;
            }*//*
        }

        @Override
        protected void onPostExecute(String latestVersions) {
            super.onPostExecute(latestVersions);
            Log.i("info", "version_name : " + version);
            Log.i("info", "latestVersions : " + latestVersions);

            if (latestVersions != null && !latestVersions.equals(version)) {
                System.out.println("The two strings are not the same.");
                @SuppressLint("RestrictedApi") android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(GuestActivity.this, R.style.Update_Dialog));
                builder.setPositiveButton(R.string.update_now, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                });
                builder.setNegativeButton(R.string.update_later, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (versionChecker != null) {
                            versionChecker.onCancelled();
                        }
                    }
                });
                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.app_update_dialog, null);
                //TextView txt = (TextView) dialoglayout.findViewById(R.id.showtext);
                builder.setView(dialoglayout);
                if (!isFinishing() && builder != null) {
                    builder.show();
                }
            }
        }
    }*/
    private void DetachScreen() {

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        /*float scaleFactor = metrics.density;
        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;
        float smallestWidth = Math.min(widthDp, heightDp);*/

        float widthDpi = metrics.xdpi;
        float heightDpi = metrics.ydpi;
        float widthInches = widthPixels / widthDpi;
        float heightInches = heightPixels / heightDpi;
        double diagonalInches = Math.sqrt(
                (widthInches * widthInches)
                        + (heightInches * heightInches));
        Log.d("Test: ", "Test: " + diagonalInches);

        // Toast.makeText(getApplicationContext(),"SCREEN SIZE : "+diagonalInches,Toast.LENGTH_LONG ).show();

        if (diagonalInches <= 4.50) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
    }


}
