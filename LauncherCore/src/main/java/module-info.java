module Launcher.core {

    requires Launcher.fileManager;
    requires Launcher.downloader;
    requires Launcher.visual;

    requires javafx.controls;
    requires javafx.web;
    requires javafx.fxml;

    requires java.desktop;

    requires com.google.common;
    requires com.google.gson;

    requires mysql.connector.java;

    requires org.apache.commons.net;

    exports com.github.lkapitman.core;
    exports com.github.lkapitman.core.api.ui.controllers to javafx.fxml;

    opens com.github.lkapitman.core.api.minecraft to com.google.gson;
    opens com.github.lkapitman.core.api.ui.controllers to javafx.fxml;
}