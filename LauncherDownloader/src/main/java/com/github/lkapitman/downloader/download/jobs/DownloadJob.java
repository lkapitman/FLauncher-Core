package com.github.lkapitman.downloader.download.jobs;

import com.github.lkapitman.downloader.download.tasks.DownloadListener;
import com.github.lkapitman.downloader.download.tasks.DownloadTask;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Download job.
 */
public class DownloadJob {

    private final String name;
    private final DownloadListener listener;

    private final Queue<DownloadTask> remainingFiles = new ConcurrentLinkedQueue<DownloadTask>();
    private final List<DownloadTask> allFiles = Collections.synchronizedList(new ArrayList<DownloadTask>());
    private final List<DownloadTask> failures = Collections.synchronizedList(new ArrayList<DownloadTask>());
    private final AtomicInteger remainingThreads = new AtomicInteger();

    private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    private boolean started;

    /**
     * Instantiates a new Download job.
     *
     * @param name     the name
     * @param listener the listener
     */
    public DownloadJob(String name, DownloadListener listener) {
        this.name = name;
        this.listener = listener;
    }

    /**
     * Add downloadable.
     *
     * @param file       the file
     * @param serverHash the server hash
     * @param url        the url
     */
    public void addDownloadable(File file, String serverHash, String url) {
        if(started)
            throw new IllegalStateException("Cannot add to download job that has already started");
        DownloadTask task = null;

        try {
            task = new DownloadTask(new URL(url), file, serverHash);
        } catch (MalformedURLException ignored) {}

        this.allFiles.add(task);
        this.remainingFiles.add(task);
    }

    private void popAndDownload() {
        DownloadTask task;
        while ((task = remainingFiles.poll()) != null) {
            if (task.getNunAttempts() > 5 ) {
                System.err.println("Gave up trying to download " + task.getUrl() + " for job '" + name + "'");
            }
            else{
                try {
                    final String result = task.download();
                    System.out.println("Finished downloading " + task.getDestination() + " for job '" + name + "'" + ": " + result);
                    this.listener.onDownloadJobProgressChanged(this);
                } catch (IOException e) {
                    System.err.println("Couldn't download " + task.getUrl() + " for job '" + name + "'");
                    remainingFiles.add(task);
                }
            }
        }
        if (remainingThreads.decrementAndGet() <= 0) {
            this.listener.onDownloadJobFinished(this);
        }
    }

    /**
     * Start downloading.
     *
     * @param executor the executor
     */
    public void startDownloading(final ThreadPoolExecutor executor){
        if (started) {
            throw new IllegalStateException("Cannot start download job that as already started !");
        }
        started = true;
        if (allFiles.isEmpty()) {
            System.out.println("Download job '" + name + "' skipped as there are no files to download");
            this.listener.onDownloadJobFinished(this);
        }
        else {
            final int threads = executor.getMaximumPoolSize();
            remainingThreads.set(threads);
            System.out.println("Download job '" + name + "' started (" + threads + " threads, " + allFiles.size() + " files)");
            this.listener.onDownloadJobStarted(this);
            for (int i = 0; i < threads; i++) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        popAndDownload();
                    }
                });
            }
        }
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets executor service.
     *
     * @return the executor service
     */
    public ThreadPoolExecutor getExecutorService() {
        return executorService;
    }

    /**
     * Gets remaining files.
     *
     * @return the remaining files
     */
    public Queue<DownloadTask> getRemainingFiles() {
        return remainingFiles;
    }

    /**
     * Gets failures.
     *
     * @return the failures
     */
    public int getFailures() {
        return failures.size();
    }

    /**
     * Gets all files.
     *
     * @return the all files
     */
    public List<DownloadTask> getAllFiles() {
        return allFiles;
    }

    /**
     * Set executor service.
     *
     * @param number the number
     */
    public void setExecutorService(int number){
        executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(number);
    }

    /**
     * Is complete boolean.
     *
     * @return the boolean
     */
    public boolean isComplete(){
        return started && remainingFiles.isEmpty() && remainingThreads.get() == 0;
    }}
