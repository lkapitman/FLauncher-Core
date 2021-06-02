package com.github.lkapitman.core.api.minecraft;

import java.util.List;

public class VersionManager {

}

class VersionInformation {

    //TODO: Arguments
    //TODO: assetsIdex
    public String assets;
    public int complianceLevel;
    //TODO: downloads
    public String id;

    public JavaVersion javaVersion;
    public List<LibraryInfo> libraries;
    public LoggingInfo logging;

    public String mainClass;
    public int minimumLauncherVersion;
    public String type; // "snapshot" | "release"
    public String time; // "2021-05-27T09:30:46+00:00"
    public String releaseTime; // "2021-04-21T16:41:14+00:00"
}

class LoggingInfo {
    public LoggingClient client;

}

class LibraryInfo {
    public List<OneLibraryInfo> library;

}

class JavaVersion {
    public String component;
    public int majorVersion;
}

class OneLibraryInfo {
    public Downloads downloads;
    public String name;
}

class Downloads {
    public Artifact artifact;
}

class Artifact {

    public String path;
    public String sha1;
    public int size;
    public String url;
}

class LoggingClient {
    public String argument;
    public LoggingFile loggingFile;
    public String type;
}

class LoggingFile {
    public String id;
    public String sha1;
    public int size;
    public String url;
}

