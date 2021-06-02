package com.github.lkapitman.core.api.minecraft;

import java.util.List;

/**
 * The type Version manager.
 */
public class VersionManager {

}

/**
 * The type Version information.
 */
class VersionInformation {

    /**
     * The Assets.
     */
//TODO: Arguments
    //TODO: assetsIdex
    public String assets;
    /**
     * The Compliance level.
     */
    public int complianceLevel;
    /**
     * The Id.
     */
//TODO: downloads
    public String id;

    /**
     * The Java version.
     */
    public JavaVersion javaVersion;
    /**
     * The Libraries.
     */
    public List<LibraryInfo> libraries;
    /**
     * The Logging.
     */
    public LoggingInfo logging;

    /**
     * The Main class.
     */
    public String mainClass;
    /**
     * The Minimum launcher version.
     */
    public int minimumLauncherVersion;
    /**
     * The Type.
     */
    public String type; // "snapshot" | "release"
    /**
     * The Time.
     */
    public String time; // "2021-05-27T09:30:46+00:00"
    /**
     * The Release time.
     */
    public String releaseTime; // "2021-04-21T16:41:14+00:00"
}

/**
 * The type Logging info.
 */
class LoggingInfo {
    /**
     * The Client.
     */
    public LoggingClient client;

}

/**
 * The type Library info.
 */
class LibraryInfo {
    /**
     * The Library.
     */
    public List<OneLibraryInfo> library;

}

/**
 * The type Java version.
 */
class JavaVersion {
    /**
     * The Component.
     */
    public String component;
    /**
     * The Major version.
     */
    public int majorVersion;
}

/**
 * The type One library info.
 */
class OneLibraryInfo {
    /**
     * The Downloads.
     */
    public Downloads downloads;
    /**
     * The Name.
     */
    public String name;
}

/**
 * The type Downloads.
 */
class Downloads {
    /**
     * The Artifact.
     */
    public Artifact artifact;
}

/**
 * The type Artifact.
 */
class Artifact {

    /**
     * The Path.
     */
    public String path;
    /**
     * The Sha 1.
     */
    public String sha1;
    /**
     * The Size.
     */
    public int size;
    /**
     * The Url.
     */
    public String url;
}

/**
 * The type Logging client.
 */
class LoggingClient {
    /**
     * The Argument.
     */
    public String argument;
    /**
     * The Logging file.
     */
    public LoggingFile loggingFile;
    /**
     * The Type.
     */
    public String type;
}

/**
 * The type Logging file.
 */
class LoggingFile {
    /**
     * The Id.
     */
    public String id;
    /**
     * The Sha 1.
     */
    public String sha1;
    /**
     * The Size.
     */
    public int size;
    /**
     * The Url.
     */
    public String url;
}

