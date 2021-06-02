package com.github.lkapitman.downloader.download.tasks;

import com.github.lkapitman.downloader.download.hash.HashUtil;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The type Download task.
 */
public class DownloadTask {

    private final URL url;
    private final File destination;
    private final String hashServer;

    private int nunAttempts= 0;

    /**
     * Instantiates a new Download task.
     *
     * @param url         the url
     * @param destination the destination
     * @param hashServer  the hash server
     */
    public DownloadTask(URL url, File destination, String hashServer) {
        this.url = url;
        this.destination = destination;
        this.hashServer = hashServer;
    }

    /**
     * Download string.
     *
     * @return the string
     * @throws IOException the io exception
     */
    public String download()throws IOException{
        String hashLocal = null;
        nunAttempts++;
        destination.getParentFile().mkdirs();
        if (destination.isFile())
            hashLocal = HashUtil.getMD5(destination);

        if (destination.isFile() && !destination.canWrite())
            throw new RuntimeException("Do not have write permissions for " + destination + " - aborting!");

        try {
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36");

            final int status = connection.getResponseCode();

            if (status / 100 == 2) {
                DataInputStream dis = new DataInputStream(connection.getInputStream());

                byte[] fileData = new byte[connection.getContentLength()];

                for (int x = 0; x < fileData.length; x++) {
                    fileData[x] = dis.readByte();
                }

                dis.close();

                FileOutputStream fos = new FileOutputStream(destination);
                fos.write(fileData);

                fos.close();

                hashLocal = HashUtil.getMD5(destination);

                if(hashServer.contains("-"))
                    return "Didn't have hash so assuming our copy is good";
                if(hashServer.equalsIgnoreCase(hashLocal))
                    return "Downloaded successfully and hash matched";
                throw new IOException(String.format("Hash did not match downloaded MD5 (Hash was %s, downloaded %s)", new Object[] { hashServer, hashLocal }));
            }

            if(destination.isFile())
                return "Couldn't connect to server (responded with " + status + ") but have local file, assuming it's good";
            throw new IOException("Server responded with " + status);
        }
        catch (IOException e) {
            if(destination.isFile())
                return "Couldn't connect to server (" + e.getClass().getSimpleName() + ": '" + e.getMessage() + "') but have local file, assuming it's good";
            throw e;
        }

    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public URL getUrl() {
        return url;
    }

    /**
     * Gets destination.
     *
     * @return the destination
     */
    public File getDestination() {
        return destination;
    }

    /**
     * Gets hash server.
     *
     * @return the hash server
     */
    public String getHashServer() {
        return hashServer;
    }

    /**
     * Gets nun attempts.
     *
     * @return the nun attempts
     */
    public int getNunAttempts() {
        return nunAttempts;
    }
}
