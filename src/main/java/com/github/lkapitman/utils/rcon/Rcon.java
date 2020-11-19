package com.github.lkapitman.utils.rcon;

import com.github.lkapitman.utils.rcon.ex.AuthenticationException;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;

public class Rcon {
    private final Object sync = new Object();

    private final Random rand = new Random();

    private int requestId;

    private Socket socket;

    private Charset charset;

    public Rcon(String host, int port, byte[] password) throws IOException, AuthenticationException {
        this.charset = Charset.forName("UTF-8");
        connect(host, port, password);
    }

    private void connect(String host, int port, byte[] password) throws IOException, AuthenticationException {
        if (host == null || host.trim().isEmpty())
            throw new IllegalArgumentException("Host can't be null or empty");
        if (port < 1 || port > 65535)
            throw new IllegalArgumentException("Port is out of range");
        synchronized (this.sync) {
            this.requestId = this.rand.nextInt();
            this.socket = new Socket(host, port);
        }
        RconPacket res = send(3, password);
        if (res.getRequestId() == -1)
            throw new AuthenticationException("Password rejected by server");
    }

    public void disconnect() throws IOException {
        synchronized (this.sync) {
            this.socket.close();
        }
    }

    public String command(String payload) throws IOException {
        if (payload == null || payload.trim().isEmpty())
            throw new IllegalArgumentException("Payload can't be null or empty");
        RconPacket response = send(2, payload.getBytes());
        return new String(response.getPayload(), getCharset());
    }

    private RconPacket send(int type, byte[] payload) throws IOException {
        synchronized (this.sync) {
            return RconPacket.send(this, type, payload);
        }
    }

    public int getRequestId() {
        return this.requestId;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }
}
