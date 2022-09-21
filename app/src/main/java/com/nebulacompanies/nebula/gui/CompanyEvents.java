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
import com.nebulacompanies.nebula.Model.Guest.EventList;
import com.nebulacompanies.nebula.Model.Guest.Events;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.CompanyEventsAdapter;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 11/5/2016.
 */

public class CompanyEvents extends Base2Activity implements AdapterView.OnItemClickListener {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    private APIInterface mAPIInterface;
    public static ArrayList<EventList> arrayListEvents = new ArrayList<>();
    public static final String TAG = "Events";
    CompanyEventsAdapter companyEventsAdapter;
    Boolean isRefreshed = false;
    Boolean isNotificationClicked = false;
    ConnectionDetector cd;
    LinearLayout imageLinearLayout;

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;

    String message;

    MyTextView titletext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_event);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            isNotificationClicked = b.getBoolean("Notification_Click");
        }

        if (isNotificationClicked) {
            Config.NOTIFICATION_COUNT--;
        }
        titletext=(MyTextView)findViewById(R.id.events_title);
        setActionBar();
        init();
        getEventList();
    }

    void setActionBar() {
        /*MyTextView tv = new MyTextView(getApplicationContext());
        // Create a LayoutParams for TextView
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, // Width of TextView
                ActionBar.LayoutParams.WRAP_CONTENT); // Height of TextView
        tv.setLayoutParams(lp);
        tv.setText(R.string.events); // ActionBar title text
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(16);

        getSupportActionBar().setDisplayOptions(getSupportActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#570054")));*/

        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void init() {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        initError();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.company_event_swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.listview_company_events);
        listView.setOnItemClickListener(this);

          imageLinearLayout=(LinearLayout)findViewById(R.id.events_image_icon);
          imageLinearLayout.setBackgroundResource(R.drawable.event_bg);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /*if (listView.getChildAt(0) != null) {
                    mSwipeRefreshLayout.setEnabled(listView.getFirstVisiblePosition() == 0 && listView.getChildAt(0).getTop() == 0);
                }*/
                int topRowVerticalPosition = (listView == null || listView.getChildCount() == 0) ? 0 : listView.getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
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
            if (companyEventsAdapter != null) {
                companyEventsAdapter.clearData();
                companyEventsAdapter.notifyDataSetChanged();
            }
            getEventList();
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    private void getEventList() {
        if (isInternetPresent) {
            final ProgressDialog mProgressDialog = new ProgressDialog(this, R.style.MyTheme);
           /* mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);*/
            if (!isRefreshed) {
                mProgressDialog.show();
            }
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progressdialog);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            Call<Events> wsCallingEvents = mAPIInterface.getEventList();
            wsCallingEvents.enqueue(new Callback<Events>() {
                @Override
                public void onResponse(Call<Events> call, Response<Events> response) {
                    if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                        mProgressDialog.dismiss();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    arrayListEvents.clear();
                    if (response.isSuccessful()) {
                        if(response.code()==200){
                        if (response.body().getStatusCode() == REQUEST_STATUS_CODE_NO_RECORDS) {
                            noRecordsFound();
                        } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_SUCCESS) {
                            arrayListEvents.addAll(response.body().getData());
                            companyEventsAdapter = new CompanyEventsAdapter(CompanyEvents.this, arrayListEvents);
                            listView.setAdapter(companyEventsAdapter);
                            companyEventsAdapter.notifyDataSetChanged();
                        } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_ERROR
                                || response.body().getStatusCode() == REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE) {
                            serviceUnavailable();
                        }
                        if (arrayListEvents.size() > 0) {
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
                public void onFailure(Call<Events> call, Throwable t) {
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
        Intent i1 = new Intent(this, ViewEvents.class);
        i1.putExtra("Event_Name", arrayListEvents.get(i).getEventName());
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
