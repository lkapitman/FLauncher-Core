package com.github.lkapitman.downloader.download;

import com.github.lkapitman.downloader.download.jobs.DownloadJob;

import java.io.File;

/**
 * The type Download manager.
 */
public class DownloadManager {

    private final String url;
    private final DownloadJob job;
    private final File file;

    /**
     * Instantiates a new Download manager.
     *
     * @param url  the url
     * @param job  the job
     * @param file the file
     */
    public DownloadManager(String url, DownloadJob job, File file) {
        this.url = url;
        this.job = job;
        this.file = file;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets job.
     *
     * @return the job
     */
    public DownloadJob getJob() {
        return job;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }
}
