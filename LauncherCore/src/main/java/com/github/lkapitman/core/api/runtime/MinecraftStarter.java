package com.github.lkapitman.core.api.runtime;

import java.io.IOException;
import java.io.File;

public class MinecraftStarter {

  private String arguments;
  private String playerName;
  private File gameFolder;

  public MinecraftStarter(String arguments, File gameFolder, String playerName) {
    this.gameFolder = gameFolder;
    this.arguments = arguments;
    this.playerName = playerName;

  }

  private String parse() {
    String cmd = arguments.replaceAll("C:/Users/Lion/AppData/Roaming/.minecraft", gameFolder.getAbsolutePath());
    cmd = arguments.replaceAll("Aziat", playerName);

    return cmd;

  }
  public void start() {
    try {
      Runtime.getRuntime().exec(parse());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
