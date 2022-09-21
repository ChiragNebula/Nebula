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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nebulacompanies.nebula.Config;
import com.nebulacompanies.nebula.Model.Guest.VideoList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.DownloadProgressView;

import java.util.ArrayList;

/**
 * Created by Sagar Virvani on 19-12-2017.
 */

public class CompanyVideoListAdapter extends BaseAdapter {
    Context context;
    DownloadManager downloadManager;
    ArrayList<VideoList> arrayListVideoList_ = new ArrayList<>();
    public CompanyVideoListAdapter(Context context, ArrayList<VideoList> videoList) {
        this.context = context;
        arrayListVideoList_.clear();
        arrayListVideoList_.addAll(videoList);
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle, txtSize;
        DownloadProgressView downloadProgressView;
        LinearLayout videoLinearLayout;
    }

    @Override
    public int getCount() {
        return arrayListVideoList_.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        holder = new ViewHolder();

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_videos, null);
            holder.videoLinearLayout = (LinearLayout) convertView.findViewById(R.id.list_item_video);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.list_item_title);
            holder.txtSize = (TextView) convertView.findViewById(R.id.list_item_size);
            holder.downloadProgressView = (DownloadProgressView) convertView.findViewById(R.id.list_item_downloadProgress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position < arrayListVideoList_.size()) {
            /*Log.i("INFO","call for information:-"+detail.get(position).toString());*/
                holder.txtTitle.setText(arrayListVideoList_.get(position).getTitle().toString());
                holder.txtSize.setText(arrayListVideoList_.get(position).getSize().toString());
                Glide.with(context).load(arrayListVideoList_.get(position).getThumbnailImages().toString())
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.nebula_placeholder_land)
                        .into(holder.imageView);
                //get for video
            Typeface tf1 = Typeface.createFromAsset(context.getAssets(), Config.FONT_STYLE);
            holder.txtTitle.setTypeface(tf1);
            holder.txtSize.setTypeface(tf1);
            if (!Config.myDownloadReference_videos.isEmpty()) {
                if (!Config.myDownloadReference_videos.get(position).toString().isEmpty()) {
                    Log.i("INFO", "Download ID :" + Config.myDownloadReference_videos.get(position));
                }
            }
        }
            return convertView;
        }


        public void clearData() {
        // clear the data
            arrayListVideoList_.clear();
    }
}
