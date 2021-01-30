package com.github.lkapitman.core;

import com.github.lkapitman.core.api.Constants;
import com.github.lkapitman.core.api.CoreAPI;
import com.github.lkapitman.core.api.download.DownloadManager;
import com.github.lkapitman.core.api.download.Updater;
import com.github.lkapitman.core.api.download.jobs.DownloadJob;
import com.github.lkapitman.core.api.file.FileManager;
import com.github.lkapitman.core.api.ui.PanelManager;
import com.github.lkapitman.core.api.ui.panels.PanelLogin;
import com.github.lkapitman.logger.AppLogger;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Core implements CoreAPI {

    private static AppLogger logger = new AppLogger();


    private static final FileManager fileManager = new FileManager(Constants.PROJECT_NAME);

    private PanelManager panelManager;
    private PanelLogin panelLogin;
    private static boolean downloaded;

    private static ResourceBundle res = ResourceBundle.getBundle("data");

    public void init(Stage stage) {

        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
        this.panelManager.showPanel(panelLogin = new PanelLogin());

    }

    public void launchGame() {
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

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static ResourceBundle getRes() {
        return res;
    }

    public static AppLogger getLogger() {
        return logger;
    }

    @Override
    public String getHello() {
        return this.getClass().getName();
    }
}
