package com.nebulacompanies.nebula.gui;

import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_ERROR;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_NO_RECORDS;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SUCCESS;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.Base2Activity;
import com.nebulacompanies.nebula.Model.Guest.SiteProductList;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.SiteProductsAdapter;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 8/6/2016.
 */

public class SiteProducts extends Base2Activity implements AdapterView.OnItemClickListener {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    private APIInterface mAPIInterface;
    public static ArrayList<SiteProductList> arrayListSiteProducts = new ArrayList<>();
    public static final String TAG = "SiteProducts";
    SiteProductsAdapter siteProductsAdapter;
    Boolean isRefreshed = false;
    LinearLayout imageLinearLayout;
    ProgressDialog mProgressDialog;
    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_progress);

        setActionBar();
        init();
        getSiteProductList();
    }

    void setActionBar() {
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void init() {
        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        initError();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.site_progress_swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.listview_site_progress);
        listView.setOnItemClickListener(this);
        imageLinearLayout = (LinearLayout) findViewById(R.id.site_progress_image_icon);
        imageLinearLayout.setBackgroundResource(R.drawable.site_progress_page_design);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                int topRowVerticalPosition = (listView == null || listView.getChildCount() == 0) ? 0 : listView.getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(i == 0 && topRowVerticalPosition >= 0);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            if (siteProductsAdapter != null) {
                siteProductsAdapter.clearData();
                siteProductsAdapter.notifyDataSetChanged();
            }
            getSiteProductList();
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    private void getSiteProductList() {
        if (isInternetPresent) {
            final ProgressDialog mProgressDialog = new ProgressDialog(this, R.style.MyTheme);
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

                    if (SiteProducts.this != null && !SiteProducts.this.isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
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
                                siteProductsAdapter = new SiteProductsAdapter(SiteProducts.this, arrayListSiteProducts);
                                listView.setAdapter(siteProductsAdapter);
                                siteProductsAdapter.notifyDataSetChanged();

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //String projects = ProjectName.get(position).toString().replace(" ", "%20");
        Intent i = new Intent(this, SiteProgress.class);
        i.putExtra("Product_Name", arrayListSiteProducts.get(position).getProjectName());
        i.putExtra("ProjectId", arrayListSiteProducts.get(position).getProjectId());
        startActivity(i);
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

    @Override
    protected void onPause() {
        super.onPause();
        if (SiteProducts.this != null && !SiteProducts.this.isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
