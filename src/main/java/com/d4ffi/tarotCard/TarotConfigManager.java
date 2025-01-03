package com.d4ffi.tarotCard;

import com.d4ffi.OkiroTarotCards;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import java.io.*;
import java.util.*;

public class TarotConfigManager {

    private static final String CONFIG_FILE_NAME = "okiro-cards-config.toml";
    Map<String, String> config = new HashMap<>();
    private static final HashMap<String, String> autoSmeltBlocks = new HashMap<>();
    private static final HashMap<Item, Item> turnToGoldItems = new HashMap<>();
    private static final Set<StatusEffect> highPriestessNegateEffects = new HashSet<>();
    private static List<Class<?>> cardsWithKeyEffects = new ArrayList<>();

    public TarotConfigManager() {
        try {
            loadconfig();
        } catch (IOException e) {
            OkiroTarotCards.LOGGER.info("Error loading config file || {}", e.getMessage());
        }
    }


    public void loadconfig() throws IOException {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                writeDefaultConfig(configFile);
            } catch (Exception e) {
                OkiroTarotCards.LOGGER.info("Error creating config file || {}", e.getMessage());
            }
        }

        config = parseTomlFile(configFile.getAbsolutePath());
    }

    public static Map<String, String> parseTomlFile(String filePath) throws IOException {
        Map<String, String> configMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim().replaceAll("\"", "");
                        configMap.put(key, value);
                    }
                }
            }
        }
        return configMap;
    }

    public static void writeDefaultConfig(File configFile) throws IOException {
        try (FileWriter writer = new FileWriter(configFile)) {
            writeCommentedConfig(writer, "Config file for okiro Mod :D", null);
            writeCommentedConfig(writer, "If you are reading this, you are awesome!", null);
            writer.write("\n");

            writeCommentedConfig(writer,
                    "Set the amount of ticks that have to pass before updating the cards",
                    "by default is set to 20",
                    "updateTicks = 20");

            writeCommentedConfig(writer,
                    "Select if you wish yo usea global spawn chance for all cards",
                    "by default is set to false, all cards have a spawn chance similar to armor trims",
                    "example:",
                    "Ancient cities --> 1.2% chance to spawn special cards and a 5% chance to spawn common cards",
                    "useCustomSpawnChance = false");

            writeCommentedConfig(writer,
                    "Set the chance for common cards to spawn in the world",
                    "by default is set to 15%,",
                    "specialSpawnChance = 0.15");

            writeCommentedConfig(writer,
                    "Amount of cooldown between Fool's dashes",
                    "by default is set to 80 ticks",
                    "foolColdown = 80");

            writeCommentedConfig(writer,
                    "Amount of time in seconds that The Magician burns the target",
                    "by default is set to 5",
                    "theMagicianBurnTime = 5");

            writeCommentedConfig(writer,
                    "Amount of time in Ticks that Death withers the target",
                    "by default is set to 100",
                    "deathWithersFor = 100");

            writeCommentedConfig(writer,
                    "Amount of cooldown that The Empress has between uses",
                    "by default is set to 40",
                    "theEmpressCooldown = 40");

            writeCommentedConfig(writer,
                    "How Strong is the regeneration effect that lovers grant",
                    "by default is set to 1 --> Regeneration 2",
                    "loversRegeneration = 1");

            writeCommentedConfig(writer,
                    "Chariot speed level",
                    "by default is set to Speed 1 --> 0",
                    "theChariotSpeedLevel = 0");

            writeCommentedConfig(writer,
                    "Strength level given by the strength card",
                    "by default is set to 1",
                    "strengthLevel = 1");

            writeCommentedConfig(writer,
                    "Max health that temperance can grant to its user",
                    "by default is set to 20",
                    "temperanceMaxHealth = 60");

            writeCommentedConfig(writer,
                    "Sets the slowness effect that The World will emit",
                    "by default is set to 3",
                    "theWorldSlowness = 3");

            writeCommentedConfig(writer, "Finally special thanks to all Fabric community <3", null);
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

    public int getUpdateTicks() {

        return Integer.parseInt(config.getOrDefault("updateTicks", "20"));
    }

    public float getSpawnChance() {

        return Float.parseFloat(config.getOrDefault("spawnChance", "0.20"));
    }

    public boolean getSpecialSpawnChance() {

        return Boolean.parseBoolean(config.getOrDefault("useCustomSpawnChance", "false"));
    }

    public int getFoolCooldown() {

        return Integer.parseInt(config.getOrDefault("foolColdown", "80"));
    }

    public int getLoversHealAmount() {

        return Integer.parseInt(config.getOrDefault("loversRegeneration", "1"));
    }

    public int getEmpressCooldown() {

        return Integer.parseInt(config.getOrDefault("theEmpressCooldown", "40"));
    }

    public int getMagicianBurnTime() {

        return Integer.parseInt(config.getOrDefault("theMagicianBurnTime", "5"));
    }

    public int getDeathWitherTime() {

        return Integer.parseInt(config.getOrDefault("deathWithersFor", "100"));
    }

    public int getChariotSpeed() {

        return Integer.parseInt(config.getOrDefault("theChariotSpeedLevel", "0"));
    }

    public void initHighPriestessNegateEffects() {
        highPriestessNegateEffects.add(StatusEffects.WITHER);
        highPriestessNegateEffects.add(StatusEffects.POISON);
        highPriestessNegateEffects.add(StatusEffects.SLOWNESS);
        highPriestessNegateEffects.add(StatusEffects.BLINDNESS);
        highPriestessNegateEffects.add(StatusEffects.DARKNESS);
        highPriestessNegateEffects.add(StatusEffects.NAUSEA);
        highPriestessNegateEffects.add(StatusEffects.WEAKNESS);
        highPriestessNegateEffects.add(StatusEffects.LEVITATION);
        highPriestessNegateEffects.add(StatusEffects.GLOWING);
        highPriestessNegateEffects.add(StatusEffects.UNLUCK);
    }

    public Set<StatusEffect> getHighPriestessNegateEffects() {
        return highPriestessNegateEffects;
    }

    public void initTurnToGoldItems() {
        turnToGoldItems.put(Items.CARROT, Items.GOLDEN_CARROT);
        turnToGoldItems.put(Items.APPLE, Items.GOLDEN_APPLE);
    }

    public HashMap<Item, Item> getTurnToGoldItems() {
        return turnToGoldItems;
    }

    public float getTemperanceMaxHealth() {

        return Float.parseFloat(config.getOrDefault("temperanceMaxHealth", "20"));
    }

    public void initAutoSmeltBlocks() {
        autoSmeltBlocks.put("minecraft:iron_ore", "minecraft:iron_ingot");
        autoSmeltBlocks.put("minecraft:gold_ore", "minecraft:gold_ingot");
        autoSmeltBlocks.put("minecraft:copper_ore", "minecraft:copper_ingot");

        autoSmeltBlocks.put("minecraft:nether_gold_ore", "minecraft:gold_ingot");

        autoSmeltBlocks.put("minecraft:deepslate_iron_ore", "minecraft:iron_ingot");
        autoSmeltBlocks.put("minecraft:deepslate_gold_ore", "minecraft:gold_ingot");
        autoSmeltBlocks.put("minecraft:deepslate_copper_ore", "minecraft:copper_ingot");

        autoSmeltBlocks.put("minecraft:stone", "minecraft:stone");
        autoSmeltBlocks.put("minecraft:cobblestone", "minecraft:stone");
        autoSmeltBlocks.put("minecraft:deepslate", "minecraft:deepslate");
        autoSmeltBlocks.put("minecraft:cobbled_deepslate", "minecraft:deepslate");

        autoSmeltBlocks.put("minecraft:raw_iron_block", "minecraft:iron_block");
        autoSmeltBlocks.put("minecraft:raw_gold_block", "minecraft:gold_block");
        autoSmeltBlocks.put("minecraft:raw_copper_block", "minecraft:copper_block");

        autoSmeltBlocks.put("minecraft:oak_log", "minecraft:charcoal");
        autoSmeltBlocks.put("minecraft:spruce_log", "minecraft:charcoal");
        autoSmeltBlocks.put("minecraft:birch_log", "minecraft:charcoal");
        autoSmeltBlocks.put("minecraft:jungle_log", "minecraft:charcoal");
        autoSmeltBlocks.put("minecraft:acacia_log", "minecraft:charcoal");
        autoSmeltBlocks.put("minecraft:dark_oak_log", "minecraft:charcoal");
        autoSmeltBlocks.put("minecraft:mangrove_log", "minecraft:charcoal");
        autoSmeltBlocks.put("minecraft:cherry_log", "minecraft:charcoal");
    }

    public HashMap<String, String> getAutoSmeltBlocks() {
        return autoSmeltBlocks;
    }

    public int getWorldSlowness() {

        return Integer.parseInt(config.getOrDefault("theWorldSlowness", "3"));
    }
}