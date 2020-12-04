package com.github.lkapitman;

import javafx.application.Application;
import javafx.stage.Stage;

public class FxApplication extends Application {


    @Override
    public void start(Stage stage) {
        new FLauncher().init(stage);
    }
}
