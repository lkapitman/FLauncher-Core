package com.github.lkapitman.files;

import com.github.lkapitman.utils.OperatingSystem;

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

    private File getSettingsFolder() {
        return new File(createGameDir(), "settings");
    }

    public File getSettingsFile() {
        if (getSettingsFolder().exists() && getSettingsFolder().isDirectory()) {
            for (File file : getSettingsFolder().listFiles()) {
                if (file.getName().equalsIgnoreCase("launcher.properties")) {
                    return file;
                } else {
                    new File(getSettingsFolder(), "launcher.settings");

                }
            }
        }
        return null;
    }

}
