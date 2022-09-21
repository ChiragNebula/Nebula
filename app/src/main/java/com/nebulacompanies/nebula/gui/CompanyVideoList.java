package com.nebulacompanies.nebula.gui;

import static com.nebulacompanies.nebula.Permissions.isWriteStoragePermissionGranted;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.CompanyVideoListAdapter;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.view.DownloadProgressView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sagar Virvani on 19-12-2017.
 */

public class CompanyVideoList extends Base2Activity implements AdapterView.OnItemClickListener{

    String VIDEO_TITLE;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    CompanyVideoListAdapter companyVideosAdapter;
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

     ArrayList<VideoList> arrayListVideos ;

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_events);

        Intent b = getIntent();

        if(b != null) {
            VIDEO_TITLE=b.getExtras().getString("projectName");
            arrayListVideos = (ArrayList<VideoList>) b.getSerializableExtra("projectVideo");
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
        tv.setText(VIDEO_TITLE); // ActionBar title text
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(16);

        getSupportActionBar().setDisplayOptions(getSupportActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#570054")));
    }

    void init(){
        initError();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.company_event_swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.listview_company_events);

        listView.setOnItemClickListener(this);


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

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

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
    }

    private void getVideoList()
    {
            companyVideosAdapter = new CompanyVideoListAdapter(this,arrayListVideos);
            listView.setAdapter(companyVideosAdapter);
            companyVideosAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        downloadProgressView = (DownloadProgressView) view.findViewById(R.id.list_item_downloadProgress);
        String Title = arrayListVideos.get(position).getTitle();
        String Url = arrayListVideos.get(position).getUploadPath();
        Log.i("INFO", "Url :" + Url);
        String fileName = Url.substring( Url.lastIndexOf('/')+1, Url.length() );

        String filesize = arrayListVideos.get(position).getSize().toString();
        String[] size_ = filesize.split(" ");
        Log.i("INFO", "Size :" + size_[0]);
        itemSize = Float.parseFloat(size_[0]);
        Log.i("INFO", "itemSize :" + itemSize);



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
            downloadVideo(position, Title, fileName);
        }

    }

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            if (companyVideosAdapter != null) {
                companyVideosAdapter.clearData();
                companyVideosAdapter.notifyDataSetChanged();
            }
            mSwipeRefreshLayout.setRefreshing(false);
            getVideoList();
        }
        else {
            mSwipeRefreshLayout.setEnabled(false);
            noInternetConnection();

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

   /* @Override
    public void processFinish(String output, String Tag) {
        if (output == null) {
            output = "THERE WAS AN ERROR";
        }
        String response = output.toString();
        Log.i("INFO", "response :" + response);
        if (Tag.equals("VacationVideos")) {
            try {
                JSONObject object = new JSONObject(response);
                String status_code = object.getString("Statuscode");
                if (status_code.equals("0")) {
                    JSONArray new_array = object.getJSONArray("Data");
                    for (int i = 0; i < new_array.length(); i++) {
                        try {
                            JSONObject jsonObject = new_array.getJSONObject(i);
                            Detail.add(jsonObject.getString("Detail").toString());
                            if (jsonObject.getString("Detail").toString().equals(VIDEO_TITLE)) {
                                title.add(jsonObject.getString("Title").toString());
                                size.add(jsonObject.getString("Size").toString());
                                thumbnail.add(jsonObject.getString("Images"));
                                url.add(jsonObject.getString("Upload").toString());
                               }
                            companyVideosAdapter = new CompanyVideoListAdapter(this, title, size, thumbnail, url, Detail);
                            listView.setAdapter(companyVideosAdapter);
                            companyVideosAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                } else if (status_code.equals("1")) {
                    // NO Data Found
                } else if (status_code.equals("-1")) {
                    // Please Reload, There is an error.
                }
                mSwipeRefreshLayout.setRefreshing(false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInParallel(AsyncComplex task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }*/
    public void downloadVideo(final int position, final String videoName, final String videoFileName){
        new AlertDialog.Builder(CompanyVideoList.this)
                .setTitle(R.string.download)
                .setMessage(R.string.video_download_toast)
                .setCancelable(true)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(DownloadManagerResolver.resolve(CompanyVideoList.this)) {
                            Uri uri = Uri.parse(arrayListVideos.get(position).getUploadPath().replace(" ","%20"));
                            Log.i("INFO","uri video:-"+arrayListVideos.get(position).getUploadPath());
                            final DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setDescription("Download in progress").setTitle(videoName);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, videoFileName);
                            request.setVisibleInDownloadsUi(true);
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.allowScanningByMediaScanner();
                            myDownloadReference = downloadManager.enqueue(request);
                            Log.i("INFO", "myDownloadReference :" + Config.myDownloadReference_videos);

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
    @Override
    protected void onResume() {
        super.onResume();

        //getDownloadStatus();

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        receiverNotificationClicked = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraId = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
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
}
