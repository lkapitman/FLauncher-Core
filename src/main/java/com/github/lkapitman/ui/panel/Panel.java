package com.github.lkapitman.ui.panel;

import com.github.lkapitman.ui.PanelManager;
import javafx.animation.FadeTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public class Panel implements IPanel {

    protected GridPane layout = new GridPane();
    protected PanelManager panelManager;

    @Override
    public void init(PanelManager panelManager) {
        this.panelManager = panelManager;
        GridPane.setHgrow(layout, Priority.ALWAYS);
        GridPane.setVgrow(layout, Priority.ALWAYS);
    }

    @Override
    public GridPane getLayout() {
        return this.layout;
    }

    @Override
    public void onShow() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), this.layout);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }


}
