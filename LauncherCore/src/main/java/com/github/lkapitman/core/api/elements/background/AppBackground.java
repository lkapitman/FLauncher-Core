package com.github.lkapitman.core.api.elements.background;

import com.github.lkapitman.core.Core;
import javafx.scene.image.Image;

public class AppBackground {

    private static String programName;
    private static String resourcePath;

    public static String setResponsiveBackground(String url) {
        return "-fx-background-image: url('"+url+"');"
                +"-fx-backgound-repeat: skretch;"+"-fx-backgound-position: center center;"
                +"-fx-background-size: cover;";
    }

    public static Image loadImage(String image) {
        return new Image(Core.class.getResourceAsStream(getResourcePath() + "/" + image));
    }

    public static void setProgramName(String programName) {
        AppBackground.programName = programName;
    }

    public static void setResourcePath(String resourcePath) {
        AppBackground.resourcePath = resourcePath.endsWith("/") ? resourcePath.substring(0, resourcePath.length() - 1) : resourcePath;
    }
    public static String getResourcePath() {
        return resourcePath;
    }

}
