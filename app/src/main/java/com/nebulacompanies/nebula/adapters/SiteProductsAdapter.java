package com.nebulacompanies.nebula.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nebulacompanies.nebula.Model.Guest.SiteProductList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.MyBoldTextView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;

/*import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.OnViewInflateListener;*/

/**
 * Created by Sagar Virvani on 11-12-2017.
 */

public class SiteProductsAdapter extends BaseAdapter {

    Activity context;
    ArrayList<SiteProductList> arrayListSiteProductList = new ArrayList<>();
    //FancyShowCaseView mFancyShowCaseView;

    public SiteProductsAdapter(Activity context, ArrayList<SiteProductList> siteProductLists) {
        this.context = context;
        arrayListSiteProductList.clear();
        arrayListSiteProductList.addAll(siteProductLists);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return arrayListSiteProductList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayListSiteProductList.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private class ViewHolder {
        MyBoldTextView titletxtname;
        MyTextView detailstxtName;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        holder = new ViewHolder();

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_site_products, null);
            holder.titletxtname = (MyBoldTextView) convertView.findViewById(R.id.events_list_item1);
            holder.detailstxtName = (MyTextView) convertView.findViewById(R.id.events_list_item2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position < arrayListSiteProductList.size()) {

            if (arrayListSiteProductList.get(position).getProjectName().equals("Aavaas, Hyderabad"))
            {
                holder.titletxtname.setText(R.string.aavaas_miyapur_title);
            }
            else
            {
                holder.titletxtname.setText(arrayListSiteProductList.get(position).getProjectName());
            }
            holder.detailstxtName.setText(arrayListSiteProductList.get(position).getDetail());

            /*if(position == 0) {
                mFancyShowCaseView = new FancyShowCaseView.Builder(context)
                        .focusOn(holder.titletxtname)
                        .title("Click to View")
                        .customView(R.layout.layout_site_progress, new OnViewInflateListener() {
                            @Override
                            public void onViewInflated(View view) {
                                view.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //fancyShowCaseView.hide();
                                        FancyShowCaseView.hideCurrent((Activity) context);
                                        Intent i = new Intent(context, SiteProgress.class);
                                        i.putExtra("Product_Name", arrayListSiteProductList.get(position).getProjectName());
                                        i.putExtra("ProjectId", arrayListSiteProductList.get(position).getProjectId());
                                        context.startActivity(i);
                                    }
                                });
                            }
                        })
                        .showOnce("1")
                        .closeOnTouch(false)
                        .build();
                mFancyShowCaseView.show();

            }*/
        }
        return convertView;
    }

    public void clearData() {
        // clear the data
        arrayListSiteProductList.clear();
    }
}
