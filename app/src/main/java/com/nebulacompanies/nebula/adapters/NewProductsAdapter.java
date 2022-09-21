package com.nebulacompanies.nebula.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nebulacompanies.nebula.Model.Guest.SiteProductList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.gui.ProductDescriptionAavaas;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;


public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.SiteProductsHolder> {
    private Context context;
    ArrayList<SiteProductList> arrayListSiteProductList = new ArrayList<>();

   // public static final Integer[] images = {R.drawable.aavaas_sanand,R.drawable.aavaas_logo_small, R.drawable.hawthorn_logo, R.drawable.aavaas_logo_small, R.drawable.aavaas_chennai};


    public NewProductsAdapter(Activity context, ArrayList<SiteProductList> siteProductLists) {
        this.context = context;
        arrayListSiteProductList.clear();
        arrayListSiteProductList.addAll(siteProductLists);
    }

    public class SiteProductsHolder extends RecyclerView.ViewHolder {

        MyTextView titletxtname;
        ImageView imgProduct;

        public SiteProductsHolder(View convertView) {
            super(convertView);
            context = convertView.getContext();
            titletxtname = (MyTextView) convertView.findViewById(R.id.list_item_name);
            imgProduct = (ImageView) convertView.findViewById(R.id.list_item_image);

        }
    }

    @Override
    public SiteProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_image_name, parent, false);

        return new SiteProductsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SiteProductsHolder holder, @SuppressLint("RecyclerView") final int position) {

        final SiteProductList siteProductList = arrayListSiteProductList.get(position);

        holder.titletxtname.setText(siteProductList.getProjectName());

        Glide.with(context)
                .load(siteProductList.getProjectThumbnail())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                //.placeholder(R.drawable.nebula_placeholder)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {

                        return false;
                    }
                })
                .into(holder.imgProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(context, ProductDescriptionAavaas.class);
                    i.putExtra("ProjectId", arrayListSiteProductList.get(position).getProjectId());
                    i.putExtra("ProjectName", arrayListSiteProductList.get(position).getProjectName());
                    i.putExtra("Product_type", false);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListSiteProductList.size();
    }

    public void clearData() {
        // clear the data
        arrayListSiteProductList.clear();
    }
}