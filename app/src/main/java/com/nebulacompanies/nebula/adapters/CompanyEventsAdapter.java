package com.nebulacompanies.nebula.adapters;

import static com.nebulacompanies.nebula.util.SetDateFormat.SetGmtTime;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nebulacompanies.nebula.Model.Guest.EventList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.MyBoldTextView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;

/**
 * Created by Palak Mehta on 11/5/2016.
 */

public class CompanyEventsAdapter extends BaseAdapter {

    Context context;
    ArrayList<EventList> arrayListEventList = new ArrayList<>();

    public CompanyEventsAdapter(Context context, ArrayList<EventList> eventLists) {
        this.context = context;
        arrayListEventList.clear();
        arrayListEventList.addAll(eventLists);
    }

    public int getCount() {
        return arrayListEventList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayListEventList.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        MyBoldTextView txtTitle;
        MyTextView txtPlace, txtDate;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_events, null);
            holder = new ViewHolder();
            //holder.imageView = (ImageView) convertView.findViewById(R.id.lis t_item_image1);
            holder.txtTitle = (MyBoldTextView) convertView.findViewById(R.id.list_item_name1);
            holder.txtPlace = (MyTextView) convertView.findViewById(R.id.list_item_place1);
            holder.txtDate = (MyTextView) convertView.findViewById(R.id.list_item_date1);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position < arrayListEventList.size()) {
            holder.txtTitle.setText(arrayListEventList.get(position).getEventName());
            holder.txtPlace.setText(arrayListEventList.get(position).getLocation());

            holder.txtDate.setText(SetGmtTime(arrayListEventList.get(position).getEventDate()));

            /*Glide.with(context).load(arrayListEventList.get(position).getEventlogo())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);*/
        }
        return convertView;
    }

    public void clearData() {
        // clear the data
        arrayListEventList.clear();
    }

}
