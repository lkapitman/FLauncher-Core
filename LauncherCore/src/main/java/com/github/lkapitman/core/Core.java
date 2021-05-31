package com.github.lkapitman.core;

import com.github.lkapitman.core.api.Constants;
import com.github.lkapitman.core.api.CoreAPI;
import com.github.lkapitman.core.api.ui.PanelManager;
import com.github.lkapitman.core.api.ui.panels.PanelLogin;
import com.github.lkapitman.downloader.download.DownloadManager;
import com.github.lkapitman.downloader.download.Updater;
import com.github.lkapitman.downloader.download.jobs.DownloadJob;
import com.github.lkapitman.filemanager.FileManager;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import java.util.ResourceBundle;

public class Core implements CoreAPI {

    private static final FileManager fileManager = new FileManager(Constants.PROJECT_NAME);

    private static final File gameFolder = fileManager.getGameFolder();

    private PanelLogin panelLogin;
    private static boolean downloaded;

    private static final ResourceBundle res = ResourceBundle.getBundle("data");

    private static boolean isConnect() {
        try {
            final URL url = new URL(Constants.GAME_URL);
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void init(Stage stage) {

        if (isConnect()) {
            System.err.println("No Internet connection");
            System.exit(1);
        }

        PanelManager panelManager = new PanelManager(this, stage);
        panelManager.init();
        panelManager.showPanel(panelLogin = new PanelLogin());

    }

    public void downloadGame() {
        Updater updater = new Updater();
        DownloadJob game = new DownloadJob("game", this.panelLogin.getHomePanel());
        game.setExecutorService(5);
        updater.addJobToDownload(new DownloadManager(Constants.GAME_URL, game, fileManager.getGameFolder()));
        updater.setFileDeleter(true);
        Thread thread = new Thread(updater::start);
        thread.start();
    }

    public static boolean isDownloaded() {
        return downloaded;
    }

    public static void setDownloaded(boolean downloaded) {
        Core.downloaded = downloaded;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static ResourceBundle getRes() {
        return res;
    }

    @Override
    public String getHello() {
        return this.getClass().getName();
    }

}
