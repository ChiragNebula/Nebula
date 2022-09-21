package com.nebulacompanies.nebula.util;

/**
 * Created by Palak Mehta on 11-Jan-18.
 */


public class Constants {

    public static final int REQUEST_STATUS_CODE_SUCCESS = 1;
    public static final int REQUEST_STATUS_CODE_NO_RECORDS = 0;
    public static final int REQUEST_STATUS_CODE_ERROR = -1;
    public static final int REQUEST_STATUS_CODE_SERVICE_UNAVAILABLE = -2;

    public final static String NEBULA_URL = "http://203.88.139.169:9065";

    //INCOME LIST ID
    public static final int ID_RETAIL_INCOME = 2;
    public static final int ID_STAR_POOL_INCOME = 3;
    public static final int ID_THREE_STAR_POOL_INCOME = 4;
    public static final int ID_GENERATION_INCOME = 7;
    public static final int ID_GOLD_INCOME = 8;
    public static final int ID_SPOT_INCOME = 13;
    public static final int ID_BOOSTER_INCOME = 5;
    public static final int ID_SUPER_BOOSTER_INCOME = 6;
    public static final int ID_SU_BOOSTER_INCOME = 10;
    public static final int ID_CAR_PROGRAM_INCOME = 9;
    public static final int ID_DREAM_Volume_INCOME = 14;
    public static final int ID_LEADERSHIP_BONUS_INCOME = 15;
    public static final int ID_HOLIDAY_ACHIEVER = 16;
    public static final int ID_HOLIDAY_MINI_BONANZA = 17;
    public static final int ID_HOLIDAY_BONANZA = 18;
    public static final int ID_RANK_BONUS = 19;
    public static final int ID_SPONSOR_INCOME = 20;
    public static final int ID_HOLIDAY_REWARDS_INCOME = 1;
    public static final int ID_HOLIDAY_BOOSTER_INCOME = 1;

    //PROJECT LIST ID
    public static final int ID_AAVAAS_CHANGODER_COMPLEX = 7;
    public static final int ID_AAVAAS_CHANGODER = 5;
    public static final int ID_HAWTHORN_DWARKA = 6;
    public static final int ID_HOLIDAY = 8;
    public static final int ID_AAVAAS_HYDERABD = 4;
    public static final int ID_AAVAAS_CHENNAI = 10;
    public static final int ID_AAVAAS_SANAND = 12;

    //Holidays Packages
    public static final int HOLIDAYS_THAILAND = 8;
    public static final int HOLIDAYS_DWARKA = 9;
    //Old Id
    /* public static final int ID_AAVAAS_CHANGODER_COMPLEX=4;
     public static final int ID_AAVAAS_CHANGODER=2;
     public static final int ID_HAWTHORN_DWARKA=3;
     public static final int ID_HOLIDAY=5;
     public static final int ID_AAVAAS_HYDERABD=1;*/
    public static final String KEY_RANK_ID = "RankId";

    /* public static final int ID_AAVAAS_CHANGODER_COMPLEX=4;
     public static final int ID_AAVAAS_CHANGODER=2;
     public static final int ID_HAWTHORN_DWARKA=3;
     public static final int ID_HOLIDAY=5;
     public static final int ID_AAVAAS_HYDERABD=1;*/
    public static class WEB_SERVICES {

        public static final String WS_GET_VERSION = "/Api/Dashboard/VersionChecker";
        public static final String WS_GET_LOCATION = "/API/Dashboard/LocationUpdate";
        //Guest
        public static final String WS_GET_PROJECTS = "/API/Projects/CompanyProjects";
        public static final String WS_GET_SITE_PRODUCTS = "/API/SiteProgress/SiteProgressMaster";
        public static final String WS_GET_SITE_PROGRESS = "/API/SiteProgress/GetSiteCategory";
       // public static final String WS_GET_SITE_PROGRESS_IMAGES = "/API/SiteProgress/GetSiteProgress";
       public static final String WS_GET_SITE_PROGRESS_IMAGES = "/API/SiteProgress/GetSiteProgressWithPagination";
        public static final String WS_GET_EDOCUMENTS = "/API/EDocument/LoadDocument";
        public static final String WS_GET_VIDEOS = "/Api/Video/Company";
        public static final String WS_GET_NOTIFICATION = "API/Notifications/NotificationList";
        public static final String WS_GET_NEWSLETTERS = "/API/EDocument/GetEDocument?DocumentName=Newsletters";
        public static final String WS_GET_EVENTS = "/Api/Event/Category";
       // public static final String WS_GET_EVENT_DETAILS = "/Api/Event/EventByCategory";
        public static final String PERSONAL_LIST = "/API/Dashboard/UpdatePersonalNotification";
        public static final String WS_GET_EVENT_DETAILS = "/API/Event/EventByCategoryWithLikeWithPagination";
        //IBO Login
        public static final String WS_GET_LOGIN = "/API/Login/GetLogin";
        public static final String WS_GET_PRODUCTS = "/API/Inventory/Project";
        public static final String WS_GET_PRODUCT_MASTER = "/API/Inventory/ProductMaster";
        public static final String WS_GET_RATECHARTS = "/API/Downloads/MasterRateChart";
        public static final String WS_GET_RATECHARTS_DETAILS = "/API/Downloads/RateCharts";
        public static final String WS_GET_DOWNLINE_STATUS = "/API/Dashboard/GetDownlinelevelNo";
        public static final String WS_GET_LAST_TEN_JOIN = "/API/Dashboard/Getlast10User";
        public static final String WS_GET_MY_SALES = "/API/MySalePurchase/GetMyProductSale";
        public static final String WS_GET_MY_PURCHASES = "/API/MySalePurchase/GetMyProductPurchase";
        public static final String WS_GET_MY_SALES_DWARKA = "/API/MySalePurchase/GetMyProductSale";
        public static final String WS_GET_WEAKER_LEG_LIST = "/API/VactionLegDashboard/GetMainWeakerLegSaleDisplay";
        public static final String WS_GET_WEAKEST_LEG_LIST = "/API/VactionLegDashboard/GetOtherLegSaleDisplay";
        public static final String WS_GET_PLACEMENT_TREE_LIST = "/API/DownlineData/GetPlacement";
        public static final String WS_GET_SPONSER_TREE_LIST = "/API/DownlineData/GetSponser";
        public static final String WS_INCOME_LIST = "API/IncomeList/ShowIncomes";
        public static final String WS_GET_INCOMES = "/API/SubIncomes/ShowSubIncomes";

        public static final String WS_GET_HOLIDAY_ACHIEVER_BONUS = "/API/Holidayincome/flightDate";
        public static final String WS_GET_HOLIDAY_ACHIEVER_BONUS_SELECTION = "/API/HolidayIncome/PostflightDate";
        public static final String WS_GET_HOLIDAY_ACHIEVER = "/API/HolidayIncome/AchieverDetails";
        //Team update
        public static final String TEAM_LIST = "/API/Dashboard/UpdateTeamNotification";
        //Holiday
        public static final String WS_LONGING = "API/HolidayPackage/GetCustomerLogin";

        public static final String WS_CURRENCY_CONVERSION = "latest";

        public static final String WS_CURRENCY_CONVERTER = "/Api/Config/CurrencyConverter";


        public static final String WS_HOLIDAY_PACKAGE_LIST = "/API/Holidays/Packages";
        public static final String WS_HOLIDAY_PACKAGE_DETAIL = "/API/Holidays/PackageDetails";
        public static final String WS_HOLIDAY_HOTEL_INFO = "/API/Holidays/HotelInfo";
        public static final String WS_HOLIDAY_VIDEO_LIST = "/API/Holidays/HolidayVideo";


        public static final String WS_VIDEO_LIST = "api/HolidayVideo/SelectVideo";
        public static final String WS_WRITE_REVIEW = "/API/Holidays/SaveReview";
        public static final String WS_REVIEW_IMAGE_UPLOAD = "/API/Holidays/SaveImage";

        public static final String WS_SHOW_REVIEW = "/API/Holidays/ShowReview";
        public static final String WS_BOOKING_FORM_IBO_ID_LIST = "API/CheckIBO/CheckIBOID";

        public static final String WS_HOLIDAY_GALLERY_LIST = "/API/Holidays/HolidayGallery";
        public static final String WS_BOOKING_FORM_IBO_DETAILS = "/API/Holidays/GetSponsorDetails";


        //ON TESTING SERVER
        public static final String WS_REQUEST_CHECK_IBO_ID = "/API/ChennaiBooking/GetCheckDownlineIBO";
        public static final String WS_STATE_LIST = "/API/Config/StateInfo";
        public static final String WS_CITY_LIST = "/API/Config/CityInfo";
        public static final String WS_BANK_LIST = "/API/Config/BankInfo";
        public static final String WS_PAYMENT_MODE_LIST = "/API/Config/PaymentModeInfo";
        public static final String WS_SUBMIT_BOOKING_FORM = "/API/HolidayBooking/HolidayOnlineBookingForm";
        public static final String WS_NAME_CHANGE_REQUEST = "/API/HolidayBooking/NameChange";

        //Testing
        public static final String WS_GET_TOKEN = "/API/Token";
        public static final String WS_GET_RANK_AND_VOLUMES = "/API/Dashboard/RankAndVolumes";
        public static final String WS_GET_RANK_LIST = "/API/Dashboard/IBORankInfo?IBOKeyID=69b68abb-9bc0-45b6-aa6a-f8727f9609bf"; // + Constants.WEB_SERVICES_PARAM.KEY_IBO_KEY_ID; //69b68abb-9bc0-45b6-aa6a-f8727f9609bf
        public static final String WS_GET_RANK_HISTORY = "/API/Dashboard/RankHistory";
        public static final String WS_GET_INCOME_HISTORY = "/API/Dashboard/IncomeHistory";
        public static final String WS_GET_UPDATES = "/API/Dashboard/UpdateNotification";
        public static final String WS_GET_NEW_JOINEES = "/API/Dashboard/Last10Joinings";
        public static final String WS_GET_DOWNLINE_RANKS = "/API/Dashboard/DownlineRankCount";

        public static final String WS_GET_INCOME = "/API/Income/IncomeDetails";
        public static final String WS_GET_MY_PROFILE = "/API/Profile/Me";
        public static final String WS_GET_PROJECT_LIST = "/API/Inventory/Project";
        public static final String WS_GET_MYSALE_LIST = "/API/Sales/MySale";
        public static final String WS_GET_INCOME_LIST = "/API/Income/MyIncome";
        public static final String WS_GET_INCOME_SUMMARY = "/API/Income/IncomeSummary";
        public static final String WS_GET_MINI_BONANZA = "/API/HolidayIncome/MiniBonanzaDetails";
        public static final String WS_GET_MINI_BONANZA_LEG = "/API/Holidayincome/MiniBonanzaLegDetails";
        public static final String WS_GET_BONANZA = "/API/HolidayIncome/BonanzaDetails";
        public static final String WS_GET_BONANZA_LEG = "/API/Holidayincome/BonanzaLegDetails";

        public static final String WS_PROFILE = "/API/Profile/Me";
        public static final String WS_POST_CHANGE_PASSWORD = "/API/Profile/ChangePassword";
        public static final String WS_POST_UPDATE_PROFILE_PIC = "/API/Profile/UpdateProfilePic";
        public static final String WS_POST_IBO_GEO_DETAIL = "/API/Dashboard/DownlineLocator";
        public static final String WS_GET_ARRIVAL_DATE = "/API/Holidays/GetArrivalDate";
        public static final String WS_GET_DATE_CHANGE_LIST = "/API/Holidays/GetArrivalDateChangeBind\n";
        public static final String WS_GET_CUSTOMER_DETAIL = "/API/Holidays/GetCustomerDetail";
        public static final String WS_POST_SUBMIT_ARRIVAL_DATE = "/API/HolidayBooking/UpdateArrivalDate";
        public static final String WS_POST_CANCEL_DATE = "/API/HolidayBooking/CancelHolidayPackage";
        public static final String WS_POST_CHANGE_ARRIVAL_DATE = "/API/HolidayBooking/ArrivalDateChange";
        public static final String WS_GET_HOLIDAY_CHARGES = "/API/Holidays/HolidayCRCharges";
        public static final String WS_GET_HOLIDAY_NAME_CHARGES = "/API/Holidays/HolidayNameChangeCharges";
        public static final String PROJECT_LIST = "/API/ProjectFacility/ProjectFacility2";
        public static final String WS_GET_IBO_LIST_SPONSOR = "/API/MyDownline/SponsorTree";
        public static final String WS_GET_IBO_LIST_PLACEMENT = "/API/MyDownline/PlacementTree";
        public static final String WS_GET_MYPURCHASE_LIST = "/API/Sales/MyPurchase";
        public static final String WS_GET_DOWNLINE_IBO_RANK_LIST = "/API/Dashboard/DownlineIBORankCountWiseList";


        //Holiday Package Thailand
        public static final String WS_TERMS_AND_CONDITIONS_THAILAND = "/API/Holidays/TermsAndCondition?projectid=" + Constants.HOLIDAYS_THAILAND;
        public static final String WS_FAQS_THAILAND = "api/Holidays/FaqsQuestion?projectid=" + Constants.HOLIDAYS_THAILAND;
        public static final String WS_BACKGROUND_THAILAND = "/Api/Holidays/TermsConditionAndFaqsBackGroundImage?projectid=" + Constants.HOLIDAYS_THAILAND;
        public static final String WS_ADVERTISEMENT_IMAGES_LIST_THAILAND = "/API/Holidays/AdvertisementBanner?projectid=" + Constants.HOLIDAYS_THAILAND;// "API/AdvertisementBanner/AdvertisementBannerImageList";

        //Holiday Package Dwarka
        public static final String WS_TERMS_AND_CONDITIONS_DWARKA = "/API/Holidays/TermsAndCondition?projectid=" + Constants.HOLIDAYS_DWARKA;
        public static final String WS_FAQS_DWARKA = "api/Holidays/FaqsQuestion?projectid=" + Constants.HOLIDAYS_DWARKA;
        public static final String WS_GET_CR_UPDATES = "/API/HolidayIncome/GetRequestStatus";
        public static final String WS_BACKGROUND_DWARKA = "/Api/Holidays/TermsConditionAndFaqsBackGroundImage?projectid=" + Constants.HOLIDAYS_DWARKA;
        public static final String WS_ADVERTISEMENT_IMAGES_LIST_DWARKA = "/API/Holidays/AdvertisementBanner?projectid=" + Constants.HOLIDAYS_DWARKA;// "API/AdvertisementBanner/AdvertisementBannerImageList";
        public static final String WS_DOCUMENT_LIST_DWARKA= "/API/Holidays/EdocumentHoliday";
        public static final String WS_HOLIDAY_LIST= "/Api/Holidays/ImageForProject";
        // For Customer and IBO
        public static final String WS_FORGOT_PASSWORD = "/API/Profile/ForgotPassword";
        public static final String WS_POST_TOKEN_KEY = "/API/Notifications/UpdateDeviceToken";
        public static final String WS_GET_PROJECT = "/API/Projects/ProjectInfo";
        public static final String WS_GET_SEND_PASSOWRD = "/API/Profile/CustomerForgotPassword";

        // Forgot API
        public static final String FORGOT_IBO = "/API/ForgotPassword/CheckUserDetails";
        public static final String FORGOT_OTP_IBO = "API/ForgotPassword/OTPSendIBO";
        public static final String FORGOT_OTP_VERIFY_IBO = "/API/ForgotPassword/ForgotPasswordOTP";

        public static final String WS_GET_UNIT_LIST = "/API/Profile/CustForgotPassword";
        public static final String WS_GET_CUSTOMER_SEND_PASSOWRD = "/API/Profile/CustomerResetPassword";
        public static final String FORGOT_OTP_VERIFY_CUSTMOER = "/API/Profile/CustomerUpdatePassword";


        public static final String WS_Login = "/API/CustomerPanel/Login";

    }

    public static class WEB_SERVICES_PARAM {

        public static final String KEY_BlockId = "BlockId";
        public static final String KEY_FlatNo = "FlatNo";

        public static final String KEY_ADMIN_LOGIN = "AdminLogin";
        public static final String KEY_USERNAME = "Username";
        public static final String KEY_PASSWORD = "Password";
        public static final String KEY_PASSWORD_HOLIDAY = "Password";
        public static final String KEY_IMEI_NUMBER = "IMEINumberID";
        public static final String KEY_PROJECT_ID = "ProjectId";
        public static final String KEY_EVENT_ID = "EventId";
        public static final String KEY_STATUS = "Status";
        public static final String KEY_SITE_ID = "SiteID";
        public static final String KEY_PROJECT_NAME = "ProjectId";
        public static final String KEY_MONTH = "Month";
        public static final String KEY_YEAR = "Year";
        public static final String KEY_APPROVED = "approved";
        public static final String KEY_DOWNLINE_STATUS_ID = "MemberIDlevelNo";
        public static final String KEY_LAST_TEN_JOIN = "MemberID10User";
        public static final String KEY_MY_SALES_IBOID = "SaleMemberID";
        public static final String KEY_MY_IBO_ID = "MemberID";
        public static final String KEY_MY_INCOME_ID = "IncomeId";
        public static final String KEY_WEAKER_ID = "MainWeakerMemberID";
        public static final String KEY_OTHER_LEG_ID = "IBOId";
        public static final String KEY_MAIN_LEG_MEMBER_ID = "MainLegMemberID";
        public static final String KEY_WEAKER_LEG_MEMBER_ID = "WeakerLegMemberID";
        public static final String KEY_PLACEMENT_TREE = "Tree";
        public static final String KEY_SPONSER_TREE_ID = "MemberIDSponser";
        public static final String KEY_DOCUMENT_NAME = "EDocumentType";
        public static final String KEY_IBO_ID = "MemberID";
        public static final String KEY_IBO_ID_ = "IBOId";
        public static final String KEY_COUNTRY_NAME = "CountryName";
        public static final String KEY_RATE_CHART_ID = "RateChartId";

        public static final String KEY_TOKEN = "TokenKey";
        public static final String KEY_DEVICE_ID = "DeviceId";
        public static final String KEY_IMEI1 = "IMEI1";
        public static final String KEY_IMEI2 = "IMEI2";
        public static final String KEY_USERID = "UserId";

        public static final String KEY_ID = "id";
        public static final String KEY_FLAG = "flag";


        public static final String KEY_EVENT_NAME = "Category";

        //Holiday

        public static final String KEY_USER_NAME = "UserName";
        public static final String KEY_USER_NAME_HOLIDAY = "UserName";

        public static final String KEY_BASE_CURRENCY = "base";
        public static final String KEY_SYMBOL_CURRENCY = "symbols";
        public static final String KEY_HOTEL_ID = "HotelId";
        public static final String KEY_PACKAGE_ID = "PackageId";
        // Modified By Sagar.
        public static final String KEY_TITLE = "Title";
        public static final String KEY_COMMENT = "Comment";
        public static final String KEY_RATING = "Rating";
        public static final String KEY_CUSTOMERID = "SaleId";
        public static final String KEY_REVIEWID = "ReviewId";
        // Modified by Palak Mehta
        public static final String KEY_INCOME_ID = "IncomeId";
        public static final String KEY_AUTHOR_ID = "OtherIBOID";
        public static final String KEY_COUNTRY_ID = "CountryId";
        public static final String KEY_STATE_ID = "StateId";

        //Testing
        /*public static final String KEY_USERNAME_ = "username";
        public static final String KEY_PASSWORD_ = "password";
        public static final String KEY_GRANT_TYPE = "grant_type";*/

        public static final String KEY_USERNAME_ = "username";
        public static final String KEY_PASSWORD_ = "password";
        public static final String KEY_GRANT_TYPE = "grant_type";

        public static final String KEY_TOKEN_TYPE = "token_type";
        public static final String KEY_ACCESS_TOKEN = "access_token";
        public static final String KEY_REFRESH_TOKEN = "refresh_token";
        public static final String KEY_ERROR_DECRIPTION = "error_description";
        public static final String KEY_ROLE = "Role";
        public static final String KEY_DISPLAYNAME = "DisplayName";
        public static final String KEY_IBO_KEY_ID = "IBOKeyId";

        public static final String KEY_INCOME_TYPE = "IncomeType";
        public static final String KEY_UPDATE_PIC_TYPE = "EncodedImage";
        public static final String KEY_OLD_PASSWORD_TYPE = "OldPassword";
        public static final String KEY_NEW_PASSWORD_TYPE = "NewPassword";
        public static final String KEY_CATEGORY = "Category";
        public static final String KEY_PAGE_LENGTH = "PageLength";
        public static final String KEY_START_INDEX = "StartIndex";
        public static final String KEY_RANK_ID = "RankId";
        public static final String KEY_SEARCH_TEXT = "SearchText";
        public static final String KEY_PAGE_INDEX = "PageIndex";

        //Added By Jadav Chirag

        public static final String KEY_LATITUDE = "Latitude";
        public static final String KEY_LONGITUDE = "Longitude";
        public static final String KEY_VIDEO_TYPE = "VideoType";

        public static final String KEY_PRODUCT_SALE_ID = "ProductSaleID";
        public static final String KEY_CHARGE_AMOUNT = "ChargeAmount";
        public static final String KEY_ARRIVAL_DATE = "ArrivalDate";

        public static final String KEY_SALE_IBO_KEY_ID = "SaleIBOKeyId";
        public static final String KEY_FIRST_NAME = "FirstName";
        public static final String KEY_LAST_NAME = "LastName";
        public static final String KEY_GENDER = "Gender";
        public static final String KEY_MARITAL_STATUS = "MaritalStatus";
        public static final String KEY_DOB = "DOB";
        public static final String KEY_ADDRESS = "AddressLine1";
        public static final String KEY_PHONE_NUMBER = "PhoneNumber";
        public static final String KEY_ALTERNATE_NUMBER = "AlternateMobile";
        public static final String KEY_STATE = "State";
        public static final String KEY_CITY = "City";
        public static final String KEY_COUNTRY = "Country";
        public static final String KEY_PINCODE = "ZIPCode";
        public static final String KEY_EMAIL = "Email";
        public static final String KEY_PASSPORT_NO = "PassportNo";
        public static final String KEY_PASSPORT_EXP_DATE = "PassportExpiryDate";
        public static final String KEY_EMERG_CONTACT_NAME = "EmergencyContactName";
        public static final String KEY_EMERG_CONTACT_NUMBER = "EmergencyContact";
        public static final String KEY_PASSPORT_PROOF = "PassportProof";
        public static final String KEY_ID_PROOF = "IDProof";
        public static final String KEY_IBO_LEG_KEY = "LegIBOKeyId";

        public static final String KEY_BLOCK_NO_KEY = "BlockNo";
        public static final String KEY_FLAT_KEY = "flat";

        //Forgot IBO Password
        public static final String KEY_IBO_ID_FORGOT_PASSWORD = "IBOID";

        //Forgot OTP VERIFY IBO Password
        public static final String KEY_IBO_PHONE_NUMBER_FORGOT_PASSWORD_VERIFY = "PhoneNumber";
        public static final String KEY_IBO_OTP_FORGOT_PASSWORD_VERIFY = "OTP";
        public static final String KEY_IBO_ID_FORGOT_PASSWORD_VERIFY = "UserName";
        public static final String KEY_IBO_CODE_FORGOT_PASSWORD_VERIFY = "Code";
        public static final String KEY_IBO_PASSWORD_FORGOT_PASSWORD_VERIFY = "Password";

      //add sagar
      //holiday Key
        public static final String KEY_PROJECTS_ID = "projectid";
        public static final String KEY_PHONE_EMAIL = "CustPhoneNumberEmail";
        public static final String KEY_UNIT_ID = "unitid";
        public static final String KEY_CUSTMOER_NAME = "customerUserName";
        public static final String KEY_NEW_PASSWORD = "newPassword";

    }

}
