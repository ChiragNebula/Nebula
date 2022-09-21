package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Palak Mehta on 17-Jan-18.
 */

public class EDocumentList {

    @SerializedName("DocumentName")
    @Expose
    private String documentName;
    @SerializedName("DocumentThumbnail")
    @Expose
    private String documentThumbnail;
    @SerializedName("DocumentFilePath")
    @Expose
    private String documentFilePath;
    @SerializedName("DocumentFileSize")
    @Expose
    private String documentFileSize;

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentThumbnail() {
        return documentThumbnail;
    }

    public void setDocumentThumbnail(String documentThumbnail) {
        this.documentThumbnail = documentThumbnail;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    public String getDocumentFileSize() {
        return documentFileSize;
    }

    public void setDocumentFileSize(String documentFileSize) {
        this.documentFileSize = documentFileSize;
    }

}
