package com.github.lkapitman.core.api.elements.background;

import com.github.lkapitman.core.Core;
import javafx.scene.image.Image;

/**
 * The type App background.
 */
public class AppBackground {

    private static String programName;
    private static String resourcePath;

    /**
     * Sets responsive background.
     *
     * @param url the url
     * @return the responsive background
     */
    public static String setResponsiveBackground(String url) {
        return "-fx-background-image: url('"+url+"');"
                +"-fx-backgound-repeat: skretch;"+"-fx-backgound-position: center center;"
                +"-fx-background-size: cover;";
    }

    /**
     * Load image image.
     *
     * @param image the image
     * @return the image
     */
    public static Image loadImage(String image) {
        return new Image(Core.class.getResourceAsStream(getResourcePath() + "/" + image));
    }

    /**
     * Sets program name.
     *
     * @param programName the program name
     */
    public static void setProgramName(String programName) {
        AppBackground.programName = programName;
    }

    /**
     * Sets resource path.
     *
     * @param resourcePath the resource path
     */
    public static void setResourcePath(String resourcePath) {
        AppBackground.resourcePath = resourcePath.endsWith("/") ? resourcePath.substring(0, resourcePath.length() - 1) : resourcePath;
    }

    /**
     * Gets resource path.
     *
     * @return the resource path
     */
    public static String getResourcePath() {
        return resourcePath;
    }

}
