package com.github.lkapitman.downloader.download.data;

/**
 * The type Data file.
 */
public class DataFile {
    private String path;
    private String hash;
    private String url;

    /**
     * Instantiates a new Data file.
     *
     * @param path the path
     * @param hash the hash
     * @param url  the url
     */
    public DataFile(String path, String hash, String url) {
        this.path = path;
        this.hash = hash;
        this.url = url;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public String getHash() {
        return hash;
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
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
