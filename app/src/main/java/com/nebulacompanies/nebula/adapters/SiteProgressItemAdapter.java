package com.nebulacompanies.nebula.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nebulacompanies.nebula.Model.Guest.SiteProgressList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.gui.ShowFullScreenSiteProgress;


import java.util.ArrayList;
import java.util.List;

public class SiteProgressItemAdapter extends RecyclerView.Adapter<SiteProgressItemAdapter.ItemViewHolder> {

    private List<SiteProgressList> Items = new ArrayList<>();
    OnItemClickListener mItemClickListener;
    Context mContext;
    final String PREFS_WALKTHROUGH_SITE_SUP = "eventsuptwalkthrough";
    ArrayList<String> imagepic = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Integer> count = new ArrayList<Integer>();
    private boolean isLoadingAdded = false;
    boolean product_type_sub, OnBack, OnBackSiteProgress, OnBackSiteProduct;
    String name, monthIntext, year,PRODUCT_NAME;
    int productid, month;

    SharedPreferences walkthrough;
    public SiteProgressItemAdapter(Context context, String name, ArrayList<String> imagepic, ArrayList<String> date, ArrayList<Integer> count, String monthIntext, String year, int productid, int month, boolean product_type_sub, boolean OnBack, String PRODUCT_NAME) {
        Items = new ArrayList<>();
        mContext = context;
        this.imagepic=imagepic;
        this.date=date;
        this.count=count;
        this.name=name;
        this.monthIntext=monthIntext;
        this.year=year;
        this.month=month;
        this.product_type_sub=product_type_sub;
        this.OnBack=OnBack;
        this.PRODUCT_NAME=PRODUCT_NAME;
        this.productid=productid;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgUserAvatar;

        public ItemViewHolder(View view) {
            super(view);
            imgUserAvatar = (ImageView) view.findViewById(R.id.picture1);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_events, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SiteProgressList model = Items.get(position);

        if (position==Items.size()-1)
        {
            holder.imgUserAvatar.setVisibility(View.GONE);
        }
        else
        {
            holder.imgUserAvatar.setVisibility(View.VISIBLE);
        }


        Glide.with(mContext).load(model.getThumbnailImage())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.nebula_placeholder)
                .into(holder.imgUserAvatar);


        //Picasso.with(mContext).load("https://nebulacompanies.net/Content/SiteProgress/AlterImage/Thumbnail/11464c3e1e6f4bcea2299a5bb2753295_IMG_20201105_150328-1.jpg).into(holder.imgUserAvatar");
        //Picasso.with(mContext).load(model.getThumbnailImage()).placeholder(R.drawable.nebula_placeholder).into(holder.imgUserAvatar);
        RecyclerView.ViewHolder finalHolder1 = holder;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                walkthrough = mContext.getSharedPreferences(PREFS_WALKTHROUGH_SITE_SUP, 0);
                if (position==1 && walkthrough.getBoolean("walkthrough_first_time_site_sup", true)) {
                    SharedPreferences skipMainGet = mContext.getSharedPreferences("skip_main", 0);
                    boolean isSkipMain = skipMainGet.getBoolean("isSkipMain", false);
                    SharedPreferences skipModuleGet = mContext.getSharedPreferences("guideskipSiteProgress", 0);
                    boolean isSkipModule = skipModuleGet.getBoolean("guideskipSiteProgress", false);

                    SharedPreferences guideViewSkipSiteProgressBack = mContext.getSharedPreferences("MainSiteProgressViewSpot", 0);
                    boolean isSkipModuleSiteProgressBack = guideViewSkipSiteProgressBack.getBoolean("MainSiteProgressViewSpotLight", false);


                }
            }
        }, 3500);



        holder.imgUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShowFullScreenSiteProgress.class);
                intent.putExtra("id", position);
                //intent.putExtra("image_list", arrayListSiteProgressImages);
                intent.putExtra("Product_Name", PRODUCT_NAME);
                intent.putExtra("Name", name);
                intent.putExtra("Month", month);
                intent.putExtra("MonthInText", monthIntext);
                intent.putExtra("Year",year);
                intent.putExtra("imagepic",imagepic);
                intent.putExtra("date",date);
                intent.putExtra("ProjectId",productid);

                intent.putExtra("OnBack", false);
                intent.putExtra("first_time_site_sup", false);
                SharedPreferences guideViewSkipEventMain = mContext.getSharedPreferences("MainSiteProgressViewSpot", MODE_PRIVATE);
                SharedPreferences.Editor et = guideViewSkipEventMain.edit();
                et.putBoolean("MainSiteProgressViewSpotLight", true);
                et.apply();
                mContext.startActivity(intent);
              //  Log.d("ProjectIdGetAdapter","ProjectIdGetAdapter "+ productid +" " + PRODUCT_NAME);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClicklListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setItems(List<SiteProgressList> items) {
        Items = items;
        notifyDataSetChanged();
    }

    public void clearData() {
        // clear the data
        Items.clear();
    }

    public void add(SiteProgressList r) {
        Items.add(r);
        notifyItemInserted(Items.size() - 1);
        // notifyItemInserted(Items.size());
    }

    public void addAll(List<SiteProgressList> moveResults) {
        for (SiteProgressList result : moveResults) {
            if (!moveResults.contains(result.getThumbnailImage()))
            {
                add(result);
            }
            // add(result);
        }
    }

    public void remove(SiteProgressList r) {
        int position = Items.indexOf(r);
        if (position > -1) {
            Items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        Items.clear();
        /*while (getItemCount() > 0) {
            remove(getItem(0));
        }*/
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new SiteProgressList());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = Items.size() - 1;
        SiteProgressList result = getItem(position);

        if (result != null) {
            Items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public SiteProgressList getItem(int position) {
        return Items.get(position);
    }
}
