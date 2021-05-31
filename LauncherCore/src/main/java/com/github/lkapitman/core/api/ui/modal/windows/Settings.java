package com.github.lkapitman.core.api.ui.modal.windows;

import com.github.lkapitman.core.Core;
import com.github.lkapitman.core.api.ui.modal.ModalAPI;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Settings implements ModalAPI {

    private final Stage stage;

    public Settings(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Settings");
        stage.getIcons().add(new Image(Core.class.getResource("/visual/settings.png").toExternalForm()));

        stage.setMinWidth(404);
        stage.setMinHeight(567);
        stage.setMaxWidth(404);
        stage.setMaxHeight(567);

        // TODO: Добавить разные кнопочки и поля + сделать вывод настроек в конфиг!

        stage.show();
    }
}
