package com.github.lkapitman.starter;

import com.github.lkapitman.core.App;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Starter extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label(new App().getHello());
        label.setFont(Font.font(48));
        StackPane pane = new StackPane(label);
        Scene scene = new Scene(pane, 480, 240);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
