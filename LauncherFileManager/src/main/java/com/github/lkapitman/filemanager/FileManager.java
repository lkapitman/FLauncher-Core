package com.github.lkapitman.filemanager;

import java.io.File;
import java.io.IOException;

/**
 * The type File manager.
 */
public class FileManager {

    private final String serverName;
    private final String fileSeparator = File.separator;

    /**
     * Instantiates a new File manager.
     *
     * @param serverName the server name
     */
    public FileManager(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Create game dir file.
     *
     * @return the file
     */
    public File createGameDir() {
        final String userHome = System.getProperty("user.home");

        switch (OperatingSystem.getCurrentlyPlatform()) {
            case WINDOWS:
                return new File(userHome + fileSeparator + "AppData" + fileSeparator + "Roaming" + fileSeparator + this.serverName);
            case MACOS:
                return new File(userHome + fileSeparator + "Library" + fileSeparator + "Application Support" + fileSeparator + this.serverName);
            default:
                return new File(userHome + fileSeparator + this.serverName);
        }
    }

    /**
     * Gets game folder.
     *
     * @return the game folder
     */
    public File getGameFolder() {
        return createGameDir();
    }

    /**
     * Gets versions folder.
     *
     * @return the versions folder
     */
    public File getVersionsFolder() {
        return new File(createGameDir() + fileSeparator + "versions");
    }

    /**
     * Gets libraries folder.
     *
     * @return the libraries folder
     */
    public File getLibrariesFolder() {
        return new File(createGameDir() + fileSeparator + "libraries");
    }

    /**
     * Gets assets folder.
     *
     * @return the assets folder
     */
    public File getAssetsFolder() {
        return new File(createGameDir() + fileSeparator + "assets");
    }

    /**
     * Gets settings folder.
     *
     * @return the settings folder
     */
    public File getSettingsFolder() {
        return new File(createGameDir() + fileSeparator + "settings");

    }

    /**
     * Create settings file file.
     *
     * @return the file
     */
    public File createSettingsFile() {
        if (new File(getSettingsFolder() + File.separator + "settings.properties").exists()) {
            return new File(getSettingsFolder() + File.separator + "settings.properties");
        }
        try {
            new File(getSettingsFolder() + File.separator + "settings.prpoperties").createNewFile();
            return createSettingsFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
