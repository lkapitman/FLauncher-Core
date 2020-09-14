package com.github.lkapitman.auth.mineweb;

import com.github.lkapitman.Main;
import com.github.lkapitman.utils.rcon.Rcon;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicReference;

public class RegisterMineWeb {

    private boolean connected = false;
    private String pseudo;
    private String hash;
    private String host;
    private int port;
    private String pass;

    public boolean isConnected() {
        return connected;
    }

    public String getHash() {
        return hash;
    }

    public String getPseudo() {
        return pseudo;
    }

    public RegisterMineWeb(String name , String password, String rePasword) {
        try {
            Rcon connection = new Rcon(host, port, pass.getBytes());
            connection.connect(host, port, password.getBytes());
            if (rePasword.length() != 0 || rePasword.equals(password)) {
                connection.command("/flauncher add " + name + " " + password + " " + rePasword);
            } else {
                Main.logger.warn("пароль пустой. Или повтор - не верный!");
            }
        } catch (Exception e) {
            Main.logger.warn("Нет соеденения!");
        }

    }

    private String getSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        AtomicReference<StringBuffer> stringBuffer = new AtomicReference<>(new StringBuffer());
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                stringBuffer.get().append('0');
            }
            stringBuffer.get().append(hex);
        }
        return stringBuffer.toString();
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
