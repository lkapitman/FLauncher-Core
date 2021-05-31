package com.github.lkapitman.core;

import com.github.lkapitman.core.api.Constants;
import com.github.lkapitman.core.api.CoreAPI;
import com.github.lkapitman.core.api.minecraft.ManifestManager;
import com.github.lkapitman.core.api.ui.PanelManager;
import com.github.lkapitman.core.api.ui.panels.PanelLogin;

import com.github.lkapitman.downloader.download.jobs.DownloadJob;
import com.github.lkapitman.downloader.download.tasks.DownloadListener;
import com.github.lkapitman.filemanager.FileManager;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import java.nio.file.Files;
import java.util.ResourceBundle;

public class Core implements CoreAPI, DownloadListener {

    private static final FileManager fileManager = new FileManager(Constants.PROJECT_NAME);

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
        try {
            downloadManifest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isConnect()) {
            System.err.println("No Internet connection");
            System.exit(1);
        }

        PanelManager panelManager = new PanelManager(this, stage);
        panelManager.init();
        panelManager.showPanel(panelLogin = new PanelLogin());

    }

    public void downloadManifest() throws IOException {
        if (!new File(fileManager.getVersionsFolder().getAbsolutePath() + File.separator + "version_manifest.json").exists()) {
            URL url = new URL("https://launchermeta.mojang.com/mc/game/version_manifest.json");
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();
            Files.copy(inputStream, new File(fileManager.getVersionsFolder().getAbsolutePath() + File.separator + "version_manifest.json").toPath());
        }
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





    @Override
    public void onDownloadJobFinished(DownloadJob job) { }
    @Override
    public void onDownloadJobProgressChanged(DownloadJob job) { }
    @Override
    public void onDownloadJobStarted(DownloadJob job) { }
}
