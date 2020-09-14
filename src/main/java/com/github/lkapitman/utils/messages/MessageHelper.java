package com.github.lkapitman.utils.messages;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class MessageHelper {

    private String textMSG;

    public MessageHelper(String text) {
        textMSG = text;

    }

    public void showInfoMSG() {
        javafx.scene.image.Image error = new Image("/info-msg.png");
        ImageView imageView = new ImageView(error);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Notifications notificationsBuilder = Notifications.create().title("Информация!").text(textMSG).graphic(imageView).hideAfter(Duration.seconds(1000)).position(Pos.CENTER);
        notificationsBuilder.darkStyle();
        notificationsBuilder.show();

    }

    public void showWarnMSG() {
        javafx.scene.image.Image error = new Image("/warn-msg.png");
        ImageView imageView = new ImageView(error);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Notifications notificationsBuilder = Notifications.create().title("Предупреждение!").text(textMSG).graphic(imageView).hideAfter(Duration.seconds(1000)).position(Pos.CENTER);
        notificationsBuilder.darkStyle();
        notificationsBuilder.show();

    }

    public void showErrorMSG() {
        javafx.scene.image.Image error = new Image("/error-msg.png");
        ImageView imageView = new ImageView(error);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Notifications notificationsBuilder = Notifications.create().title("Ошибка!").text(textMSG).graphic(imageView).hideAfter(Duration.seconds(1000)).position(Pos.CENTER);
        notificationsBuilder.darkStyle();
        notificationsBuilder.show();

    }
}