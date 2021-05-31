package com.github.lkapitman.starter;

import com.github.lkapitman.core.Core;
import javafx.application.Application;

import javafx.stage.Stage;


public class Starter extends Application {

    @Override
    public void start(Stage stage) {
        new Core().init(stage);
    }

}
