package com.github.lkapitman.ui;

import com.github.lkapitman.FxLauncher;
import com.github.lkapitman.Main;
import com.github.lkapitman.files.FileManager;
import com.github.lkapitman.utils.Constants;
import com.github.lkapitman.utils.GameStarter;
import fr.arinonia.arilibfx.ui.utils.ResizeHelper;
import com.github.lkapitman.ui.panel.IPanel;
import com.github.lkapitman.ui.panels.includes.TopPanel;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PanelManager  {

    private final FxLauncher fxLauncher;
    private final Stage stage;

    private GridPane layout;
    private TopPanel topPanel = new TopPanel();
    private GridPane centerPanel = new GridPane();

    private Image icon = new Image(Constants.ICON);

    private String donateURL = Constants.DONATE_URL;
    private String vkURL = Constants.VK_URL;
    private String helpURL = Constants.HELP_URL;

    private Button playButton = new Button();
    private Button helpButton = new Button();
    private Button vkButton = new Button();
    private Button donateButton = new Button();
    private Button folderButton = new Button();

    private final FileManager fileManager = new FileManager();

    private final Main logger = new Main();

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

        // Button's

        // Button play
        playButton.setOpacity(0);
        playButton.setTranslateX(726);
        playButton.setTranslateY(580);
        playButton.setMinWidth(224);
        playButton.setMinHeight(95);
        playButton.setMaxWidth(224);
        playButton.setMaxHeight(95);

        playButton.setOnMouseClicked(e-> {
            // TODO: Сделать запуск .bat Добавить распаковку ZIP
            GameStarter gameStarter = new GameStarter(fileManager.getGameDIR().getAbsolutePath(), fileManager.getGameDIR().getAbsolutePath(), fileManager.getGameDIR().getAbsolutePath());
            gameStarter.startGame();
        });

        // Button help
        helpButton.setOpacity(0);
        helpButton.setTranslateX(33);
        helpButton.setTranslateY(580);
        helpButton.setMinWidth(240);
        helpButton.setMaxWidth(240);
        helpButton.setMinHeight(95);
        helpButton.setMaxHeight(95);

        helpButton.setOnMouseClicked(e-> {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(helpURL));
                logger.getLogger().log("Произведено открытие "  + helpURL);
            } catch (IOException ex) {
                logger.getLogger().warn("Указаного URL не существует! \n" + ex.getMessage());
            }
        });

        // Button vk
        vkButton.setOpacity(0);
        vkButton.setTranslateX(33);
        vkButton.setTranslateY(404);
        vkButton.setMinWidth(240);
        vkButton.setMaxWidth(240);
        vkButton.setMinHeight(95);
        vkButton.setMaxHeight(95);

        vkButton.setOnMouseClicked(e-> {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(vkURL));
                logger.getLogger().log("Произведено открытие "  + vkURL);
            } catch (IOException ex) {
                logger.getLogger().warn("Указаного URL не существует! \n" + ex.getMessage());
            }
        });

        // Button donate
        donateButton.setOpacity(0);
        donateButton.setTranslateX(33);
        donateButton.setTranslateY(505);
        donateButton.setMinWidth(240);
        donateButton.setMaxWidth(240);
        donateButton.setMinHeight(95);
        donateButton.setMaxHeight(95);

        donateButton.setOnMouseClicked(e-> {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(donateURL));
                logger.getLogger().log("Произведено открытие "  + donateURL);
            } catch (IOException ex) {
                logger.getLogger().warn("Указаного URL не существует! \n" + ex.getMessage());
            }
        });
        // Folder Button
        folderButton.setOpacity(0);
        folderButton.setTranslateX(615);
        folderButton.setTranslateY(591);
        folderButton.setMinWidth(92);
        folderButton.setMaxWidth(92);
        folderButton.setMinHeight(69);
        folderButton.setMaxHeight(69);

        folderButton.setOnMouseClicked(e-> {
            try {
                Runtime.getRuntime().exec("explorer " + fileManager.getGameDIR().getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        this.layout.getChildren().addAll(playButton, helpButton, vkButton, donateButton, folderButton);

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
