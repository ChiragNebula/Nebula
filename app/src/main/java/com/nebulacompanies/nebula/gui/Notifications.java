package com.nebulacompanies.nebula.gui;

import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_ERROR;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_NO_RECORDS;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SUCCESS;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;
import static com.nebulacompanies.nebula.util.SetDateFormat.SetGmtTime;

import android.app.NotificationManager;
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

import androidx.appcompat.app.ActionBar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.Base2Activity;
import com.nebulacompanies.nebula.Config;
import com.nebulacompanies.nebula.Model.Guest.Notification;
import com.nebulacompanies.nebula.Model.Guest.NotificationList;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.NotificationsListViewAdapter;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.view.MyTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 6/25/2016.
 */
public class Notifications extends Base2Activity implements AdapterView.OnItemClickListener {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView lview;
    NotificationManager manager;

    NotificationsListViewAdapter notificationsListViewAdapter;

   /* ArrayList<String> message = new ArrayList<>();
    ArrayList<String> created_date = new ArrayList<>();
    ArrayList<String> redirect = new ArrayList<>();
    ArrayList<String> image = new ArrayList<>();*/

    public static ArrayList<NotificationList> arrayListNotifications = new ArrayList<>();
    Boolean isRefreshed = false;
    String year = "", month = "";
    ConnectionDetector cd;
    int mon;
    private APIInterface mAPIInterface;
    public static final String TAG = "notification";

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        setActionBar();
        init();
        getNotificationList();
    }

    void setActionBar() {
        MyTextView tv = new MyTextView(getApplicationContext());
        // Create a LayoutParams for TextView
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, // Width of TextView
                ActionBar.LayoutParams.WRAP_CONTENT); // Height of TextView
        tv.setLayoutParams(lp);
        tv.setText(R.string.notifications); // ActionBar title text
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(16);

        getSupportActionBar().setDisplayOptions(getSupportActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#570054")));
    }

    void init() {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        initError();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_notification_swipe_refresh_layout);
        lview = (ListView) findViewById(R.id.notifications_listview);
        lview.setOnItemClickListener(this);

        lview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /*if (lview.getChildAt(0) != null) {
                    mSwipeRefreshLayout.setEnabled(lview.getFirstVisiblePosition() == 0 && lview.getChildAt(0).getTop() == 0);
                }*/

                /*int topRowVerticalPosition = (guidesList == null || guidesList.getChildCount() == 0) ? 0 : guidesList.getChildAt(0).getTop();
                swipeContainer.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);*/

                mSwipeRefreshLayout.setEnabled(firstVisibleItem == 0);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void getNotificationList() {
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
            Call<Notification> wsCallingNotification = mAPIInterface.getNotificationList();
            wsCallingNotification.enqueue(new Callback<Notification>() {
                @Override
                public void onResponse(Call<Notification> call, Response<Notification> response) {
                    if (mProgressDialog != null && mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    arrayListNotifications.clear();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            if (response.body().getStatusCode() == REQUEST_STATUS_CODE_NO_RECORDS) {
                                noRecordsFound();
                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_SUCCESS) {
                                arrayListNotifications.addAll(response.body().getData());
                                notificationsListViewAdapter = new NotificationsListViewAdapter(Notifications.this, arrayListNotifications);
                                lview.setAdapter(notificationsListViewAdapter);
                                notificationsListViewAdapter.notifyDataSetChanged();
                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_ERROR
                                    || response.body().getStatusCode() == REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE) {
                                serviceUnavailable();
                            }

                            if (arrayListNotifications.size() > 0) {
                                llEmpty.setVisibility(View.GONE);
                                lview.setVisibility(View.VISIBLE);
                            } else {
                                llEmpty.setVisibility(View.VISIBLE);
                                lview.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        serviceUnavailable();
                    }
                }

                @Override
                public void onFailure(Call<Notification> call, Throwable t) {
                    mSwipeRefreshLayout.setEnabled(false);
                    mProgressDialog.dismiss();
                    serviceUnavailable();
                }
            });
        } else {
            noInternetConnection();
        }
    }
       /* private void callAPI() {
        asyncComplex = new AsyncComplex(this, NOTIFICATIONS_API, "Notifications", isRefreshed);
        asyncComplex.asyncResponse = this;
        StartAsyncTaskInParallel(asyncComplex);
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(mNetworkChangeReceiver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Config.NOTIFICATION_COUNT = 0;
        //Config.NOTIFICATION_OPEN = false;
        manager.cancelAll();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                Config.NOTIFICATION_COUNT = 0;
                //Config.NOTIFICATION_OPEN = false;
                manager.cancelAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // String date = arrayListNotifications.get(position).getCreatedDate().toString();
       /* String[] parts;
        parts = date.split("T");
        System.out.println("Date: " + parts[0]);*/

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date d = sdf.parse(SetGmtTime(arrayListNotifications.get(position).getCreatedDate()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);

            int day = cal.get(Calendar.DAY_OF_MONTH);
            mon = cal.get(Calendar.MONTH) + 1;
            int yr = cal.get(Calendar.YEAR);
            year = String.valueOf(yr);
            //month = (String)android.text.format.DateFormat.format("MMMM", d);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            month = simpleDateFormat.format(d);

            System.out.println("Date: day : " + day);
            System.out.println("Date: month : " + month);
            System.out.println("Date: year : " + year);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (arrayListNotifications.get(position).getCategory().equals("Site_Progress_Aavaas_Changodar")) {
            Intent i = new Intent(this, ViewSiteProgress.class);
            i.putExtra("ProjectId", arrayListNotifications.get(position).getProjectId());
            i.putExtra("MonthInText", month);
            i.putExtra("Month", mon);
            i.putExtra("Year", year);
            startActivity(i);
        } else if (arrayListNotifications.get(position).getCategory().equals("Site_Progress_Hawthorn_Dwarka")) {
            Intent i = new Intent(this, ViewSiteProgress.class);
            i.putExtra("ProjectId", arrayListNotifications.get(position).getProjectId());
            i.putExtra("MonthInText", month);
            i.putExtra("Month", mon);
            i.putExtra("Year", year);
            startActivity(i);
        } else if (arrayListNotifications.get(position).getCategory().equals("Site_Progress_Aavaas_Sanand")) {
            Intent i = new Intent(this, ViewSiteProgress.class);
            i.putExtra("ProjectId", arrayListNotifications.get(position).getProjectId());
            i.putExtra("MonthInText", month);
            i.putExtra("Month", mon);
            i.putExtra("Year", year);
            startActivity(i);
        } else if (arrayListNotifications.get(position).getCategory().equals("Site_Progress_Aavaas_Hyderabad")) {
            Intent i = new Intent(this, ViewSiteProgress.class);
            i.putExtra("ProjectId", arrayListNotifications.get(position).getProjectId());
            i.putExtra("MonthInText", month);
            i.putExtra("Month", mon);
            i.putExtra("Year", year);
            startActivity(i);
        } else if (arrayListNotifications.get(position).getCategory().equals("Site_Progress_Aavaas_Chennai")) {
            Intent i = new Intent(this, ViewSiteProgress.class);
            i.putExtra("ProjectId", arrayListNotifications.get(position).getProjectId());
            i.putExtra("MonthInText", month);
            i.putExtra("Month", mon);
            i.putExtra("Year", year);
            startActivity(i);
        } else if (arrayListNotifications.get(position).getCategory().equals("Event")) {
            Intent eve = new Intent(this, CompanyEvents.class);
            startActivity(eve);
        } else if (arrayListNotifications.get(position).getCategory().equals("Video")) {
//            Intent vid = new Intent(this, CompanyVideos.class);
//            startActivity(vid);
        } else if (arrayListNotifications.get(position).getCategory().equals("EDocument")) {
//            Intent eb = new Intent(this, EDocuments.class);
//            startActivity(eb);
        } else if (arrayListNotifications.get(position).getCategory().equals("Newsletter")) {
//            Intent nl = new Intent(this, Newsletters.class);
//            startActivity(nl);
        } else if (arrayListNotifications.get(position).getCategory().equals("Project")) {
            Intent pro = new Intent(this, Products.class);
            startActivity(pro);
        } else if (arrayListNotifications.get(position).getCategory().equals("Other")) {
            //Toast.makeText(this, "call Other", Toast.LENGTH_SHORT).show();

            if (!arrayListNotifications.get(position).getIcon().equals("")) {
                //if (!arrayListNotifications.get(position).getCategory().toString().equals("")) {
                //showNotificationImage(message.get(position).toString(), image.get(position).toString());

                Intent intent = new Intent(this, ShowNotificationImage.class);
                intent.putExtra("id", position);
                intent.putExtra("image_text", arrayListNotifications.get(position).getMessage().toString());
                intent.putExtra("image_path", arrayListNotifications.get(position).getIcon().toString());
                startActivity(intent);
                //}
            }
        }
    }

    /*void showNotificationImage(String title, String imgUrl){
        LayoutInflater mInflater1 = (LayoutInflater ) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final Dialog dialog = new Dialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View convertView = mInflater1.inflate(R.layout.notification_image_popup, null);
        dialog.setContentView(convertView);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        final TextView titleTextView = (TextView) convertView.findViewById(R.id.notification_title);
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.notification_image);

        titleTextView.setText(title);
        Glide.with(this).load(imgUrl)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.load)
                .into(imageView);

        dialog.show();
    }*/

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

   /* @Override
    public void processFinish(String output, String Tag) {
        if(output == null) {
            output = "THERE WAS AN ERROR";
            noRecordsTextView.setVisibility(View.VISIBLE);
            noRecordsTextView.setText("Network Error");
        }

        String response = output.toString();
        Log.i("INFO", "response :" + response);

        if(Tag.equals("Notifications")){
            try {
                JSONObject object = new JSONObject(response);
                String status_code = object.getString("Statuscode");

                if (status_code.equals("0")) {

                    JSONArray new_array = object.getJSONArray("Data");

                    for (int i = 0; i < new_array.length(); i++) {
                        try {
                            JSONObject jsonObject = new_array.getJSONObject(i);

                            message.add(jsonObject.getString("MoreDetails").toString());
                            created_date.add(jsonObject.getString("CreatedDate").toString());
                            redirect.add(jsonObject.getString("LinkTag").toString());
                            image.add(jsonObject.getString("Detail").toString());

                            notificationsListViewAdapter = new NotificationsListViewAdapter(this, message, created_date);
                            lview.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                            lview.setAdapter(notificationsListViewAdapter);
                            notificationsListViewAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(status_code.equals("1")){
                    // NO Data Found
                    noRecordsTextView.setVisibility(View.VISIBLE);
                    noRecordsTextView.setText("No records found..!");
                }
                else if(status_code.equals("-1")){
                    // Please Reload, There is an error.
                    noRecordsTextView.setVisibility(View.VISIBLE);
                    noRecordsTextView.setText("Please reload..!");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }*/

   /* @Override
    public void processFinish(String output, String Tag) {
        if(output == null) {
            output = "THERE WAS AN ERROR";
        }

        String response = output.toString();
        Log.i("INFO", "response :" + response);

        if(Tag.equals("Notifications")){
            try {
                JSONObject object = new JSONObject(response);
                JSONArray new_array = object.getJSONArray("Data");

                for (int i = 0; i < new_array.length(); i++) {
                    try {
                        JSONObject jsonObject = new_array.getJSONObject(i);
                        if (jsonObject.getString("MoreDetails").toString().equals("0")) {
                        }
                        else {
                            message.add(jsonObject.getString("MoreDetails").toString());
                            created_date.add(jsonObject.getString("CreatedDate").toString());
                            redirect.add(jsonObject.getString("LinkTag").toString());
                        }

                        notificationsListViewAdapter = new NotificationsListViewAdapter(this, message, created_date);
                        lview.setBackgroundDrawable(getResources().getDrawable(R.drawable.border));
                        lview.setAdapter(notificationsListViewAdapter);
                        notificationsListViewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }*/

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            if (notificationsListViewAdapter != null) {
                notificationsListViewAdapter.clearData();
                notificationsListViewAdapter.notifyDataSetChanged();
            }
            getNotificationList();
            Config.NOTIFICATION_COUNT = 0;
            manager.cancelAll();
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

   /* @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInParallel(AsyncComplex task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }*/

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
        lview.setVisibility(View.GONE);
    }

    void serviceUnavailable() {
        txtErrorTitle.setText(R.string.error_service_unavailable);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        lview.setVisibility(View.GONE);
    }

    void noInternetConnection() {
        txtErrorTitle.setText(R.string.error_msg_network);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        lview.setVisibility(View.GONE);
    }

    void showRecords() {
        llEmpty.setVisibility(View.GONE);
        txtRetry.setVisibility(View.GONE);
        lview.setVisibility(View.VISIBLE);
    }
}
