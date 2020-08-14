package com.github.lkapitman.ui;

import com.github.lkapitman.FxLauncher;
import com.github.lkapitman.utils.Constants;
import fr.arinonia.arilibfx.ui.utils.ResizeHelper;
import com.github.lkapitman.ui.panel.IPanel;
import com.github.lkapitman.ui.panels.includes.TopPanel;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager  {

    private final FxLauncher fxLauncher;
    private final Stage stage;

    private GridPane layout;
    private TopPanel topPanel = new TopPanel();
    private GridPane centerPanel = new GridPane();

    public PanelManager(FxLauncher fxLauncher, Stage stage) {
        this.fxLauncher = fxLauncher;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle(Constants.NAME);

        this.stage.setMinWidth(1000);
        this.stage.setWidth(1000);
        this.stage.setMinHeight(700);
        this.stage.setHeight(700);
        this.stage.setResizable(true);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.centerOnScreen();
        this.stage.show();

        this.layout = new GridPane();
        this.layout.setStyle("-fx-background-image: url('"+"img/background.png"+"');");

        this.stage.setScene(new Scene(this.layout));

        RowConstraints topPanelConstraint = new RowConstraints();
        topPanelConstraint.setValignment(VPos.TOP);
        topPanelConstraint.setMinHeight(25);
        topPanelConstraint.setMaxHeight(25);

        this.layout.getRowConstraints().addAll(topPanelConstraint,new RowConstraints());
        this.layout.add(this.topPanel.getLayout(),0,0);
        this.topPanel.init(this);

        this.layout.add(this.centerPanel,0,1);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        ResizeHelper.addResizeListener(this.stage);

    }

    public void showPanel(IPanel panel) {
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
        panel.onShow();
    }

    public FxLauncher getLauncher() { return this.fxLauncher; }

    public Stage getStage() {
        return this.stage;
    }

    public TopPanel getTopPanel() { return topPanel; }

    public GridPane getLayout() {
        return layout;
    }

    public GridPane getCenterPanel() {
        return centerPanel;
    }


}
