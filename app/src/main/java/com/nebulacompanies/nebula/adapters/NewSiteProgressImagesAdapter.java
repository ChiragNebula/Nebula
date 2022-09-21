package com.nebulacompanies.nebula.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nebulacompanies.nebula.Model.Guest.SiteProgressImageList;
import com.nebulacompanies.nebula.R;

import java.util.ArrayList;



public class NewSiteProgressImagesAdapter extends BaseAdapter {

    Activity context;
    ArrayList<SiteProgressImageList> arrayListSiteProgressImageList = new ArrayList<>();

    private int mItemHeight = 0;
    private RelativeLayout.LayoutParams mImageViewLayoutParams;

    boolean product_type_sub;
    String name,monthIntext,year;
    int productid,month;

    // FancyShowCaseView mFancyShowCaseView;
    public NewSiteProgressImagesAdapter(Activity context, ArrayList<SiteProgressImageList> siteProgressImageLists, boolean product_type_sub, String name, int productid, int month, String monthIntext, String year) {
        this.context = context;
        this.product_type_sub=product_type_sub;
        this.name=name;
        this.monthIntext=monthIntext;
        this.month=month;
        this.productid=productid;
        this.year=year;
        arrayListSiteProgressImageList.clear();
        arrayListSiteProgressImageList.addAll(siteProgressImageLists);

        mImageViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }


    public int getCount() {
        // TODO Auto-generated method stub
        return arrayListSiteProgressImageList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayListSiteProgressImageList.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private class ViewHolder {
        ImageView cover;
       // AVLoadingIndicatorView siteprogessAvLoadingIndicatorView;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.grid_item, null);
            holder.cover = (ImageView) view.findViewById(R.id.picture1);
        //    holder.siteprogessAvLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.aviLoadingPackageSiteProgres);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Check the height matches our calculated column width
        if (holder.cover.getLayoutParams().height != mItemHeight) {
            holder.cover.setLayoutParams(mImageViewLayoutParams);
        }

        if (i < arrayListSiteProgressImageList.size()) {
            //System.out.println("thumbnail : " + arrayListSiteProgressImageList.get(i).getThumbnail());
            //System.out.println("image : " + arrayListSiteProgressImageList.get(i).getSubImages());

          //  holder.siteprogessAvLoadingIndicatorView.setVisibility(View.VISIBLE);

            final ViewHolder finalHolder = holder;

            Glide.with(context)
                    .load(arrayListSiteProgressImageList.get(i).getThumbnail().replaceAll(" ", "%20"))
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(R.drawable.nebula_placeholder)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                       boolean isFromMemoryCache, boolean isFirstResource) {
                        //    finalHolder.siteprogessAvLoadingIndicatorView.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.cover);

            ViewHolder finalHolder1 = holder;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (i == 1 && product_type_sub) {
                        SharedPreferences skipMainGet = context.getSharedPreferences("skip_main", 0);
                        boolean isSkipMain = skipMainGet.getBoolean("isSkipMain", false);

                    }
                }
            }, 3000);




        }
        return view;
    }

    public void clearData() {
        // clear the data
        arrayListSiteProgressImageList.clear();
    }
}
