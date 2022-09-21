package com.nebulacompanies.nebula.gui;

import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_ERROR;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_NO_RECORDS;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SUCCESS;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nebulacompanies.nebula.Base2Activity;
import com.nebulacompanies.nebula.Model.Guest.CompanyProjectDetailsModel;
import com.nebulacompanies.nebula.Model.Guest.CompanyProjectListModel;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.view.MyTextView;
import com.nebulacompanies.nebula.view.SquareImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 6/14/2016.
 */
public class ProductDescriptionAavaas extends Base2Activity {

    MyTextView aavaasTextView, highlightsTextView, highlights1TextView, highlights2TextView, highlights3TextView, highlights4TextView, highlights5TextView, highlights6TextView, knowMoreButton;
    String IsLogin;
    MyTextView descTextView;
    String PRODUCT_NAME;
    int project_id;
    private APIInterface mAPIInterface;
    Boolean isRefreshed = false;
    //Error View
    private LinearLayout llEmpty, mainLayout;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String projectDesc, projectTitle;
    public static ArrayList<CompanyProjectListModel> arrayListCompanyProjectList = new ArrayList<>();
    ImageView imgOne, imgTwo, imgThree, imgFour, imgFive, imgSix;
    /* TextView tv;*/
    boolean productType, OnBack;
    ActionBar.LayoutParams lp;
    Toolbar toolbar;
    MyTextView tv_product_description;
    RecyclerView PrdouctRecyclerView;
    ProductDescriptionAdapter productDescriptionAdapter;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_description_aavaas);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            PRODUCT_NAME = b.getString("Product_Name");
            project_id = b.getInt("ProjectId");
            productType = b.getBoolean("Product_type");
            OnBack = b.getBoolean("OnBack");
        }

        setActionBar();
        init();
        getSiteProductList();
    }

    void setActionBar() {

        toolbar = (Toolbar) findViewById(R.id.product_description_toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setTitle("");

    }

    void init() {
        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        initError();
        mainLayout = (LinearLayout) findViewById(R.id.project_info_layout);
        aavaasTextView = (MyTextView) findViewById(R.id.aavaas_main);
        descTextView = (MyTextView) findViewById(R.id.aavaas_details);
        highlightsTextView = (MyTextView) findViewById(R.id.aavaas_highlights);
        highlights1TextView = (MyTextView) findViewById(R.id.abad_highlights1);
        highlights2TextView = (MyTextView) findViewById(R.id.abad_highlights2);
        highlights3TextView = (MyTextView) findViewById(R.id.abad_highlights3);
        highlights4TextView = (MyTextView) findViewById(R.id.abad_highlights4);
        highlights5TextView = (MyTextView) findViewById(R.id.abad_highlights5);
        highlights6TextView = (MyTextView) findViewById(R.id.abad_highlights6);
        imgOne = (ImageView) findViewById(R.id.img_one);
        imgTwo = (ImageView) findViewById(R.id.img_two);
        imgThree = (ImageView) findViewById(R.id.img_three);
        imgFour = (ImageView) findViewById(R.id.img_four);
        imgFive = (ImageView) findViewById(R.id.img_five);
        imgSix = (ImageView) findViewById(R.id.img_six);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.site_progress_swipe_refresh_layout);
        PrdouctRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_product_description);


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        mainLayout.setVisibility(View.GONE);




    }

    private void getSiteProductList() {
        if (isInternetPresent) {
            final ProgressDialog mProgressDialog = new ProgressDialog(this, R.style.MyTheme);

            if (!isRefreshed) {
                mProgressDialog.show();
            }
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progressdialog);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Call<CompanyProjectDetailsModel> wsCallingProject = mAPIInterface.getProject(project_id);
            wsCallingProject.enqueue(new Callback<CompanyProjectDetailsModel>() {
                @Override
                public void onResponse(Call<CompanyProjectDetailsModel> call, Response<CompanyProjectDetailsModel> response) {

                    if (mProgressDialog != null && mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    arrayListCompanyProjectList.clear();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            if (response.body().getStatusCode() == REQUEST_STATUS_CODE_NO_RECORDS) {
                                //tv.setText(PRODUCT_NAME);
                                noRecordsFound();
                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_SUCCESS) {
                                arrayListCompanyProjectList.addAll(response.body().getData());
                                for (int i = 0; i < arrayListCompanyProjectList.size(); i++) {
                                    getSupportActionBar().setTitle(arrayListCompanyProjectList.get(0).getProject());
                                    descTextView.setText(arrayListCompanyProjectList.get(0).getFacility());
                                    aavaasTextView.setText(arrayListCompanyProjectList.get(0).getProject());
                                }

                                // data bind
                                productDescriptionAdapter = new ProductDescriptionAdapter();
                                gridLayoutManager = new GridLayoutManager(ProductDescriptionAavaas.this, 3, LinearLayoutManager.VERTICAL, false);
                                PrdouctRecyclerView.setLayoutManager(gridLayoutManager);
                                PrdouctRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                PrdouctRecyclerView.setAdapter(productDescriptionAdapter);
                                mainLayout.setVisibility(View.VISIBLE);


                                /* List<CompanyProjectListModel> results = response.body().getData();
                                productDescriptionAdater.addAll(results);

                                for (int i = 0; i < results.size(); i++) {
                                    getSupportActionBar().setTitle(results.get(0).getProject());
                                    descTextView.setText(results.get(0).getFacility());
                                    aavaasTextView.setText(results.get(0).getProject());
                                    if (!response.body().getData().contains(results.get(i).getImageProjectFacility())) {
                                        imagepic.add(results.get(i).getImageProjectFacility());
                                        txtDec.add(results.get(i).getProjectFacilityDescription());
                                    }
                                }

                               for (int i = 0; i < arrayListCompanyProjectList.size(); i++) {
                                    //tv.setText(arrayListCompanyProjectList.get(0).getProject());
                                    getSupportActionBar().setTitle(arrayListCompanyProjectList.get(0).getProject());
                                    descTextView.setText(arrayListCompanyProjectList.get(0).getFacility());
                                    aavaasTextView.setText(arrayListCompanyProjectList.get(0).getProject());
                                    highlights1TextView.setText(arrayListCompanyProjectList.get(0).getProjectFacilityDescription());
                                    highlight s2TextView.setText(arrayListCompanyProjectList.get(1).getProjectFacilityDescription());
                                    highlights3TextView.setText(arrayListCompanyProjectList.get(2).getProjectFacilityDescription());
                                    highlights4TextView.setText(arrayListCompanyProjectList.get(3).getProjectFacilityDescription());
                                    highlights5TextView.setText(arrayListCompanyProjectList.get(4).getProjectFacilityDescription());
                                    highlights6TextView.setText(arrayListCompanyProjectList.get(5).getProjectFacilityDescription());

                                    RequestManager requestManager = Glide.with(ProductDescriptionAavaas.this);
                                    requestManager.
                                            load(arrayListCompanyProjectList.get(0).getImageProjectFacility().replaceAll(" ", "%20"))
                                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                            //.placeholder(R.drawable.nebula_placeholder_land)
                                            .into(imgOne);
                                    RequestManager requestManagerFirst = Glide.with(ProductDescriptionAavaas.this);
                                    requestManagerFirst.
                                            load(arrayListCompanyProjectList.get(1).getImageProjectFacility().replaceAll(" ", "%20"))
                                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                            //.placeholder(R.drawable.nebula_placeholder_land)
                                            .into(imgTwo);

                                    RequestManager requestManagerSecond = Glide.with(ProductDescriptionAavaas.this);
                                    requestManagerSecond.
                                            load(arrayListCompanyProjectList.get(2).getImageProjectFacility().replaceAll(" ", "%20"))
                                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                            //.placeholder(R.drawable.nebula_placeholder_land)
                                            .into(imgThree);

                                    RequestManager requestManagerThree = Glide.with(ProductDescriptionAavaas.this);
                                    requestManagerThree.load(arrayListCompanyProjectList.get(3).getImageProjectFacility().replaceAll(" ", "%20"))
                                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                            //.placeholder(R.drawable.nebula_placeholder_land)
                                            .into(imgFour);


                                    RequestManager requestManagerFour = Glide.with(ProductDescriptionAavaas.this);
                                    requestManagerFour. load(arrayListCompanyProjectList.get(4).getImageProjectFacility().replaceAll(" ", "%20"))
                                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                            //.placeholder(R.drawable.nebula_placeholder_land)
                                            .into(imgFive);



                                    RequestManager requestManagerFive = Glide.with(ProductDescriptionAavaas.this);
                                    requestManagerFive. load(arrayListCompanyProjectList.get(5).getImageProjectFacility().replaceAll(" ", "%20"))
                                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                            //.placeholder(R.drawable.nebula_placeholder_land)
                                            .into(imgSix);

                                    mainLayout.setVisibility(View.VISIBLE);
                                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            SharedPreferences skipMainGet = getSharedPreferences("skip_main", 0);
                                            boolean isSkipMain = skipMainGet.getBoolean("isSkipMain", false);
                                            if (!isSkipMain){
                                                if (productType) {
                                                    SpotlightViewNew spotLight = new SpotlightViewNew.Builder(ProductDescriptionAavaas.this)
                                                            .introAnimationDuration(400)
                                                            .enableRevealAnimation(true)
                                                            .performClick(true)
                                                            .fadeinTextDuration(400)
                                                            .setTypeface(FontUtil.get(ProductDescriptionAavaas.this, "fonts/Montserrat-Regular.ttf"))
                                                            .headingTvColor(Color.parseColor("#eb273f"))
                                                            .headingTvSize(18)
                                                            .headingTvText("More Details")
                                                            .subHeadingTvColor(Color.parseColor("#ffffff"))
                                                            .subHeadingTvSize(14)
                                                            .subHeadingTvText("More about this section")
                                                            .maskColor(Color.parseColor("#dc000000"))
                                                            .target(btnMoreDetails)
                                                            .lineAnimDuration(400)
                                                            .targetPadding(1)
                                                            .lineAndArcColor(Color.parseColor("#eb273f"))
                                                            .dismissOnTouch(false)
                                                            .dismissOnBackPress(false)
                                                            .enableDismissAfterShown(false)
                                                            .show();
                                                }
                                            }

                                        }
                                    }, 4000);
                                }*/

                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_ERROR
                                    || response.body().getStatusCode() == REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE) {
                                serviceUnavailable();
                            }
                        } else {
                            serviceUnavailable();
                        }
                    }

                }

                @Override
                public void onFailure(Call<CompanyProjectDetailsModel> call, Throwable t) {
                    mProgressDialog.dismiss();
                    mSwipeRefreshLayout.setEnabled(false);
                    serviceUnavailable();
                }
            });
        } else {
            noInternetConnection();
        }


    }

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            getSiteProductList();
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void initError() {
        llEmpty = (LinearLayout) findViewById(R.id.llEmpty);
        imgError = (ImageView) findViewById(R.id.imgError);
        txtErrorTitle = (MyTextView) findViewById(R.id.txtErrorTitle);
        txtRetry = (MyTextView) findViewById(R.id.txtRetry);
        txtRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshContent();
            }
        });
    }

    void noRecordsFound() {
        txtErrorTitle.setText(R.string.error_no_records);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.GONE);
        mainLayout.setVisibility(View.GONE);
    }

    void serviceUnavailable() {
        txtErrorTitle.setText(R.string.error_service_unavailable);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);
    }

    void noInternetConnection() {
        txtErrorTitle.setText(R.string.error_msg_network);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (OnBack) {
            SharedPreferences sp = getSharedPreferences("FullScreenBackProduct", MODE_PRIVATE);
            SharedPreferences.Editor et = sp.edit();
            et.putBoolean("isFullScreenBackEventProduct", true);
            et.apply();

            Intent i = new Intent(ProductDescriptionAavaas.this, Products.class);
            i.putExtra("onBackMain", true);
            i.putExtra("keyPro", "Products");
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else {
            SharedPreferences sp = getSharedPreferences("FullScreenBackProduct", MODE_PRIVATE);
            SharedPreferences.Editor et = sp.edit();
            et.putBoolean("isFullScreenBackEventProduct", true);
            et.apply();
            super.onBackPressed();
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.product_description_aavaas);
        getSiteProductList();
    }



    //Child View
    public class ProductDescriptionAdapter extends RecyclerView.Adapter<ProductDescriptionAdapter.ItemViewHolder> {

        Context mContext;
        private boolean isLoadingAdded = false;


        public class ItemViewHolder extends RecyclerView.ViewHolder{
            SquareImageView imgDes;
            MyTextView txtDes;
            public ItemViewHolder(View view) {
                super(view);
                imgDes = (SquareImageView) view.findViewById(R.id.dec_item_icon);
                txtDes = (MyTextView) view.findViewById(R.id.dec_item_txt);
            }

        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_description_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            CompanyProjectListModel companyProjectLists = arrayListCompanyProjectList.get(position);
            holder.txtDes.setText(companyProjectLists.getProjectFacilityDescription());
            RequestManager requestManager = Glide.with(ProductDescriptionAavaas.this);
            requestManager.load(companyProjectLists.getImageProjectFacility().replaceAll(" ", "%20"))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.imgDes);

        }

        @Override
        public int getItemCount() {
            return arrayListCompanyProjectList.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

}
