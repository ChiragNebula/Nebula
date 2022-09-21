package com.nebulacompanies.nebula.gui;


import static com.nebulacompanies.nebula.Permissions.isWriteStoragePermissionGranted;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_ERROR;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_NO_RECORDS;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SUCCESS;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.Base2Activity;
import com.nebulacompanies.nebula.Config;
import com.nebulacompanies.nebula.DownloadManagerResolver;
import com.nebulacompanies.nebula.Model.Guest.VideoList;
import com.nebulacompanies.nebula.Model.Guest.Videos;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.CompanyVideosAdapter;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.view.DownloadProgressView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 11/28/2016.
 */

public class CompanyVideos extends Base2Activity implements AdapterView.OnItemClickListener{

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    private APIInterface mAPIInterface;
    public static final String TAG = "Video";
    public static ArrayList<VideoList> arrayListVideos = new ArrayList<>();
    public static ArrayList<VideoList> arrayListVideosAhmedabad = new ArrayList<>();
    public static ArrayList<VideoList> arrayListVideosDwarka = new ArrayList<>();
    public static ArrayList<VideoList> arrayListVideosHyderabad = new ArrayList<>();
    public static ArrayList<VideoList> arrayListVideosChennai = new ArrayList<>();
    CompanyVideosAdapter companyVideosAdapter;
    long myDownloadReference;
    DownloadManager downloadManager;
    private BroadcastReceiver receiverDownloadComplete;
    private BroadcastReceiver receiverNotificationClicked;
    DownloadProgressView downloadProgressView;
    static Float availableSpace;
    Float itemSize;
    Boolean isRefreshed = false;
    Boolean isNotificationClicked = false;
    ConnectionDetector cd;
    String projectTitle;

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;

    ArrayList<String> videoList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_events);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            isNotificationClicked = b.getBoolean("Notification_Click");
        }

        if(isNotificationClicked){
            Config.NOTIFICATION_COUNT--;
        }

        setActionBar();
        init();
        getVideoList();
    }

    void setActionBar(){
        MyTextView tv = new MyTextView(getApplicationContext());
        // Create a LayoutParams for TextView
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, // Width of TextView
                ActionBar.LayoutParams.WRAP_CONTENT); // Height of TextView
        tv.setLayoutParams(lp);
        tv.setText(R.string.videos); // ActionBar title text
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(16);

        getSupportActionBar().setDisplayOptions(getSupportActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#570054")));
    }

    void init(){
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        initError();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.company_event_swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.listview_company_events);
        listView.setOnItemClickListener(this);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (listView.getChildAt(0) != null) {
                    mSwipeRefreshLayout.setEnabled(listView.getFirstVisiblePosition() == 0 && listView.getChildAt(0).getTop() == 0);
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

    }

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            if (companyVideosAdapter != null) {
                companyVideosAdapter.clearData();
            }
            getVideoList();
            mSwipeRefreshLayout.setRefreshing(true);
            showRecords();
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    private void getVideoList() {
        if (isInternetPresent) {
            final ProgressDialog mProgressDialog = new ProgressDialog(this, R.style.MyTheme);
           /* mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);*/
            if (!isRefreshed) {
                mProgressDialog.show();
            }
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progressdialog);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Call<Videos> wsCallingVideos = mAPIInterface.getVideos();
            wsCallingVideos.enqueue(new Callback<Videos>() {
                @Override
                public void onResponse(Call<Videos> call, Response<Videos> response) {
                    if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                        mProgressDialog.dismiss();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    arrayListVideos.clear();

                    videoList.clear();
                    arrayListVideosAhmedabad.clear();
                    arrayListVideosDwarka.clear();
                    arrayListVideosHyderabad.clear();
                    arrayListVideosChennai.clear();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            if (response.body().getStatusCode() == REQUEST_STATUS_CODE_NO_RECORDS) {
                                noRecordsFound();
                            }
                            else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_SUCCESS) {
                                for (VideoList item : response.body().getData()) {
                                    projectTitle = item.getDetail();
                                    if (item.getDetail().equalsIgnoreCase("Company Video")) {
                                        arrayListVideos.add(item);
                                        videoList.add(projectTitle);
                                    }
                                    else {
                                        if (!videoList.contains(projectTitle)) {

                                            arrayListVideos.add(item);
                                            videoList.add(projectTitle);
                                        }

                                        if (projectTitle.equalsIgnoreCase("Aavaas Changodar")) {

                                            arrayListVideosAhmedabad.add(item);
                                        }
                                        if (projectTitle.equalsIgnoreCase("Hawthorn dwarka")) {

                                            arrayListVideosDwarka.add(item);
                                        }
                                        if (projectTitle.equalsIgnoreCase("Aavaas (Miyapur), Hyderabad")) {

                                            arrayListVideosHyderabad.add(item);
                                        }
                                        if (projectTitle.equalsIgnoreCase("Aavaas Chennai")) {

                                            arrayListVideosChennai.add(item);
                                        }
                                    }
                                }

                                companyVideosAdapter = new CompanyVideosAdapter(CompanyVideos.this, arrayListVideos);
                                listView.setAdapter(companyVideosAdapter);
                                companyVideosAdapter.notifyDataSetChanged();

                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_ERROR
                                    || response.body().getStatusCode() == REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE) {
                                serviceUnavailable();
                            }

                            if (arrayListVideos.size() > 0) {
                                llEmpty.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                                companyVideosAdapter.notifyDataSetChanged();
                            } else {
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
                public void onFailure(Call<Videos> call, Throwable t) {
                    mSwipeRefreshLayout.setEnabled(false);
                    mProgressDialog.dismiss();
                    serviceUnavailable();
                }
            });
        }
        else {
            noInternetConnection();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String detail = arrayListVideos.get(i).getDetail();

        if (detail.equalsIgnoreCase("Company Video"))
        {
            downloadProgressView = (DownloadProgressView) view.findViewById(R.id.list_item_downloadProgress);
            String Title = arrayListVideos.get(i).getTitle();
            String Url = arrayListVideos.get(i).getUploadPath();
            String fileName = Url.substring( Url.lastIndexOf('/')+1, Url.length() );
            String filesize = arrayListVideos.get(i).getSize();
            String[] size_ = filesize.split(" ");
            itemSize = Float.parseFloat(size_[0]);


            String videopath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + fileName + "/";
            File file = new File(videopath);

            if (file.exists()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videopath));
                intent.setDataAndType(Uri.parse(videopath), "video/mp4");
                //intent.setDataAndType(Uri.fromFile(file), "video/mp4");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "No Application available to view Video", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(availableSpace > itemSize) {
                    if (isWriteStoragePermissionGranted(this)) {
                        downloadVideo(i, Title, fileName);
                    }
                    else{
                        Toast.makeText(this, R.string.give_storage_permission, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                  //  InsufficientStorageInDevice(this, "VacationVideos");
                }
            }
        }
        else if (detail.equalsIgnoreCase("Aavaas Changodar"))
        {
            Intent videoListIntent=new Intent(CompanyVideos.this, CompanyVideoList.class);
            videoListIntent.putExtra("ID", i);
            videoListIntent.putExtra("projectName", detail);
            videoListIntent.putExtra("projectVideo", arrayListVideosAhmedabad);
            startActivity(videoListIntent);
        }
        else if (detail.equalsIgnoreCase("Hawthorn dwarka"))
        {
            Intent videoListIntent=new Intent(CompanyVideos.this, CompanyVideoList.class);
            videoListIntent.putExtra("ID", i);
            videoListIntent.putExtra("projectName", detail);
            videoListIntent.putExtra("projectVideo", arrayListVideosDwarka);
            startActivity(videoListIntent);
        }
        else if (detail.equalsIgnoreCase("Aavaas (Miyapur), Hyderabad"))
        {
            //Aavaas (Miyapur), Hyderabad , Aavaas (Miyapur), Hyderabad
            Intent videoListIntent=new Intent(CompanyVideos.this, CompanyVideoList.class);
            videoListIntent.putExtra("ID", i);
            videoListIntent.putExtra("projectName", detail);
            videoListIntent.putExtra("projectVideo", arrayListVideosHyderabad);
            startActivity(videoListIntent);
        } else if (detail.equalsIgnoreCase("Aavaas Chennai"))
        {
            Intent videoListIntent=new Intent(CompanyVideos.this, CompanyVideoList.class);
            videoListIntent.putExtra("ID", i);
            videoListIntent.putExtra("projectName", detail);
            videoListIntent.putExtra("projectVideo", arrayListVideosChennai);
            startActivity(videoListIntent);
        }
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

    public void downloadVideo(final int position, final String videoName, final String videoFileName){
        new AlertDialog.Builder(CompanyVideos.this)
                .setTitle(R.string.download)
                .setMessage(R.string.video_download_toast)
                .setCancelable(true)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(DownloadManagerResolver.resolve(CompanyVideos.this)) {
                            Uri uri = Uri.parse(arrayListVideos.get(position).getUploadPath().replace(" ","%20"));
                            final DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setDescription("Download in progress").setTitle(videoName);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, videoFileName);
                            request.setVisibleInDownloadsUi(true);
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.allowScanningByMediaScanner();
                            myDownloadReference = downloadManager.enqueue(request);
                            downloadProgressView.show(myDownloadReference, new DownloadProgressView.DownloadStatusListener() {
                                @Override
                                public void downloadFailed(int reason) {

                                }

                                @Override
                                public void downloadSuccessful() {

                                }

                                @Override
                                public void downloadCancelled() {
                                    downloadManager.remove(myDownloadReference);
                                }
                            });
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.stat_sys_download)

                .show();

    }

    public void getDownloadStatus(){
        final ProgressDialog progressBarDialog= new ProgressDialog(this, R.style.MyTheme);
        progressBarDialog.setTitle("Download App Data, Please Wait");

        progressBarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBarDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,
                                int whichButton){
                // Toast.makeText(getBaseContext(),
                //       "OK clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        progressBarDialog.setProgress(0);

        new Thread(new Runnable() {

            @Override
            public void run() {

                boolean downloading = true;

                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                while (downloading) {

                    DownloadManager.Query q = new DownloadManager.Query();
                    //q.setFilterById(DownloadManagerId); //filter by id which you have receieved when reqesting download from download manager
                    Cursor cursor = manager.query(q);
                    cursor.moveToFirst();
                    int bytes_downloaded = cursor.getInt(cursor
                            .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false;
                    }

                    final int dl_progress = (int) ((bytes_downloaded * 100l) / bytes_total);

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            progressBarDialog.setProgress((int) dl_progress);

                        }
                    });

                    cursor.close();
                }

            }
        }).start();


        //show the dialog
        if(progressBarDialog != null) {
            try {
                progressBarDialog.show();
            } catch (Error e) {}
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //getDownloadStatus();

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        receiverNotificationClicked = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraId = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
                long[] references = intent.getLongArrayExtra(extraId);
               /* for(long reference : references) {
                    if(reference == myDownloadReference){
                        // do something with download file
                    }
                }*/
            }
        };
        registerReceiver(receiverNotificationClicked, filter);

        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        receiverDownloadComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            }
        };
        registerReceiver(receiverDownloadComplete, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(receiverDownloadComplete);
        unregisterReceiver(receiverNotificationClicked);
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

    void showRecords() {
        llEmpty.setVisibility(View.GONE);
        txtRetry.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }
}
