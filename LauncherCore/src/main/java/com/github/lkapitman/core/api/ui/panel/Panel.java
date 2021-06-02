package com.github.lkapitman.core.api.ui.panel;

import com.github.lkapitman.core.api.ui.PanelManager;
import javafx.animation.FadeTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

/**
 * The type Panel.
 */
public class Panel implements IPanel {

    /**
     * The Layout.
     */
    protected GridPane layout = new GridPane();
    /**
     * The Panel manager.
     */
    protected PanelManager panelManager;

    /**
     * Init.
     *
     * @param panelManager the panel manager
     */
    @Override
    public void init(PanelManager panelManager) {
        this.panelManager = panelManager;
        GridPane.setHgrow(layout, Priority.ALWAYS);
        GridPane.setVgrow(layout, Priority.ALWAYS);
    }

    /**
     * Gets layout.
     *
     * @return the layout
     */
    @Override
    public GridPane getLayout() {
        return this.layout;
    }

    /**
     * On show.
     */
    @Override
    public void onShow() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), this.layout);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }


}
