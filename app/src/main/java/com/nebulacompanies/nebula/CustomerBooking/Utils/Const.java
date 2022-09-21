package com.nebulacompanies.nebula.CustomerBooking.Utils;


/**
 * Class : Const
 * Details: This Class for Declare public application variables.
 * Create by : Jadav Chirag At NebulaApplication Infraspace LLP 08-09-2017
 * Modification by :
 */

public class Const {

    public static final String CHANNEL_ID = "CustomerBooking";
    public static final String CHANNEL_NAME = "CustomerBookingNotification";
    public static final String CHANNEL_DESCRIPTION = "http://www.groupnebula.com/";

    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAIL = -1;

    public static final int KEY_STATUS_COMPLETED = 1;
    public static final int KEY_STATUS_PENDING = 2;
    public static final int KEY_STATUS_REJECTED = 3;

    public static final String KEY_STATUS = "StatusCode";
    public static final String KEY_MESSAGE = "Message";

    public static final String KEY_TITLE = "TITLE";

    public static final String KEY_CHEQUE = "Cheque";
    public static final String KEY_ECS = "ECS";
    public static final String KEY_ACCOUNT_TRANSFER = "Account Transfer";

    public static final int RC_PERSONAL_DETAILS = 0;
    public static final int RC_COMMUNICATION_DETAILS = 1;
    public static final int RC_RESIDENTIAL_DETAILS = 2;
    public static final int RC_TRAVEL_REFERENCES_DETAILS = 3;
    public static final int RC_PASSPORT_DETAILS = 4;

    public static final int REQUEST_STATUS_CODE_SUCCESS = 1;
    public static final int REQUEST_STATUS_CODE_FAIL = 0;
    public static final int REQUEST_STATUS_CODE_NO_RECORDS = 0;
    public static final int REQUEST_STATUS_CODE_ERROR = -1;
    public static final int REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE = -2;

    public static final int REQUEST_STATUS_CODE_ID_DOCUMENT = 1;
    public static final int REQUEST_STATUS_CODE_ADDRESS_DOCUMENT = 2;
    public static final int REQUEST_STATUS_CODE_ID_CAPTURE = 3;
    public static final int REQUEST_STATUS_CODE_ADDRESS_CAPTURE = 4;
    public static final int REQUEST_STATUS_CODE_APPLICANT_PHOTO_CAPTURE = 5;

    //TODO : Notification Event Const.
    public static final int EVENT_REMINDER = 28641;
    public static final String EVENT_EVENT = "Others";
    public static final int EVENT_NOTIFICATION = 28643;
    public static final String EVENT_EMI_LAPSED = "LapsedPayment";
    public static final String EVENT_EMI_UPCOMING = "UpcomingPayment";

    public static final String KEY_IS_REDIRECT = "IsRedirect";
    public static final String KEY_URL = "ProjectICON";
    public static final String KEY_REDIRECT_ACTION = "RedirectAction";
    public static final String KEY_EVENT_TYPE = "EventType";
    public static final String KEY_BODY = "Body";
    public static final String KEY_NOTIFICATION_TITLE = "Title";


    public static final int ID_AAVAAS_CHANGODER_COMPLEX = 7;
    public static final int ID_AAVAAS_CHANGODER = 5;
    public static final int ID_HAWTHORN_DWARKA = 6;
    public static final int ID_HOLIDAY = 8;
    public static final int ID_AAVAAS_HYDERABD = 4;
    public static final int ID_AAVAAS_CHENNAI = 10;

    public final static String BASE_URL = "http://203.88.139.169:9065/API/";// "http://192.168.3.100:9065/API/";

    public static class WEB_SERVICES {
        public static final String WS_PROJECT_DETAILS = "/API/Config/ProjectInfo";
        public static final String WS_REQUEST_BOOKING = "/API/BookingRequest/RequestInitiate";
        public static final String WS_REQUEST_UPLOAD_DOCUMENT = "/API/BookingRequest/UploadDocuments";
        public static final String WS_REQUEST_TOKEN = "/API/Token";
        public static final String WS_LOGIN = "";
        public static final String WS_CHECK_STATUS_SERVICE_REQUEST = "/API/CustomerPanel/CheckSRNStatus";

        public static final String WS_CUSTOMER_DASH_BOARD = "/API/CustomerPanel/CustomerDashboard";
        public static final String WS_MY_SRN_STATUS = "/API/CustomerPanel/MySRNStatus";
        public static final String WS_UNIT_LIST = "/API/CustomerPanel/ProjectUnits";
        public static final String WS_INSTALLMENT_LIST_BY_UNIT = "/API/CustomerPanel/ProjectEMIDetail";
        public static final String WS_MY_UNITS = "/API/CustomerPanel/MYUnits";

        public static final String WS_APPLICANT_DETAILS = "/API/CustomerPanel/ApplicantDetails";
    }

    public static class WEB_SERVICES_PARAM {

        public static final String KEY_USER_NAME = "username";
        public static final String KEY_USER_PROFILE_NAME = "userprofilename";
        public static final String KEY_PASSWORD = "password";
        public static final String KEY_GRANT_TYPE = "grant_type";

        public static final String KEY_FA_PHOTO = "FAPhoto";
        public static final String KEY_FA_ID_PROOF = "FAIDProof";
        public static final String KEY_FA_ADDRESS_PROOF = "FAAddressProof";

        public static final String KEY_SA_PHOTO = "SAPhoto";
        public static final String KEY_SA_ID_PROOF = "SAIDProof";
        public static final String KEY_SA_ADDRESS_PROOF = "SAAddressProof";
        public static final String KEY_SRN = "SRN";
        public static final String KEY_DOCUMENT_CONFIG = "DocumentConfig";

        public static final String KEY_TOKEN_TYPE = "token_type";
        public static final String KEY_ACCESS_TOKEN = "access_token";

        public static final String KEY_UNIT_ID = "SaleID";
        public static final String KEY_SRN_NO = "SRNNo";
        public static final String KEY_PROJECT_URL = "ProjectURL";
        public static final String KEY_PROJECT_NAME = "ProjectId";
        public static final String KEY_SITE_ID = "SiteID";
        public static final String KEY_MONTH = "Month";
        public static final String KEY_YEAR = "Year";
        public static final String KEY_APPROVED = "approved";
        public static final String KEY_STATUS = "Status";
        public static final String KEY_FCM_TOKEN = "FCMToken";

    }
}
