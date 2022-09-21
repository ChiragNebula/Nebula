package com.nebulacompanies.nebula.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
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
import com.nebulacompanies.nebula.view.MyTextView;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by Palak Mehta on 12/27/2016.
 */

public class EBrochuresAdapter extends BaseAdapter {

    Context context;
    ArrayList<EDocumentList> arrayListEDocumentList = new ArrayList<>();
    String  Pdffile;
    Intent intentShareFile;

    public EBrochuresAdapter(Context context, ArrayList<EDocumentList> edocumentsLists) {
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
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_edocuments, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.edoc_thumnbail);
            holder.txtTitle = (MyTextView) convertView.findViewById(R.id.edoc_text);
            holder.txtSize = (MyTextView) convertView.findViewById(R.id.edoc_size);
            holder.share = (ImageView) convertView.findViewById(R.id.edoc_share);
            //holder.downloadProgressView = (DownloadProgressView) convertView.findViewById(R.id.list_item_downloadProgress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position < arrayListEDocumentList.size()) {
            holder.txtTitle.setText(arrayListEDocumentList.get(position).getDocumentName());
            holder.txtSize.setText(arrayListEDocumentList.get(position).getDocumentFileSize());

            Glide.with(context).load(arrayListEDocumentList.get(position).getDocumentThumbnail())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.nebula_placeholder_land)
                    .into(holder.imageView);

            String filePath = arrayListEDocumentList.get(position).getDocumentFilePath().replace("%20", "");

            final String file_Name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length());
            String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + file_Name + "/";
            final File file = new File(pdfpath);
            final Uri path;
            if (Build.VERSION.SDK_INT >= 24) {
                path = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
            }else
            {
                path = Uri.fromFile(file);
            }
            //final Uri path = Uri.fromFile(file);
            
            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (file.exists()) {
                        if (file_Name.endsWith("pdf"))
                        {
                            Pdffile = "application/pdf";
                            intentShareFile = new Intent(Intent.ACTION_SEND);
                            intentShareFile.setType(Pdffile);
                            intentShareFile.putExtra(Intent.EXTRA_STREAM, path);
                            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Sharing File...");
                            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
                        }
                        else
                        {
                            intentShareFile = new Intent(Intent.ACTION_SEND);
                            intentShareFile.setType("image/*");
                            intentShareFile.putExtra(Intent.EXTRA_STREAM, path);
                        }

                        /*intentShareFile.setType("application/pdf");
                        intentShareFile.putExtra(Intent.EXTRA_STREAM, path);
                        intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Sharing File...");
                        intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
                        context.startActivity(Intent.createChooser(intentShareFile, context.getResources().getString(R.string.share_via)));*/
                        context.startActivity(Intent.createChooser(intentShareFile, context.getResources().getString(R.string.share_via)));
                    } else {
                        Toast.makeText(context, R.string.pdf_share_toast, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //Walkthrough
            /*  if(position == 0){
                fancyShowCaseView = new FancyShowCaseView.Builder((Activity) context)
                        .focusOn(convertView)
                        //.title("Click to view")
                        //.enableTouchOnFocusedView(true)
                        .customView(R.layout.layout_projects_pointer, new OnViewInflateListener() {
                            @Override
                            public void onViewInflated(View view) {
                                //view.findViewById(R.id.image1).setOnClickListener(mClickListener);

                                view.findViewById(R.id.image1).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        FancyShowCaseView.hideCurrent((Activity) context);

                                        //walkThroughListener.onMethodCallback("EBrochure");
                                        *//*Intent i = new Intent(context, FullScreenSwipeActivity.class);
                                        i.putExtra("id", position);
                                        i.putExtra("image_path", arrayListProjectList);
                                        context.startActivity(i);*//*
                                    }
                                });
                            }
                        })
                        .dismissListener(new DismissListener() {
                            @Override
                            public void onDismiss(String id) {
                                *//*Intent i = new Intent(context, FullScreenSwipeActivity.class);
                                i.putExtra("id", position);
                                i.putExtra("image_path", arrayListProjectList);
                                context.startActivity(i);*//*
                                //fancyShowCaseView.hide();
                            }

                            @Override
                            public void onSkipped(String id) {

                            }
                        })
                        .closeOnTouch(false)
                        .titleSize(40, 0)
                        .build();
                fancyShowCaseView.show();
            }*/
        }
        return convertView;
    }

    public void clearData() {
        // clear the data
        arrayListEDocumentList.clear();
    }

}
