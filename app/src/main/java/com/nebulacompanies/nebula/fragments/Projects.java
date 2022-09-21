package com.nebulacompanies.nebula.fragments;

import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_ERROR;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_NO_RECORDS;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE;
import static com.nebulacompanies.nebula.util.Constants.REQUEST_STATUS_CODE_SUCCESS;
import static com.nebulacompanies.nebula.util.NetworkChangeReceiver.isInternetPresent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.Model.Guest.ProjectList;
import com.nebulacompanies.nebula.Network.APIClient;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.adapters.ProjectsAdapter;
import com.nebulacompanies.nebula.gui.FullScreenSwipeActivity;
import com.nebulacompanies.nebula.view.MyTextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Palak Mehta on 12/30/2016.
 */ 

public class Projects extends Fragment implements AdapterView.OnItemClickListener {

    SwipeRefreshLayout mSwipeRefreshLayout;
    GridView gridView;
    Boolean isRefreshed = false;
    private APIInterface mAPIInterface;
    public static ArrayList<ProjectList> arrayListProjects = new ArrayList<>();
    public static final String TAG = "Projects";
    ProjectsAdapter projectsAdapter;

    //Error View
    private LinearLayout llEmpty;
    private ImageView imgError;
    private MyTextView txtErrorTitle, txtRetry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mAPIInterface = APIClient.getClient(getActivity()).create(APIInterface.class);
        initError(view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.projects_swipe_refresh_layout);
        gridView = (GridView) view.findViewById(R.id.projects_gridview);
        gridView.setOnItemClickListener(this);

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (gridView.getChildAt(0) != null) {
                    mSwipeRefreshLayout.setEnabled(gridView.getFirstVisiblePosition() == 0 && gridView.getChildAt(0).getTop() == 0);
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            if (projectsAdapter == null){
                getProjectList();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("ProjectDetail", arrayListProjects);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            ArrayList<ProjectList> projectLists = (ArrayList<ProjectList>) savedInstanceState.getSerializable("ProjectDetail");

            projectsAdapter = new ProjectsAdapter(getActivity(), projectLists);
            gridView.setAdapter(projectsAdapter);
            projectsAdapter.notifyDataSetChanged();
        }
    }

    private void refreshContent() {
        if (isInternetPresent) {
            isRefreshed = true;
            if(projectsAdapter != null) {
                projectsAdapter.clearData();
                projectsAdapter.notifyDataSetChanged();
            }
            getProjectList();
            mSwipeRefreshLayout.setRefreshing(true);
        }
        else {
            mSwipeRefreshLayout.setRefreshing(false);
            noInternetConnection();
        }
    }

    private void getProjectList() {
        if (isInternetPresent) {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
           /* mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);*/
            if(!isRefreshed) {
                mProgressDialog.show();
            }
            mProgressDialog.setCancelable(false);
            mProgressDialog.setContentView(R.layout.progressdialog);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            Call<com.nebulacompanies.nebula.Model.Guest.Projects> wsCallingProjects = mAPIInterface.getProjectList();
            wsCallingProjects.enqueue(new Callback<com.nebulacompanies.nebula.Model.Guest.Projects>() {
                @Override
                public void onResponse(Call<com.nebulacompanies.nebula.Model.Guest.Projects> call, Response<com.nebulacompanies.nebula.Model.Guest.Projects> response) {
                    if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                        mProgressDialog.dismiss();
                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                    arrayListProjects.clear();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {

                            if (response.body().getStatusCode() == REQUEST_STATUS_CODE_NO_RECORDS) {
                                noRecordsFound();
                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_SUCCESS) {
                                arrayListProjects.addAll(response.body().getData());
                                projectsAdapter = new ProjectsAdapter(getActivity(), arrayListProjects);
                                gridView.setAdapter(projectsAdapter);
                                projectsAdapter.notifyDataSetChanged();
                            } else if (response.body().getStatusCode() == REQUEST_STATUS_CODE_ERROR
                                    || response.body().getStatusCode() == REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE) {
                                serviceUnavailable();
                            }

                            if (arrayListProjects.size() > 0) {
                                llEmpty.setVisibility(View.GONE);
                                gridView.setVisibility(View.VISIBLE);
                            } else {
                                llEmpty.setVisibility(View.VISIBLE);
                                gridView.setVisibility(View.GONE);
                            }
                        }
                    }
                    else{
                        serviceUnavailable();
                    }
                }

                @Override
                public void onFailure(Call<com.nebulacompanies.nebula.Model.Guest.Projects> call, Throwable t) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    mProgressDialog.dismiss();
                    Log.e(TAG, "ERROR : " + t.getMessage());
                    serviceUnavailable();
                }
            });
        } else {
            noInternetConnection();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent i = new Intent(getActivity(), FullScreenSwipeActivity.class);
        i.putExtra("id", position);
        i.putExtra("image_path", arrayListProjects);
        startActivity(i);
    }

    void initError(View view){
        llEmpty = (LinearLayout) view.findViewById(R.id.llEmpty);
        imgError = (ImageView) view.findViewById(R.id.imgError);
        txtErrorTitle = (MyTextView) view.findViewById(R.id.txtErrorTitle);
        txtRetry = (MyTextView) view.findViewById(R.id.txtRetry);
        txtRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshContent();
            }
        });
    }

    void noRecordsFound(){
        txtErrorTitle.setText(R.string.error_no_records);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.GONE);
        gridView.setVisibility(View.GONE);
    }

    void serviceUnavailable(){
        txtErrorTitle.setText(R.string.error_service_unavailable);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
    }

    void noInternetConnection(){
        txtErrorTitle.setText(R.string.error_msg_network);
        imgError.setImageResource(R.drawable.ic_cloud_off_black_24dp);
        llEmpty.setVisibility(View.VISIBLE);
        txtRetry.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
    }

}

