 package com.nebulacompanies.nebula.adapters;

 import android.app.Activity;
 import android.app.DownloadManager;
 import android.content.Context;
 import android.graphics.Typeface;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.BaseAdapter;
 import android.widget.ImageView;
 import android.widget.LinearLayout;
 import android.widget.TextView;

 import com.bumptech.glide.Glide;
 import com.bumptech.glide.load.resource.drawable.GlideDrawable;
 import com.bumptech.glide.request.RequestListener;
 import com.bumptech.glide.request.target.Target;
 import com.nebulacompanies.nebula.Config;
 import com.nebulacompanies.nebula.Model.Guest.VideoList;
 import com.nebulacompanies.nebula.R;
 import com.nebulacompanies.nebula.view.DownloadProgressView;

 import java.util.ArrayList;

 /**
  * Created by Palak Mehta on 11/28/2016.
  */

 public class CompanyVideosAdapter extends BaseAdapter {

     Context context;
     DownloadManager downloadManager;
     //CompanyVideoList companyVideoList;
     ArrayList<VideoList> arrayListVideoList = new ArrayList<>();
     public CompanyVideosAdapter(Context context, ArrayList<VideoList> videoLists) {
         this.context = context;
         arrayListVideoList.clear();
         arrayListVideoList.addAll(videoLists);
         downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
     }

     /*private view holder class*/
     private class ViewHolder {
         ImageView imageView;
         TextView txtTitle, txtSize;
         DownloadProgressView downloadProgressView;
         LinearLayout videoLinearLayout;
     }

     public View getView(final int position, View convertView, ViewGroup parent) {
         //ViewHolder holder = null;
         final ViewHolder holder;
         LayoutInflater mInflater = (LayoutInflater)
                 context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
         if (convertView == null) {
             convertView = mInflater.inflate(R.layout.list_item_videos, null);
             holder = new ViewHolder();
             holder.videoLinearLayout=(LinearLayout)convertView.findViewById(R.id.list_item_video);
             holder.imageView = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
             holder.txtTitle = (TextView) convertView.findViewById(R.id.list_item_title);
             holder.txtSize = (TextView) convertView.findViewById(R.id.list_item_size);
             holder.downloadProgressView = (DownloadProgressView) convertView.findViewById(R.id.list_item_downloadProgress);
             convertView.setTag(holder);
         }
         else {
             holder = (ViewHolder) convertView.getTag();
         }

             if(position < arrayListVideoList.size()) {
                 if (!arrayListVideoList.get(position).getDetail().equals("Company Video"))
                 {
                     holder.txtTitle.setText(arrayListVideoList.get(position).getDetail());
                     holder.txtSize.setText(" ");

                     Glide.with(context).load(arrayListVideoList.get(position).getProjectLogo())
                             .placeholder(R.drawable.nebula_placeholder)
                             .dontAnimate()
                             .listener(new RequestListener<String, GlideDrawable>() {
                                 @Override
                                 public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                     return false;
                                 }

                                 @Override
                                 public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                     holder.imageView.setVisibility(View.VISIBLE);
                                     return false;
                                 }
                             })
                             .into(holder.imageView);

                     /*Picasso.with(context).load(arrayListVideoList.get(position).getProjectLogo())
                             .into(holder.imageView, new com.squareup.picasso.Callback() {
                                 @Override
                                 public void onSuccess() {
                                     holder.imageView.setVisibility(View.VISIBLE);
                                 }
                                 @Override
                                 public void onError() {
                                 }
                             });
 */
                 }
                 else
                 {
                     holder.txtTitle.setText(arrayListVideoList.get(position).getTitle());
                     holder.txtSize.setText(arrayListVideoList.get(position).getSize());

                     Glide.with(context)
                             .load(arrayListVideoList.get(position).getProjectLogo())
                             .placeholder(R.drawable.nebula_placeholder)
                             .dontAnimate()
                             .listener(new RequestListener<String, GlideDrawable>() {
                                 @Override
                                 public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                     return false;
                                 }

                                 @Override
                                 public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                     holder.imageView.setVisibility(View.VISIBLE);
                                     return false;
                                 }
                             })
                             .into(holder.imageView);

                    /* Picasso.with(context)
                             .load(arrayListVideoList.get(position).getThumbnailImages())
                             .into(holder.imageView, new com.squareup.picasso.Callback() {
                                 @Override
                                 public void onSuccess() {
                                     holder.imageView.setVisibility(View.VISIBLE);
                                 }
                                 @Override
                                 public void onError() {

                                 }
                             });*/
             }

                 Typeface tf1 = Typeface.createFromAsset(context.getAssets(), Config.FONT_STYLE);
                 holder.txtTitle.setTypeface(tf1);
                 holder.txtSize.setTypeface(tf1);
                 if(!Config.myDownloadReference_videos.isEmpty()) {
                     if(!Config.myDownloadReference_videos.get(position).toString().isEmpty()){
                         Log.i("INFO", "Download ID :" + Config.myDownloadReference_videos.get(position));
                     }
                 }
         }
         return convertView;
     }

     @Override
     public int getCount() {
         return arrayListVideoList.size();
     }

     @Override
     public Object getItem(int position) {
         return position;
     }

     @Override
     public long getItemId(int position) {
         return position;
     }

     public void clearData() {
         // clear the data
         arrayListVideoList.clear();
     }

 }
