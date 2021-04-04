package com.github.lkapitman.core.api;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class FTPUpload {

    private final String username;
    private final String password;
    private final String server;
    private final String pathToFile1;
    private final String pathToFile2;

    private final int port;

    public FTPUpload(String username, String password, String server, int port, String pathToFile1, String pathToFile2) {

        this.username = username;
        this.password = password;
        this.server = server;
        this.port = port;
        this.pathToFile1 = pathToFile1;
        this.pathToFile2 = pathToFile2;
    }

    public void upload() {
        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // ПОДХОД №1: загружает первый файл с помощью InputStream
            File firstLocalFile = new File(pathToFile1);

            String firstRemoteFile = "SYSTEM";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Запуск загрузки первого файла");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("Файл успешно загружен!");
            }

            // ПОДХОД № 2: загружает второй файл с помощью OutputStream
            File secondLocalFile = new File(pathToFile2);
            String secondRemoteFile = "sam/SAM";
            inputStream = new FileInputStream(secondLocalFile);

            System.out.println("Запуск загрузки второго файла!");
            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0;

            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();

            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("Второй файл успешно загружен!");
            }
        } catch (IOException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
