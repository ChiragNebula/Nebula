package com.nebulacompanies.nebula.gui;

import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_ERROR;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_NO_RECORDS;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SUCCESS;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.Base2Activity;
import com.nebulacompanies.nebula.Config;
import com.nebulacompanies.nebula.Model.Guest.SiteProductList;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.NewProductsAdapter;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.util.Session;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 5/11/2016.
 */
public class Products extends Base2Activity {

    RecyclerView listView;
    Boolean isNotificationClicked = false;
    ConnectionDetector cd;
    public static final String TAG = "Projects";
    Boolean isRefreshed = false;
    private APIInterface mAPIInterface;
    NewProductsAdapter siteProductsAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;


    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;
    Session session;

    public static ArrayList<SiteProductList> arrayListSiteProducts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_new);
        setActionBar();
        init();
        getProject();

    }

    void setActionBar() {
        MyTextView tv = new MyTextView(getApplicationContext());
        // Create a LayoutParams for TextView
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, // Width of TextView
                ActionBar.LayoutParams.WRAP_CONTENT); // Height of TextView
        tv.setLayoutParams(lp);
        tv.setText(R.string.projects_); // ActionBar title text
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(16);

        getSupportActionBar().setDisplayOptions(getSupportActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#570054")));

    }

    void init() {
        cd = new ConnectionDetector(Products.this);
        isInternetPresent = cd.isConnectingToInternet();

        session = new Session(Products.this);
        mAPIInterface = APIClient.getClient(Products.this).create(APIInterface.class);
        initError();


        if (isNotificationClicked) {
            Config.NOTIFICATION_COUNT--;
        }


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.products_swipe_refresh_layout);
        listView = (RecyclerView) findViewById(R.id.nebula_products);

        DividerItemDecoration myDivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        myDivider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_recyclerview));
        listView.addItemDecoration(myDivider);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getProject() {
        if (isInternetPresent) {
            final ProgressDialog mProgressDialog = new ProgressDialog(Products.this, R.style.MyTheme);
            /*mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);*/
            if (!isRefreshed) {
                mProgressDialog.show();
            }
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progressdialog);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Call<com.nebulacompanies.nebula.Model.Guest.SiteProducts> wsCallingSiteProducts = mAPIInterface.getSiteProductList();
            wsCallingSiteProducts.enqueue(new Callback<com.nebulacompanies.nebula.Model.Guest.SiteProducts>() {
                @Override
                public void onResponse(Call<com.nebulacompanies.nebula.Model.Guest.SiteProducts> call, Response<com.nebulacompanies.nebula.Model.Guest.SiteProducts> response) {
                    if (Products.this != null && !Products.this.isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    arrayListSiteProducts.clear();

                    if (response.isSuccessful()) {

                        if (response.code() == 200) {
                            if (response.body().getStatusCode() == REQUEST_STATUS_CODE_NO_RECORDS) {
                                noRecordsFound();
                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_SUCCESS) {
                                arrayListSiteProducts.addAll(response.body().getData());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                listView.setLayoutManager(mLayoutManager);
                                listView.setItemAnimator(new DefaultItemAnimator());
                                siteProductsAdapter = new NewProductsAdapter(Products.this, arrayListSiteProducts);
                                listView.setAdapter(siteProductsAdapter);



                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_ERROR
                                    || response.body().getStatusCode() == REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE) {
                                serviceUnavailable();
                            }

                            if (arrayListSiteProducts.size() > 0) {
                                llEmpty.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                            } else {
                                llEmpty.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        serviceUnavailable();
                    }
                }

                @Override
                public void onFailure(Call<com.nebulacompanies.nebula.Model.Guest.SiteProducts> call, Throwable t) {
                    mProgressDialog.dismiss();
                    mSwipeRefreshLayout.setEnabled(false);
                    serviceUnavailable();
                }
            });
        } else {
            noInternetConnection();
        }

    }

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            if (siteProductsAdapter != null) {
                siteProductsAdapter.clearData();
                siteProductsAdapter.notifyDataSetChanged();
            }

            getProject();
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }

    }


    void initError() {
        llEmpty = (LinearLayout) findViewById(R.id.llEmpty);
        imgError = (ImageView) findViewById(R.id.imgError);
        txtErrorTitle = (MyTextView) findViewById(R.id.txtErrorTitle);
        txtRetry = (MyTextView) findViewById(R.id.txtRetry);
        txtRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshContent();
            }
        });
    }

    void noRecordsFound() {
        txtErrorTitle.setText(R.string.error_no_records);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
    }

    void serviceUnavailable() {
        txtErrorTitle.setText(R.string.error_service_unavailable);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

    }

    void noInternetConnection() {
        txtErrorTitle.setText(R.string.error_msg_network);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }


}