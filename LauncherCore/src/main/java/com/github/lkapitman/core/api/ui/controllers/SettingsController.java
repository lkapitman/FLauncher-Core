package com.github.lkapitman.core.api.ui.controllers;

import com.github.lkapitman.core.Core;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Settings controller.
 */
public class SettingsController {

    /**
     * The Display one.
     */
    public TextField displayOne;
    /**
     * The Display two.
     */
    public TextField displayTwo;

    /**
     * The Ready button.
     */
    public Button readyButton;
    /**
     * The Cancel button.
     */
    public Button cancelButton;
    /**
     * The Save button.
     */
    public Button saveButton;

    /**
     * The Fullscreen check box.
     */
    public CheckBox fullscreenCheckBox;

    /**
     * The Jvm args one.
     */
    public TextField jvmArgsOne;

    /**
     * The Ram.
     */
    public TextField ram;

    /**
     * The Is fullscreen.
     */
    public boolean isFullscreen = false;

    /**
     * Gets display one.
     *
     * @return the display one
     */
    public String getDisplayOne() {
        return displayOne.getText();
    }

    /**
     * Gets display two.
     *
     * @return the display two
     */
    public String getDisplayTwo() {
        return displayTwo.getText();
    }

    /**
     * Gets jvm args one.
     *
     * @return the jvm args one
     */
    public String getJVMArgsOne() {
        return jvmArgsOne.getText();
    }

    /**
     * Gets ram.
     *
     * @return the ram
     */
    public String getRAM() {
        return ram.getText();
    }

    /**
     * Click cancel.
     *
     * @param event the event
     */
    public void clickCancel(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Click save.
     *
     * @param event the event
     */
    public void clickSave(ActionEvent event) {
        Properties settings = new Properties();
        try {
            settings.load(new FileReader(Core.getFileManager().createSettingsFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        settings.setProperty("Display", getDisplayOne() + "x" + getDisplayTwo());
        settings.setProperty("FULLSCREEN", Boolean.toString(isFullscreen));
        settings.setProperty("JVMArgs", getJVMArgsOne());
        settings.setProperty("RAM", getRAM());

        try {
            settings.store(new FileOutputStream(Core.getFileManager().createSettingsFile()), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Click ready.
     *
     * @param event the event
     */
    public void clickReady(ActionEvent event) {
        clickSave(event);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Fullscreen.
     *
     * @param actionEvent the action event
     */
    public void fullscreen(ActionEvent actionEvent) {
        isFullscreen = true;
    }

}
