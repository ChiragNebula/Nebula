package com.nebulacompanies.nebula.gui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
import com.nebulacompanies.nebula.fragments.MapCorporateOfficeChennai;
import com.nebulacompanies.nebula.fragments.MapCorporateOfficeHyderabad;
import com.nebulacompanies.nebula.fragments.MapHeadOfficeAhmedabad;
import com.nebulacompanies.nebula.fragments.MapSiteOfficeAhmedabad;
import com.nebulacompanies.nebula.fragments.MapSiteOfficeChennai;
import com.nebulacompanies.nebula.fragments.MapSiteOfficeHyderabad;
import com.nebulacompanies.nebula.fragments.MapSiteOfficeSanand;
import com.nebulacompanies.nebula.view.EnhancedWrapContentViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Palak Mehta on 10/31/2017.
 */

public class ContactUs extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float zoomLevel = 17;
    SupportMapFragment mapFragment;
    EnhancedWrapContentViewPager wrappingViewPager, wrappingViewPager1, /*wrappingViewPager2,*/ wrappingViewPager3;
    ImageView leftHyderabadImageView, rightHyderabadImageView, rightAhmedabadImageView, leftAhmedabadImageView,rightChennaiImageView,leftchennaiImageView;
    ImageView ahmedabadArrow, hyderabadArrow, kolkataArrow, chennaiArrow;

    //Ahmedabad
    double latitude_headoffice_ahmedabad = 23.012725;
    double longitude_headoffice_ahmedabad = 72.513192;
    double latitude_siteoffice_ahmedabad = 22.919672;
    double longitude_siteoffice_ahmedabad = 72.429997;

    double latitude_siteoffice_sanand = 23.0013205;
    double longitude_siteoffice_sanand = 72.3781123;
    String title_headoffice_ahmedabad = "Nebula Companies, Head Office, Ahmedabad";
    String title_siteoffice_ahmedabad = "Aavaas (Changodar), Ahmedabad";
    String title_siteoffice_sanand = "Aavaas (Sanand), Ahmedabad";

    //Hyderabad
    double latitude_corporateoffice_hyderabad = 17.491652;
    double longitude_corporateoffice_hyderabad = 78.393555;
    double latitude_siteoffice_hyderabad = 17.522135;
    double longitude_siteoffice_hyderabad = 78.356305;
    String title_corporateoffice_hyderabad = "Nebula Companies, Corporate Office, Hyderabad";
    String title_siteoffice_hyderabad = "Aavaas, Hyderabad";

    //Kolkata
   // double latitude_corporateoffice_kolkata = 22.554480;
   // double longitude_corporateoffice_kolkata = 88.354421;
   // String title_corporateoffice_kolkata = "Nebula Companies, Corporate Office, Kolkata";


    //Chennai
    double latitude_corporateoffice_chennai = 12.742213;//12.742213, 79.990199
    double longitude_corporateoffice_chennai = 79.990199;
    String title_corporateoffice_chennai = "Nebula Companies, Corporate Office, Chennai";
    String title_site_office_chennai = "Nebula Companies, Chennai";
    LinearLayout titleChennai, descchennai;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        init();
    }

    private void init() {
//        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(ContactUs.this);

        wrappingViewPager = (EnhancedWrapContentViewPager) findViewById(R.id.viewpager_ahmedabad);
        setupViewPager(wrappingViewPager);

        wrappingViewPager1 = (EnhancedWrapContentViewPager) findViewById(R.id.viewpager_hyderabad);
        setupViewPager1(wrappingViewPager1);

        //wrappingViewPager2 = (WrappingViewPager) findViewById(R.id.viewpager_kolkata);
      //  setupViewPager2(wrappingViewPager2);

        //add chennai
        wrappingViewPager3 = (EnhancedWrapContentViewPager) findViewById(R.id.viewpager_chennai);
        setupViewPager3(wrappingViewPager3);


        final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.ahmedabad_layout_title);
        final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.ahmedabad_layout_desc);

        final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.hyderabad_layout_title);
        final LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.hyderabad_layout_desc);

      //  final LinearLayout linearLayout5 = (LinearLayout) findViewById(R.id.kolkata_layout_title);
      //  final LinearLayout linearLayout6 = (LinearLayout) findViewById(R.id.kolkata_layout_desc);


        //add chennai
        titleChennai = (LinearLayout) findViewById(R.id.chennai_layout_title);
        descchennai = (LinearLayout) findViewById(R.id.chennai_layout_desc);

        leftAhmedabadImageView = (ImageView) findViewById(R.id.left);
        rightAhmedabadImageView = (ImageView) findViewById(R.id.right);

        leftHyderabadImageView = (ImageView) findViewById(R.id.left1);
        rightHyderabadImageView = (ImageView) findViewById(R.id.right1);

        ahmedabadArrow = (ImageView) findViewById(R.id.ahmedabad_icon);
        hyderabadArrow = (ImageView) findViewById(R.id.hyderabad_icon);
        kolkataArrow = (ImageView) findViewById(R.id.kolkata_icon);
        chennaiArrow = (ImageView) findViewById(R.id.chennai_icon);
        leftchennaiImageView = (ImageView) findViewById(R.id.chennai_left_icon);
        rightChennaiImageView = (ImageView) findViewById(R.id.chennai_right_icon);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout2.getVisibility() == View.GONE) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.GONE);
                 //   linearLayout6.setVisibility(View.GONE);
                    descchennai.setVisibility(View.GONE);
                    wrappingViewPager.setCurrentItem(0);
                    rightAhmedabadImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_headoffice_ahmedabad, longitude_headoffice_ahmedabad, title_headoffice_ahmedabad);
                    ahmedabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
                    kolkataArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    hyderabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    chennaiArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                } else {
                    linearLayout2.setVisibility(View.GONE);
                    ahmedabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                }
            }
        });


        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout4.getVisibility() == View.GONE) {
                    linearLayout4.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                  //  linearLayout6.setVisibility(View.GONE);
                    descchennai.setVisibility(View.GONE);
                    wrappingViewPager1.setCurrentItem(0);
                    rightHyderabadImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_corporateoffice_hyderabad, longitude_corporateoffice_hyderabad, title_corporateoffice_hyderabad);
                    hyderabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
                    ahmedabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    kolkataArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    chennaiArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                } else {
                    linearLayout4.setVisibility(View.GONE);
                    hyderabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                }
            }
        });

      /*  linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout6.getVisibility() == View.GONE) {
                    linearLayout6.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    descchennai.setVisibility(View.GONE);
                    showMap(latitude_corporateoffice_kolkata, longitude_corporateoffice_kolkata, title_corporateoffice_kolkata);
                    kolkataArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
                    ahmedabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    hyderabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    chennaiArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                } else {
                    kolkataArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    linearLayout6.setVisibility(View.GONE);
                }
            }
        });*/

        titleChennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (descchennai.getVisibility() == View.GONE) {
                    descchennai.setVisibility(View.VISIBLE);
                    rightChennaiImageView.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                   // linearLayout6.setVisibility(View.GONE);
                    showMap(latitude_corporateoffice_chennai, longitude_corporateoffice_chennai, title_corporateoffice_chennai);
                    chennaiArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_white_24dp);
                    ahmedabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    hyderabadArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    kolkataArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                } else {
                    chennaiArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
                    descchennai.setVisibility(View.GONE);
                }
            }
        });

        wrappingViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    leftAhmedabadImageView.setVisibility(View.INVISIBLE);
                    rightAhmedabadImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_headoffice_ahmedabad, longitude_headoffice_ahmedabad, title_headoffice_ahmedabad);
                }
                if (position == 1) {
                    rightAhmedabadImageView.setVisibility(View.VISIBLE);
                    leftAhmedabadImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_siteoffice_ahmedabad, longitude_siteoffice_ahmedabad, title_siteoffice_ahmedabad);
                }

                if (position == 2) {
                    rightAhmedabadImageView.setVisibility(View.INVISIBLE);
                    leftAhmedabadImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_siteoffice_sanand, longitude_siteoffice_sanand, title_siteoffice_sanand);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rightAhmedabadImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrappingViewPager.setCurrentItem(wrappingViewPager.getCurrentItem() + 1);
            }
        });
        leftAhmedabadImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrappingViewPager.setCurrentItem(wrappingViewPager.getCurrentItem() - 1);
            }
        });

        wrappingViewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    leftHyderabadImageView.setVisibility(View.INVISIBLE);
                    rightHyderabadImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_corporateoffice_hyderabad, longitude_corporateoffice_hyderabad, title_corporateoffice_hyderabad);
                }
                if (position == 1) {
                    rightHyderabadImageView.setVisibility(View.INVISIBLE);
                    leftHyderabadImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_siteoffice_hyderabad, longitude_siteoffice_hyderabad, title_siteoffice_hyderabad);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        leftHyderabadImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrappingViewPager1.setCurrentItem(wrappingViewPager1.getCurrentItem() - 1);
            }
        });

        rightHyderabadImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrappingViewPager1.setCurrentItem(wrappingViewPager1.getCurrentItem() + 1);
            }
        });


       /* wrappingViewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    leftAhmedabadImageView.setVisibility(View.INVISIBLE);
                    rightAhmedabadImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_corporateoffice_kolkata, longitude_corporateoffice_kolkata, title_corporateoffice_kolkata);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
        wrappingViewPager3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    leftchennaiImageView.setVisibility(View.INVISIBLE);
                    rightChennaiImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_corporateoffice_chennai, longitude_corporateoffice_chennai, title_corporateoffice_chennai);
                }
                if (position==1) {
                    rightChennaiImageView.setVisibility(View.INVISIBLE);
                    leftchennaiImageView.setVisibility(View.VISIBLE);
                    showMap(latitude_corporateoffice_chennai, longitude_corporateoffice_chennai, title_site_office_chennai);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        leftchennaiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrappingViewPager3.setCurrentItem(wrappingViewPager3.getCurrentItem() - 1);
            }
        });

        rightChennaiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrappingViewPager3.setCurrentItem(wrappingViewPager3.getCurrentItem() + 1);
            }
        });



    }


    private void showMap(Double lattitude, Double longitude, String address) {
        LatLng latLng = new LatLng(lattitude, longitude);
        if (mMap != null) {
            mMap.addMarker(new MarkerOptions().position(latLng).title(address));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapHeadOfficeAhmedabad(), "MapHeadOfficeAhmedabad");
        adapter.addFragment(new MapSiteOfficeAhmedabad(), "MapSiteOfficeAhmedabad");
        adapter.addFragment(new MapSiteOfficeSanand(), "MapSiteOfficeSanand");
        viewPager.setAdapter(adapter);
    }

    private void setupViewPager1(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapCorporateOfficeHyderabad(), "MapCorporateOfficeHyderabad");
        adapter.addFragment(new MapSiteOfficeHyderabad(), "MapSiteOfficeHyderabad");
        viewPager.setAdapter(adapter);
    }


    private void setupViewPager3(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapCorporateOfficeChennai(), "MapCorporateOfficeChennai");
        adapter.addFragment(new MapSiteOfficeChennai(), "MapCorporateOfficeChennai");
        viewPager.setAdapter(adapter);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng nebula = new LatLng(latitude_headoffice_ahmedabad, longitude_headoffice_ahmedabad);
        mMap.addMarker(new MarkerOptions().position(nebula).title(title_headoffice_ahmedabad));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nebula, zoomLevel));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
