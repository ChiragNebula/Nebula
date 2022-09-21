package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 25-01-2018.
 */

public class VacationBoosterList {

    @SerializedName("BookingID")
    @Expose
    private String BookingID;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("ArrivalDate")
    @Expose
    private String ArrivalDate;

    @SerializedName("PackageName")
    @Expose
    private String PackageName;


    @SerializedName("PaymentDate")
    @Expose
    private String PaymentDate;

    public String getBookingID() {
        return BookingID;
    }

    public void setBookingID(String bookingID) {
        BookingID = bookingID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }
}
