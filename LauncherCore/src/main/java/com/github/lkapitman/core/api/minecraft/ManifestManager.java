package com.github.lkapitman.core.api.minecraft;

import com.github.lkapitman.core.Core;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The type Manifest manager.
 */
public class ManifestManager {

    /**
     * Gets version url.
     *
     * @param version the version
     * @param type    the type
     * @return the version url
     */
    public String getVersionUrl(String version, String type) {
        Gson g = new Gson();
        String jsonString = "";
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(Core.getFileManager().getVersionsFolder().getAbsolutePath() + File.separator + "version_manifest.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Info info = g.fromJson(jsonString, Info.class);
        String url = "";
        for (VersionInfo vi :  info.versions) {
            if (vi.id.equals(version) && vi.type.equals(type)) {
               url = vi.url;
               break;
            }
        }
        return url;
    }
}

/**
 * The type Info.
 */
class Info {
    /**
     * The Latest.
     */
    public Latest latest;
    /**
     * The Versions.
     */
    public List<VersionInfo> versions;
}

/**
 * The type Latest.
 */
class Latest {
    /**
     * The Release.
     */
    public String release;
    /**
     * The Snapshot.
     */
    public String snapshot;
}

/**
 * The type Version info.
 */
class VersionInfo {
    /**
     * The Id.
     */
    public String id;
    /**
     * The Type.
     */
    public String type; // "snapshot" | "release"
    /**
     * The Url.
     */
    public String url;
    /**
     * The Time.
     */
    public String time; // "2021-05-27T09:30:46+00:00"
    /**
     * The Release time.
     */
    public String releaseTime; // "2021-04-21T16:41:14+00:00"
}