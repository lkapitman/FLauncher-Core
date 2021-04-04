package com.github.lkapitman.downloader.download.tasks;


import com.github.lkapitman.downloader.download.jobs.DownloadJob;

public interface DownloadListener {
    public void onDownloadJobFinished(DownloadJob job);
    public void onDownloadJobProgressChanged(DownloadJob job);
    public void onDownloadJobStarted(DownloadJob job);

}