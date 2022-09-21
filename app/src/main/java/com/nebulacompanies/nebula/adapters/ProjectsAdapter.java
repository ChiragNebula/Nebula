package com.nebulacompanies.nebula.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nebulacompanies.nebula.Model.Guest.ProjectList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;


/**
 * Created by Palak Mehta on 12/30/2016.
 */

public class ProjectsAdapter extends BaseAdapter {

    Context context;
    ArrayList<ProjectList> arrayListProjectList = new ArrayList<>();
  //  FancyShowCaseView fancyShowCaseView;
    Boolean isFirstTime = true;

    public ProjectsAdapter(Context context, ArrayList<ProjectList> projectLists) {
        this.context = context;
        arrayListProjectList.clear();
        arrayListProjectList.addAll(projectLists);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return arrayListProjectList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayListProjectList.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private class ViewHolder {
        ImageView imageView;
        MyTextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        holder = new ViewHolder();

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.projects_grid_item, null);
            holder.txtTitle = (MyTextView) convertView.findViewById(R.id.text);
            holder.imageView = (ImageView) convertView.findViewById(R.id.picture);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position < arrayListProjectList.size()) {
            holder.txtTitle.setText(arrayListProjectList.get(position).getProjectName());

            Glide.with(context).load(arrayListProjectList.get(position).getProjectThumbnail())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.nebula_placeholder)
                    .into(holder.imageView);

            if(position == 0){

                /*int[] location = new int[2];
                convertView.getLocationOnScreen(location);

                Toast.makeText(context, "location x = " + location[0] + " y = "+ location[1] , Toast.LENGTH_LONG).show();*/

            /*    if(isFirstTime) {

                    fancyShowCaseView = new FancyShowCaseView.Builder((Activity) context)
                            .focusOn(convertView)
                            //.enableTouchOnFocusedView(true)
                            .customView(R.layout.layout_projects_pointer, new OnViewInflateListener() {
                                @Override
                                public void onViewInflated(View view) {
                                    //view.findViewById(R.id.image1).setOnClickListener(mClickListener);

                                    view.findViewById(R.id.projects_layout).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            FancyShowCaseView.hideCurrent((Activity) context);

                                            Intent i = new Intent(context, FullScreenSwipeActivity.class);
                                            i.putExtra("id", position);
                                            i.putExtra("image_path", arrayListProjectList);
                                            context.startActivity(i);
                                        }
                                    });
                                }
                            })
                            //.showOnce("1")
                            .dismissListener(new DismissListener() {
                                @Override
                                public void onDismiss(String id) {
                               *//* Intent i = new Intent(context, FullScreenSwipeActivity.class);
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

                isFirstTime = false;
                }*/
            }

        }
        return convertView;
    }

    public void clearData() {
        // clear the data
        arrayListProjectList.clear();
    }

    /*void enterReveal() {
        // previously invisible view
        final View myView = findViewById(R.id.my_view);

        // get the center for the clipping circle
        int cx = myView.getMeasuredWidth() / 2;
        int cy = myView.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }*/

}
