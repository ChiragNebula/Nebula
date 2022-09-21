package com.nebulacompanies.nebula.gui;

import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;
import static com.nebulacompanies.nebula.util.SetDateFormat.SetGmtTime;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.Base2Activity;
import com.nebulacompanies.nebula.Model.Guest.NewSiteProgressImages;
import com.nebulacompanies.nebula.Model.Guest.SiteProgressList;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.NewSiteProgressImagesAdapter;
import com.nebulacompanies.nebula.adapters.SiteProgressItemAdapter;
import com.nebulacompanies.nebula.util.PaginationScrollListener;
import com.nebulacompanies.nebula.view.MyBoldTextView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 9/15/2016.
 */
public class ViewSiteProgress extends Base2Activity {

    SwipeRefreshLayout mSwipeRefreshLayout;
    String name, monthIntext, year;
    int productid, month;
    private APIInterface mAPIInterface;
    //public static ArrayList<SiteProgressImageList> arrayListSiteProgressImages = new ArrayList<>();
    public static final String TAG = "ViewSiteProgress";
    NewSiteProgressImagesAdapter siteProgressImagesAdapter;
    Boolean isRefreshed = false;
    Boolean isNotificationClicked = false;

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry, tv;
    private MyBoldTextView tvTitle;
    Toolbar toolbar;
    boolean product_type_sub, OnBack, OnBackSiteProgress, OnBackSiteProduct;


    String PRODUCT_NAME;
    int ProjectId;

    private static final int PAGE_START = 1;
    SharedPreferences prefsFirstTime;
    RecyclerView rv;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static final int TOTAL_PAGES = 100;
    private int currentPage = PAGE_START;
    GridLayoutManager gridLayoutManager;

    ArrayList<String> imagepic = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Integer> count = new ArrayList<Integer>();

    SiteProgressItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_projects_new);
        prefsFirstTime = getSharedPreferences("PREFERENCESCROLLVIEWSITEPROGESSSPOTLIGHT", MODE_PRIVATE);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            name = b.getString("Name");
            monthIntext = b.getString("MonthInText");
            year = b.getString("Year");
            productid = b.getInt("ProjectId");
            month = b.getInt("Month");
            product_type_sub = b.getBoolean("product_type_sub");
            OnBack = b.getBoolean("OnBack");
            PRODUCT_NAME = b.getString("Product_Name");
            ProjectId = b.getInt("ProjectId");
            Log.d("ProjectIdGet","ProjectIdGet "+ ProjectId +" " + PRODUCT_NAME);

        }
        setActionBar();
        init();
        loadFirstPage();
    }

    void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_projects);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setTitle(monthIntext + " " + year);
    }

    void init() {

        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        initError();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.projects_view_swipe_refresh_layout);
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        //rv.setOnItemClickListener(this);

        tv = (MyTextView) findViewById(R.id.tv);
        tvTitle = (MyBoldTextView) findViewById(R.id.tv_title);


        tvTitle.setText("Construction Update");

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                    refreshContent();
            }
        });
        adapter = new SiteProgressItemAdapter(ViewSiteProgress.this, name, imagepic, date, count, monthIntext, year, productid, month, product_type_sub, OnBack, PRODUCT_NAME);

        gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(gridLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);
        rv.setNestedScrollingEnabled(false);


        PageScroll();
    }



    private void PageScroll() {
        rv.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            public void firstVisible() {
                mSwipeRefreshLayout.setEnabled(gridLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }

            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            if (siteProgressImagesAdapter != null) {
                siteProgressImagesAdapter.clearData();
                siteProgressImagesAdapter.notifyDataSetChanged();
            }
            date.clear();
            imagepic.clear();
            count.clear();
            adapter.clear();
            adapter.notifyDataSetChanged();
            loadFirstPage();
            PageScroll();
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    private void loadNextPage() {
        callSiteProgressApi().enqueue(new Callback<NewSiteProgressImages>() {
            @Override
            public void onResponse(Call<NewSiteProgressImages> call, Response<NewSiteProgressImages> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<SiteProgressList> results = response.body().getData().getData();
                adapter.addAll(results);

                for (int i = 0; i < results.size(); i++) {
                    if (!response.body().getData().getData().contains(results.get(i).getThumbnailImage())) {
                        imagepic.add(results.get(i).getRootImage());
                        date.add(SetGmtTime(results.get(i).getCreatedOn()));
                    }
                }
                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<NewSiteProgressImages> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadFirstPage() {
        final ProgressDialog mProgressDialog = new ProgressDialog(this, R.style.MyTheme);
        mProgressDialog.setCancelable(false);
        if (!isRefreshed) {
            mProgressDialog.show();
        }
        mProgressDialog.setContentView(R.layout.progressdialog);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        currentPage = PAGE_START;
        callSiteProgressApi().enqueue(new Callback<NewSiteProgressImages>() {
            @Override
            public void onResponse(Call<NewSiteProgressImages> call, Response<NewSiteProgressImages> response) {
                if (ViewSiteProgress.this != null && !ViewSiteProgress.this.isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }

                mSwipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    // Got data. Send it to adapter
                    List<SiteProgressList> results = response.body().getData().getData();
                    // progressBar.setVisibility(View.GONE);
                    adapter.addAll(results);
                    String projectDescription = response.body().getData().getProjectDescription();

                    if (projectDescription != null) {
                        tv.setText(projectDescription.toString());
                    }

                    for (int i = 0; i < results.size(); i++) {
                        if (!response.body().getData().getData().contains(results.get(i).getThumbnailImage())) {
                            imagepic.add(results.get(i).getRootImage());
                            date.add(SetGmtTime(results.get(i).getCreatedOn()));
                        }
                    }
                    if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                    else isLastPage = true;
                }
            }

            @Override
            public void onFailure(Call<NewSiteProgressImages> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private Call<NewSiteProgressImages> callSiteProgressApi() {
        return mAPIInterface.getSiteProgressImages(currentPage, 15, productid, month, year);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
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
        rv.setVisibility(View.GONE);
    }

    void serviceUnavailable() {
        txtErrorTitle.setText(R.string.error_service_unavailable);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);
    }

    void noInternetConnection() {
        txtErrorTitle.setText(R.string.error_msg_network);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);
    }
}
