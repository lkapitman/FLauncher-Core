package com.github.lkapitman.core.api.minecraft;

import com.github.lkapitman.core.Core;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ManifestManager {

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

class Info {
    public Latest latest;
    public List<VersionInfo> versions;
}

class Latest {
    public String release;
    public String snapshot;
}

class VersionInfo {
    public String id;
    public String type; // "snapshot" | "release"
    public String url;
    public String time; // "2021-05-27T09:30:46+00:00"
    public String releaseTime; // "2021-04-21T16:41:14+00:00"
}