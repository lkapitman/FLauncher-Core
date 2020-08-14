package com.github.lkapitman.utils.zipUtils;

import com.github.lkapitman.Main;
import com.github.lkapitman.files.FileManager;
import com.github.lkapitman.utils.Constants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class ZipDownloader {

    private final FileManager fileManager = new FileManager();
    private final Main logger = new Main();
    private boolean ok;

    private String save;
    public ZipDownloader(String path) {
        save = path;
    }

    public void downloadZip(String URL, String fileName) {
        try {
            java.net.URL url = new URL(URL);
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            FileOutputStream out = new FileOutputStream(save + "/" + fileName);
            byte[] b = new byte[1024];
            int count;
            while ((count = in.read(b)) >= 0) {
                out.write(b, 0, count);
            }
            out.flush(); out.close(); in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Runnable download = ()-> {
        if(fileManager.getGameDIR().isDirectory() && Objects.requireNonNull(fileManager.getGameDIR().list()).length == 0)  {
            logger.getLogger().warn("Директория игры - пуста!");
            logger.getLogger().log("Загрузка была начата!");
            downloadZip(Constants.URL, "game.zip");
//            try {
//                Files.copy(Paths.get("D:\\Дистрибутивы\\game.zip"), fileManager.getGameDIR().toPath().resolve("game.zip"), StandardCopyOption.REPLACE_EXISTING);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            if (fileManager.getGameDIR().isDirectory() && Objects.requireNonNull(fileManager.getGameDIR().list().length == 1)) {
                logger.getLogger().log("Загрузка была закончена!");
                ok = true;
            } else {
                ok = false;
            }

        }
    };

    public boolean getOK() {
        return ok;
    }

    public Runnable getDownload() {
        return download;
    }
}
