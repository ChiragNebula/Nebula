package com.nebulacompanies.nebula;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Palak Mehta on 7/12/2016.
 */
public class Config {

    //public static String FONT_STYLE = "fonts/FUTURASTD-MEDIUM_7.OTF";
    public static String FONT_STYLE = "fonts/Calibri.ttf";
    public static String FONT_STYLE_BOLD = "fonts/Calibri_Bold.ttf";
    public static String FONT_STYLE_BOLD_LATO = "fonts/Lato-Bold.ttf";
    public static String FONT_STYLE_FONTASWOME = "fonts/FontAwesome.ttf";
    public static int NOTIFICATION_COUNT = 0;
    public static String AAVAAS_AHMEDABAD = "Aavaas (Changodar), Ahmedabad";
    public static String HAWTHORN_DWARKA = "Hawthorn Suites, Dwarka";
    public static String AAVAAS_HYDERABAD = "Aavaas, Hyderabad";
    public static String HOLIDAY = "Holiday";
    public static boolean PRODUCTS_SELECTED = false;
    public static boolean MY_SALES_SELECTED = false;
    public static boolean NOTIFICATIONS = false;
    public static boolean HOME = false;
    public static boolean isFirstTime = true;

    public static String NEBULA_COMPANIES = "https://nebulacompanies.net";
    //public static String NEB_API = "http://neb.hksolutions.in";
    public static String NEB_API = "https://nebulacompanies.net";
    //public static String NEB_VACATION_API = "http://192.168.3.100:9064";
    public static String NEB_VACATION_API = "https://nebulacompanies.net";


    // LIVE URL
  // public static String TESTING_API = "http://151.106.35.35:9080/";
   public static String TESTING_API = "https://nebulacompanies.net";
  //  public static String TESTING_API = "http://203.88.139.169:9064";

    public static String RANK_ID = "";
//
     // LOCAL URL
    //public static String TESTING_API = "http://192.168.3.100:9064";

    //live url use for
    //public static String TESTING_API = "http://203.88.139.169:9064";

    public static String GET_STATE_LIST = "http://neb.hksolutions.in/Documents/cities.txt";

    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String SHARED_PREF = "firebase";
    public static String REGISTERED_TOKEN = "";

    public static final int PROGRESS = 0;
    public static Date sentDate = null;

    public static ArrayList<Long> myDownloadReference_videos = new ArrayList<>();

    public static int PRIZE_AMOUNT = 0;
    public static int SALE_COUNT = 0;

    public static boolean isForm1Valid = false;
    public static boolean isForm2Valid = false;
    public static boolean isForm3Valid = false;
    public static boolean isForm4Valid = false;

    public static String CUSTOMER_LOGIN_ID = "";

    public static String CUSTOMER_LOGIN_ID_DWARKA = "";

    public static String ACTION_PREVIOUS = "PREVIOUS";
    public static String ACTION_NEXT = "NEXT";
    public static String ACTION_SUMMARY = "SUMMARY";



    public static String OTP = "";

    /*public static String[] rankValues = {
            "Associate",
            "IBO",
            "Bronze",
            "Silver",
            "Gold",
            "Platinum",
            "Star Platinum",
            "2 Star Platinum",
            "3 Star Platinum",
            "4 Star Platinum",
            "5 Star Platinum",
            "6 Star Platinum",
            "7 Star Platinum",
            "Crown",
            "Noble Crown",
            "Royal Crown",
    };*/


    public static String[] rankValues = {
            "Associate",
            "IBO",
            "Bronze",
            "Silver",
            "Gold",
            "Platinum",
            "Star Platinum",
            "2 Star Platinum",
            "3 Star Platinum",
            "4 Star Platinum",
            "5 Star Platinum",
            "Brand Ambassador",
            "Universal Brand Ambassador",
    };

   public static int[] rankImages = {
            R.drawable.member,
            R.drawable.ibo,
            R.drawable.bronze,
            R.drawable.silver,
            R.drawable.gold,
            R.drawable.platinum,
            R.drawable.star_platinum,
            R.drawable.two_star_platinum,
            R.drawable.three_star_platinum,
            R.drawable.four_star_platinum,
            R.drawable.five_star_platinum,
            R.drawable.six_star_platinum,
            R.drawable.seven_star_platinum,
            R.drawable.crown,
            R.drawable.noble_crown,
            R.drawable.royal_crown,
    };

    public static double[] incomeValues = {
            Double.parseDouble("100000"),
            Double.parseDouble("500000"),
            Double.parseDouble("1000000"),
            Double.parseDouble("2500000"),
            Double.parseDouble("5000000"),
            Double.parseDouble("10000000"),
            Double.parseDouble("25000000"),
            Double.parseDouble("50000000"),
            Double.parseDouble("100000000"),
            Double.parseDouble("250000000")
    };

    public static int[] incomeImages = {
            R.drawable.one_lakh,
            R.drawable.five_lakhs,
            R.drawable.ten_lakhs,
            R.drawable.twentyfive_lakhs,
            R.drawable.fifty_lakhs,
            R.drawable.one_crore,
            R.drawable.twopfive_crores,
            R.drawable.five_crores,
            R.drawable.ten_crores,
            R.drawable.twentyfive_crores
    };

    public static String[] incomeValuesRs =
            {"₹1,00,000", "₹5,00,000", "₹10,00,000", " ₹25,00,000", "₹50,00,000", "₹100,00,000", "₹2,50,00,000", "₹5,00,00,000", " ₹10,00,00,000", "₹25,00,00,000"};

    public static String[] incomeValues1 =
            {"On reaching", "On reaching", "On reaching", "On reaching", "On reaching", "On reaching", "On reaching", "On reaching", "On reaching", "On reaching"};

    public static int[] mainLeg = {10, 30, 90, 270, 810};
    public static int[] weakerLeg = {10, 30, 90, 270, 810};
    public static int[] WeakestLeg = {9, 29, 89, 269, 809};
    public static int[] moreinfo = {
            R.drawable.ic_info_black,
            R.drawable.ic_info_black,
            R.drawable.ic_info_black,
            R.drawable.ic_info_black,
            R.drawable.ic_info_black,
    };

    public static NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    public static String title ="";
    public static String content ="";
    public static String imageUrl ="";
}
