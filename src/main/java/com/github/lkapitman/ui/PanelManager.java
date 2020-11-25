package com.github.lkapitman.ui;

import com.github.lkapitman.FLauncher;
import com.github.lkapitman.ui.panel.IPanel;
import com.github.lkapitman.ui.panels.includes.TopPanel;
import fr.arinonia.arilibfx.AriLibFX;
import fr.arinonia.arilibfx.ui.utils.ResizeHelper;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {

    private final FLauncher fLauncher;
    private final Stage stage;
    private GridPane layout;
    private TopPanel topPanel = new TopPanel();
    private GridPane centerPanel = new GridPane();

    public PanelManager(FLauncher fLauncher, Stage stage) {
        this.fLauncher = fLauncher;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle("FLauncher");
        this.stage.setMinWidth(1280);
        this.stage.setWidth(1280);
        this.stage.setMinHeight(720);
        this.stage.setHeight(720);

        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.centerOnScreen();

        this.stage.show();

        this.layout = new GridPane();
        this.layout.setStyle(AriLibFX.setResponsiveBackground("bg.png"));
        this.stage.setScene(new Scene(this.layout));

        RowConstraints topPanelConstrains = new RowConstraints();
        topPanelConstrains.setValignment(VPos.TOP);

        topPanelConstrains.setMinHeight(25);
        topPanelConstrains.setMaxHeight(25);

        this.layout.getRowConstraints().addAll(topPanelConstrains, new RowConstraints());
        this.layout.add(this.topPanel.getLayout(), 0,0);

        this.topPanel.init(this);
        this.layout.add(centerPanel, 0,1);

        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);

        ResizeHelper.addResizeListener(this.stage);


    }

    public void showPanel(IPanel panel) {
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
        panel.onShow();
    }
    public Stage getStage() {
        return stage;
    }

    public FLauncher getfLauncher() {
        return fLauncher;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

}
