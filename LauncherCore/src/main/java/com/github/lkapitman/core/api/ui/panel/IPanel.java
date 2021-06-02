package com.github.lkapitman.core.api.ui.panel;

import com.github.lkapitman.core.api.ui.PanelManager;
import javafx.scene.layout.GridPane;

/**
 * The interface Panel.
 */
public interface IPanel {

    /**
     * Init.
     *
     * @param panelManager the panel manager
     */
    void init(PanelManager panelManager);

    /**
     * Gets layout.
     *
     * @return the layout
     */
    GridPane getLayout();

    /**
     * On show.
     */
    void onShow();

}
