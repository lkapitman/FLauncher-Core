package com.github.lkapitman.starter;

import com.github.lkapitman.core.Core;
import javafx.application.Application;

import javafx.stage.Stage;


/**
 * The type Starter.
 */
public class Starter extends Application {

    /**
     * Start.
     *
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        new Core().init(stage);
    }

}
