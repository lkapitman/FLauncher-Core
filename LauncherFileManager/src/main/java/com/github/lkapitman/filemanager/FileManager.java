package com.github.lkapitman.filemanager;

import java.io.File;

public class FileManager {

    private String serverName;

    public FileManager(String serverName) {
        this.serverName = serverName;
    }

    public File createGameDir() {
        final String userHome = System.getProperty("user.home");
        final String fileSeparator = File.separator;

        switch (OperatingSystem.getCurrentlyPlatform()) {
            case WINDOWS:
                return new File(userHome + fileSeparator + "AppData" + fileSeparator + "Roaming" + fileSeparator + this.serverName);
            case MACOS:
                return new File(userHome + fileSeparator + "Library" + fileSeparator + "Application Support" + fileSeparator + this.serverName);
            default:
                return new File(userHome + fileSeparator + this.serverName);
        }

    }

    public File getGameFolder() {
        return createGameDir();
    }

    public File getMetaDataFolder() {
        return new File(createGameDir(), "metadata");
    }
}
