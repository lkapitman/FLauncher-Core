package com.github.lkapitman;

import com.github.lkapitman.utils.Constants;
import fr.arinonia.arilibfx.utils.AriLogger;
import javafx.application.Application;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.Properties;


public class App {

    public static AriLogger logger;

    private static FileInputStream fis;
    private static Properties property = new Properties();

    public static void main(String[] args) {
        logger = new AriLogger(Constants.PROJECT_NAME);
        try {
            Class.forName("javafx.application.Application");
            Application.launch(FxApplication.class, args);
        } catch (ClassNotFoundException e) {
            logger.warn("JavaFX - Not Found!");
            JOptionPane.showMessageDialog(null, FLauncher.getRes().getString("javafx.not.found"), FLauncher.getRes().getString("javafx.not.found.title"), JOptionPane.ERROR_MESSAGE);
        }
    }

}
