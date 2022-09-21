package com.nebulacompanies.nebula.Network;




import static com.nebulacompanies.nebula.CustomerBooking.Utils.Const.WEB_SERVICES_PARAM.KEY_GRANT_TYPE;
import static com.nebulacompanies.nebula.CustomerBooking.Utils.Const.WEB_SERVICES_PARAM.KEY_PASSWORD;
import static com.nebulacompanies.nebula.CustomerBooking.Utils.Const.WEB_SERVICES_PARAM.KEY_UNIT_ID;
import static com.nebulacompanies.nebula.CustomerBooking.Utils.Const.WEB_SERVICES_PARAM.KEY_USER_NAME;

import com.google.gson.JsonObject;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Const;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.MyApplicant;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.MyUnits;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.ProjectUnits;
import com.nebulacompanies.nebula.CustomerBooking.Utils.Model.UnitInstallment;
import com.nebulacompanies.nebula.Model.Guest.CompanyProjectDetailsModel;
import com.nebulacompanies.nebula.Model.Guest.EDocuments;
import com.nebulacompanies.nebula.Model.Guest.Events;
import com.nebulacompanies.nebula.Model.Guest.NewSiteProgressImages;
import com.nebulacompanies.nebula.Model.Guest.NewSubEventDetails;
import com.nebulacompanies.nebula.Model.Guest.Notification;
import com.nebulacompanies.nebula.Model.Guest.Projects;
import com.nebulacompanies.nebula.Model.Guest.SiteProducts;
import com.nebulacompanies.nebula.Model.Guest.SiteProgress;
import com.nebulacompanies.nebula.Model.Guest.VersionCheck;
import com.nebulacompanies.nebula.Model.Guest.Videos;
import com.nebulacompanies.nebula.Model.Login.getLoginResonse;
import com.nebulacompanies.nebula.util.Constants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Class : APIInterface
 * Details:
 * Create by : Palak Mehta At Nebula Infra space LLP 09-01-2018
 * Modification by :
 */
public interface APIInterface {

    @GET(Constants.WEB_SERVICES.WS_GET_VERSION)
    Call<VersionCheck> getVersion();

    /**
     * This REST API Method for get authorization token
     *
     * @param userName  An Authorized User Name.
     * @param password  An Authorized Password.
     * @param grantType The Password Grant Type Value
     * @return The json object if authorized else return error.
     */
    @FormUrlEncoded
    @POST(Const.WEB_SERVICES.WS_REQUEST_TOKEN)
    Call<JsonObject> getUpdateToken(@Field(KEY_USER_NAME) String userName,
                                    @Field(KEY_PASSWORD) String password,
                                    @Field(KEY_GRANT_TYPE) String grantType);

    /**
     * This REST API Method for get Unit Details List based on User.
     *
     * @return An Custom Array List of Unit Details.
     */
    @GET(Const.WEB_SERVICES.WS_UNIT_LIST)
    Call<ProjectUnits> getUnitListByUser();

    /**
     * This REST API Method for Getting Booked Unit Details of an applicant.
     *
     * @param unitId An Unit Id.
     * @return The Applicant Details it Unit.
     */
    @GET(Const.WEB_SERVICES.WS_APPLICANT_DETAILS)
    Call<MyApplicant> getMyApplicantByUnit(@Query(KEY_UNIT_ID) int unitId);



    /**
     * This REST API Method for getting the details UNIT Based on User Login.
     *
     * @return An Array List of My Units details.
     */
    @GET(Const.WEB_SERVICES.WS_MY_UNITS)
    Call<MyUnits> getMYUnits();


    /**
     * This REST API Method for get Installment Details List By Unit.
     *
     * @param unitId An Unit ID.
     * @return An Array List of Unit Installment.
     */
    @GET(Const.WEB_SERVICES.WS_INSTALLMENT_LIST_BY_UNIT)
    Call<UnitInstallment> getInstallmentListByUnit(@Query(KEY_UNIT_ID) int unitId);

    @GET(Constants.WEB_SERVICES.WS_GET_SITE_PRODUCTS)
    Call<SiteProducts> getSiteProductList();

    @POST(Constants.WEB_SERVICES.PROJECT_LIST)
    Call<CompanyProjectDetailsModel> getProject(@Query(Constants.WEB_SERVICES_PARAM.KEY_PROJECTS_ID) int id);



    @GET(Constants.WEB_SERVICES.WS_GET_EVENTS)
    Call<Events> getEventList();



    @GET(Constants.WEB_SERVICES.WS_GET_EVENT_DETAILS)
    Call<NewSubEventDetails> getEvent(@Query(Constants.WEB_SERVICES_PARAM.KEY_PAGE_INDEX) int PageIndex,
                                      @Query(Constants.WEB_SERVICES_PARAM.KEY_PAGE_LENGTH) int PageLength,
                                      @Query(Constants.WEB_SERVICES_PARAM.KEY_EVENT_NAME) String EventName,
                                      @Query(Constants.WEB_SERVICES_PARAM.KEY_USERID) String userId);

    @GET(Constants.WEB_SERVICES.WS_GET_SITE_PROGRESS_IMAGES)
    Call<NewSiteProgressImages> getSiteProgressImages(@Query(Constants.WEB_SERVICES_PARAM.KEY_PAGE_INDEX) int PageIndex,
                                                      @Query(Constants.WEB_SERVICES_PARAM.KEY_PAGE_LENGTH) int PageLength,
                                                      @Query(Constants.WEB_SERVICES_PARAM.KEY_PROJECT_ID) Integer SiteID,
                                                      @Query(Constants.WEB_SERVICES_PARAM.KEY_MONTH) Integer Month,
                                                      @Query(Constants.WEB_SERVICES_PARAM.KEY_YEAR) String Year);

    @GET(Constants.WEB_SERVICES.WS_GET_SITE_PROGRESS)
    Call<SiteProgress> getSiteProgressList(@Query(Constants.WEB_SERVICES_PARAM.KEY_PROJECT_ID) Integer ProjectName);

    @POST(Constants.WEB_SERVICES.WS_Login)
    Call<getLoginResonse> getUserLogin(@Query(Constants.WEB_SERVICES_PARAM.KEY_BlockId) String blockId, @Query(Constants.WEB_SERVICES_PARAM.KEY_FlatNo) String flatNo);

    @GET(Constants.WEB_SERVICES.WS_GET_NOTIFICATION)
    Call<Notification> getNotificationList();


    @GET(Constants.WEB_SERVICES.WS_GET_EDOCUMENTS)
    Call<EDocuments> getEDocuments(@Query(Constants.WEB_SERVICES_PARAM.KEY_DOCUMENT_NAME) String DocumentName);

    @GET(Constants.WEB_SERVICES.WS_GET_VIDEOS)
    Call<Videos> getVideos();

    //Guest
    @GET(Constants.WEB_SERVICES.WS_GET_PROJECTS)
    Call<Projects> getProjectList();


}
