package com.github.lkapitman.starter;

import com.github.lkapitman.core.Core;
import com.github.lkapitman.filemanager.FileManager;
import javafx.application.Application;

import javafx.stage.Stage;

import java.io.File;

public class Starter extends Application {

    @Override
    public void start(Stage stage) {
        new Core().init(stage);
    }

}
