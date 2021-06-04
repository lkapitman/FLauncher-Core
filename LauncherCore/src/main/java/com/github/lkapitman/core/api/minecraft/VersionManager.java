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

    //TODO: Arguments


    public AssetIndex assetIndex;

    /**
     * The Assets.
     */

    public String assets;

    /**
     * The Compliance level.
     */

    public int complianceLevel;

    public DownloadingInfo downloads;

    /**
     * The Id.
     */

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

class DownloadingInfo {
    public DownloadingClient client;
    public DownloadingClientMappings client_mappings;
    public DownloadingServer server;
    public DownloadingServerMappings server_mappings;
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

class DownloadingClient {

    public String sha1;
    public int size;
    public String url;

}

class DownloadingClientMappings {

    public String sha1;
    public int size;
    public String url;

}

class DownloadingServer {

    public String sha1;
    public int size;
    public String url;

}

class DownloadingServerMappings {

    public String sha1;
    public int size;
    public String url;

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

class AssetIndex {

        public String id;
        public String sha1;
        public int size;
        public int totalSize;
        public String url;
}

