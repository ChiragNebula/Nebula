package com.nebulacompanies.nebula.gui;

import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_ERROR;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_NO_RECORDS;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SUCCESS;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.nebulacompanies.nebula.Config;
import com.nebulacompanies.nebula.Model.Guest.SiteProgressList;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.SiteProgressAdapter;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.util.Constants;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 9/15/2016.
 */
public class SiteProgress extends Base2Activity implements AdapterView.OnItemClickListener {

    SwipeRefreshLayout mSwipeRefreshLayout;
    String PRODUCT_NAME;
    int project_id;
    private APIInterface mAPIInterface;
    public static ArrayList<SiteProgressList> arrayListSiteProgress = new ArrayList<>();
    public static final String TAG = "SiteProgress";
    ListView listView;
    SiteProgressAdapter siteProgressAdapter;
    Boolean isRefreshed = false;
    ConnectionDetector cd;
    LinearLayout imageLinearLayout;
    ImageView  circleImageView;
    MyTextView siteprogesstitleMyTextView;

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;
    Boolean isNotificationClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_progress);

        Bundle b = getIntent().getExtras();

        if(b != null) {
            PRODUCT_NAME = b.getString("Product_Name");
            project_id=b.getInt("ProjectId");
            isNotificationClicked = b.getBoolean("Notification_Click");
        }
        if(isNotificationClicked){
            Config.NOTIFICATION_COUNT--;
        }
        setActionBar();
        init();
        getSiteProgressList();
    }

    void setActionBar(){
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void init(){
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        initError();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.site_progress_swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.listview_site_progress);
        listView.setOnItemClickListener(this);
        imageLinearLayout=(LinearLayout)findViewById(R.id.site_progress_image_icon);
        siteprogesstitleMyTextView=(MyTextView)findViewById(R.id.site_progress_title);
        circleImageView=(ImageView)findViewById(R.id.circle_image);

        if(project_id== Constants.ID_AAVAAS_CHANGODER ) {
            imageLinearLayout.setBackgroundResource(R.drawable.site_progress_page_design);
            siteprogesstitleMyTextView.setText(PRODUCT_NAME);
            circleImageView.setImageResource(R.drawable.aavaas_circle_image);
        }
        else if(project_id== Constants.ID_HAWTHORN_DWARKA ){
            imageLinearLayout.setBackgroundResource(R.drawable.dwarka_site_progress);
            siteprogesstitleMyTextView.setText(PRODUCT_NAME);
            circleImageView.setImageResource(R.drawable.dwark_circle_image);
        }
        else if(project_id== Constants.ID_AAVAAS_HYDERABD ){
            imageLinearLayout.setBackgroundResource(R.drawable.hyderabad_site_progress);
            siteprogesstitleMyTextView.setText(PRODUCT_NAME);
            circleImageView.setImageResource(R.drawable.hyderabad_circle_image);
        }
        else if(project_id== Constants.ID_AAVAAS_CHENNAI ){
            imageLinearLayout.setBackgroundResource(R.drawable.chennai__site_progress);
            siteprogesstitleMyTextView.setText(PRODUCT_NAME);
            circleImageView.setImageResource(R.drawable.chennai_circle_image);
        }else {
            imageLinearLayout.setBackgroundResource(R.drawable.site_progress_page_design);
            siteprogesstitleMyTextView.setText(PRODUCT_NAME);
            circleImageView.setImageResource(R.drawable.aavaas_circle_image);
        }
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
            if(siteProgressAdapter != null) {
                siteProgressAdapter.clearData();
                siteProgressAdapter.notifyDataSetChanged();
            }
            getSiteProgressList();
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    private void getSiteProgressList() {
        if (isInternetPresent) {
            final ProgressDialog mProgressDialog = new ProgressDialog(this, R.style.MyTheme);
            /*mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);*/
            if(!isRefreshed) {
                mProgressDialog.show();
            }
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progressdialog);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            Call<com.nebulacompanies.nebula.Model.Guest.SiteProgress> wsCallingSiteProgress = mAPIInterface.getSiteProgressList(project_id);
            wsCallingSiteProgress.enqueue(new Callback<com.nebulacompanies.nebula.Model.Guest.SiteProgress>() {
                @Override
                public void onResponse(Call<com.nebulacompanies.nebula.Model.Guest.SiteProgress> call, Response<com.nebulacompanies.nebula.Model.Guest.SiteProgress> response) {
                    if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                        mProgressDialog.dismiss();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    arrayListSiteProgress.clear();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            if (response.body().getStatusCode() == REQUEST_STATUS_CODE_NO_RECORDS) {
                                noRecordsFound();
                            }
                            else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_SUCCESS) {
                                arrayListSiteProgress.addAll(response.body().getData());
                                siteProgressAdapter = new SiteProgressAdapter(SiteProgress.this, arrayListSiteProgress,PRODUCT_NAME,project_id);
                                listView.setAdapter(siteProgressAdapter);
                                siteProgressAdapter.notifyDataSetChanged();
                            }
                            else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_ERROR
                                    || response.body().getStatusCode() == REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE) {
                                serviceUnavailable();
                            }

                            if (arrayListSiteProgress.size() > 0) {
                                llEmpty.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                            }
                            else {
                                llEmpty.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            }
                        }
                    }
                    else{
                        serviceUnavailable();
                    }
                }

                @Override
                public void onFailure(Call<com.nebulacompanies.nebula.Model.Guest.SiteProgress> call, Throwable t) {
                    mSwipeRefreshLayout.setEnabled(false);
                    mProgressDialog.dismiss();
                    serviceUnavailable();
                }
            });
        } else {
            noInternetConnection();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent i1 = new Intent(this,
                ViewSiteProgress.class);
        i1.putExtra("ProjectId",project_id);
        i1.putExtra("Name", PRODUCT_NAME);
        i1.putExtra("Month", arrayListSiteProgress.get(i).getMonth());
        i1.putExtra("MonthInText",arrayListSiteProgress.get(i).getMonthinText());
        i1.putExtra("Year", arrayListSiteProgress.get(i).getYear());
        startActivity(i1);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

    void initError(){
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

    void noRecordsFound(){
        txtErrorTitle.setText(R.string.error_no_records);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
    }

    void serviceUnavailable(){
        txtErrorTitle.setText(R.string.error_service_unavailable);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    void noInternetConnection(){
        txtErrorTitle.setText(R.string.error_msg_network);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }
}
