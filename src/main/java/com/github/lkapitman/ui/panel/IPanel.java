package com.github.lkapitman.ui.panel;

import com.github.lkapitman.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
}
