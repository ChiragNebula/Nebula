package com.nebulacompanies.nebula.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nebulacompanies.nebula.Model.Guest.SiteProgressList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.MyBoldTextView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;

/*import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.OnViewInflateListener;*/

/**
 * Created by Palak Mehta on 9/15/2016.
 */
public class SiteProgressAdapter extends BaseAdapter {

    Activity context;
    ArrayList<SiteProgressList> arrayListSiteProgressList = new ArrayList<>();
   // FancyShowCaseView mFancyShowCaseView;
    String  product_name;
    int project_id;

    public SiteProgressAdapter(Activity context, ArrayList<SiteProgressList> siteProgressLists, String product_name, int project_id) {
        this.context = context;
        this.product_name=product_name;
        this.project_id=project_id;
        arrayListSiteProgressList.clear();
        arrayListSiteProgressList.addAll(siteProgressLists);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return arrayListSiteProgressList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayListSiteProgressList.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /*private view holder class*/
    private class ViewHolder {
        MyTextView yeartxtname;
        MyBoldTextView monthtxtTitle;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_site_products, null);
            holder = new ViewHolder();
            holder.monthtxtTitle = (MyBoldTextView) convertView.findViewById(R.id.events_list_item1);
            holder.yeartxtname=(MyTextView) convertView.findViewById(R.id.events_list_item2);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position < arrayListSiteProgressList.size()) {
            holder.yeartxtname.setText(arrayListSiteProgressList.get(position).getYear());
            holder.monthtxtTitle.setText(arrayListSiteProgressList.get(position).getMonthinText());

           /* if(position == 0) {
                mFancyShowCaseView = new FancyShowCaseView.Builder(context)
                        .focusOn(convertView)
                        .title("Click to View")

                        .customView(R.layout.layout_site_progress, new OnViewInflateListener() {
                            @Override
                            public void onViewInflated(View view) {
                                view.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //fancyShowCaseView.hide();
                                        FancyShowCaseView.hideCurrent((Activity) context);
                                        Intent i = new Intent(context, ViewSiteProgress.class);
                                        i.putExtra("ProjectId",project_id);
                                        i.putExtra("Name", project_id);
                                        i.putExtra("Month", arrayListSiteProgressList.get(position).getMonth());
                                        i.putExtra("MonthInText",arrayListSiteProgressList.get(position).getMonthinText());
                                        i.putExtra("Year", arrayListSiteProgressList.get(position).getYear());
                                        context.startActivity(i);
                                    }
                                });
                            }
                        })
                        .closeOnTouch(false)
                        .build();
                mFancyShowCaseView.show();
            }*/

        }
        return convertView;
    }

    public void clearData() {
        // clear the data
        arrayListSiteProgressList.clear();
    }

}
