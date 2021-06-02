package com.github.lkapitman.downloader.download.json;

/**
 * The type Data maintenance.
 */
public class DataMaintenance {
    private boolean maintenance;
    private String message;

    /**
     * Is maintenance boolean.
     *
     * @return the boolean
     */
    public boolean isMaintenance() {
        return maintenance;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets maintenance.
     *
     * @param maintenance the maintenance
     */
    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}