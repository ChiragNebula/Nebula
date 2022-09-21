package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 29-01-2018.
 */

public class LegList {

    @SerializedName("MemberID")
    @Expose
    private String IBOID;

    @SerializedName("MemberName")
    @Expose
    private String IBO;

    @SerializedName("Entryno")
    @Expose
    private String BookingID;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    public String getIBOID() {
        return IBOID;
    }

    public void setIBOID(String IBOID) {
        this.IBOID = IBOID;
    }

    public String getIBO() {
        return IBO;
    }

    public void setIBO(String IBO) {
        this.IBO = IBO;
    }

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
}
