module Launcher.core {

    requires Launcher.fileManager;
    requires Launcher.downloader;
    requires Launcher.visual;

    requires javafx.controls;
    requires java.desktop;

    requires com.google.common;
    requires com.google.gson;

    requires mysql.connector.java;

    requires org.apache.commons.net;


    exports com.github.lkapitman.core;
}