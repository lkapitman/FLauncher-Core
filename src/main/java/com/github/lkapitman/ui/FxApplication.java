package com.github.lkapitman.ui;

import com.github.lkapitman.FxLauncher;
import javafx.application.Application;
import javafx.stage.Stage;

public class FxApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new FxLauncher().init(stage);
    }
}
