package com.github.lkapitman.core.api.ui;

import com.github.lkapitman.core.Core;
import com.github.lkapitman.core.api.Constants;
import com.github.lkapitman.core.api.elements.background.AppBackground;
import com.github.lkapitman.core.api.elements.background.ResizeHelper;
import com.github.lkapitman.core.api.ui.panel.IPanel;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class PanelManager {

    private final Core core;
    private final Stage stage;
    private GridPane layout;
    private GridPane centerPanel = new GridPane();

    private int stateWindow = 1;

    private TrayIcon trayIcon;

    public PanelManager(Core core, Stage stage) {
        this.core = core;
        this.stage = stage;
    }

    public void init() {
        if (!SystemTray.isSupported()) {
            Core.getLogger().ERROR_LOG(Core.getRes().getString("javafx.error.not.support"));
            return;
        }

        URL url = Core.class.getResource("/visual/img/icon.png");
        Image image = Toolkit.getDefaultToolkit().getImage(url);

        final PopupMenu popup = new PopupMenu();

        trayIcon = new TrayIcon(image, Constants.PROJECT_NAME, popup);

        final MenuItem exit = new MenuItem(Core.getRes().getString("javafx.menu.item.exit"));
        final SystemTray tray = SystemTray.getSystemTray();
        ActionListener menuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        exit.addActionListener(menuActionListener);
        popup.add(exit);

        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (stateWindow == 1) {
                                stage.hide();
                                stateWindow = 0;
                            } else if (stateWindow == 0) {
                                stage.show();
                                stateWindow = 1;
                            }
                        }
                    });
                }
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            Core.getLogger().ERROR_LOG(Core.getRes().getString("javafx.error.tray.icon"));
        }

        Platform.setImplicitExit(false);

        this.stage.setTitle(Constants.PROJECT_NAME);
        this.stage.setWidth(1613);
        this.stage.setMinWidth(1612);
        this.stage.setHeight(870);
        this.stage.setMinHeight(869);

        this.stage.initStyle(StageStyle.DECORATED);
        this.stage.centerOnScreen();

        this.stage.show();

        this.layout = new GridPane();
        this.layout.setStyle(AppBackground.setResponsiveBackground("visual/bg.png"));
        this.stage.setScene(new Scene(this.layout));

        RowConstraints topPanelConstrains = new RowConstraints();
        topPanelConstrains.setValignment(VPos.TOP);

        topPanelConstrains.setMinHeight(25);
        topPanelConstrains.setMaxHeight(25);

        this.layout.getRowConstraints().addAll(topPanelConstrains, new RowConstraints());

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

    public Core getCore() {
        return core;
    }

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }

}
