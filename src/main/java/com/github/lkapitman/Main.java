package com.github.lkapitman;

import fr.arinonia.arilibfx.utils.AriLogger;
import com.github.lkapitman.files.FileManager;
import com.github.lkapitman.ui.FxApplication;
import javafx.application.Application;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static final AriLogger LOGGER = new AriLogger("FxLauncher");

    public static void main(String[] args) {
        new Main().init(args);
    }

    private void init(String... args){
        if(loadJavaFX()){
            LOGGER.log("JavaFX найден, запуск приложения");
            Application.launch(FxApplication.class, args);
        }else{
            LOGGER.warn("JavaFX не найден");
            JOptionPane.showMessageDialog(null, "JavaFX не найден!", "Java ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean loadJavaFX() {

        if(isJavaFXLoaded()) {
            LOGGER.log("JFX уже инициализрован");
            return true;
        }

        File jfxrt =  new File(FileManager.getGameDIR(), "runtime" + File.separator + "java" + File.separator + "jre" + File.separator + "lib" + File.separator + "ext" + File.separator + "jfxrt.jar");

        if(jfxrt.exists())

            LOGGER.log("jfxrt существует.");

        else
            LOGGER.warn("jfxrt не существует! wtf?!");

        if(jfxrt.isFile()) {

            try {
                LOGGER.log("Попытка загрузить {" + jfxrt + "}...");
                addToSystemClassLoader(jfxrt);
                LOGGER.log("JFX был успешно найден и загружен!");
                return true;
            }

            catch (Throwable e) {
                LOGGER.log("JFX был успешно найден, но не успешно загружен!"+ e);
                return false;
            }

        } else {

        }

        return false;
    }

    public void addToSystemClassLoader(final File file) throws IntrospectionException {

        if(ClassLoader.getSystemClassLoader() instanceof URLClassLoader) {

            final URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

            try {
                final Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(classLoader, file.toURI().toURL());
            }
            catch (Throwable t) {
                LOGGER.log("Не можем добавить " + file + " В системный classloader"+ t);
            }
        }
    }

    private boolean isJavaFXLoaded() {

        try {
            this.getClass().getClassLoader().loadClass("javafx.embed.swing.JFXPanel");
            return true;
        }

        catch (ClassNotFoundException localClassNotFoundException) {
            System.out.println(localClassNotFoundException.getMessage());
            return false;
        }
    }

    public AriLogger getLogger() {
        return LOGGER;
    }
}
