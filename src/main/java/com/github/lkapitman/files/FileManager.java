package com.github.lkapitman.files;

import com.github.lkapitman.Main;
import com.github.lkapitman.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileManager {

    private static final File DIR = createGameDir();

    private final Main logger = new Main();

    public static File getGameDIR() {
        return DIR;
    }

    public FileManager() {
        if (!DIR.exists())
            DIR.mkdirs();
    }

    private static File createGameDir() {
        Path gameDir = Paths.get(System.getProperty("user.home"));
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            gameDir = gameDir.resolve("AppData").resolve("Roaming");
        else if (os.contains("mac"))
            gameDir = gameDir.resolve("Library").resolve("Application Support");
        return gameDir.resolve(Constants.NAME).toFile();
    }

    public File getDIR() {
        return DIR;
    }
}
