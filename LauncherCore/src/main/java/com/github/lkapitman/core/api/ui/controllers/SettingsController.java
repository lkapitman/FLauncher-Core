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

public class SettingsController {

    public TextField displayOne;
    public TextField displayTwo;

    public Button readyButton;
    public Button cancelButton;
    public Button saveButton;

    public CheckBox fullscreenCheckBox;

    public TextField jvmArgsOne;

    public TextField ram;

    public boolean isFullscreen = false;

    public String getDisplayOne() {
        return displayOne.getText();
    }

    public String getDisplayTwo() {
        return displayTwo.getText();
    }

    public String getJVMArgsOne() {
        return jvmArgsOne.getText();
    }

    public String getRAM() {
        return ram.getText();
    }

    public void clickCancel(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

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

    public void clickReady(ActionEvent event) {
        clickSave(event);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void fullscreen(ActionEvent actionEvent) {
        isFullscreen = true;
    }

}
