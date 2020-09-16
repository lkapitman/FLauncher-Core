package com.github.lkapitman.auth.mineweb;

import com.github.lkapitman.Main;
import com.github.lkapitman.utils.Server;
import com.github.lkapitman.utils.messages.MessageHelper;
import com.github.lkapitman.utils.rcon.Rcon;
import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicReference;

public class LoginMineWeb {

    private boolean connected = false;
    private String pseudo;
    private String hash;

    private String host;

    private int port;

    private String passRcon;

    private String returnValue;
    private Server server = new Server();
    private String ip;

    public boolean isConnected() {
        return connected;
    }

    public LoginMineWeb(String name , String password) {
        if (password.length() != 0 && !password.equalsIgnoreCase("") && name.length() != 0 && !name.equalsIgnoreCase("")) {
            try {
                Rcon connection = new Rcon(host, port, passRcon.getBytes());
                connection.connect(host, port, password.getBytes());
                connected = true;
                setIp();
                server.openServer();
                connection.command("/flauncher login " + name + " " + password + " " + ip);
                if (server.isValue()) {
                    returnValue = server.getValue();
                }

                connection.disconnect();
                server.closeServer();

            } catch (Exception e) {
                Main.logger.warn("Нет соеденения!");
                connected = false;
                new MessageHelper("Нет соеденения!").showErrorMSG();
            }

        } else {
            Main.logger.warn("Пароль - пустой!");
            new MessageHelper("Пароль - пустой!").showErrorMSG();
        }

    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPassRcon(String passRcon) {
        this.passRcon = passRcon;
    }

    public String isReturnValue() {
        return returnValue;
    }

    public void setIp() {
        InetAddress inetAddress = InetAddress.getLoopbackAddress();
        ip = InetAddress.getLoopbackAddress().toString();
    }

}
