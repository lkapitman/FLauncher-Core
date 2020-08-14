package com.github.lkapitman.ui.panels.includes;

import com.github.lkapitman.instance.Instance;
import com.github.lkapitman.ui.PanelManager;
import com.github.lkapitman.ui.components.InstanceButton;
import com.github.lkapitman.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class LeftPanel extends Panel {


    public LeftPanel() {

    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        //Creator components for each server
        final VBox vBox = new VBox();
        GridPane.setVgrow(vBox, Priority.ALWAYS);
        GridPane.setHgrow(vBox, Priority.ALWAYS);
        vBox.setSpacing(5.0D);
        Separator separator = new Separator();
        GridPane.setVgrow(separator, Priority.ALWAYS);
        GridPane.setHgrow(separator, Priority.ALWAYS);
        GridPane.setHalignment(separator, HPos.RIGHT);
        separator.setOrientation(Orientation.VERTICAL);
        separator.setTranslateY(1);
        separator.setTranslateX(4);
        separator.setMinWidth(2);
        separator.setMaxWidth(2);
        separator.setOpacity(0.30);
        this.layout.getChildren().add(vBox);
        this.layout.getChildren().add(separator);
    }
}
