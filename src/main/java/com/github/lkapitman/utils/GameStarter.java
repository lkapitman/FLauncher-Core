package com.github.lkapitman.utils;

import com.github.lkapitman.Main;
import com.github.lkapitman.files.FileManager;
import com.github.lkapitman.utils.zipUtils.UnzipUtility;
import com.github.lkapitman.utils.zipUtils.ZipDownloader;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class GameStarter {

    private final FileManager fileManager = new FileManager();
    private static final Main logger = new Main();

    private static String source;
    private static String destination;

    private static String pathToBAT = "";
    private final UnzipUtility unzip = new UnzipUtility();

    private static String zipFile;

    private static boolean stopDownload = true;

    private final Starter starter = new Starter();

    public GameStarter(String pathTOZIP, String destinationExtact, String pathTOBAT) {
        source = pathTOZIP;
        destination = destinationExtact;
        pathToBAT = pathTOBAT;
    }


    public boolean startGame() {
        //TODO:
        if (fileManager.getGameDIR().isDirectory() && fileManager.getGameDIR().list().length > 1) {

            stopDownload = false;

            File[] files = fileManager.getGameDIR().listFiles();
            for (int i = 0; i <= files.length; i++) {
                if (files[i].getName().contains("run1.12.2.bat")) {
                    pathToBAT = files[i].getAbsolutePath();
                    JOptionPane.showMessageDialog(null, "Файлы игры были успешно найдены! \n" + pathToBAT, "Файлы Игры были найдены!", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            JOptionPane.showMessageDialog(null, "Сейчас будет запущена игра. Спасибо за установку!", "Игра будет запущена!", JOptionPane.INFORMATION_MESSAGE);

            Path gameDir = Paths.get(System.getProperty("user.home"));

            gameDir = gameDir.resolve("AppData").resolve("Roaming").resolve(Constants.NAME).resolve("run1.12.2.bat");

            starter.startBat(gameDir);

        } else {
            if (fileManager.getGameDIR().isDirectory() && Objects.requireNonNull(fileManager.getGameDIR().list()).length == 0) {
                stopDownload = false;
                ZipDownloader zipDownloader = new ZipDownloader(fileManager.getGameDIR().getAbsolutePath());
                Thread downloader = new Thread(zipDownloader.getDownload(),"Downloader");
                JOptionPane.showMessageDialog(null, "Идёт процесс загрузки файлов игры. Просим вас подождать. (Со скоростью 1mbps/с у вас будет качаться ~15 минут) Вам покажется окно, когда игра будет скачана!", "Загрузка файлов Начата!", JOptionPane.INFORMATION_MESSAGE);

                downloader.start();
                if (zipDownloader.getOK()) {
                    stopDownload = true;
                    JOptionPane.showMessageDialog(null, "Все файлы игры, были успешно загружены!", "Загрузка файлов Закончена!", JOptionPane.INFORMATION_MESSAGE);
                    downloader.stop();
                }

            } else {
                if (fileManager.getGameDIR().isDirectory() && Objects.requireNonNull(fileManager.getGameDIR().list()).length == 1) {
                    File[] files = fileManager.getGameDIR().listFiles();
                    for (int i = 0; i <= files.length; i++) {
                        if (files[i].getName().equalsIgnoreCase("game.zip")) {
                            zipFile = files[i].getAbsolutePath();
                            break;
                        }
                    }

                    Runnable unziper = ()-> {
                        try {
                            unzip.unzip(zipFile, fileManager.getGameDIR().getAbsolutePath());
                            JOptionPane.showMessageDialog(null, "Игра была Успешно распакована", "Игра была успешно распакована!", JOptionPane.INFORMATION_MESSAGE);
                            for (int i = 0; i <= files.length; i++) {
                                if (files[i].getName().equalsIgnoreCase("game.zip")) {
                                    break;
                                }
                            }
                        } catch (ZipException e) {
                            JOptionPane.showMessageDialog(null, "Произошла ошибка при распаковке игры!", "Ошибка в Распаковке игры!", JOptionPane.ERROR_MESSAGE);
                        }
                    };

                    Thread unzipper = new Thread(unziper, "Unzipper");

                    unzipper.start();

                    JOptionPane.showMessageDialog(null, "Перезагрузите лаунчер и нажмите на кнопку играть. Спасибо за установку!", "Игра будет запущена!", JOptionPane.INFORMATION_MESSAGE);


                }
            }
        }
        return false;
    }

}

