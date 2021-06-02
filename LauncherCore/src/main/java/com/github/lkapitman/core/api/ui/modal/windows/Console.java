package com.github.lkapitman.core.api.ui.modal.windows;

import com.github.lkapitman.core.Core;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Console {

    private final Stage stage;

    /**
     * Instantiates a new Console.
     *
     * @param stage the stage
     */
    public Console(Stage stage) {
        this.stage = stage;
    }

    /**
     * Init.
     */
    public void init() {
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(Core.getRes().getString("settings.window"));
        stage.getIcons().add(new Image(Core.class.getResource("/visual/console.png").toExternalForm()));

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/console.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
