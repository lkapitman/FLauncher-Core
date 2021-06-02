package com.github.lkapitman.core.api.ui.controllers;

import com.github.lkapitman.core.Core;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleController {

    public TextArea Console;

    public Button cancelButton;

    public void clickCancel(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        System.out.println("second");

        Console.setWrapText(true);

        try {
            Scanner s = new Scanner(new File(Core.getFileManager().getLogsFolder() + File.separator + "latest.log")).useDelimiter("\\s+");
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
