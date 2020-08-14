package com.github.lkapitman.utils;

public class OCDetected {

    public String checkOC() {
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch").toLowerCase();
        String swtFileName =
            osName.contains("win") ? "win" :
            osName.contains("mac") ? "macosx" :
            null;

        if (swtFileName == null)
            throw new RuntimeException("Unknown OS name: "+osName);

        if(osArch.contains("64")) {
            return "Архитектура ОС - x64";
        }
        if (osArch.contains("32")) {
            return "Архитектура ОС - x32";
        }
        return "Неизвестная архитектура!";
    }
}
