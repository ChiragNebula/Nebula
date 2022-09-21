package com.nebulacompanies.nebula.adapters;

import static com.nebulacompanies.nebula.util.SetDateFormat.SetGmtTime;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nebulacompanies.nebula.Config;
import com.nebulacompanies.nebula.Model.Guest.NotificationList;
import com.nebulacompanies.nebula.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Palak Mehta on 6/25/2016.
 */
public class NotificationsListViewAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<NotificationList> arrayListNotificationList = new ArrayList<>();
    public NotificationsListViewAdapter(Activity activity, ArrayList<NotificationList> NotificationLists) {
        this.activity = activity;
        arrayListNotificationList.clear();
        arrayListNotificationList.addAll(NotificationLists);
    }

    @Override
    public int getCount() {
        return arrayListNotificationList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView messageTextView, created_dateTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)
                activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        holder = new ViewHolder();

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_row_notifications, null);
            holder.messageTextView = (TextView) convertView.findViewById(R.id.message);
            holder.created_dateTextView = (TextView) convertView.findViewById(R.id.created_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position < arrayListNotificationList.size()) {
            holder.messageTextView.setText(arrayListNotificationList.get(position).getMessage());

            String date = SetGmtTime(arrayListNotificationList.get(position).getCreatedDate());
           /* String[] parts;

            parts = date.split("T");
             System.out.println("Date: " + parts[0]);
           System.out.println("Time: " + parts[1]);*/
            String differenceindays = "";
            Date currentDate = new Date();
            String fDate = new SimpleDateFormat("dd-MM-yyyy").format(currentDate);
            System.out.println("currentDate: " + fDate);

            try {
                Date date1;
                Date date2;
                SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");
                //Setting dates
                date1 = dates.parse(date);
                date2 = dates.parse(fDate);
                //Comparing dates
                long difference = Math.abs(date1.getTime() - date2.getTime());
                long differenceDates = difference / (24 * 60 * 60 * 1000);
                //Convert long to String
                String dayDifference = Long.toString(differenceDates);

                if (dayDifference.equals("0")) {
                    differenceindays = "Today";
                } else if (dayDifference.equals("1")) {
                    differenceindays = dayDifference + " day ago";
                } else {
                    differenceindays = dayDifference + " days ago";
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.created_dateTextView.setText(differenceindays);
            Typeface tf1 = Typeface.createFromAsset(activity.getAssets(), Config.FONT_STYLE);
            holder.messageTextView.setTypeface(tf1);
            holder.created_dateTextView.setTypeface(tf1);
        }
        return convertView;
    }

    public void clearData() {
        // clear the data
       arrayListNotificationList.clear();
    }
}
