module Launcher.starter {
    requires Launcher.core;
    requires javafx.controls;

    opens com.github.lkapitman.starter to javafx.controls;
    exports com.github.lkapitman.starter to javafx.graphics;
}