package com.nebulacompanies.nebula.adapters;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nebulacompanies.nebula.BuildConfig;
import com.nebulacompanies.nebula.Model.Guest.EDocumentList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.DownloadProgressView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sagar Virvani on 12-07-2018.
 */

public class EBrochuresAdapters extends BaseAdapter {

    Activity context;
    ArrayList<EDocumentList> arrayListEDocumentList = new ArrayList<>();
    String Pdffile;
    Intent intentShareFile;

   /* FancyShowCaseView mFancyShowCaseView;*/

    static Float availableSpace;
    Float itemSize;
    private long myDownloadReference;
    DownloadManager downloadManager;
   // DownloadProgressView downloadProgressView;

    public EBrochuresAdapters(Activity context, ArrayList<EDocumentList> edocumentsLists) {
        this.context = context;
        arrayListEDocumentList.clear();
        arrayListEDocumentList.addAll(edocumentsLists);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return arrayListEDocumentList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayListEDocumentList.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private class ViewHolder {
        ImageView imageView, share;
        MyTextView txtTitle, txtSize;
        DownloadProgressView downloadProgressView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_edocuments, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.edoc_thumnbail);
            holder.txtTitle = (MyTextView) convertView.findViewById(R.id.edoc_text);
            holder.txtSize = (MyTextView) convertView.findViewById(R.id.edoc_size);
            holder.share = (ImageView) convertView.findViewById(R.id.edoc_share);
            holder.downloadProgressView = (DownloadProgressView) convertView.findViewById(R.id.edoc_downloadProgressView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position < arrayListEDocumentList.size()) {
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            holder.txtTitle.setText(arrayListEDocumentList.get(position).getDocumentName());
            holder.txtSize.setText(arrayListEDocumentList.get(position).getDocumentFileSize());

            Glide.with(context).load(arrayListEDocumentList.get(position).getDocumentThumbnail())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.nebula_placeholder_land)
                    .into(holder.imageView);



            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String filePath = arrayListEDocumentList.get(position).getDocumentFilePath().replace("%20", "");

                    final String file_Name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length());
                    String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + file_Name + "/";
                    final File file = new File(pdfpath);
                    final Uri path;
                    if (Build.VERSION.SDK_INT >= 24) {
                        path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
                    } else {
                        path = Uri.fromFile(file);
                    }
                    Log.d("brochures",file_Name+" : "+path+ " : "+new File(String.valueOf(path)).isFile()+" : "+file.exists());
                   if (file.exists()) {
                       Log.d("brochures","Exists:: "+file_Name);
                        if (file_Name.endsWith("pdf")) {
                            Pdffile = "application/pdf";
                            intentShareFile = new Intent(Intent.ACTION_SEND);
                            intentShareFile.setType(Pdffile);
                            intentShareFile.putExtra(Intent.EXTRA_STREAM, path);
                            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Sharing File...");
                            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
                          /*  List<ResolveInfo> resInfoList =context.getPackageManager().queryIntentActivities(intentShareFile, PackageManager.MATCH_DEFAULT_ONLY);
                            for (ResolveInfo resolveInfo : resInfoList) {
                                String packageName = resolveInfo.activityInfo.packageName;
                                context.grantUriPermission(packageName, path,  Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            }*/
                        } else {
                            intentShareFile = new Intent(Intent.ACTION_SEND);
                            intentShareFile.setType("image/*");
                            intentShareFile.putExtra(Intent.EXTRA_STREAM, path);
                        }
                        context.startActivity(Intent.createChooser(intentShareFile, context.getResources().getString(R.string.share_via)));
                    } else {
                        Toast.makeText(context, R.string.pdf_share_toast, Toast.LENGTH_SHORT).show();
                    }
                }
            });

           /* final ViewHolder finalHolder = holder;
            mFancyShowCaseView = new FancyShowCaseView.Builder(context)
                    .focusOn(holder.txtTitle)
                    .customView(R.layout.layout_ebrochuer_view, new OnViewInflateListener() {
                        @Override
                        public void onViewInflated(View view) {
                            view.findViewById(R.id.ebro_layout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    FancyShowCaseView.hideCurrent((Activity) context);
                                    String name = arrayListEDocumentList.get(position).getDocumentName();
                                    //String thumbnail = DocumentThumbnail.get(i).toString();
                                    String filepath = arrayListEDocumentList.get(position).getDocumentFilePath();
                                    String filesize = arrayListEDocumentList.get(position).getDocumentFileSize();

                                    String path = filepath.replaceAll("%20", "");

                                    final String fileName = path.substring(path.lastIndexOf('/') + 1, path.length());

                                    String[] size_ = filesize.split(" ");

                                    itemSize = Float.parseFloat(size_[0]);


                                    if (filesize.contains("MB")) {
                                        availableSpace = megabytesAvailable();
                                    } else if (filesize.contains("KB")) {
                                        availableSpace = kilobytesAvailable();
                                    }

                                    String brochurepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + fileName + "/";
                                    File file = new File(brochurepath);

                                    if (file.exists()) {

                                        Uri uri;
                                        if (Build.VERSION.SDK_INT >= 24) {
                                            uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
                                        } else {
                                            uri = Uri.fromFile(file);
                                        }

                                        if (isReadStoragePermissionGranted(context)) {
                                            Intent intent = new Intent(Intent.ACTION_VIEW);
                                            intent.setDataAndType(uri, "application/pdf");
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                                            for (ResolveInfo resolveInfo : resInfoList) {
                                                String packageName = resolveInfo.activityInfo.packageName;
                                                context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                            }
                                            try {
                                                context.startActivity(intent);
                                            } catch (ActivityNotFoundException e) {
                                                Toast.makeText(context, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(context, R.string.give_storage_permission, Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        if (availableSpace > itemSize) {
                                            if (isWriteStoragePermissionGranted(context)) {
                                                new AlertDialog.Builder(context)
                                                        .setTitle(R.string.download)
                                                        .setMessage(R.string.pdf_dwonload_toast)
                                                        .setCancelable(true)
                                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                if (DownloadManagerResolver.resolve(context)) {
                                                                    Uri uri = Uri.parse(arrayListEDocumentList.get(position).getDocumentFilePath());
                                                                    final DownloadManager.Request request = new DownloadManager.Request(uri);
                                                                    request.setDescription("Download in progress").setTitle(file_Name);
                                                                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                                                                    request.setVisibleInDownloadsUi(true);
                                                                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                                                                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                                                    request.allowScanningByMediaScanner();
                                                                    myDownloadReference = downloadManager.enqueue(request);
                                                                    //downloadManager.remove(myDownloadReference);


                                finalHolder.downloadProgressView.show(myDownloadReference, new DownloadProgressView.DownloadStatusListener() {
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

                                            } else {
                                                Toast.makeText(context, R.string.give_storage_permission, Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            InsufficientStorageInDevice(context, "EBrochures");
                                        }
                                    }
                                }
                            });
                        }
                    })
                    .closeOnTouch(false)
                    .build();
            mFancyShowCaseView.show();*/
        }
        return convertView;
    }

    public void clearData() {
        // clear the data
        arrayListEDocumentList.clear();
    }

}

