package com.d4ffi.tarotCard;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TarotConfigManager {

    Path configDir = FabricLoader.getInstance().getConfigDir();
    Path configFile = configDir.resolve("tarot_config.json");

    // If File does not exist, create it,
    /*Todo: Method should write all default config into the file:
    *  points to add:
    *  1. list of cards allow in the game (this should prevent not wanted cards from spawn)
    *  2. default values for card powers like maxHealth or Regeneration level
    *  3. default value for checking the player inv, it should be 20 ticks by default
    *  4. optional: Add capability for cards to spawn on other mods loot tables*/
    public void createConfigFile() {
        try {
            Files.createFile(configFile);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
