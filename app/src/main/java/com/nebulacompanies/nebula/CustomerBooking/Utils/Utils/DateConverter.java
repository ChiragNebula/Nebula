package com.nebulacompanies.nebula.CustomerBooking.Utils.Utils;
import java.util.Date;

/**
 * Class : DateConverter
 * Details: Date Converter for converting Long Date to Time stamp format.
 * Create by : Jadav Chirag At NebulaApplication Infraspace LLP 1/17/2018.
 * Modification by :
 */

public class DateConverter {

    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp * 1000);
    }


    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
