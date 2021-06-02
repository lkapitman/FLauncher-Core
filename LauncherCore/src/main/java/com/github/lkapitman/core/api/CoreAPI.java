package com.github.lkapitman.core.api;

import javafx.stage.Stage;

/**
 * The interface Core api.
 */
public interface CoreAPI {

    /**
     * Gets hello.
     *
     * @return the hello
     */
    public String getHello();

    /**
     * Init.
     *
     * @param stage the stage
     */
    public void init(Stage stage);

}
