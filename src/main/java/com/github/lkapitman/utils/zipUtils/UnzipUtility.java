package com.github.lkapitman.utils.zipUtils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class UnzipUtility {

    public void unzip(String pathToZIP, String pathToDestination) throws ZipException {

        ZipFile zipFile = new ZipFile(pathToZIP);
        zipFile.extractAll(pathToDestination);

    }

}
