package com.nebulacompanies.nebula.adapters;

/**
 * Created by Palak Mehta on 5/1/2017.
 */

public class DownloadProgressValues {

    String downloadedSize, totalSize;
    int percentage;

    public DownloadProgressValues(String downloadedSize, String totalSize, int percentage) {
        this.downloadedSize = downloadedSize;
        this.totalSize = totalSize;
        this.percentage = percentage;
    }

    public String getDownloadedSize() {
        return downloadedSize;
    }

    public void setDownloadedSize(String downloadedSize) {

        this.downloadedSize = downloadedSize;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }


}
