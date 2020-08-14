package com.github.lkapitman.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

public class Saver {

    private final File file;
    private final Properties properties;

    public Saver(File file) {
        this.file = file;
        this.properties = new Properties();

        if (file.exists())
            load();
    }

    public void set(String key, String value) {
        properties.setProperty(key, value);
        save();
    }
    public String get(String key) {
        return properties.getProperty(key);
    }

    public String get(String key, String def) {
        String value = properties.getProperty(key);
        return value == null ? def : value;
    }

    public void save() {
        try {
            properties.store(new BufferedWriter(new FileWriter(file)), "Создано при помощи OpenLauncherLib Saver");
        } catch (Throwable t) {
            throw new RuntimeException("Не можем сохранить настройки!", t);
        }
    }

    public void load() {
        try {
            properties.load(new FileInputStream(file));
        }
        catch (Throwable t) {
            throw new RuntimeException("Не можем загрузить настройки!", t);
        }
    }
}

