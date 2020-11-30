package com.github.lkapitman;

import com.github.lkapitman.files.FileManager;
import com.github.lkapitman.ui.PanelManager;
import com.github.lkapitman.ui.panels.PanelLogin;
import fr.arinonia.arilibfx.updater.DownloadJob;
import fr.arinonia.arilibfx.updater.DownloadManager;
import fr.arinonia.arilibfx.updater.Updater;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class FLauncher {

    private static final FileManager fileManager = new FileManager("HeavenLauncher");

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
        updater.addJobToDownload(new DownloadManager("http://localhost/instance.json", game, fileManager.getGameFolder()));
        updater.setFileDeleter(true);
        Thread thread = new Thread(updater::start);
        thread.start();

    }

    public static boolean isDownloaded() {
        return downloaded;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public static ResourceBundle getRes() {
        return res;
    }
}
