package com.nebulacompanies.nebula.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.util.WalkThroughListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Palak Mehta on 7/29/2016.
 */
public class Promoters extends Fragment {

    TabLayout promotersTabLayout;
    ViewPager promotersViewPager;
    Handler handler;
    WalkThroughListener walkThroughListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotors, container, false);

        promotersViewPager = (ViewPager) view.findViewById(R.id.viewpager_promoters);
        setupViewPager(promotersViewPager);

        promotersTabLayout = (TabLayout) view.findViewById(R.id.tab_promoters);
        promotersTabLayout.setupWithViewPager(promotersViewPager);
        promotersTabLayout.getTabAt(0).select();

        promotersViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
              //      walkThroughListener.onMethodCallback("CompanyProfile1");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
            return;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            if (promotersViewPager == null){
                setupViewPager(promotersViewPager);
            }

            handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    //displayTuto();
                }
            };
            handler.postDelayed(r, 500);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new  PromoterPacifica(), "Pacifica");
        adapter.addFragment(new PromoterFutura(), "Futura");
        adapter.addFragment(new PromoterKesar(), "Kesar");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {
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

   /* protected void displayTuto() {
        TutoShowcase.from(getActivity())
                .setListener(new TutoShowcase.Listener() {
                    @Override
                    public void onDismissed() {
//Toast.makeText(getActivity(), "Tutorial dismissed", Toast.LENGTH_SHORT).show();
                        promotersTabLayout.getTabAt(1).select();
                    }
                })
                .setContentView(R.layout.siwp_imageview)
                .setFitsSystemWindows(true)
                .on(promotersViewPager)
                .displaySwipableLeft()
                .delayed(399)
                .animated(true)
                .show();
    }*/

}
