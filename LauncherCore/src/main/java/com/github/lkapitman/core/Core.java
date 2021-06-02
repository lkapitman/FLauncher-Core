package com.github.lkapitman.core;

import com.github.lkapitman.core.api.Constants;
import com.github.lkapitman.core.api.CoreAPI;
import com.github.lkapitman.core.api.ui.PanelManager;
import com.github.lkapitman.core.api.ui.panels.PanelLogin;

import com.github.lkapitman.filemanager.FileManager;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import java.nio.file.Files;
import java.util.ResourceBundle;

/**
 * The type Core.
 */
public class Core implements CoreAPI {

    private static final FileManager fileManager = new FileManager(Constants.PROJECT_NAME);

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

    /**
     * Init.
     *
     * @param stage the stage
     */
    public void init(Stage stage) {
        initializeFiles();

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
        panelManager.showPanel(new PanelLogin());

    }

    /**
     * Download manifest.
     *
     * @throws IOException the io exception
     */
    public void downloadManifest() throws IOException {
        if (!new File(fileManager.getVersionsFolder().getAbsolutePath() + File.separator + "version_manifest.json").exists()) {
            URL url = new URL("https://launchermeta.mojang.com/mc/game/version_manifest.json");
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();
            Files.copy(inputStream, new File(fileManager.getVersionsFolder().getAbsolutePath() + File.separator + "version_manifest.json").toPath());
        }
    }

    /**
     * Initialize files.
     */
    public void initializeFiles() {
        fileManager.getLibrariesFolder().mkdir();
        fileManager.getVersionsFolder().mkdir();
        fileManager.getAssetsFolder().mkdir();
        fileManager.getSettingsFolder().mkdir();
        fileManager.createSettingsFile();
    }

    /**
     * Is downloaded boolean.
     *
     * @return the boolean
     */
    public static boolean isDownloaded() {
        return downloaded;
    }

    /**
     * Sets downloaded.
     *
     * @param downloaded the downloaded
     */
    public static void setDownloaded(boolean downloaded) {
        Core.downloaded = downloaded;
    }

    /**
     * Gets file manager.
     *
     * @return the file manager
     */
    public static FileManager getFileManager() {
        return fileManager;
    }

    /**
     * Gets res.
     *
     * @return the res
     */
    public static ResourceBundle getRes() {
        return res;
    }

    /**
     * Gets hello.
     *
     * @return the hello
     */
    @Override
    public String getHello() {
        return this.getClass().getName();
    }

}
