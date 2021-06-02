package com.github.lkapitman.visual.elements;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 * The type A progress bar.
 */
public class AProgressBar extends GridPane {

    private final Rectangle backgroundRect = new Rectangle();
    private final Rectangle foregroundRect = new Rectangle();

    /**
     * Instantiates a new A progress bar.
     */
    public AProgressBar() {
        init(100,40);
    }

    /**
     * Instantiates a new A progress bar.
     *
     * @param width  the width
     * @param height the height
     */
    public AProgressBar(int width, int height) {
        init(width,height);
    }

    private void init(int width, int height) {
        GridPane.setVgrow(foregroundRect, Priority.ALWAYS);
        GridPane.setHgrow(foregroundRect, Priority.ALWAYS);
        GridPane.setValignment(foregroundRect, VPos.CENTER);
        GridPane.setHalignment(foregroundRect, HPos.LEFT);
        setBarWidth(width);
        setBarHeight(height);
        this.getChildren().addAll(backgroundRect,foregroundRect);
    }

    /**
     * Sets bar width.
     *
     * @param width the width
     */
    public void setBarWidth(double width) {
        this.backgroundRect.setWidth(width);
        this.foregroundRect.setWidth(0);
        this.setPrefWidth(width);
        this.setMaxWidth(width);
    }

    /**
     * Sets bar height.
     *
     * @param height the height
     */
    public void setBarHeight(double height) {
        this.backgroundRect.setHeight(height);
        this.foregroundRect.setHeight(height);
        this.setPrefHeight(height);
        this.setMaxHeight(height);
    }

    /**
     * Gets bar width.
     *
     * @return the bar width
     */
    public double getBarWidth() {
        return this.backgroundRect.getWidth();
    }

    /**
     * Sets background color.
     *
     * @param fill the fill
     */
    public void setBackgroundColor(Color fill) {
        this.backgroundRect.setFill(fill);
    }

    /**
     * Sets foreground color.
     *
     * @param fill the fill
     */
    public void setForegroundColor(Paint fill) {
        this.foregroundRect.setFill(fill);
    }

    private float percentage(float val, float max) {
        return val * (float)this.getBarWidth() / max;
    }

    /**
     * Sets progress.
     *
     * @param val the val
     * @param max the max
     */
    public void setProgress(float val, float max) {
        foregroundRect.setWidth(percentage(val,max));
    }

}
