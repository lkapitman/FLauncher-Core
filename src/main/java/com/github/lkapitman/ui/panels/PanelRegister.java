package com.github.lkapitman.ui.panels;

import com.github.lkapitman.ui.PanelManager;
import com.github.lkapitman.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.concurrent.atomic.AtomicBoolean;

public class PanelRegister extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        GridPane loginPanel = new GridPane();
        GridPane mainPanel = new GridPane();
        GridPane buttonPanel = new GridPane();
        AtomicBoolean connectWithServer = new AtomicBoolean(false);

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

        Label hasAccount = new Label("Есть аккаунт?");
        Label loginHere = new Label("Войти здесь!");

        GridPane.setVgrow(hasAccount, Priority.ALWAYS);
        GridPane.setHgrow(hasAccount, Priority.ALWAYS);
        GridPane.setValignment(hasAccount, VPos.TOP);
        GridPane.setHalignment(hasAccount, HPos.CENTER);

        hasAccount.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 14px; ");

        GridPane.setVgrow(loginHere, Priority.ALWAYS);
        GridPane.setHgrow(loginHere, Priority.ALWAYS);
        GridPane.setValignment(loginHere, VPos.BOTTOM);
        GridPane.setHalignment(loginHere, HPos.CENTER);

        loginHere.setStyle("-fx-text-fill: #69a7ed; -fx-font-size: 14px; ");
        loginHere.setUnderline(true);
        loginHere.setTranslateY(-10);

        loginHere.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        loginHere.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        loginHere.setOnMouseClicked(e-> {
            this.panelManager.showPanel(new PanelLogin());
        });

        buttonPanel.getChildren().addAll(hasAccount, loginHere);

        this.layout.getChildren().add(loginPanel);

        Label connectLabel = new Label("РЕГИСТРАЦИЯ АККАУНТА");

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

        Label usernameLabel = new Label("Имя пользователя");

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

        /*

         */

        Label passwordLabel = new Label("Пароль");

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

        /*

         */

        Label RepasswordLabel = new Label("Повтор пароля");

        GridPane.setVgrow(RepasswordLabel, Priority.ALWAYS);
        GridPane.setHgrow(RepasswordLabel, Priority.ALWAYS);
        GridPane.setValignment(RepasswordLabel, VPos.TOP);
        GridPane.setHalignment(RepasswordLabel, HPos.LEFT);

        RepasswordLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        RepasswordLabel.setTranslateY(290);
        RepasswordLabel.setTranslateX(37.5);

        PasswordField RepasswordField = new PasswordField();

        GridPane.setVgrow(RepasswordField, Priority.ALWAYS);
        GridPane.setHgrow(RepasswordField, Priority.ALWAYS);
        GridPane.setValignment(RepasswordField, VPos.TOP);
        GridPane.setHalignment(RepasswordField, HPos.LEFT);

        RepasswordField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5;");

        RepasswordField.setMaxWidth(325);
        RepasswordField.setMaxHeight(40);
        RepasswordField.setTranslateY(320);
        RepasswordField.setTranslateX(37.5);

        Separator RepasswordSeparator = new Separator();

        GridPane.setVgrow(RepasswordSeparator, Priority.ALWAYS);
        GridPane.setHgrow(RepasswordSeparator, Priority.ALWAYS);
        GridPane.setValignment(RepasswordSeparator, VPos.TOP);
        GridPane.setHalignment(RepasswordSeparator, HPos.CENTER);

        RepasswordSeparator.setTranslateY(361);
        RepasswordSeparator.setMinWidth(325);
        RepasswordSeparator.setMaxWidth(325);
        RepasswordSeparator.maxHeight(1);

        RepasswordSeparator.setStyle("-fx-opacity: 40%");

        Button connectionButton = new Button("ЗАВЕРШИТЬ");

        GridPane.setVgrow(connectionButton, Priority.ALWAYS);
        GridPane.setHgrow(connectionButton, Priority.ALWAYS);
        GridPane.setValignment(connectionButton, VPos.CENTER);
        GridPane.setHalignment(connectionButton, HPos.LEFT);

        connectionButton.setTranslateX(37.5);
        connectionButton.setTranslateY(160);
        connectionButton.setMinWidth(325);
        connectionButton.setMinHeight(50);

        connectionButton.setStyle("-fx-background-color: #007dbe; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: #fff");

        connectionButton.setOnMouseEntered(e->this.layout.setCursor(Cursor.HAND));
        connectionButton.setOnMouseExited(e->this.layout.setCursor(Cursor.DEFAULT));
        connectionButton.setOnMouseClicked(e-> {
            if (connectWithServer.get()) {
                //TODO: Connect to minecraft server in rcon and use /register
            } else {
                //TODO: Error MSG
            }
        });

        mainPanel.getChildren().addAll(connectLabel, connectSeparator, usernameLabel, usernameField, usernameSeparator,
                passwordLabel, passwordField, passwordSeparator,
                connectionButton, RepasswordLabel, RepasswordField, RepasswordSeparator
        );
    }

}
