package com.nebulacompanies.nebula.CustomerBooking.Utils.Fragmetns;

import static com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils.IndianCurrencyFormat;
import static com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils.displayAlertErrorNetwork;
import static com.nebulacompanies.nebula.CustomerBooking.Utils.Const.STATUS_SUCCESS;


import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nebulacompanies.nebula.CustomerBooking.Utils.Const;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.InstallmentInfo;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.MyApplicant;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.ProjectInfo;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.ProjectUnitInfo;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.ProjectUnits;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.UnitInstallment;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UI.Activity.CustomerBookingNavigationActivity;
import com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils;
import com.nebulacompanies.nebula.CustomerBooking.Utils.UserAuthorization;
import com.nebulacompanies.nebula.Network.APIClientCustomerBooking;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.databinding.RowEmiTableBinding;
import com.nebulacompanies.nebula.util.Uttils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Class : UnitListFragment
 * Details: This Class Fragment for Display Unit Details List.
 * Create by : Jadav Chirag At NebulaApplication Infra space LLP 20-11-2017
 * Modification by :
 */

public class UnitListFragment extends Fragment {

    // Global Variable
    private int i = -1;
    private ArrayList<ProjectUnitInfo> mUnitList = new ArrayList<>();
    private ArrayList<ProjectUnitInfo> mUnitSearchList = new ArrayList<>();
    private ArrayList<InstallmentInfo> mEMIList = new ArrayList<>();

    private final String TAG = getClass().getSimpleName();
    private UnitDetailAdapter mUnitDetailAdapter;
    private APIInterface mAPIInterface;

    // UI Components
    private LinearLayout llEmpty, llEMICard;
    private RecyclerView unitRecyclerView;
    private ImageView imgError;
    private TextView txtErrorTitle;


    private UserAuthorization mUserAuthorization;


    /************************************************************
     *                    OVERRIDE METHOD
     *************************************************************/

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unit_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        mUserAuthorization = new UserAuthorization(getActivity());
        ((CustomerBookingNavigationActivity) getActivity()).setActionBarTitle(getResources().getString(R.string.nav_units));
        mAPIInterface = APIClientCustomerBooking.getClient(getActivity()).create(APIInterface.class);
      //  swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        unitRecyclerView = (RecyclerView) view.findViewById(R.id.unitRecyclerView);

        llEmpty = (LinearLayout) view.findViewById(R.id.llEmpty);
        llEMICard = (LinearLayout) view.findViewById(R.id.ll_EMICard);
        imgError = (ImageView) view.findViewById(R.id.imgError);
        txtErrorTitle = (TextView) view.findViewById(R.id.txtErrorTitle);



        mUnitDetailAdapter = new UnitDetailAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        unitRecyclerView.setLayoutManager(mLayoutManager);
        unitRecyclerView.setItemAnimator(new DefaultItemAnimator());
        unitRecyclerView.setAdapter(mUnitDetailAdapter);

        imgError.setImageResource(R.drawable.img_empty_view);
        txtErrorTitle.setText(getString(R.string.error_unit_detail));
        requestUnitList();

    }

    /************************************************************
     *                    PRIVATE METHOD
     *************************************************************/

    /**
     * This REST API Method for getting Unit Details.
     */
    private void requestUnitList() {
        if (AppUtils.isNetworkAvailable(getActivity())) {
            Uttils.showProgressDialoug(getActivity());
            Call<ProjectUnits> requestUnitList = mAPIInterface.getUnitListByUser();
            requestUnitList.enqueue(new Callback<ProjectUnits>() {
                @Override
                public void onResponse(Call<ProjectUnits> call, Response<ProjectUnits> response) {
                 //   swipeRefresh.setRefreshing(false);
                    Uttils.hideProgressDialoug();
                    mUnitSearchList.clear();
                    mUnitList.clear();
                    if (response.isSuccessful()) {
                        if (response.code() == 200 && response.body().getStatusCode() == STATUS_SUCCESS) {
                            if (response.body() != null) {
                                mUnitList.addAll(response.body().getData());
                                mUnitSearchList.addAll(response.body().getData());
                                mUnitDetailAdapter.notifyDataSetChanged();
                              //  pDialog.dismissWithAnimation();
                                try {
                                    doGetApplicants(mUnitList.get(0).getUnitID());
                                    doGetInstallmentListByUnit(mUnitList.get(0).getUnitID());
                                }catch (Exception e){

                                }

                            }
                        } else
                            AppUtils.displayErrorMessageInternalServer(getActivity());
                    } else{}
                      //  DisplayEmptyDialog(getActivity(), -1);
                }

                @Override
                public void onFailure(Call<ProjectUnits> call, Throwable throwable) {
                    Uttils.hideProgressDialoug();
                    //swipeRefresh.setRefreshing(false);
                    Log.e(getClass().getSimpleName(), "ERROR " + throwable.getMessage());
                }
            });
        } else {
          //  swipeRefresh.setRefreshing(false);
            displayAlertErrorNetwork(getActivity());
        }
    }

    /**
     * This Method for get Applicants Details based on Unit ID.
     *
     * @param UnitID An Unit ID
     */
    private void doGetApplicants(final int UnitID) {
        if (AppUtils.isNetworkAvailable(getActivity())) {
            Call<MyApplicant> doGetMyApplicant = mAPIInterface.getMyApplicantByUnit(UnitID);
            doGetMyApplicant.enqueue(new Callback<MyApplicant>() {
                @Override
                public void onResponse(Call<MyApplicant> call, Response<MyApplicant> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            // arrayListMyApplicant.clear();
                            if (response.body().getStatusCode() == Const.STATUS_SUCCESS) {
//                                arrayListMyApplicant.addAll(response.body().getData());
//                                RefreshApplicantDetails(UnitID);

                                if(response.body().getData() != null){
                                    String str_Name =  response.body().getData() .get(0).getFirstName() + " "+ response.body().getData() .get(0).getLastName() ;
                                    mUserAuthorization.setUserProfileName(str_Name);
                                }

                            } else {
                                AppUtils.displayErrorMessage(getActivity(), response.body().getMessage());
                            }
                        } else {
                            AppUtils.displayErrorMessageInternalServer(getActivity());
                        }
                    } else if (getActivity() != null) {
                        //DisplayEmptyDialog(getActivity(), -1);
                    }

                    // pDialog.dismissWithAnimation();
                }

                @Override
                public void onFailure(Call<MyApplicant> call, Throwable throwable) {
                    //pDialog.dismissWithAnimation();
                    Log.e(getClass().getSimpleName(), "ERROR : " + throwable.getMessage());
                }
            });
        } else {
            displayAlertErrorNetwork(getActivity());
        }
    }


    /**
     * This method for getting the installment list by unit id.
     *
     * @param unitId An unit id.
     */
    private void doGetInstallmentListByUnit(int unitId) {

        if (AppUtils.isNetworkAvailable(getActivity())) {
            Uttils.showProgressDialoug(getActivity());
            Call<UnitInstallment> requestInstallment = mAPIInterface.getInstallmentListByUnit(unitId);
            requestInstallment.enqueue(new Callback<UnitInstallment>() {
                @Override
                public void onResponse(Call<UnitInstallment> call, Response<UnitInstallment> response) {
                   Uttils.hideProgressDialoug();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            mEMIList.clear();
                            if (response.body().getStatusCode() == STATUS_SUCCESS) {
                                mEMIList.addAll(response.body().getData());
                                llEMICard.setVisibility(View.VISIBLE);
                                EMITable();
                            } else
                                AppUtils.displayErrorMessage(getActivity(), response.body().getMessage());
                        } else
                            AppUtils.displayErrorMessageInternalServer(getActivity());
                    } else{}

                }

                @Override
                public void onFailure(Call<UnitInstallment> call, Throwable throwable) {
                    Uttils.hideProgressDialoug();
                    Log.e(getClass().getSimpleName(), "ERROR " + throwable.getMessage());
                }
            });
        } else
            displayAlertErrorNetwork(getActivity());
    }

    /**
     * This Method Display EMI Table Details
     */
    private void EMITable() {
        RecyclerView recyclerViewEMITable =  view.findViewById(R.id.RecyclerViewEMITable);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewEMITable.setLayoutManager(mLayoutManager);
        recyclerViewEMITable.setAdapter(new InstallmentInfoAdapter());
    }


    private void ProjectDetailsUnitDialog(final ProjectInfo mProjectInfo, String projectUnit) {
        LayoutInflater mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View convertView = mLayoutInflater.inflate(R.layout.dialog_project_unit_details, null);
        dialog.setContentView(convertView);
        dialog.setCancelable(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (int) ((int)displaymetrics.widthPixels * 0.9);
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width,  WindowManager.LayoutParams.WRAP_CONTENT);
        //TODO : Initialization Components
        LinearLayout llUnitType, llCost, llPaymentPlan, llDownPayment, llDownPaymentPlan, llToken, llReceipt, llBrochure, llReceiptDownPayment, llReceiptBankLoan;
        TextView txtProjectDetail, txtUnitType, txtCost, txtPaymentPlan, txtDownPayment, txtDownPaymentPlan, txtToken, txtReceiptDownPayment, txtReceiptBankLoan, txtBrochure, llClose;


        llUnitType = (LinearLayout) convertView.findViewById(R.id.llUnitType);
        llCost = (LinearLayout) convertView.findViewById(R.id.llCost);
        llPaymentPlan = (LinearLayout) convertView.findViewById(R.id.llPaymentPlan);
        llDownPayment = (LinearLayout) convertView.findViewById(R.id.llDownPayment);
        llDownPaymentPlan = (LinearLayout) convertView.findViewById(R.id.llDownPaymentPlan);
        llToken = (LinearLayout) convertView.findViewById(R.id.llToken);
        llReceipt = (LinearLayout) convertView.findViewById(R.id.llReceipt);
        llReceiptBankLoan = (LinearLayout) convertView.findViewById(R.id.llReceiptBankLoan);
        llReceiptDownPayment = (LinearLayout) convertView.findViewById(R.id.llReceiptDownPayment);
        llBrochure = (LinearLayout) convertView.findViewById(R.id.llBrochure);

        txtProjectDetail = (TextView) convertView.findViewById(R.id.txtProjectDetail);
        txtUnitType = (TextView) convertView.findViewById(R.id.txtUnitType);
        txtCost = (TextView) convertView.findViewById(R.id.txtCost);
        txtPaymentPlan = (TextView) convertView.findViewById(R.id.txtPaymentPlan);
        txtDownPayment = (TextView) convertView.findViewById(R.id.txtDownPayment);
        txtDownPaymentPlan = (TextView) convertView.findViewById(R.id.txtDownPaymentPlan);
        txtToken = (TextView) convertView.findViewById(R.id.txtToken);
        txtReceiptDownPayment = (TextView) convertView.findViewById(R.id.txtReceiptDownPayment);
        txtReceiptBankLoan = (TextView) convertView.findViewById(R.id.txtReceiptBankLoan);
        txtBrochure = (TextView) convertView.findViewById(R.id.txtBrochure);
        llClose = (TextView) convertView.findViewById(R.id.llClose);

        //TODO : Validation & Data Binding

        txtProjectDetail.setText(projectUnit);

        if (mProjectInfo.getUnit() != null) {
            llUnitType.setVisibility(View.VISIBLE);
            txtUnitType.setText(mProjectInfo.getUnit());
        } else {
            llUnitType.setVisibility(View.GONE);
            txtUnitType.setText(null);
        }

        if (mProjectInfo.getCost() != null && mProjectInfo.getCost() > 0) {
            llCost.setVisibility(View.VISIBLE);
            txtCost.setText("" + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mProjectInfo.getCost()));
        } else {
            llCost.setVisibility(View.GONE);
            txtCost.setText(null);
        }

        if (mProjectInfo.getPaymentPlan() != null && mProjectInfo.getPaymentPlan().length() > 0) {
            llPaymentPlan.setVisibility(View.VISIBLE);
            txtPaymentPlan.setText(mProjectInfo.getPaymentPlan());
        } else {
            llPaymentPlan.setVisibility(View.GONE);
            txtPaymentPlan.setText(null);
        }

        if (mProjectInfo.getDownPayment() != null && mProjectInfo.getDownPayment().length() > 0) {
            llDownPayment.setVisibility(View.VISIBLE);
            txtDownPayment.setText(mProjectInfo.getDownPayment());
        } else {
            llDownPayment.setVisibility(View.GONE);
            txtDownPayment.setText(null);
        }

        if (mProjectInfo.getDownPaymentPlan() != null && mProjectInfo.getDownPaymentPlan().length() > 0) {
            llDownPaymentPlan.setVisibility(View.VISIBLE);
            txtDownPaymentPlan.setText(mProjectInfo.getDownPaymentPlan());
        } else {
            llDownPaymentPlan.setVisibility(View.GONE);
            txtDownPaymentPlan.setText(null);
        }

        if (mProjectInfo.getToken() != null && mProjectInfo.getToken() > 0) {
            llToken.setVisibility(View.VISIBLE);
            txtToken.setText("" + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mProjectInfo.getToken()));
        } else {
            llToken.setVisibility(View.GONE);
            txtToken.setText(null);
        }

        if (mProjectInfo.getReciptDownPayment() != null && mProjectInfo.getReciptDownPayment() > 0) {
            llReceiptDownPayment.setVisibility(View.VISIBLE);
            txtReceiptDownPayment.setText("" + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mProjectInfo.getReciptDownPayment()));
        } else {
            llReceiptDownPayment.setVisibility(View.GONE);
            txtReceiptDownPayment.setText(null);
        }

        if (mProjectInfo.getReciptBankLoan() != null && mProjectInfo.getReciptBankLoan() > 0) { //
            llReceiptBankLoan.setVisibility(View.VISIBLE);
            txtReceiptBankLoan.setText("" + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mProjectInfo.getReciptBankLoan()));
        } else {
            llReceiptBankLoan.setVisibility(View.GONE);
            txtReceiptBankLoan.setText(null);
        }

        if (mProjectInfo.getReciptDownPayment() > 0 || mProjectInfo.getReciptBankLoan() > 0)
            llReceipt.setVisibility(View.VISIBLE);
        else
            llReceipt.setVisibility(View.GONE);

        if (mProjectInfo.getBrochure() != null && mProjectInfo.getBrochure().length() > 0) {
            llBrochure.setVisibility(View.VISIBLE);
            ///txtBrochure.setText(mProjectInfo.getBrochure());
        } else {
            llBrochure.setVisibility(View.GONE);
            //txtBrochure.setText(null);
        }

        txtBrochure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(mProjectInfo.getBrochure()), "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        getActivity().grantUriPermission(packageName, Uri.parse(mProjectInfo.getBrochure()), Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                }
            }
        });


        llClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();
    }


    /**
     * This Method for Display EMI Information dialog.
     *
     * @param object An object of InstallmentInfo
     */
    private void DialogEMIInfo(InstallmentInfo object) {
        LayoutInflater mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View convertView = mLayoutInflater.inflate(R.layout.dialog_emi_table_info, null);
        dialog.setContentView(convertView);
        dialog.setCancelable(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        //TODO : Initialization Components
        TextView txtClearDate, txtPaymentType, txtTXNNo;
        LinearLayout llTXNNo;

        txtClearDate = convertView.findViewById(R.id.txtClearDate);
        txtPaymentType = convertView.findViewById(R.id.txtPaymentType);
        txtTXNNo = convertView.findViewById(R.id.txtTXNNo);
        llTXNNo = convertView.findViewById(R.id.llTXNNo);

        txtClearDate.setText(object.getClearDate());
        txtPaymentType.setText(object.getPaymentType());
        if (object.getTXNNo() != null && object.getTXNNo().length() > 0) {
            txtTXNNo.setText(object.getTXNNo());
            llTXNNo.setVisibility(View.VISIBLE);
        } else {
            llTXNNo.setVisibility(View.GONE);
            txtTXNNo.setText(null);
        }
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();
    }

    /**
     * This Method Display IBO Detail
     *
     * @param position
     */
    private void DialogIBODetail(int position) {
        //mUnitSearchList
        LayoutInflater mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View convertView = mLayoutInflater.inflate(R.layout.dialog_ibo_info, null);
        dialog.setContentView(convertView);
        dialog.setCancelable(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        //TODO : Initialization UI Components.
        TextView txtIBOName, txtContact, txtCity;

        txtIBOName = convertView.findViewById(R.id.txtIBOName);
        txtContact = convertView.findViewById(R.id.txtContact);
        txtCity = convertView.findViewById(R.id.txtCity);

        //TODO : Data Binding Details.
        txtIBOName.setText(mUnitSearchList.get(position).getIBOInfo().getName());
        txtContact.setText(mUnitSearchList.get(position).getIBOInfo().getContact());
        txtCity.setText(mUnitSearchList.get(position).getIBOInfo().getCity());

        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();
    }

    /************************************************************
     *                    CHILD CLASS
     *************************************************************/

    private class UnitDetailAdapter extends RecyclerView.Adapter<UnitDetailAdapter.UnitDetailsHolder> implements Filterable {

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.equalsIgnoreCase("removed")) {
                        mUnitSearchList = mUnitList;
                    } else {
                        ArrayList<ProjectUnitInfo> filteredList = new ArrayList<>();

                        for (ProjectUnitInfo mObject : mUnitList) {
                            String searchTag = mObject.getUnit() + ", " + mObject.getProject();
                            if (mObject.getPendingEMICount().toString().equalsIgnoreCase(charString) ||
                                    mObject.getFloor().equalsIgnoreCase(charString) ||
                                    mObject.getUnitType().equalsIgnoreCase(charString) ||
                                    mObject.getProject().equalsIgnoreCase(charString) ||
                                    mObject.getIBOName().equalsIgnoreCase(charString) ||
                                    mObject.getUnit().equalsIgnoreCase(charString) ||
                                    mObject.getPaymentPlan().equalsIgnoreCase(charString) ||
                                    mObject.getUnitBudget().toString().equalsIgnoreCase(charString) ||
                                    mObject.getPLC().toString().equalsIgnoreCase(charString) ||
                                    searchTag.equalsIgnoreCase(charString)
                                    ) {
                                filteredList.add(mObject);
                            }
                        }
                        mUnitSearchList = filteredList;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mUnitSearchList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    mUnitSearchList = (ArrayList<ProjectUnitInfo>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class UnitDetailsHolder extends RecyclerView.ViewHolder {

            CardView cardProjectUnit;
            TextView txtProjectTitle, txtFirstApp, txtIBODetails, txtUnitInfo, txtPLC, txtAreaRatePLCLabel, txtAreaRatePLC, txtPaymentPlan, txtConstArea, txtPendingEMI, txtLoanDocs;
            ImageView imgAFSStatus;
            LinearLayout llProjectDetails, llPendingEMI;

            public UnitDetailsHolder(View convertView) {
                super(convertView);
                cardProjectUnit = (CardView) convertView.findViewById(R.id.cardProjectUnit);
                txtProjectTitle = (TextView) convertView.findViewById(R.id.txtProjectTitle);
                txtFirstApp = convertView.findViewById(R.id.txtFirstName);
                txtIBODetails = (TextView) convertView.findViewById(R.id.txtIBODetails);
                txtUnitInfo = (TextView) convertView.findViewById(R.id.txtUnitInfo);
                txtPLC = (TextView) convertView.findViewById(R.id.txtPLC);
                txtAreaRatePLCLabel = (TextView) convertView.findViewById(R.id.txtAreaRatePLCLabel);
                txtAreaRatePLC = (TextView) convertView.findViewById(R.id.txtAreaRatePLC);
                imgAFSStatus = (ImageView) convertView.findViewById(R.id.imgAFSStatus);
                txtPaymentPlan = (TextView) convertView.findViewById(R.id.txtPaymentPlan);
                txtConstArea = (TextView) convertView.findViewById(R.id.txtConstArea);
                txtLoanDocs = (TextView) convertView.findViewById(R.id.txtLoanDocs);
                txtPendingEMI = convertView.findViewById(R.id.txtPendingEMI);
                llProjectDetails = (LinearLayout) convertView.findViewById(R.id.llProjectDetails);
                llPendingEMI = (LinearLayout) convertView.findViewById(R.id.llPendingEMI);
            }
        }

        @Override
        public UnitDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_project_unit, parent, false);
            UnitDetailsHolder videoViewHolder = new UnitDetailsHolder(view);

            if (mUnitSearchList.size() > 0)
                llEmpty.setVisibility(View.GONE);
            else
                llEmpty.setVisibility(View.VISIBLE);

            return videoViewHolder;
        }

        @Override
        public void onBindViewHolder(final UnitDetailsHolder holder, final int position) {

            final ProjectUnitInfo mChipDetails = mUnitSearchList.get(position);
            final String ProjectUnit = mChipDetails.getUnitType() + ", " + mChipDetails.getFloor() + ", " + mChipDetails.getProject();

            holder.txtProjectTitle.setText(ProjectUnit);
            holder.txtFirstApp.setText(mChipDetails.getFirstAppName());
            holder.txtIBODetails.setText("" + mChipDetails.getIBOID());
            holder.txtUnitInfo.setText(mChipDetails.getUnit());
            holder.txtPLC.setText("" + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mChipDetails.getPLC()));
            //holder.txtAreaRatePLC.setText("" + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mChipDetails.getUnitBudget()));
            String strAreaRatePLC = "", strAreaRatePLCLabel = "";
            if (mChipDetails.getPLC() != 0) {
                strAreaRatePLCLabel = "(Area X Rate) + PLC :";
                strAreaRatePLC = "(" + mChipDetails.getConstArea() + " X " + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mChipDetails.getUnitBudget()) + ") + " + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mChipDetails.getPLC());
            } else {
                strAreaRatePLCLabel = "(Area X Rate):";
                strAreaRatePLC = "(" + mChipDetails.getConstArea() + " X " + getString(R.string.prompt_rs) + " " + IndianCurrencyFormat.format(mChipDetails.getUnitBudget()) + ")";
            }

            holder.txtAreaRatePLCLabel.setText(strAreaRatePLCLabel);
            holder.txtAreaRatePLC.setText(strAreaRatePLC);
            holder.txtPaymentPlan.setText(mChipDetails.getPaymentPlan() + " Plan");
            holder.txtConstArea.setText(mChipDetails.getConstArea());
            holder.txtPendingEMI.setText("" + mChipDetails.getPendingEMICount());

            if (mChipDetails.getAFSStatus())
                holder.imgAFSStatus.setImageResource(R.drawable.ic_check);
            else
                holder.imgAFSStatus.setImageResource(R.drawable.ic_close);

            holder.txtIBODetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogIBODetail(position);
                }
            });

            holder.llPendingEMI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doGetInstallmentListByUnit(mChipDetails.getUnitID());
                    /*Intent intentInstallment = new Intent(getActivity(), UnitInstallmentListActivity.class);
                    intentInstallment.putExtra(Const.KEY_TITLE, ProjectUnit);
                    intentInstallment.putExtra(Const.KEY_MESSAGE, mChipDetails.getId().toString());
                    startActivity(intentInstallment);*/
                }
            });

            holder.llProjectDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProjectDetailsUnitDialog(mChipDetails.getProjectInfo(), ProjectUnit);
                }
            });

            holder.txtLoanDocs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //LoanDocumentStatusTable(mChipDetails.getLoanDocumentsStatus());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mUnitSearchList.size();
        }
    }

    private class InstallmentInfoAdapter extends RecyclerView.Adapter<InstallmentInfoAdapter.InstallmentInfoHolder> {

        public class InstallmentInfoHolder extends RecyclerView.ViewHolder {
            RowEmiTableBinding binding;


            public InstallmentInfoHolder(RowEmiTableBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

            }
        }

        @Override
        public InstallmentInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            RowEmiTableBinding itemBinding = RowEmiTableBinding.inflate(layoutInflater, parent, false);
            return new InstallmentInfoHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(final InstallmentInfoHolder holder, final int position) {
            InstallmentInfo mObject = mEMIList.get(position);
//            holder.binding.setEMI(mObject);
//            holder.binding.executePendingBindings();

            holder.binding.txtInstallment.setText(mObject.getInstallment());
            holder.binding.txtAmout.setText(mObject.getAmount());
            holder.binding.txtEmiDate.setText(mObject.getEMIDate());
            holder.binding.txtEmiStatus.setText(mObject.getStatus());
           // holder.binding.tx();

            holder.binding.txtInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mEMIList.get(position).getStatus().equalsIgnoreCase("Pending"))
                        DialogEMIInfo(mEMIList.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mEMIList.size();
        }
    }
}
