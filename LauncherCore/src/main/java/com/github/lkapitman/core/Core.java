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

    private PanelManager panelManager;
    private PanelLogin panelLogin;
    private static boolean downloaded;

    private static ResourceBundle res = ResourceBundle.getBundle("data");

    private static boolean isConnect() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void init(Stage stage) {

        if (!isConnect()) {
            System.err.println(getRes().getString("noInternetConnect"));
            System.exit(1);
        }

        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
        this.panelManager.showPanel(panelLogin = new PanelLogin());

    }

    public void downloadGame() {
        Updater updater = new Updater();
        DownloadJob game = new DownloadJob("game", this.panelLogin.getHomePanel());
        game.setExecutorService(5);
        updater.addJobToDownload(new DownloadManager("http://9dec199635fb.ngrok.io/game/instance.json", game, fileManager.getGameFolder()));
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

    public static File getGameFolder() {
        return gameFolder;
    }

    public static ResourceBundle getRes() {
        return res;
    }

    @Override
    public String getHello() {
        return this.getClass().getName();
    }

}
