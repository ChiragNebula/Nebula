package com.nebulacompanies.nebula.Model.Guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sagar Virvani on 18-01-2018.
 */

public class NewsLetterList {

    @SerializedName("DocumentThumbnail")
    @Expose
    private String DocumentThumbnail;

    @SerializedName("DocumentName")
    @Expose
    private String DocumentName;

    @SerializedName("DocumentFilePath")
    @Expose
    private String DocumentFilePath;

    @SerializedName("DocumentFileSize")
    @Expose
    private String DocumentFileSize;

    public String getDocumentThumbnail() {
        return DocumentThumbnail;
    }

    public void setDocumentThumbnail(String documentThumbnail) {
        DocumentThumbnail = documentThumbnail;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getDocumentFilePath() {
        return DocumentFilePath;
    }

    public void setDocumentFilePath(String documentFilePath) {
        DocumentFilePath = documentFilePath;
    }

    public String getDocumentFileSize() {
        return DocumentFileSize;
    }

    public void setDocumentFileSize(String documentFileSize) {
        DocumentFileSize = documentFileSize;
    }
}
