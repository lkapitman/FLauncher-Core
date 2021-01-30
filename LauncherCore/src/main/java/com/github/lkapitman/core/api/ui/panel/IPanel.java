package com.github.lkapitman.core.api.ui.panel;

import com.github.lkapitman.core.api.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {

    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();

}
