package com.github.lkapitman.file;

import com.github.lkapitman.utils.Constants;

import java.io.File;

public class FileManager {

    private static final File DIR = createGameDir();
    private Saver saver = new Saver(new File(DIR, "credentials.properties"));
    public FileManager(){
        if (!DIR.exists())DIR.mkdirs();
    }
    public static File getDIR() {
        return DIR;
    }
    public Saver getConfig() {
        return saver;
    }

    private static File createGameDir() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            return new File(System.getProperty("user.home") + "\\AppData\\Roaming\\" + Constants.NAME);
        else if (os.contains("mac"))
            return new File(System.getProperty("user.home") + "/Library/Application Support/" +  Constants.NAME);
        else
            return new File(System.getProperty("user.home") + "/" +  Constants.NAME);
    }

}
