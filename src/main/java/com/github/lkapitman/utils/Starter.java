package com.github.lkapitman.utils;

import com.github.lkapitman.files.FileManager;

import java.io.IOException;
import java.nio.file.Path;

public class Starter {

    private final FileManager fileManager = new FileManager();

    public void startBat(Path path) throws IOException {
        fileManager.changeBAT(path);
        try {

            Runtime.getRuntime().exec("cmd /c start " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
