package com.github.lkapitman.utils.messages;

public class TypeMessage {

    public static String info = "info";
    public static String warn = "warn";
    public static String error = "error";

    private String typeNow;

    public TypeMessage(String type) {
        if (type.equalsIgnoreCase("info")) {
            typeNow = info;
            return;
        }
        if (type.equalsIgnoreCase("warn")) {
            typeNow = warn;
            return;
        }
        if (type.equalsIgnoreCase("error")) {
            typeNow = error;
        }
    }

}
