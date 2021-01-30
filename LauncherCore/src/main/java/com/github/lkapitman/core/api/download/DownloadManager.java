package com.github.lkapitman.core.api.download;

import com.github.lkapitman.core.api.download.jobs.DownloadJob;

import java.io.File;

public class DownloadManager {

    private final String url;
    private final DownloadJob job;
    private final File file;

    public DownloadManager(String url, DownloadJob job, File file) {
        this.url = url;
        this.job = job;
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public DownloadJob getJob() {
        return job;
    }

    public File getFile() {
        return file;
    }
}
