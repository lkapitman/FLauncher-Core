package com.github.lkapitman.core.api.ui.controllers;

import com.github.lkapitman.core.Core;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The type Console controller.
 */
public class ConsoleController {

    /**
     * The Console.
     */
    public TextArea Console;

    /**
     * The Open button.
     */
    public Button openButton;
    /**
     * The Cancel button.
     */
    public Button cancelButton;

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
     * Click open.
     *
     * @param event the event
     */
    public void clickOpen(ActionEvent event) {
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            desktop.open(new File(Core.getFileManager().getLogsFolder() + File.separator + "latest.log"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Initialize.
     */
    public void initialize() {
        System.out.println("second");

        Console.setWrapText(true);

        try {
            Scanner s = new Scanner(new File(Core.getFileManager().getLogsFolder() + File.separator + "latest.log")).useDelimiter("\\s+");

            String fileName = Core.getFileManager().getLogsFolder() + File.separator + "latest.log";
            String search = "[";
            String replace = "\n [";
            Charset charset = StandardCharsets.UTF_8;
            Path path = Paths.get(fileName);
            Files.writeString(path, Files.readString(path, charset).replace(search, replace), charset);


            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    Console.appendText(s.nextInt() + " "); // display the found integer
                } else {
                    Console.appendText(s.next() + " "); // else read the next token
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
