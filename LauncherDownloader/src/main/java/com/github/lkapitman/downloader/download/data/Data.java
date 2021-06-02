package com.github.lkapitman.downloader.download.data;

import com.github.lkapitman.downloader.download.json.DataMaintenance;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Data.
 */
public class Data {
    private DataMaintenance maintenance;
    private List<DataFile> files = new ArrayList<DataFile>();
    private List<String> ignoreFiles = new ArrayList<String>();


    /**
     * Gets maintenance.
     *
     * @return the maintenance
     */
    public DataMaintenance getMaintenance() {
        return maintenance;
    }

    /**
     * Gets files.
     *
     * @return the files
     */
    public List<DataFile> getFiles() {
        return files;
    }

    /**
     * Gets ignore files.
     *
     * @return the ignore files
     */
    public List<String> getIgnoreFiles() {
        return ignoreFiles;
    }

    /**
     * Sets maintenance.
     *
     * @param maintenance the maintenance
     */
    public void setMaintenance(DataMaintenance maintenance) {
        this.maintenance = maintenance;
    }

    /**
     * Sets files.
     *
     * @param files the files
     */
    public void setFiles(List<DataFile> files) {
        this.files = files;
    }

    /**
     * Sets ignore files.
     *
     * @param ignoreFiles the ignore files
     */
    public void setIgnoreFiles(List<String> ignoreFiles) {
        this.ignoreFiles = ignoreFiles;
    }
}
