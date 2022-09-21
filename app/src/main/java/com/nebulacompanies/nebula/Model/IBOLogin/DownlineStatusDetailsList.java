package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 19-01-2018.
 */

public class DownlineStatusDetailsList {
    @SerializedName("NVRank")
    @Expose
    private String Rank;

    @SerializedName("COUNT")
    @Expose
    private String Total;

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
