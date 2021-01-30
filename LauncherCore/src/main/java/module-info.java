module Launcher.core {
    requires Launcher.logger;

    requires javafx.controls;
    requires java.desktop;

    requires com.google.common;
    requires com.google.gson;

    requires mysql.connector.java;

    exports com.github.lkapitman.core;
}