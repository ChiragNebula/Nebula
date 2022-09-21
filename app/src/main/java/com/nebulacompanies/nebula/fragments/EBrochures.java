package com.nebulacompanies.nebula.fragments;

import static com.nebulacompanies.nebula.Permissions.isReadStoragePermissionGranted;
import static com.nebulacompanies.nebula.Permissions.isWriteStoragePermissionGranted;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_ERROR;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_NO_RECORDS;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SUCCESS;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.BuildConfig;

import com.nebulacompanies.nebula.DownloadManagerResolver;
import com.nebulacompanies.nebula.Model.Guest.EDocumentList;
import com.nebulacompanies.nebula.Model.Guest.EDocuments;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.EBrochuresAdapters;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.view.DownloadProgressView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 12/27/2016.
 */

public class EBrochures extends Fragment implements AdapterView.OnItemClickListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    DownloadManager downloadManager;
    private long myDownloadReference;
    private BroadcastReceiver receiverDownloadComplete;
    private BroadcastReceiver receiverNotificationClicked;
    DownloadProgressView downloadProgressView;
    static Float availableSpace;
    Float itemSize;
    EBrochuresAdapters eBrochuresAdapter;
    Boolean isRefreshed = false;
    ConnectionDetector cd;
    private APIInterface mAPIInterface;
    public static ArrayList<EDocumentList> arrayListEDocuments = new ArrayList<>();
    public static final String TAG = "EBrochures";

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;
    //FancyShowCaseView mFancyShowCaseView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.company_events, container, false);
        init(view);
        getEBrochures();
        return view;
    }

    void init(View view) {
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        initError(view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.company_event_swipe_refresh_layout);
        listView = (ListView) view.findViewById(R.id.listview_company_events);
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

        /*mFancyShowCaseView = new FancyShowCaseView.Builder(getActivity())
                .focusOn(listView.getChildAt(1))
                .customView(R.layout.layout_my_custom_view, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.btn_action_1).setOnClickListener(mClickListener);
                    }
                })
                .closeOnTouch(false)
                .build();
        mFancyShowCaseView.show();*/

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            return;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cd = new ConnectionDetector(getActivity().getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        mAPIInterface = APIClient.getClient(getActivity()).create(APIInterface.class);
    }

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            if (eBrochuresAdapter != null) {
                eBrochuresAdapter.clearData();
                eBrochuresAdapter.notifyDataSetChanged();
            }
            getEBrochures();
            mSwipeRefreshLayout.setRefreshing(true);
            showRecords();
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    private void getEBrochures() {
        if (isInternetPresent) {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
            /*mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);*/
            if (!isRefreshed) {
                mProgressDialog.show();
            }
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progressdialog);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            Call<EDocuments> wsCallingEDocuments = mAPIInterface.getEDocuments("EBrochures");
            wsCallingEDocuments.enqueue(new Callback<EDocuments>() {
                @Override
                public void onResponse(Call<EDocuments> call, Response<EDocuments> response) {
                    if (mProgressDialog != null && mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    arrayListEDocuments.clear();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            if (response.body().getStatusCode() == REQUEST_STATUS_CODE_NO_RECORDS) {
                                noRecordsFound();
                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_SUCCESS) {
                                arrayListEDocuments.addAll(response.body().getData());
                                eBrochuresAdapter = new EBrochuresAdapters(getActivity(), arrayListEDocuments);
                                listView.setAdapter(eBrochuresAdapter);
                                eBrochuresAdapter.notifyDataSetChanged();
                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_ERROR
                                    || response.body().getStatusCode() == REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE) {
                                serviceUnavailable();
                            }

                            if (arrayListEDocuments.size() > 0) {
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
                public void onFailure(Call<EDocuments> call, Throwable t) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    mProgressDialog.dismiss();
                    serviceUnavailable();
                }
            });
        } else {
            noInternetConnection();
        }
    }

  /*  @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String filepath = arrayListEDocuments.get(i).getDocumentFilePath();
        String url = "https://docs.google.com/gview?embedded=true&url=" + filepath;
        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent1);
    }*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        downloadProgressView = (DownloadProgressView) view.findViewById(R.id.edoc_downloadProgressView);

        String name = arrayListEDocuments.get(i).getDocumentName();
        //String thumbnail = DocumentThumbnail.get(i).toString();
        String filepath = arrayListEDocuments.get(i).getDocumentFilePath();
        String filesize = arrayListEDocuments.get(i).getDocumentFileSize();

        String path = filepath.replaceAll("%20", "");

        String fileName = path.substring(path.lastIndexOf('/') + 1, path.length());

        String[] size_ = filesize.split(" ");

        itemSize = Float.parseFloat(size_[0]);



        String brochurepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + fileName + "/";
        File file = new File(brochurepath);
        Uri uri;

        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        File file1 = new File(String.valueOf(uri));
        Log.d("brochures In", fileName + " : " + uri + " :: " + file.canRead() + " :: " + file.exists() + " : " + file.isFile());
        Log.d("brochures In1", fileName + " : " + uri + " :: " + file1.canRead() + " :: " + file1.exists() + " : " + file1.isFile());
       if (file.exists()) {

            if (isReadStoragePermissionGranted(getActivity())) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    getActivity().grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), R.string.give_storage_permission, Toast.LENGTH_SHORT).show();
            }
        } else {
           downloadEBrochures(i, name, fileName, uri);
          }
    }

    public void downloadEBrochures(final int position, final String brochureName, final String brochureFileName, Uri path) {

        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.download)
                .setMessage(R.string.pdf_dwonload_toast)
                .setCancelable(true)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (DownloadManagerResolver.resolve(getActivity())) {
                            Uri uri = Uri.parse(arrayListEDocuments.get(position).getDocumentFilePath());
                            final DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setDescription("Download in progress").setTitle(brochureName);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, brochureFileName);
                            request.setVisibleInDownloadsUi(true);
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.allowScanningByMediaScanner();
                            myDownloadReference = downloadManager.enqueue(request);
                            //downloadManager.remove(myDownloadReference);


                            downloadProgressView.show(myDownloadReference, new DownloadProgressView.DownloadStatusListener() {
                                @Override
                                public void downloadFailed(int reason) {

                                }

                                @Override
                                public void downloadSuccessful() {
                                 /*   String Pdffile;
                                    Intent intentShareFile;
                                    if (brochureFileName.endsWith("pdf")) {
                                        String fpath = getExternalStorageDirectory().getAbsolutePath() + "/Download/" + brochureFileName;
                                        //   String fpath="/Internal storage/Download/"+brochureFileName;
                                        Log.d("fpath", fpath);
                                        Pdffile = "application/pdf";
                                        intentShareFile = new Intent(Intent.ACTION_SEND);
                                        intentShareFile.setType(Pdffile);
                                        intentShareFile.addFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION | FLAG_GRANT_PERSISTABLE_URI_PERMISSION );
                                      //  intentShareFile.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION);
                                        intentShareFile.putExtra(Intent.EXTRA_STREAM,path);
                                        intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Sharing File...");
                                        intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
*//*
                                        List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(intentShareFile, PackageManager.MATCH_DEFAULT_ONLY);
                                        for (ResolveInfo resolveInfo : resInfoList) {
                                            String packageName = resolveInfo.activityInfo.packageName;
                                            getActivity().grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        }*//*
                                    } else {
                                        intentShareFile = new Intent(Intent.ACTION_SEND);
                                        intentShareFile.setType("image/*");
                                        intentShareFile.putExtra(Intent.EXTRA_STREAM, path);
                                    }
                                    getActivity().startActivity(Intent.createChooser(intentShareFile, getActivity().getResources().getString(R.string.share_via)));*/

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
    public void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        receiverNotificationClicked = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraId = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
                long[] references = intent.getLongArrayExtra(extraId);
                for (long reference : references) {
                    if (reference == myDownloadReference) {
                        // do something with download file
                    }
                }
            }
        };
        getActivity().registerReceiver(receiverNotificationClicked, filter);

        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        receiverDownloadComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            }
        };
        getActivity().registerReceiver(receiverDownloadComplete, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiverDownloadComplete);
        getActivity().unregisterReceiver(receiverNotificationClicked);
    }

    void initError(View view) {
        llEmpty = (LinearLayout) view.findViewById(R.id.llEmpty);
        imgError = (ImageView) view.findViewById(R.id.imgError);
        txtErrorTitle = (MyTextView) view.findViewById(R.id.txtErrorTitle);
        txtRetry = (MyTextView) view.findViewById(R.id.txtRetry);
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

   /* @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //outState.putSerializable("myAdapter", projectsAdapter);
        outState.putStringArrayList("DocumentName", DocumentName);
        outState.putStringArrayList("DocumentFileSize", DocumentFileSize);
        outState.putStringArrayList("DocumentThumbnail", DocumentThumbnail);
        outState.putStringArrayList("DocumentFilePath", DocumentFilePath);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            ArrayList name = savedInstanceState.getStringArrayList("DocumentName");
            ArrayList size = savedInstanceState.getStringArrayList("DocumentFileSize");
            ArrayList thumbnail = savedInstanceState.getStringArrayList("DocumentThumbnail");
            ArrayList path = savedInstanceState.getStringArrayList("DocumentFilePath");

            eBrochuresAdapter = new EBrochuresAdapter(getActivity(), name, size, thumbnail, path);
            listView.setAdapter(eBrochuresAdapter);
            eBrochuresAdapter.notifyDataSetChanged();
        }
    }*/

    void showRecords() {
        llEmpty.setVisibility(View.GONE);
        txtRetry.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //  mFancyShowCaseView.hide();
        }
    };
}
