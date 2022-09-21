package com.nebulacompanies.nebula.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.util.WalkThroughListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Palak Mehta on 7/29/2016.
 */
public class AboutUs extends Fragment {

    ViewPager viewPagers;
    ImageView right, left;

    Handler handler;
    WalkThroughListener walkThroughListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        viewPagers = (ViewPager) view.findViewById(R.id.viewpager_about_us);
        setupViewPagers(viewPagers);

        right = (ImageView) view.findViewById(R.id.right);
        left = (ImageView) view.findViewById(R.id.left);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagers.setCurrentItem(viewPagers.getCurrentItem() + 1);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagers.setCurrentItem(viewPagers.getCurrentItem() - 1);
            }
        });

        viewPagers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    left.setVisibility(View.INVISIBLE);
                    right.setVisibility(View.VISIBLE);
                }
                if(position == 1){  // if you want the second page, for example
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.VISIBLE);
                }
                else if(position == 2){
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.INVISIBLE);

                    walkThroughListener.onMethodCallback("CompanyProfile");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
               // displayTuto();
            }
        };
        handler.postDelayed(r, 500);

      /*  paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(getResources().getDimension(R.dimen.abc_text_size_body_1_material));
        //paint.setStrikeThruText(true);
        paint.setColor(Color.RED);
        //paint.setTypeface(Typeface.createFromAsset(getAssets(), "RobotoSlab-Regular.ttf"));

        title = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        title.setTextSize(getResources().getDimension(R.dimen.abc_text_size_headline_material));
        title.setUnderlineText(true);
        title.setColor(Color.YELLOW);
        //title.setTypeface(Typeface.createFromAsset(getAssets(), "RobotoSlab-Regular.ttf"));

        showcaseView = new ShowcaseView.Builder(getActivity())
                //.withNewStyleShowcase()
                //.withMaterialShowcase()
                .withHoloShowcase()
                .setTarget(new ViewTarget(R.id.right, getActivity()))
                //.setContentTextPaint(paint)
                //.setContentTitle("IBO Login")
                .setContentText("Click to slide")
                //.setContentTitlePaint(title)
                .setStyle(R.style.CustomShowcaseTheme2)
                //.setOnClickListener(this)
                .hideOnTouchOutside()
                .build();

        //setAlpha(0.8f, showcaseView);
        showcaseView.setDetailTextAlignment(Layout.Alignment.ALIGN_CENTER);
        showcaseView.setTitleTextAlignment(Layout.Alignment.ALIGN_CENTER);
        //showcaseView.forceTextPosition(ShowcaseView.BELOW_SHOWCASE);
        showcaseView.forceTextPosition(ShowcaseView.BELOW_SHOWCASE);
        //showcaseView.setButtonText(getString(R.string.next));

        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lps.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
        lps.setMargins(margin, margin, margin, margin);


        showcaseView.setButtonPosition(lps);*/

        /*mFancyShowCaseView = new FancyShowCaseView.Builder(getActivity())
                .focusOn(right)
                .customView(R.layout.layout_my_custom_view, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.btn_action_1).setOnClickListener(mClickListener);
                    }
                })
                .closeOnTouch(true)
                .build();
        mFancyShowCaseView.show();*/

    }

  /*  View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mFancyShowCaseView.hide();
        }
    };*/

    private void setupViewPagers(final ViewPager viewPagers) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AboutUs(), "Vision");
        adapter.addFragment(new AboutUs(), "Mission");
        adapter.addFragment(new AboutUs(), "Values");
        viewPagers.setAdapter(adapter);
        viewPagers.setCurrentItem(0,true);
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
                        //walkThroughListener.onMethodCallback(true);
                        right.performClick();
                    }
                })
                .setContentView(R.layout.siwp_imageview)
                .setFitsSystemWindows(true)
                .on(viewPagers)
                .displaySwipableLeft()
                .delayed(399)
                .animated(true)
                .show();
    }*/
}
