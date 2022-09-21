package com.nebulacompanies.nebula.gui;

import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;
import static com.nebulacompanies.nebula.util.SetDateFormat.SetGmtTime;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.Base2Activity;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UserAuthorization;
import com.nebulacompanies.nebula.Model.Guest.NewSubEventDetails;
import com.nebulacompanies.nebula.Model.Guest.SubEventList;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.EventItemAdapter;
import com.nebulacompanies.nebula.util.ConnectionDetector;
import com.nebulacompanies.nebula.util.PaginationScrollListener;
import com.nebulacompanies.nebula.util.Session;
import com.nebulacompanies.nebula.view.MyBoldTextView;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 8/6/2016.
 */
public class ViewEvents extends Base2Activity  {

    SwipeRefreshLayout mSwipeRefreshLayout;
    String event;
    String eventDate;
    private APIInterface mAPIInterface;
    public static ArrayList<SubEventList> arrayListEvents = new ArrayList<>();
    public static final String TAG = "ViewEvents";
    Boolean isRefreshed = false;

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    MyTextView tvDateValue;
    private MyTextView txtErrorTitle, txtRetry, tvDescp;
    private MyBoldTextView tvTitle, tvDate;

    ConnectionDetector cd;
    Session session;
    UserAuthorization mUserAuthorization;

    final String PREFS_WALKTHROUGH_EVENT_BACK = "eventbacktwalkthrough";
    SharedPreferences walkthroughEventBack;
    Toolbar toolbar;
    boolean OnBack;
    private static final int PAGE_START = 1;
    EventItemAdapter adapter;
    RecyclerView rv;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private static final int TOTAL_PAGES = 150;
    private int currentPage = PAGE_START;

    GridLayoutManager gridLayoutManager;

    ArrayList<String> imagepic = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Integer> count = new ArrayList<Integer>();

    boolean first_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_projects_events);

        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            event = b.getString("Event_Name");
            eventDate = b.getString(" ");
            OnBack = b.getBoolean("OnBack");
            first_time = b.getBoolean("first_time");
        }
        session = new Session(this);
        mUserAuthorization = new UserAuthorization(this);

        setActionBar();
        init();

        loadFirstPage();

    }

    void setActionBar() {
        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_projects);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(event);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void init() {
        mAPIInterface = APIClient.getClient(this).create(APIInterface.class);
        initError();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.projects_view_swipe_refresh_layout);
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        tvDate = (MyBoldTextView) findViewById(R.id.tv_date);
        tvDateValue = (MyTextView) findViewById(R.id.tv_date_value);
        tvDescp = (MyTextView) findViewById(R.id.tv);
        tvTitle = (MyBoldTextView) findViewById(R.id.tv_title);
        tvDate.setVisibility(View.VISIBLE);
        tvDateValue.setVisibility(View.VISIBLE);

        tvDateValue.setText(eventDate);
        tvTitle.setText("About the event");



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                    refreshContent();

            }
        });

        adapter = new EventItemAdapter(ViewEvents.this, event, imagepic, date, count, first_time);


        gridLayoutManager = new GridLayoutManager(this, 2);
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

    private void refreshContent() {
        //progressBar.setVisibility(View.VISIBLE);
        if (isInternetPresent) {
            isRefreshed = true;
            if (callTopRatedApi().isExecuted())
                callTopRatedApi().cancel();

            // TODO: Check if data is stale.
            //  Execute network request if cache is expired; otherwise do not update data.
            adapter.clear();
            adapter.notifyDataSetChanged();
            date.clear();
            imagepic.clear();
            count.clear();
            loadFirstPage();
            PageScroll();
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
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
        callTopRatedApi().enqueue(new Callback<NewSubEventDetails>() {
            @Override
            public void onResponse(Call<NewSubEventDetails> call, Response<NewSubEventDetails> response) {
                if (ViewEvents.this != null && !ViewEvents.this.isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }

                mSwipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    // Got data. Send it to adapter
                    List<SubEventList> results = response.body().getData().getData();
                    // progressBar.setVisibility(View.GONE);
                    adapter.addAll(results);

                    if (!response.body().getMessage().equals( "No records found!")) {

                        String Descp = results.get(0).getEventDescription();

                        if (results.get(0).getEventDescription() != null) {
                            tvDescp.setText(Descp.toString());
                        }
                    }else {
                        noRecordsFound();
                    }
                    for (int i = 0; i < results.size(); i++) {
                        if (!response.body().getData().getData().contains(results.get(i).getEventPic())) {
                            imagepic.add(results.get(i).getEventPic());
                            date.add(SetGmtTime(results.get(i).getCratedOn()));
                            count.add(results.get(i).getLinkCount());
                        }
                    }
                    if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                    else isLastPage = true;
                }
            }

            @Override
            public void onFailure(Call<NewSubEventDetails> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadNextPage() {
        callTopRatedApi().enqueue(new Callback<NewSubEventDetails>() {
            @Override
            public void onResponse(Call<NewSubEventDetails> call, Response<NewSubEventDetails> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<SubEventList> results = response.body().getData().getData();
                adapter.addAll(results);
                //ImagePass(results);
                for (int i = 0; i < results.size(); i++) {
                    if (!response.body().getData().getData().contains(results.get(i).getEventPic())) {
                        imagepic.add(results.get(i).getEventPic());
                        date.add(SetGmtTime(results.get(i).getCratedOn()));
                        count.add(results.get(i).getLinkCount());
                    }
                }
                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<NewSubEventDetails> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private Call<NewSubEventDetails> callTopRatedApi() {
        return mAPIInterface.getEvent(currentPage, 15, event, "None");
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
