package com.nebulacompanies.nebula.CustomerBooking.Utils.Fragmetns;

import static com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils.displayAlertErrorNetwork;
import static com.nebulacompanies.nebula.CustomerBooking.Utils.Const.STATUS_SUCCESS;
import static com.nebulacompanies.nebula.Network.APIClientCustomerBooking.context;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nebulacompanies.nebula.CustomerBooking.Utils.AppUtils;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.MyApplicant;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.MyApplicantInfo;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.MyUnits;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.MyUnitsInfo;
import com.nebulacompanies.nebula.Network.APIClientCustomerBooking;
import com.nebulacompanies.nebula.Network.APIInterface;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.databinding.DialogApplicantFromDetailsFormalBinding;
import com.nebulacompanies.nebula.util.Uttils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Class : FirstApplicantDetailOneFragment
 * Details:
 * Create by : Jadav Chirag At NebulaApplication Infra space LLP 17-11-2017
 * Modification by :
 */

public class MyAccountFragment extends Fragment implements View.OnClickListener {

    // Global Variable
    private int i = -1;
    private String[] arrayUnit;
    private ArrayList<MyUnitsInfo> arrayListUnits = new ArrayList<MyUnitsInfo>();
    private ArrayList<MyApplicantInfo> arrayListMyApplicant = new ArrayList<>();
    private APIInterface mAPIInterface;

    /* // UI Components
     private SimpleDraweeView imgProfilePic;*/
    //private TextView txtUserName, txtContact;
    /* private Button btnAboutMe;*/
    private SimpleDraweeView imgProfilePic1stApp;
    CardView cardView1stApp;
    private TextView txtUserName1stApp,  txtContact1stApp;

    @BindView(R.id.cardUploadLoanDocs)
    CardView cardUploadLoanDocs;

    @BindView(R.id.cardUploadBooking)
    CardView cardUploadBooking;

    SwipeRefreshLayout mSwipeRefreshLayout;

    //add sagar
    // UI Components
    Spinner spinnerProjectUnit;

    ImageView iv_Profile;

    /************************************************************
     *                    OVERRIDE METHOD
     *************************************************************/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getActivity());
        mAPIInterface = APIClientCustomerBooking.getClient(getActivity()).create(APIInterface.class);
        View view = inflater.inflate(R.layout.fragment_booking_my_account, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
       /* imgProfilePic = (SimpleDraweeView) view.findViewById(R.id.imgProfilePic);
        txtUserName = (TextView) view.findViewById(R.id.txtUserName);
        txtContact = (TextView) view.findViewById(R.id.txtContact);*/
        //btnAboutMe = (Button) view.findViewById(R.id.btnAboutMe);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshaccount);
        //TODO :
        //doGetApplicants(0);
        doGetMyUnits();
        //TODO : Set Binding Data
        // btnAboutMe.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                //  doGetApplicants(0);
                doGetMyUnits();
            }
        });

        //add sagar
        spinnerProjectUnit = (Spinner) view.findViewById(R.id.spinnerProjectUnit);

        cardView1stApp = (CardView) view.findViewById(R.id.cardViewUserProfile1);
        //cardView2ndApp = (CardView) view.findViewById(R.id.cardViewUserProfile2);

        imgProfilePic1stApp = (SimpleDraweeView) view.findViewById(R.id.imgProfileFirstApp);
        //imgProfilePic2ndtApp = (SimpleDraweeView) view.findViewById(R.id.imgProfileSecondApp);

        txtUserName1stApp = (TextView) view.findViewById(R.id.txtUserName);
        txtContact1stApp = (TextView) view.findViewById(R.id.txtContact);
//
//        txtUserName2ndApp = (TextView) view.findViewById(R.id.txtUserNameSecond);
//        txtContact2ndApp = (TextView) view.findViewById(R.id.txtContactSecond);
        cardView1stApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicantProfileDetail(0);
            }
        });

//        cardView2ndApp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ApplicantProfileDetail(1);
//            }
//        });

         iv_Profile = (ImageView)view.findViewById(R.id.iv_profile);

       /* ArrayAdapter<CharSequence> adapterProject = new ArrayAdapter<CharSequence>(getActivity(), R.layout.spinner_item_my_account, arrayUnit) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(AppUtils.getTypeface(getActivity()));
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(AppUtils.getTypeface(getActivity()));
                return v;
            }
        };

        adapterProject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProjectUnit.setAdapter(adapterProject);


        spinnerProjectUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                doGetApplicants(arrayListUnits.get(i).getUnitID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


    }

    @Override
    public void onClick(View v) {
        /*if (v.getId() == R.id.btnAboutMe) {
            // Display Applicant Details.
            //doGetMyUnits();
        }*/
    }

    @OnClick(R.id.cardUploadLoanDocs)
    void intentLoanDocs() {
        // TODO call start intent...
        //Intent intentLoan = new Intent(getActivity(), LoansDocsUploadActivity.class);
        //startActivity(intentLoan);
    }



    private void ApplicantProfileDetail(int position) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_applicant_from_details_formal);
        dialog.setCancelable(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (int) ((int)displaymetrics.widthPixels * 0.9);
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width,  WindowManager.LayoutParams.WRAP_CONTENT);

        Fresco.initialize(getActivity());
       // MyApplicantInfo applicationObject = null;
        //if (position == 0 || position == 1) {
        MyApplicantInfo  applicationObject = arrayListMyApplicant.get(position);


        TextView txt_Name = (TextView)dialog.findViewById(R.id.txt_name);
        TextView txt_Dob = (TextView)dialog.findViewById(R.id.txt_DOB);
        TextView txt_Nationlity = (TextView)dialog.findViewById(R.id.txt_Nationality);
        TextView txt_ResidentalAddress = (TextView)dialog.findViewById(R.id.txt_ResidentalAddress);
        TextView txt_Marital = (TextView)dialog.findViewById(R.id.txt_MaritalStatus);
        TextView txt_Proffession = (TextView)dialog.findViewById(R.id.txt_profession);
        TextView txt_Designation = (TextView)dialog.findViewById(R.id.txt_designation);
        TextView txt_CompanyName = (TextView)dialog.findViewById(R.id.txt_companyName);
        TextView txt_MobileNo = (TextView)dialog.findViewById(R.id.txt_mobile);
        TextView txt_Residental = (TextView)dialog.findViewById(R.id.txt_telephoneResidential);
        TextView txt_ResidentalMo = (TextView)dialog.findViewById(R.id.txt_ResidentalAddress);
        TextView txt_OfficeCont = (TextView)dialog.findViewById(R.id.txt_officeContactNo);
        TextView txt_EmailId= (TextView)dialog.findViewById(R.id.txt_emailId);
        TextView txt_Fax = (TextView)dialog.findViewById(R.id.txt_fax);
        TextView txt_IdNo = (TextView)dialog.findViewById(R.id.txt_idProofNo);
        TextView txt_AddressProffNo = (TextView)dialog.findViewById(R.id.txt_addressProofNo);
        TextView txt_ResintalAddress = (TextView)dialog.findViewById(R.id.txt_residentialAddress);
        TextView txt_CommunicationAddress = (TextView)dialog.findViewById(R.id.txt_communicationAddress);


        txt_Name.setText(applicationObject.getFirstName() + " "+applicationObject.getMiddleName()+ " "+applicationObject.getLastName());
        txt_Dob.setText("DOB");
        txt_Nationlity.setText(applicationObject.getNationality());
        txt_ResidentalAddress.setText(applicationObject.getResidentialAddress());
        txt_Marital.setText(applicationObject.getMaritalStatus());
        txt_Proffession.setText(applicationObject.getProfession());
        txt_Designation.setText(applicationObject.getDesignation());
        txt_CompanyName.setText(applicationObject.getCompanyName());
        txt_MobileNo.setText(applicationObject.getMobile());
        txt_Residental.setText(applicationObject.getResidentialStatus());
        txt_ResidentalMo.setText(applicationObject.getTelephoneResidential());
        txt_OfficeCont.setText(applicationObject.getOfficeContactNo());
        txt_EmailId.setText(applicationObject.getEmailId());
        txt_Fax.setText(applicationObject.getFax());
        txt_IdNo.setText(applicationObject.getEmailId());
        txt_ResidentalAddress.setText(applicationObject.getResidentialAddress());
        txt_CommunicationAddress.setText(applicationObject.getCommunicationAddress());


        TextView txtDone = (TextView)dialog.findViewById(R.id.txtDone);
        Uri uriProfile1stApp = Uri.parse(applicationObject.getProfileUrl());

        ImageView iv_Profile = (ImageView) dialog.findViewById(R.id.imgProfile);

        Glide.with(context)
                .load(uriProfile1stApp)
                .error(getResources().getDrawable(R.drawable.app_icon))
                .into(iv_Profile);

        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

      //  dataBinding.imgProfile.setImageURI(uriProfile1stApp);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();
    }

    /************************************************************
     *                    WEB SERVICES
     *************************************************************/

    /**
     * This function for getting the my unit details list
     */
    private void doGetMyUnits() {

        if (AppUtils.isNetworkAvailable(getActivity())) {
            /*final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("Please wait");
            pDialog.show();
            pDialog.setCancelable(false);*/
            Call<MyUnits> doGetMyUnits = mAPIInterface.getMYUnits();
            doGetMyUnits.enqueue(new Callback<MyUnits>() {
                @Override
                public void onResponse(Call<MyUnits> call, Response<MyUnits> response) {


                    try {

                        /* pDialog.dismissWithAnimation();*/
                        if (response.isSuccessful()) {

                            if (response.code() == 200) {
                                if (response.body().getStatusCode() == STATUS_SUCCESS) {
                                    arrayListUnits.clear();
                                    arrayListUnits.addAll(response.body().getData());
                                    List<String> listUnits = new ArrayList<>();
                                    arrayUnit = new String[response.body().getData().size()];
                                    for (MyUnitsInfo item : response.body().getData()) {
                                        listUnits.add(item.getUnit() + ", " + item.getUnitType() + ", " + item.getFloor() + ", " + item.getProject());
                                    }
                                    arrayUnit = listUnits.toArray(arrayUnit);
                                /*if (arrayUnit.length > 0)
                                    showDialogApplicantDetail();*/
                                    ArrayAdapter<CharSequence> adapterProject = new ArrayAdapter<CharSequence>(getActivity(), R.layout.spinner_item_my_account, arrayUnit) {

                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            View v = super.getView(position, convertView, parent);
                                            ((TextView) v).setTypeface(AppUtils.getTypeface(getActivity()));
                                            return v;
                                        }

                                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                            View v = super.getDropDownView(position, convertView, parent);
                                            ((TextView) v).setTypeface(AppUtils.getTypeface(getActivity()));
                                            return v;
                                        }
                                    };

                                    adapterProject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnerProjectUnit.setAdapter(adapterProject);


                                    spinnerProjectUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            doGetApplicants(arrayListUnits.get(i).getUnitID());
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    cardView1stApp.setVisibility(View.VISIBLE);
                                    //cardView2ndApp.setVisibility(View.VISIBLE);
                                } else
                                    AppUtils.displayErrorMessage(getActivity(), response.body().getMessage());
                            } else
                                AppUtils.displayErrorMessageInternalServer(getActivity());
                        } else{}
                        //  DisplayEmptyDialog(getActivity(), -1);
                    }catch (Exception e){

                    }

                }

                @Override
                public void onFailure(Call<MyUnits> call, Throwable throwable) {
                    /* pDialog.dismissWithAnimation();*/
                    Log.e(getClass().getSimpleName(), "ERROR : " + throwable.getMessage());
                }
            });

            //
        } else
            displayAlertErrorNetwork(getActivity());
    }

    /**
     * This Method for get Applicants Details based on Unit ID.
     *
     * @param UnitID An Unit ID
     */
    private void doGetApplicants(final int UnitID) {
        if (AppUtils.isNetworkAvailable(getActivity())) {
            try {
                Uttils.showProgressDialoug(getActivity());
                Call<MyApplicant> doGetMyApplicant = mAPIInterface.getMyApplicantByUnit(UnitID);
                doGetMyApplicant.enqueue(new Callback<MyApplicant>() {
                    @Override
                    public void onResponse(Call<MyApplicant> call, Response<MyApplicant> response) {
                        Uttils.hideProgressDialoug();
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                arrayListMyApplicant.clear();
                                if (response.body().getStatusCode() == STATUS_SUCCESS) {
                                    arrayListMyApplicant.addAll(response.body().getData());
                                    RefreshApplicantDetails(UnitID);
                                } else {
                                    AppUtils.displayErrorMessage(getActivity(), response.body().getMessage());
                                }
                            } else {
                                AppUtils.displayErrorMessageInternalServer(getActivity());
                            }
                        } else if (getActivity() != null) {
                            // DisplayEmptyDialog(getActivity(), -1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MyApplicant> call, Throwable throwable) {
                        Uttils.hideProgressDialoug();
                        Log.e(getClass().getSimpleName(), "ERROR : " + throwable.getMessage());
                    }
                });
            }catch (Exception e){

            }

        } else {
            displayAlertErrorNetwork(getActivity());
        }
    }

    /**
     * This Method for Display and Refresh An Applicant Detail Content
     */
    private void RefreshApplicantDetails(int UnitID) {
        try {
            if (imgProfilePic1stApp != null) {

                if (arrayListMyApplicant.get(0) != null) {
                    Uri uriProfile1stApp = Uri.parse(arrayListMyApplicant.get(0).getProfileUrl());
                    imgProfilePic1stApp.setImageURI(uriProfile1stApp);
                    txtUserName1stApp.setText(" " + arrayListMyApplicant.get(0).getFirstName() + arrayListMyApplicant.get(0).getMiddleName() + arrayListMyApplicant.get(0).getLastName());
                    cardView1stApp.setVisibility(View.VISIBLE);

                    Glide.with(context)
                            .load(uriProfile1stApp)
                            .error(getResources().getDrawable(R.drawable.app_icon))
                            .into(iv_Profile);


                } else
                    cardView1stApp.setVisibility(View.GONE);

                if (arrayListMyApplicant.size() == 2 && arrayListMyApplicant.get(1) != null) {
                    Uri uriProfile2ndApp = Uri.parse(arrayListMyApplicant.get(1).getProfileUrl());
                    //   imgProfilePic2ndtApp.setImageURI(uriProfile2ndApp);
                    //   txtUserName2ndApp.setText(" " + arrayListMyApplicant.get(1).getFirstName() + arrayListMyApplicant.get(1).getMiddleName() + arrayListMyApplicant.get(1).getLastName());
                    //cardView2ndApp.setVisibility(View.VISIBLE);
                } else{}
                //   cardView2ndApp.setVisibility(View.GONE);

            }
        }catch (Exception e){

        }

       /* if (UnitID == 0 && arrayListMyApplicant.size() > 0) {
            if (arrayListMyApplicant.get(0).getProfileUrl() != null) {
                Uri uri = Uri.parse(arrayListMyApplicant.get(0).getProfileUrl());
                imgProfilePic.setImageURI(uri);
            }
            txtUserName.setText(arrayListMyApplicant.get(0).getFirstName() + " " + arrayListMyApplicant.get(0).getLastName());
        }*/
    }
}
