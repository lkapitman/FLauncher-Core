package com.github.lkapitman.downloader.download.tasks;


import com.github.lkapitman.downloader.download.jobs.DownloadJob;

/**
 * The interface Download listener.
 */
public interface DownloadListener {
    /**
     * On download job finished.
     *
     * @param job the job
     */
    public void onDownloadJobFinished(DownloadJob job);

    /**
     * On download job progress changed.
     *
     * @param job the job
     */
    public void onDownloadJobProgressChanged(DownloadJob job);

    /**
     * On download job started.
     *
     * @param job the job
     */
    public void onDownloadJobStarted(DownloadJob job);

}