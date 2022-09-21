package com.nebulacompanies.nebula.CustomerBooking.Utils.UI.Activity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Fragmetns.MyAccountFragment;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Fragmetns.UnitListFragment;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UserAuthorization;
import com.nebulacompanies.nebula.GuestActivity;
import com.nebulacompanies.nebula.Model.CustomerLogin.ProjectInfomation;
import com.nebulacompanies.nebula.Model.CustomerLogin.ProjectList;
import com.nebulacompanies.nebula.Network.APIClientCustomerBooking;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.util.Uttils;
import com.nebulacompanies.nebula.view.MyTextView;
import com.nebulacompanies.nebula.view.NebulaEditText;
import cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class : CustomerBookingNavigationActivity
 * Details:
 * Create by : Jadav Chirag At NebulaApplication Infraspace LLP 17-11-2017
 * Modification by :
 */
public class CustomerBookingNavigationActivity extends AppCompatActivity {

    // Global Variable

    private long lastBackPressTime = 0;
    private Toast toast;
    private APIInterface mAPIInterface;
    private UserAuthorization mUserAuthorization;

    TextView txt_Header;
    ImageView iv_Logout ;
    BottomNavigationView bottomNavigation;
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);
        bottomNavigation.getMenu().getItem(1).setChecked(true);


        // db = AppDatabase.getAppDatabase(this);
        // mNotificationCount = db.notificationDAO().getAllNotificationCount();
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mAPIInterface = APIClientCustomerBooking.getClient(CustomerBookingNavigationActivity.this).create(APIInterface.class);
        mUserAuthorization = new UserAuthorization(CustomerBookingNavigationActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(null);
        txt_Header = (TextView) toolbar.findViewById(R.id.txt_Header);
        txt_Header.setText("Apartment");
        showFragment(new UnitListFragment());
        iv_Logout = (ImageView)toolbar.findViewById(R.id.iv_Logout);
        iv_Logout.setVisibility(View.GONE);
        setAction();




    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.tab_home){

            Intent va = new Intent(CustomerBookingNavigationActivity.this, GuestActivity.class);
            startActivity(va);
            finish();
        }
        if (itemId == R.id.tab_unit) {
            iv_Logout.setVisibility(View.GONE);
           // item.setIcon(getResources().getDrawable(R.drawable.icon_unit_selected));
          //  bottomNavigation.getMenu().getItem(2).setIcon(getResources().getDrawable(R.drawable.icon_user_unselected));
            showFragment(new UnitListFragment());
            txt_Header.setText("Apartment");
        }

        if (itemId == R.id.tab_account) {
        //    item.setIcon(getResources().getDrawable(R.drawable.icon_user_selected));
         //   bottomNavigation.getMenu().getItem(1).setIcon(getResources().getDrawable(R.drawable.icon_unit_unselected));
            iv_Logout.setVisibility(View.VISIBLE);
            showFragment(new MyAccountFragment());
            txt_Header.setText("My Account");
        }

        return true;
    };
    private void setAction() {
        iv_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(CustomerBookingNavigationActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Logout")
                        .setContentText("Would you like to logout?")
                        .setConfirmText("Yes")
                        .setCancelText("No")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                mUserAuthorization.setUserCredential("", "", false);
                                AppUtils.displayErrorMessage(CustomerBookingNavigationActivity.this, "Logout Successful");
                                sDialog.dismiss();
                                mUserAuthorization.setAuthorizationToken("");
                                mUserAuthorization.setUserCredential("", "", false);
                                mUserAuthorization.setUserProfileName("");

                                Intent va = new Intent(CustomerBookingNavigationActivity.this, GuestActivity.class);
                                startActivity(va);
                                finish();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        // put your code here...
    }

    /************************************************************
     *                    PRIVATE METHOD
     *************************************************************/

    /**
     * This Method for set Actionbar Title
     *
     * @param title
     */
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    /**
     * This method for replace fragment from navigation drawer.
     *
     * @param fragment Fragment Details
     */
    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}
