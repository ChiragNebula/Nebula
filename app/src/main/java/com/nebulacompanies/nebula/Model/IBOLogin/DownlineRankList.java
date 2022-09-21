package com.nebulacompanies.nebula.Model.IBOLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 22-Feb-18.
 */

public class DownlineRankList {

    @SerializedName("Rank")
    @Expose
    private String Rank;
    @SerializedName("Count")
    @Expose
    private Integer Count;

    @SerializedName("RankId")
    @Expose
    private Integer RankId;

    @SerializedName("RankIcon")
    @Expose
    private String RankIcon;


    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public Integer getRankId() {
        return RankId;
    }

    public void setRankId(Integer rankId) {
        RankId = rankId;
    }

    public String getRankIcon() {
        return RankIcon;
    }

    public void setRankIcon(String rankIcon) {
        RankIcon = rankIcon;
    }
}
