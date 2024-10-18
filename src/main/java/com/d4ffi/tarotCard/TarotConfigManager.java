package com.d4ffi.tarotCard;

import com.d4ffi.Okiro;
import com.moandjiezana.toml.Toml;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TarotConfigManager {

    private static final String CONFIG_FILE_NAME = "okiro_config.toml";
    private static FileWriter fileWriter;

    public TarotConfigManager() {
        try {
            loadconfig();
        } catch (IOException e) {
            Okiro.LOGGER.error("Error loading config file || {}", e.getMessage());
        }
    }

    public void loadconfig() throws IOException {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                Okiro.LOGGER.error("Error creating config file || {}", e.getMessage());
            }
        }

        Toml config = new Toml().read(configFile);

        if (config.isEmpty()) {
            writeDefaultConfig(configFile);
        }
    }

    public static void writeDefaultConfig(File configFile) throws IOException {
        try (FileWriter writer = new FileWriter(configFile)) {
            writeCommentedConfig(writer, "Config file for okiro Mod :D", null);
            writer.write("\n");

            writeCommentedConfig(writer,
                    "Set the amount of ticks that have to pass before updating the cards",
                    "by default is set to 20",
                    "updateTicks = 20");

            writeCommentedConfig(writer,
                    "Only the following cards will be active in the game",
                    "by default all 22 cards are active",
                    "activeCards = [\"thefool\",\"themagician\",\"thehighpriestess\",\"theempress\",\"theemperor\",\"thehierophant\",\"theloovers\",\"thechariot\",\"strength\",\"thehermit\",\"wheeloffortune\",\"justice\",\"thehangedman\",\"death\",\"temperance\",\"theDevil\",\"thetower\",\"thestar\",\"themoon\",\"thesun\",\"judgement\",\"theworld\"]");

            writeCommentedConfig(writer,
                    "Amount of time that The Fool can copy another card ability",
                    "by default is set to 6000",
                    "theFoolCopyTime = 6000");

            writeCommentedConfig(writer,
                    "Amount of time that The Magician burns the target",
                    "by default is set to 60",
                    "theMagicianBurnTime = 60");

            writeCommentedConfig(writer,
                    "List of effects that The High Priestess can negate",
                    "by default is set to [\"minecraft:poison\", \"minecraft:wither\",\"minecraft:slowness\"]",
                    "theHighPriestessNegateEffects = [\"minecraft:poison\", \"minecraft:wither\",\"minecraft:slowness\",\"minecraft:blindness\",\"minecraft:darkness\",\"minecraft:nausea\",\"minecraft:hunger\",\"minecraft:weakness\",\"minecraft:levitation\",\"minecraft:glowing\",\"minecraft:unluck\"]");

            writeCommentedConfig(writer,
                    "Amount of cooldown that The Empress has between uses",
                    "by default is set to 1200",
                    "theEmpressCooldown = 1200");

            writeCommentedConfig(writer,
                    "Does the Emperor can peace the piglins?",
                    "by default is set to true",
                    "theEmperorPeacePiglins = true");

            writeCommentedConfig(writer,
                    "Does the Emperor can give Hero of the Village to the player? and how strong is the effect?",
                    "by default is set to true and 3",
                    "theEmperorHeroOfTheVillage = true",
                    "theEmperorHeroOfTheVillageLevel = 3");

            writeCommentedConfig(writer,
                    "Does the lovers can regenerate players and friendly mobs nearby?",
                    "by default is set to false",
                    "theLoversHeal = false");

            writeCommentedConfig(writer,
                    "Sets the amount of health that the lovers can regenerate",
                    "by default is set to 1",
                    "theLoversHealAmount = 1");

            writeCommentedConfig(writer,
                    "Chariot boost only works on paths?",
                    "by default is set to false",
                    "theChariotBoostOnlyPaths = false");

            writeCommentedConfig(writer,
                    "Chariot speed level",
                    "by default is set to 1",
                    "theChariotSpeedLevel = 1");

            writeCommentedConfig(writer,
                    "Strength level given by the strength card",
                    "by default is set to 1",
                    "strengthLevel = 1");

            writeCommentedConfig(writer,
                    "Max health that temperance can grant to its user",
                    "by default is set to 20",
                    "temperanceMaxHealth = 20");

            writeCommentedConfig(writer,
                    "Sets the slowness effect that The World will emit",
                    "by default is set to 3",
                    "theWorldSlowness = 3");

            writeCommentedConfig(writer,
                    "Does the World can stop time? We mean to add a slowness effect V to stop everything around the player",
                    "by default is set to false",
                    "theWorldCanStopTime = false");

            writeCommentedConfig(writer,
                    "Sets the duration of the time stop",
                    "by default is set to 10",
                    "theWorldTimeStopDuration = 10");

            writeCommentedConfig(writer,
                    "Sets the cooldown of the time stop",
                    "by default is set to 3600",
                    "theWorldTimeStopCooldown = 3600");
        }
    }

    private static void writeCommentedConfig(FileWriter writer, String... lines) throws IOException {
        for (String line : lines) {
            if (line == null) {
                writer.write("\n");
            } else if (line.contains("=")) {
                writer.write(line + "\n");
            } else {
                writer.write("#" + line + "\n");
            }
        }
        writer.write("\n");
    }
}