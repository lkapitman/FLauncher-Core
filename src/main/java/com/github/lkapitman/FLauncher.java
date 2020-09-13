package com.github.lkapitman;

import com.github.lkapitman.ui.PanelManager;
import com.github.lkapitman.ui.panels.PanelLogin;
import javafx.stage.Stage;

public class FLauncher {
    private PanelManager panelManager;

    public void init(Stage stage) {
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
        this.panelManager.showPanel(new PanelLogin());
    }
}
