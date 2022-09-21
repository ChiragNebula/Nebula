package com.nebulacompanies.nebula.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.WrappingViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar-pc on 5/26/2017.
 */

public class MapAhmedabad extends Fragment implements View.OnClickListener,OnMapReadyCallback {

    WrappingViewPager AhemadabadViewPager;
    View view;
    private GoogleMap mMap;
    ImageView rightImageView,leftImageView;
    SupportMapFragment mapFragment;
    float zoomLevel = 17;
    double lattitude_headoffice = 23.012725, longitude_headoffice = 72.513192, lattitude_siteoffice = 22.919672, longitude_siteoffice = 72.429997;
    String headOfficeTitle = "Nebula Companies, Head Office, Ahmedabad";
    String siteOfficeTitle = "Aavaas (Changodar), Ahmedabad";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map_ahmedabad, container, false);

        mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map_Ahemadabad);
        mapFragment.getMapAsync(this);

        // Inflate the layout for this fragment
        leftImageView=(ImageView)view.findViewById(R.id.left_Ahemadabad);
        rightImageView=(ImageView)view.findViewById(R.id.right_Ahemadabad);

        leftImageView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);

        AhemadabadViewPager=(WrappingViewPager) view.findViewById(R.id.view_map_Ahemadabad);
        setupViewPager(AhemadabadViewPager);

        AhemadabadViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    if (position==0){
                        leftImageView.setVisibility(View.INVISIBLE);
                        rightImageView.setVisibility(View.VISIBLE);
                        showMap(headOfficeTitle, lattitude_headoffice, longitude_headoffice);
                    }
                    if (position==1){
                        leftImageView.setVisibility(View.VISIBLE);
                        rightImageView.setVisibility(View.INVISIBLE);
                        showMap(siteOfficeTitle, lattitude_siteoffice, longitude_siteoffice);
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MapHeadOfficeAhmedabad(), "MapHeadOfficeAhmedabad");
        adapter.addFragment(new MapSiteOfficeAhmedabad(),"MapSiteOfficeAhmedabad");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_Ahemadabad:
                AhemadabadViewPager.setCurrentItem(AhemadabadViewPager.getCurrentItem() - 1);
                leftImageView.setVisibility(View.INVISIBLE);
                rightImageView.setVisibility(View.VISIBLE);
                showMap(headOfficeTitle, lattitude_headoffice, longitude_headoffice);
                break;

            case R.id.right_Ahemadabad:
                AhemadabadViewPager.setCurrentItem(AhemadabadViewPager.getCurrentItem() + 1);
                rightImageView.setVisibility(View.INVISIBLE);
                leftImageView.setVisibility(View.VISIBLE);
                showMap(siteOfficeTitle, lattitude_siteoffice, longitude_siteoffice);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        showMap(headOfficeTitle, lattitude_headoffice, longitude_headoffice);
    }

    private void showMap(String title, double lattitude, double longitude){
        LatLng nebula = new LatLng(lattitude, longitude);
        mMap.addMarker(new MarkerOptions().position(nebula).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nebula, zoomLevel));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
    
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
    
}
