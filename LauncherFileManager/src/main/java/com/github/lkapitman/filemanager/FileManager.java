package com.github.lkapitman.filemanager;

import java.io.File;

public class FileManager {

    private final String serverName;
    private final String fileSeparator = File.separator;

    public FileManager(String serverName) {
        this.serverName = serverName;
    }

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

    public File getGameFolder() {
        return createGameDir();
    }

    public File getVersionFolder() {
        return new File(createGameDir() + fileSeparator + "versions" + fileSeparator + "1.12.2");
    }

    public File getVersionsFolder() {
        return new File(createGameDir() + fileSeparator + "versions");
    }

    public File getLibrariesFolder() {
        return new File(createGameDir() + fileSeparator + "libraries");
    }

    public File getAssetsFolder() {
        return new File(createGameDir() + fileSeparator + "assets");
    }

}
