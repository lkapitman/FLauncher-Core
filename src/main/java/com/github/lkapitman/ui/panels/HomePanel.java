package com.github.lkapitman.ui.panels;

import com.github.lkapitman.FLauncher;
import com.github.lkapitman.Main;
import com.github.lkapitman.files.FileManager;
import com.github.lkapitman.ui.PanelManager;
import com.github.lkapitman.ui.panel.Panel;
import com.github.lkapitman.utils.ReplaceFile;
import com.github.lkapitman.utils.messages.MessageHelper;
import com.sun.webkit.WebPage;
import fr.arinonia.arilibfx.ui.component.AProgressBar;
import fr.arinonia.arilibfx.updater.DownloadJob;
import fr.arinonia.arilibfx.updater.DownloadListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

public class HomePanel extends Panel implements DownloadListener {

    private GridPane centerPane = new GridPane();
    private AProgressBar leftDownloadBar;
    private AProgressBar aBigDownloadBar;
    private Button resultButton;
    private String s = "java -Djava.net.preferIPv4Stack=true -Xmn128M -Xmx512M -Djava.library.path=C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\versions\\1.12.2\\natives -cp C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\minecraftforge\\forge\\1.12.2-14.23.5.2854\\forge-1.12.2-14.23.5.2854.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\ow2\\asm\\asm-debug-all\\5.2\\asm-debug-all-5.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\minecraft\\launchwrapper\\1.12\\launchwrapper-1.12.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\jline\\jline\\3.5.1\\jline-3.5.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\typesafe\\akka\\akka-actor_2.11\\2.3.3\\akka-actor_2.11-2.3.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\typesafe\\config\\1.2.1\\config-1.2.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-actors-migration_2.11\\1.1.0\\scala-actors-migration_2.11-1.1.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-compiler\\2.11.1\\scala-compiler-2.11.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\plugins\\scala-continuations-library_2.11\\1.0.2_mc\\scala-continuations-library_2.11-1.0.2_mc.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\plugins\\scala-continuations-plugin_2.11.1\\1.0.2_mc\\scala-continuations-plugin_2.11.1-1.0.2_mc.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-library\\2.11.1\\scala-library-2.11.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-parser-combinators_2.11\\1.0.1\\scala-parser-combinators_2.11-1.0.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-reflect\\2.11.1\\scala-reflect-2.11.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-swing_2.11\\1.0.1\\scala-swing_2.11-1.0.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\scala-lang\\scala-xml_2.11\\1.0.2\\scala-xml_2.11-1.0.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\lzma\\lzma\\0.0.1\\lzma-0.0.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\java3d\\vecmath\\1.5.2\\vecmath-1.5.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\sf\\trove4j\\trove4j\\3.0.3\\trove4j-3.0.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\maven\\maven-artifact\\3.5.3\\maven-artifact-3.5.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\5.0.3\\jopt-simple-5.0.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\tlauncher\\patchy\\1.1\\patchy-1.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\oshi-project\\oshi-core\\1.1\\oshi-core-1.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\java\\dev\\jna\\jna\\4.4.0\\jna-4.4.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\java\\dev\\jna\\platform\\3.4.0\\platform-3.4.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\ibm\\icu\\icu4j-core-mojang\\51.2\\icu4j-core-mojang-51.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\5.0.3\\jopt-simple-5.0.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\codecjorbis\\20101023\\codecjorbis-20101023.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\codecwav\\20101023\\codecwav-20101023.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\libraryjavasound\\20101123\\libraryjavasound-20101123.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\librarylwjglopenal\\20100824\\librarylwjglopenal-20100824.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\paulscode\\soundsystem\\20120107\\soundsystem-20120107.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\io\\netty\\netty-all\\4.1.9.Final\\netty-all-4.1.9.Final.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\google\\guava\\guava\\21.0\\guava-21.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\commons\\commons-lang3\\3.5\\commons-lang3-3.5.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\commons-io\\commons-io\\2.5\\commons-io-2.5.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\commons-codec\\commons-codec\\1.10\\commons-codec-1.10.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\java\\jinput\\jinput\\2.0.5\\jinput-2.0.5.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\net\\java\\jutils\\jutils\\1.0.0\\jutils-1.0.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\google\\code\\gson\\gson\\2.8.0\\gson-2.8.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\tlauncher\\authlib\\1.6.25\\authlib-1.6.25.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\mojang\\realms\\1.10.22\\realms-1.10.22.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\commons\\commons-compress\\1.8.1\\commons-compress-1.8.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\httpcomponents\\httpclient\\4.3.3\\httpclient-4.3.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\commons-logging\\commons-logging\\1.1.3\\commons-logging-1.1.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\httpcomponents\\httpcore\\4.3.2\\httpcore-4.3.2.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\it\\unimi\\dsi\\fastutil\\7.1.0\\fastutil-7.1.0.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\logging\\log4j\\log4j-api\\2.8.1\\log4j-api-2.8.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\apache\\logging\\log4j\\log4j-core\\2.8.1\\log4j-core-2.8.1.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\lwjgl\\lwjgl\\lwjgl\\2.9.4-nightly-20150209\\lwjgl-2.9.4-nightly-20150209.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\org\\lwjgl\\lwjgl\\lwjgl_util\\2.9.4-nightly-20150209\\lwjgl_util-2.9.4-nightly-20150209.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\libraries\\com\\mojang\\text2speech\\1.10.3\\text2speech-1.10.3.jar;C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\versions\\1.12.2\\1.12.2.jar -Dminecraft.applet.TargetDirectory=C:\\Users\\Lion\\AppData\\Roaming\\.minecraft -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M -Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.ignorePatchDiscrepancies=true net.minecraft.launchwrapper.Launch --username Aziat --version 1.12.2 --gameDir C:\\Users\\Lion\\AppData\\Roaming\\.minecraft --assetsDir C:\\Users\\Lion\\AppData\\Roaming\\.minecraft\\assets --assetIndex 1.12 --uuid 00000000-0000-0000-0000-000000000000 --accessToken null --userType legacy --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker --versionType Forge --width 1920 --height 720 --server migosmc.ru";

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
        //TODO: SHOW LEFT BAR
        showleftBar(buttonGridPane);
        leftBarPane.getChildren().addAll(rightSeparator, buttonGridPane);
        this.layout.add(leftBarPane,0,0);
        this.layout.add(this.centerPane, 1,0);

        GridPane.setVgrow(centerPane, Priority.ALWAYS);
        GridPane.setHgrow(centerPane, Priority.ALWAYS);

        ScrollPane scrollPane = new ScrollPane();
        GridPane.setVgrow(scrollPane, Priority.ALWAYS);
        GridPane.setHgrow(scrollPane, Priority.ALWAYS);
        scrollPane.getStylesheets().addAll(Main.class.getResource("/css/scrollbar.css").toExternalForm());

        VBox vBox = new VBox();
        GridPane.setVgrow(vBox, Priority.ALWAYS);
        GridPane.setHgrow(vBox, Priority.ALWAYS);
        vBox.setMinHeight(200);
        vBox.setMinWidth(900);
        vBox.setMaxWidth(900);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateX(30);

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
        Label valkyriaTitle = new Label("Heaven Project");
        GridPane.setVgrow(valkyriaTitle, Priority.ALWAYS);
        GridPane.setHgrow(valkyriaTitle, Priority.ALWAYS);
        GridPane.setValignment(valkyriaTitle, VPos.TOP);
        valkyriaTitle.setStyle("-fx-font-size: 26px; -fx-text-fill:  #fff; -fx-font-weight: bold;");
        valkyriaTitle.setTranslateY(20);

        Label rolePlay = new Label("RolePlay");
        GridPane.setVgrow(rolePlay, Priority.ALWAYS);
        GridPane.setHgrow(rolePlay, Priority.ALWAYS);
        GridPane.setValignment(rolePlay, VPos.TOP);
        rolePlay.setStyle("-fx-font-size: 14px; -fx-text-fill:  #fff; -fx-opacity: 70%;");
        rolePlay.setTranslateY(70);

        Label complet = new Label("Complete");
        GridPane.setVgrow(complet, Priority.ALWAYS);
        GridPane.setHgrow(complet, Priority.ALWAYS);
        GridPane.setValignment(complet, VPos.TOP);
        complet.setStyle("-fx-font-size: 14px; -fx-text-fill:  #fff; -fx-opacity: 70%;");
        complet.setTranslateY(70);
        complet.setTranslateX(80);

        Label desc = new Label("Какое-то описание сервера...");
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

        String content_url =
                "<iframe style='background : rgba(0,0,0,0);' width=\"420\" height=\"320\"" +
                " src=\"http://arinonia.chaun14.fr/pages/video.html\" frameborder=\"0\" allow=\"accelerometer; autoplay=1; encrypted-media; gyroscope; picture-in-picture\"" +
                " allowfullscreen></iframe>";
        WebView webView = new WebView();
        webView.setStyle("overflow-x: hidden; overflow-y: hidden");

        WebEngine webEngine = webView.getEngine();

        webEngine.loadContent(content_url);

        bigVideo.getChildren().addAll(webView);
        webView.getChildrenUnmodifiable().addListener((ListChangeListener< Node >) change -> {
            Set<Node> deadSeaScrolls = webView.lookupAll(".scroll-bar");
            for (Node scroll : deadSeaScrolls) {
                scroll.setVisible(false);
            }
        });

        try {
            Field field = webEngine.getClass().getDeclaredField("page");
            field.setAccessible(true);
            WebPage page = (WebPage)field.get(webEngine);
            SwingUtilities.invokeLater(() -> {
                page.setBackgroundColor(new Color(255, 255, 255, 0).getRGB());
            });
        } catch (Exception e) {

        }
        if (FLauncher.isDownloaded())
            resultButton = new Button("Играть!");
        else
            resultButton = new Button("Установить!");

        GridPane.setVgrow(resultButton, Priority.ALWAYS);
        GridPane.setHgrow(resultButton, Priority.ALWAYS);
        GridPane.setValignment(resultButton, VPos.TOP);
        GridPane.setHalignment(resultButton, HPos.LEFT);
        resultButton.setTranslateY(260);
        resultButton.setMinWidth(140);
        resultButton.setMaxHeight(40);
        resultButton.setStyle("-fx-background-color: #115ffa; -fx-border-radius: 0; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff; ");

        resultButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        resultButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        resultButton.setOnMouseClicked(e-> {
            if (FLauncher.isDownloaded()) {
                try {
                    Runtime.getRuntime().exec(s.replaceAll("C:/Users/Lion/AppData/Roaming/.minecraft", this.panelManager.getfLauncher().getFileManager().getGameFolder().getAbsolutePath()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                this.panelManager.getfLauncher().launchGame();
            }
        });

        aBigDownloadBar = new AProgressBar(400,20);
        aBigDownloadBar.setBackgroundColor(javafx.scene.paint.Color.rgb(3,48,90));
        Stop[] stops = new Stop[]{new Stop(0, javafx.scene.paint.Color.rgb(7,85,136)), new Stop(1, javafx.scene.paint.Color.rgb(3,163,219))};

        LinearGradient lg = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        aBigDownloadBar.setForegroundColor(lg);
        aBigDownloadBar.setTranslateY(150);

        pane.getChildren().addAll(valkyriaTitle, rolePlay, complet, desc, bigVideo, resultButton, aBigDownloadBar);
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
        Image logoImage = new Image(Main.class.getResource("/valkyria.png").toExternalForm());
        ImageView imageViewLogo = new ImageView(logoImage);

        GridPane.setHgrow(imageViewLogo, Priority.ALWAYS);
        GridPane.setVgrow(imageViewLogo, Priority.ALWAYS);
        GridPane.setValignment(imageViewLogo, VPos.CENTER);
        imageViewLogo.setTranslateX(34);
        imageViewLogo.setFitHeight(28);
        imageViewLogo.setFitWidth(28);

        Label valkyria = new Label("Heaven");
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

    @Override
    public void onDownloadJobFinished(DownloadJob job) {
        Main.logger.log(job.getName() + " finished!");
    }

    @Override
    public void onDownloadJobProgressChanged(DownloadJob job) {
        this.leftDownloadBar.setProgress(job.getAllFiles().size() - job.getRemainingFiles().size(), job.getAllFiles().size());
        this.aBigDownloadBar.setProgress(job.getAllFiles().size() - job.getRemainingFiles().size(), job.getAllFiles().size());

    }

    @Override
    public void onDownloadJobStarted(DownloadJob job) {
        Main.logger.log("'" + job.getName() + "' started to download!");
    }

}
