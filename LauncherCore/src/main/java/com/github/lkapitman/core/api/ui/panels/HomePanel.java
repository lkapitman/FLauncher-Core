package com.github.lkapitman.core.api.ui.panels;

import com.github.lkapitman.core.Core;
import com.github.lkapitman.core.api.Constants;
import com.github.lkapitman.core.api.ui.modal.windows.Settings;
import com.github.lkapitman.downloader.download.jobs.DownloadJob;
import com.github.lkapitman.downloader.download.tasks.DownloadListener;
import com.github.lkapitman.visual.elements.AProgressBar;
import com.github.lkapitman.core.api.ui.PanelManager;
import com.github.lkapitman.core.api.ui.panel.Panel;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * The type Home panel.
 */
public class HomePanel extends Panel implements DownloadListener {

    private final GridPane centerPane = new GridPane();
    private AProgressBar leftDownloadBar;
    private AProgressBar aBigDownloadBar;

    private final String arguments = "java -Djava.net.preferIPv4Stack=true -Xmn8GB -Xmx8GB -Djava.library.path=C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\versions\\1.12.2\\natives -cp C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\minecraftforge\\forge\\1.12.2-14.23.5.2855\\forge-1.12.2-14.23.5.2855.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\ow2\\asm\\asm-debug-all\\5.2\\asm-debug-all-5.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\minecraft\\launchwrapper\\1.12\\launchwrapper-1.12.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\jline\\jline\\3.5.1\\jline-3.5.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\typesafe\\akka\\akka-actor_2.11\\2.3.3\\akka-actor_2.11-2.3.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\typesafe\\config\\1.2.1\\config-1.2.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-actors-migration_2.11\\1.1.0\\scala-actors-migration_2.11-1.1.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-compiler\\2.11.1\\scala-compiler-2.11.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\plugins\\scala-continuations-library_2.11\\1.0.2_mc\\scala-continuations-library_2.11-1.0.2_mc.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\plugins\\scala-continuations-plugin_2.11.1\\1.0.2_mc\\scala-continuations-plugin_2.11.1-1.0.2_mc.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-library\\2.11.1\\scala-library-2.11.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-parser-combinators_2.11\\1.0.1\\scala-parser-combinators_2.11-1.0.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-reflect\\2.11.1\\scala-reflect-2.11.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-swing_2.11\\1.0.1\\scala-swing_2.11-1.0.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-xml_2.11\\1.0.2\\scala-xml_2.11-1.0.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\lzma\\lzma\\0.0.1\\lzma-0.0.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\java3d\\vecmath\\1.5.2\\vecmath-1.5.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\sf\\trove4j\\trove4j\\3.0.3\\trove4j-3.0.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\maven\\maven-artifact\\3.5.3\\maven-artifact-3.5.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\5.0.3\\jopt-simple-5.0.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\tlauncher\\patchy\\1.1\\patchy-1.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\oshi-project\\oshi-core\\1.1\\oshi-core-1.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\java\\dev\\jna\\jna\\4.4.0\\jna-4.4.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\java\\dev\\jna\\platform\\3.4.0\\platform-3.4.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\ibm\\icu\\icu4j-core-mojang\\51.2\\icu4j-core-mojang-51.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\5.0.3\\jopt-simple-5.0.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\codecjorbis\\20101023\\codecjorbis-20101023.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\codecwav\\20101023\\codecwav-20101023.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\libraryjavasound\\20101123\\libraryjavasound-20101123.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\librarylwjglopenal\\20100824\\librarylwjglopenal-20100824.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\soundsystem\\20120107\\soundsystem-20120107.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\io\\netty\\netty-all\\4.1.9.Final\\netty-all-4.1.9.Final.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\google\\guava\\guava\\21.0\\guava-21.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\commons\\commons-lang3\\3.5\\commons-lang3-3.5.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\commons-io\\commons-io\\2.5\\commons-io-2.5.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\commons-codec\\commons-codec\\1.10\\commons-codec-1.10.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\java\\jinput\\jinput\\2.0.5\\jinput-2.0.5.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\java\\jutils\\jutils\\1.0.0\\jutils-1.0.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\google\\code\\gson\\gson\\2.8.0\\gson-2.8.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\tlauncher\\authlib\\1.6.25\\authlib-1.6.25.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\mojang\\realms\\1.10.22\\realms-1.10.22.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\commons\\commons-compress\\1.8.1\\commons-compress-1.8.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\httpcomponents\\httpclient\\4.3.3\\httpclient-4.3.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\commons-logging\\commons-logging\\1.1.3\\commons-logging-1.1.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\httpcomponents\\httpcore\\4.3.2\\httpcore-4.3.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\it\\unimi\\dsi\\fastutil\\7.1.0\\fastutil-7.1.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\logging\\log4j\\log4j-api\\2.8.1\\log4j-api-2.8.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\logging\\log4j\\log4j-core\\2.8.1\\log4j-core-2.8.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\lwjgl\\lwjgl\\lwjgl\\2.9.4-nightly-20150209\\lwjgl-2.9.4-nightly-20150209.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\lwjgl\\lwjgl\\lwjgl_util\\2.9.4-nightly-20150209\\lwjgl_util-2.9.4-nightly-20150209.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\mojang\\text2speech\\1.10.3\\text2speech-1.10.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\versions\\1.12.2\\1.12.2.jar -Dminecraft.applet.TargetDirectory=C:\\Users\\Lion\\AppData\\Roaming\\.minecraft -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M -Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.ignorePatchDiscrepancies=true net.minecraft.launchwrapper.Launch --username Aziat --version 1.12.2 --gameDir C:\\Users\\Lion\\AppData\\Roaming\\.minecraft --assetsDir C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\assets --assetIndex 1.12 --uuid 00000000-0000-0000-0000-000000000000 --accessToken null --userType legacy --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker --versionType Forge --width 1920 --height 720 --server migosmc.ru";

    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();

    /**
     * Init.
     *
     * @param panelManager the panel manager
     */
    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        ColumnConstraints menuPointConstrains = new ColumnConstraints();

        menuPointConstrains.setHalignment(HPos.LEFT);
        menuPointConstrains.setMinWidth(300);
        menuPointConstrains.setMaxWidth(300);

        this.layout.getColumnConstraints().addAll(menuPointConstrains, new ColumnConstraints());

        GridPane leftBarPane = new GridPane();

        GridPane.setVgrow(leftBarPane, Priority.ALWAYS);
        GridPane.setHgrow(leftBarPane, Priority.ALWAYS);

        Separator rightSeparator = new Separator();

        GridPane.setVgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHalignment(rightSeparator, HPos.RIGHT);

        rightSeparator.setOrientation(Orientation.VERTICAL);
        rightSeparator.setTranslateY(1);
        rightSeparator.setTranslateX(4);
        rightSeparator.setMinWidth(2);
        rightSeparator.setMaxWidth(2);
        rightSeparator.setOpacity(0.30D);

        GridPane buttonGridPane = new GridPane();

        GridPane.setVgrow(buttonGridPane, Priority.ALWAYS);
        GridPane.setHgrow(buttonGridPane, Priority.ALWAYS);
        GridPane.setHalignment(buttonGridPane, HPos.LEFT);
        GridPane.setValignment(buttonGridPane, VPos.TOP);

        buttonGridPane.setTranslateY(30);
        buttonGridPane.setMinHeight(40);
        buttonGridPane.setMaxHeight(40);
        buttonGridPane.setMinWidth(300);
        buttonGridPane.setMaxWidth(300);
        showleftBar(buttonGridPane);

        leftBarPane.getChildren().addAll(rightSeparator, buttonGridPane);

        this.layout.add(leftBarPane,0,0);
        this.layout.add(this.centerPane, 1,0);

        GridPane.setVgrow(centerPane, Priority.ALWAYS);
        GridPane.setHgrow(centerPane, Priority.ALWAYS);

        ScrollPane scrollPane = new ScrollPane();
        GridPane.setVgrow(scrollPane, Priority.ALWAYS);
        GridPane.setHgrow(scrollPane, Priority.ALWAYS);

        scrollPane.getStylesheets().addAll(Core.class.getResource("/visual/css/scrollbar.css").toExternalForm());

        VBox vBox = new VBox();
        GridPane.setVgrow(vBox, Priority.ALWAYS);
        GridPane.setHgrow(vBox, Priority.ALWAYS);
        vBox.setTranslateX(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinHeight(200);
        vBox.setMinWidth(900);
        vBox.setMaxWidth(900);

        GridPane topPanel = new GridPane();
        GridPane.setVgrow(topPanel, Priority.ALWAYS);
        GridPane.setHgrow(topPanel, Priority.ALWAYS);
        GridPane.setValignment(topPanel, VPos.TOP);

        topPanel.setMaxWidth(880);
        topPanel.setMinWidth(880);
        topPanel.setMaxHeight(340);
        topPanel.setMinHeight(340);
        addTopPanel(topPanel);

        this.centerPane.getChildren().add(scrollPane);

        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, topPanel);

    }

    private void addTopPanel(GridPane pane) {
        Label projectTitle = new Label(Constants.PROJECT_NAME);
        GridPane.setVgrow(projectTitle, Priority.ALWAYS);
        GridPane.setHgrow(projectTitle, Priority.ALWAYS);
        GridPane.setValignment(projectTitle, VPos.TOP);

        projectTitle.setStyle("-fx-font-size: 26px; -fx-text-fill:  #fff; -fx-font-weight: bold;");
        projectTitle.setTranslateY(20);

        Label rolePlay = new Label(Constants.GAME_TYPE);
        GridPane.setVgrow(rolePlay, Priority.ALWAYS);
        GridPane.setHgrow(rolePlay, Priority.ALWAYS);
        GridPane.setValignment(rolePlay, VPos.TOP);

        rolePlay.setStyle("-fx-font-size: 14px; -fx-text-fill:  #fff; -fx-opacity: 70%;");
        rolePlay.setTranslateY(70);

        Label complete = new Label(Core.getRes().getString("home.complete"));
        GridPane.setVgrow(complete, Priority.ALWAYS);
        GridPane.setHgrow(complete, Priority.ALWAYS);
        GridPane.setValignment(complete, VPos.TOP);

        complete.setStyle("-fx-font-size: 14px; -fx-text-fill:  #fff; -fx-opacity: 70%;");
        complete.setTranslateY(70);
        complete.setTranslateX(80);

        Label desc = new Label(Core.getRes().getString("home.description"));
        GridPane.setVgrow(desc, Priority.ALWAYS);
        GridPane.setHgrow(desc, Priority.ALWAYS);
        GridPane.setValignment(desc, VPos.TOP);

        desc.setStyle("-fx-font-size: 14px; -fx-text-fill: #bcc6e7; -fx-opacity: 70%;");
        desc.setTranslateY(130);

        GridPane bigVideo = new GridPane();

        GridPane.setVgrow(bigVideo, Priority.ALWAYS);
        GridPane.setHgrow(bigVideo, Priority.ALWAYS);
        GridPane.setValignment(bigVideo, VPos.TOP);
        GridPane.setHalignment(bigVideo, HPos.RIGHT);

        bigVideo.setMinWidth(430);
        bigVideo.setMaxWidth(430);
        bigVideo.setMinHeight(320);
        bigVideo.setMaxHeight(320);

        Button resultButton;
        if (Core.isDownloaded()) {
            resultButton = new Button(Core.getRes().getString("home.play"));
            Core.setDownloaded(true);
        } else {
            resultButton = new Button(Core.getRes().getString("home.install"));
        }

        GridPane.setVgrow(resultButton, Priority.ALWAYS);
        GridPane.setHgrow(resultButton, Priority.ALWAYS);
        GridPane.setValignment(resultButton, VPos.TOP);
        GridPane.setHalignment(resultButton, HPos.LEFT);

        resultButton.setTranslateY(260);
        resultButton.setMinWidth(140);
        resultButton.setMaxHeight(40);
        resultButton.setStyle("-fx-background-color: #115ffa; -fx-border-radius: 0; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff; ");

        resultButton.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        resultButton.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));

        resultButton.setOnMouseClicked(e-> {

        // TODO: Start Game!

        });

        Image settingsIcon = new Image(Core.class.getResource("/visual/settings.png").toExternalForm());
        ImageView imageViewSettings = new ImageView(settingsIcon);

        GridPane.setHgrow(imageViewSettings, Priority.ALWAYS);
        GridPane.setVgrow(imageViewSettings, Priority.ALWAYS);
        GridPane.setValignment(imageViewSettings, VPos.CENTER);

        imageViewSettings.setTranslateX(185);
        imageViewSettings.setTranslateY(110);
        imageViewSettings.setFitHeight(28);
        imageViewSettings.setFitWidth(28);

        imageViewSettings.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        imageViewSettings.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));

        imageViewSettings.setOnMouseClicked(e-> {
            Settings settings = new Settings(new Stage());
            settings.init();
        });

        Image folderImage = new Image(Core.class.getResource("/visual/folder.png").toExternalForm());
        ImageView imageViewFolder = new ImageView(folderImage);

        GridPane.setHgrow(imageViewFolder, Priority.ALWAYS);
        GridPane.setVgrow(imageViewFolder, Priority.ALWAYS);
        GridPane.setValignment(imageViewFolder, VPos.CENTER);

        imageViewFolder.setTranslateX(150);
        imageViewFolder.setTranslateY(110);
        imageViewFolder.setFitHeight(28);
        imageViewFolder.setFitWidth(28);

        imageViewFolder.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        imageViewFolder.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        imageViewFolder.setOnMouseClicked(e-> {
            File file = new File(Core.getFileManager().getGameFolder().getAbsolutePath());
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        aBigDownloadBar = new AProgressBar(400,20);
        aBigDownloadBar.setBackgroundColor(Color.rgb(3,48,90));

        Stop[] stops = new Stop[] {
                new Stop(0, Color.rgb(7,85,136)),
                new Stop(1, Color.rgb(3,163,219))
        };

        LinearGradient lg = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        aBigDownloadBar.setForegroundColor(lg);
        aBigDownloadBar.setTranslateY(150);

        webEngine.load(Constants.NEWS_URL);

        browser.setTranslateY(350);

        browser.setMinSize(420, 275);
        browser.setMaxSize(420,275);


        pane.getChildren().addAll(projectTitle, rolePlay, complete, desc, bigVideo, resultButton, imageViewFolder, imageViewSettings, aBigDownloadBar, browser);
    }

    private void showleftBar(GridPane pane) {
        Separator blueLeftSeparator = new Separator();
        GridPane.setVgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(blueLeftSeparator, Priority.ALWAYS);

        blueLeftSeparator.setOrientation(Orientation.VERTICAL);
        blueLeftSeparator.setMinWidth(3);
        blueLeftSeparator.setMaxWidth(3);
        blueLeftSeparator.setMinHeight(40);
        blueLeftSeparator.setMaxHeight(40);
        blueLeftSeparator.setStyle("-fx-background-color: rgb(5,179,242); -fx-border-width: 3 3 3 0; -fx-border-color: rgb(5,179,242)");

        Image logoImage = new Image(Core.class.getResource("/visual/valkyria.png").toExternalForm());
        ImageView imageViewLogo = new ImageView(logoImage);

        GridPane.setHgrow(imageViewLogo, Priority.ALWAYS);
        GridPane.setVgrow(imageViewLogo, Priority.ALWAYS);
        GridPane.setValignment(imageViewLogo, VPos.CENTER);

        imageViewLogo.setTranslateX(34);
        imageViewLogo.setFitHeight(28);
        imageViewLogo.setFitWidth(28);

        Label valkyria = new Label(Constants.GAME_NAME);
        GridPane.setHgrow(valkyria, Priority.ALWAYS);
        GridPane.setVgrow(valkyria, Priority.ALWAYS);
        GridPane.setValignment(valkyria, VPos.CENTER);

        valkyria.setTranslateX(90);
        valkyria.setStyle("-fx-font-size: 16px; -fx-text-fill: #fff;");

        leftDownloadBar = new AProgressBar(170,3);
        leftDownloadBar.setBackgroundColor(javafx.scene.paint.Color.rgb(222,222,222, 0.3d));
        leftDownloadBar.setForegroundColor(javafx.scene.paint.Color.rgb(255,255,255));
        leftDownloadBar.setTranslateX(90.0d);
        leftDownloadBar.setTranslateY(12.0d);

        pane.getChildren().addAll(blueLeftSeparator, imageViewLogo, valkyria, leftDownloadBar);

    }


    /**
     * On download job finished.
     *
     * @param job the job
     */
    @Override
    public void onDownloadJobFinished(DownloadJob job) {
        System.out.println(job.getName() + " finished!");
        this.panelManager.getTrayIcon().displayMessage(Constants.PROJECT_NAME, Core.getRes().getString("javafx.download.stop"), TrayIcon.MessageType.INFO);
    }

    /**
     * On download job progress changed.
     *
     * @param job the job
     */
    @Override
    public void onDownloadJobProgressChanged(DownloadJob job) {
        this.leftDownloadBar.setProgress(job.getAllFiles().size() - job.getRemainingFiles().size(), job.getAllFiles().size());
        this.aBigDownloadBar.setProgress(job.getAllFiles().size() - job.getRemainingFiles().size(), job.getAllFiles().size());

    }

    /**
     * On download job started.
     *
     * @param job the job
     */
    @Override
    public void onDownloadJobStarted(DownloadJob job) {
        System.out.println("'" + job.getName() + "' started to download!");
        this.panelManager.getTrayIcon().displayMessage(Constants.PROJECT_NAME, Core.getRes().getString("javafx.download.start"), TrayIcon.MessageType.INFO);
    }

}
