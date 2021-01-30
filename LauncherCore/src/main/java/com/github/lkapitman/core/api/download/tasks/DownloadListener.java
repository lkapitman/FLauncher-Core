package com.github.lkapitman.core.api.download.tasks;


import com.github.lkapitman.core.api.download.jobs.DownloadJob;

public interface DownloadListener {
    public void onDownloadJobFinished(DownloadJob job);
    public void onDownloadJobProgressChanged(DownloadJob job);
    public void onDownloadJobStarted(DownloadJob job);

}