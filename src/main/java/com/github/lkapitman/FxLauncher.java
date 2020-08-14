package com.github.lkapitman;

import com.github.lkapitman.files.FileManager;
import com.github.lkapitman.instance.Instance;
import com.github.lkapitman.ui.PanelManager;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FxLauncher {

    private final FileManager fileManager = new FileManager();

    private PanelManager panelManager;

    public void init(Stage stage) {
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public PanelManager getPanelManager() {
        return panelManager;
    }
}
