package com.github.lkapitman.ui.panels;

import com.github.lkapitman.FLauncher;
import com.github.lkapitman.ui.PanelManager;
import com.github.lkapitman.ui.panel.Panel;
import com.github.lkapitman.utils.Constants;
import com.google.common.hash.Hashing;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class PanelLogin extends Panel {

    private HomePanel homePanel;

    @Override
    public void init(PanelManager panelManager) {

        super.init(panelManager);
        GridPane loginPanel = new GridPane();
        GridPane mainPanel = new GridPane();
        GridPane buttonPanel = new GridPane();

        loginPanel.setMaxWidth(400);
        loginPanel.setMinWidth(400);
        loginPanel.setMaxHeight(580);
        loginPanel.setMinHeight(580);

        GridPane.setVgrow(loginPanel, Priority.ALWAYS);
        GridPane.setHgrow(loginPanel, Priority.ALWAYS);
        GridPane.setValignment(loginPanel, VPos.CENTER);
        GridPane.setHalignment(loginPanel, HPos.CENTER);

        RowConstraints buttonConstrains = new RowConstraints();

        buttonConstrains.setValignment(VPos.BOTTOM);
        buttonConstrains.setMaxHeight(55);

        loginPanel.getRowConstraints().addAll(new RowConstraints(), buttonConstrains);
        loginPanel.add(mainPanel, 0,0);
        loginPanel.add(buttonPanel, 0,1);

        GridPane.setVgrow(mainPanel, Priority.ALWAYS);
        GridPane.setHgrow(mainPanel, Priority.ALWAYS);
        GridPane.setVgrow(buttonPanel, Priority.ALWAYS);
        GridPane.setHgrow(buttonPanel, Priority.ALWAYS);

        mainPanel.setStyle("-fx-background-color: #181818;");
        buttonPanel.setStyle("-fx-background-color: #181818; -fx-opacity: 50%");

        Label noAccount = new Label(FLauncher.getRes().getString("login.noAccount"));
        Label registerHere = new Label(FLauncher.getRes().getString("login.registerHere"));

        GridPane.setVgrow(noAccount, Priority.ALWAYS);
        GridPane.setHgrow(noAccount, Priority.ALWAYS);
        GridPane.setValignment(noAccount, VPos.TOP);
        GridPane.setHalignment(noAccount, HPos.CENTER);

        noAccount.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 14px; ");

        GridPane.setVgrow(registerHere, Priority.ALWAYS);
        GridPane.setHgrow(registerHere, Priority.ALWAYS);
        GridPane.setValignment(registerHere, VPos.BOTTOM);
        GridPane.setHalignment(registerHere, HPos.CENTER);

        registerHere.setStyle("-fx-text-fill: #69a7ed; -fx-font-size: 14px; ");
        registerHere.setUnderline(true);
        registerHere.setTranslateY(-10);

        registerHere.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        registerHere.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        registerHere.setOnMouseClicked(e-> {
            openURL();
        });

        buttonPanel.getChildren().addAll(noAccount, registerHere);

        this.layout.getChildren().add(loginPanel);

        Label connectLabel = new Label(FLauncher.getRes().getString("login.login.into.account"));
        GridPane.setVgrow(connectLabel, Priority.ALWAYS);
        GridPane.setHgrow(connectLabel, Priority.ALWAYS);
        GridPane.setValignment(connectLabel, VPos.TOP);

        connectLabel.setTranslateY(27);
        connectLabel.setTranslateX(37.5);
        connectLabel.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 16px;");

        Separator connectSeparator = new Separator();

        GridPane.setVgrow(connectSeparator, Priority.ALWAYS);
        GridPane.setHgrow(connectSeparator, Priority.ALWAYS);
        GridPane.setValignment(connectSeparator, VPos.TOP);
        GridPane.setHalignment(connectSeparator, HPos.CENTER);

        connectSeparator.setTranslateY(60);
        connectSeparator.setMinWidth(325);
        connectSeparator.setMaxWidth(325);

        connectSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 50%");

        Label usernameLabel = new Label(FLauncher.getRes().getString("login.username"));

        GridPane.setVgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setHgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setValignment(usernameLabel, VPos.TOP);
        GridPane.setHalignment(usernameLabel, HPos.LEFT);

        usernameLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        usernameLabel.setTranslateY(110);
        usernameLabel.setTranslateX(37.5);

        TextField usernameField = new TextField();

        GridPane.setVgrow(usernameField, Priority.ALWAYS);
        GridPane.setHgrow(usernameField, Priority.ALWAYS);
        GridPane.setValignment(usernameField, VPos.TOP);
        GridPane.setHalignment(usernameField, HPos.LEFT);

        usernameField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5;");

        usernameField.setMaxWidth(325);
        usernameField.setMaxHeight(40);
        usernameField.setTranslateY(140);
        usernameField.setTranslateX(37.5);

        Separator usernameSeparator = new Separator();

        GridPane.setVgrow(usernameSeparator, Priority.ALWAYS);
        GridPane.setHgrow(usernameSeparator, Priority.ALWAYS);
        GridPane.setValignment(usernameSeparator, VPos.TOP);
        GridPane.setHalignment(usernameSeparator, HPos.CENTER);

        usernameSeparator.setTranslateY(181);
        usernameSeparator.setMinWidth(325);
        usernameSeparator.setMaxWidth(325);
        usernameSeparator.maxHeight(1);

        usernameSeparator.setStyle("-fx-opacity: 40%");

        Label passwordLabel = new Label(FLauncher.getRes().getString("login.password"));

        GridPane.setVgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setHgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setValignment(passwordLabel, VPos.TOP);
        GridPane.setHalignment(passwordLabel, HPos.LEFT);

        passwordLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        passwordLabel.setTranslateY(200);
        passwordLabel.setTranslateX(37.5);

        PasswordField passwordField = new PasswordField();

        GridPane.setVgrow(passwordField, Priority.ALWAYS);
        GridPane.setHgrow(passwordField, Priority.ALWAYS);
        GridPane.setValignment(passwordField, VPos.TOP);
        GridPane.setHalignment(passwordField, HPos.LEFT);

        passwordField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5;");

        passwordField.setMaxWidth(325);
        passwordField.setMaxHeight(40);
        passwordField.setTranslateY(230);
        passwordField.setTranslateX(37.5);

        Separator passwordSeparator = new Separator();

        GridPane.setVgrow(passwordSeparator, Priority.ALWAYS);
        GridPane.setHgrow(passwordSeparator, Priority.ALWAYS);
        GridPane.setValignment(passwordSeparator, VPos.TOP);
        GridPane.setHalignment(passwordSeparator, HPos.CENTER);

        passwordSeparator.setTranslateY(271);
        passwordSeparator.setMinWidth(325);
        passwordSeparator.setMaxWidth(325);
        passwordSeparator.maxHeight(1);

        passwordSeparator.setStyle("-fx-opacity: 40%");

        Button connectionButton = new Button(FLauncher.getRes().getString("login.login"));

        GridPane.setVgrow(connectionButton, Priority.ALWAYS);
        GridPane.setHgrow(connectionButton, Priority.ALWAYS);
        GridPane.setValignment(connectionButton, VPos.CENTER);
        GridPane.setHalignment(connectionButton, HPos.LEFT);

        connectionButton.setTranslateX(37.5);
        connectionButton.setTranslateY(80);
        connectionButton.setMinWidth(325);
        connectionButton.setMinHeight(50);

        connectionButton.setStyle("-fx-background-color: #007dbe; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff");

        connectionButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        connectionButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        connectionButton.setOnMouseClicked(e-> {
            String pass = Hashing.sha512().hashString(passwordField.getText(), StandardCharsets.UTF_8).toString();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                String JdbcURL = "jdbc:mysql://localhost/authme";
                String Username = "authme";
                String password = "authme1234";
                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet rst = null;
                String myQuery = "select id,realname,password from authme";
                con = DriverManager.getConnection(JdbcURL, Username, password);
                pstmt = con.prepareStatement(myQuery);
                rst = pstmt.executeQuery();
                System.out.println("id\t\trealname\t\tpassword\n");
                while(rst.next()) {
                    System.out.print(rst.getInt(1));
                    System.out.print("\t\t"+rst.getString(2));
                    System.out.print("\t\t"+rst.getString(3));
                    System.out.println();
                    if (rst.getString(2).equals(usernameField.getText()) && rst.getString(3).equalsIgnoreCase(pass)) {
                        this.panelManager.getTrayIcon().displayMessage(Constants.PROJECT_NAME,FLauncher.getRes().getString("login.goodgame"), TrayIcon.MessageType.INFO);
                        panelManager.showPanel(homePanel = new HomePanel());
                        return;
                    } else {
                        this.panelManager.getTrayIcon().displayMessage(Constants.PROJECT_NAME,FLauncher.getRes().getString("login.errorlogin"), TrayIcon.MessageType.ERROR);
                        return;
                    }
                }

            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        });

        mainPanel.getChildren().addAll(connectLabel, connectSeparator, usernameLabel, usernameField, usernameSeparator,
                passwordLabel, passwordField, passwordSeparator, connectionButton
        );
    }

    private void openURL() {
        try {
            Desktop.getDesktop().browse(new URI("https://vk.com/oldroleplayminecraftserver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public HomePanel getHomePanel() {
        return homePanel;
    }
}
