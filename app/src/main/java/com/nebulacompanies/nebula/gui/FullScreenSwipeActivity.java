package com.nebulacompanies.nebula.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nebulacompanies.nebula.Model.Guest.ProjectList;
import com.nebulacompanies.nebula.R;

import java.util.ArrayList;

/**
 * Created by Palak Mehta on 7/30/2016.
 */
public class FullScreenSwipeActivity extends Activity{

    ArrayList<ProjectList> projectLists;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_view);

        Intent i = getIntent();
        if(i != null) {
            id = i.getExtras().getInt("id");
            projectLists = (ArrayList<ProjectList>) i.getSerializableExtra("image_path");
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_swipe);
        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(id);
    }

    private class ImagePagerAdapter extends PagerAdapter
    {
        @Override
        public int getCount()
        {
            return projectLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            ImageView imageView = new ImageView(container.getContext());
            Log.d("FullScreenimage",""+projectLists.get(position).getImagePath());
            if(position < projectLists.size()) {
                Glide.with(container.getContext()).load(projectLists.get(position).getImagePath())
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.load)
                        .into(imageView);
                (container).addView(imageView, 0);
            }

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}