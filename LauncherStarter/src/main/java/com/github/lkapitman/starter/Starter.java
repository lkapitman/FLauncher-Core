package com.github.lkapitman.starter;

import com.github.lkapitman.core.Core;
import com.github.lkapitman.filemanager.FileManager;
import javafx.application.Application;

import javafx.stage.Stage;

import java.io.File;

public class Starter extends Application {

    public boolean checkMetaFolder(File dir) {
        // TODO: сделать прорверку.
        return true;
    }

    @Override
    public void start(Stage stage) {
        if (checkMetaFolder(Core.getGameFolder())) {
            new Core().init(stage);
        }
    }

}
