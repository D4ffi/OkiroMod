package com.d4ffi.tarotCard;

import com.d4ffi.OkiroTarotCards;
import com.moandjiezana.toml.Toml;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TarotConfigManager {

    private static final String CONFIG_FILE_NAME = "okiro_config.toml";
    private Toml config;
    private static Toml serverConfig;
    private static FileWriter fileWriter;
    private static final HashMap<String, String> autoSmeltBlocks = new HashMap<>();
    private static final HashMap<Item, Item> turnToGoldItems = new HashMap<>();
    private static final Set<StatusEffect> highPriestessNegateEffects = new HashSet<>();
    private static List<Class<?>> cardsWithKeyEffects = new ArrayList<>();

    public TarotConfigManager() {
        try {
            loadconfig();
            loadStaticConfig();
        } catch (IOException e) {
            OkiroTarotCards.LOGGER.info("Error loading config file || {}", e.getMessage());
        }
    }

    public static void loadStaticConfig() {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();

        // assumes there is a config file

        serverConfig = new Toml().read(configFile);
    }

    public void loadconfig() throws IOException {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                OkiroTarotCards.LOGGER.info("Error creating config file || {}", e.getMessage());
            }
        }

        config = new Toml().read(configFile);

        if (config.isEmpty()) {
            writeDefaultConfig(configFile);
        }
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
                    "Only the following cards will be active in the game",
                    "by default all 22 cards are active",
                    "activeCards = [\"thefool\",\"themagician\",\"thehighpriestess\",\"theempress\",\"theemperor\",\"thehierophant\",\"thelovers\",\"thechariot\",\"strength\",\"thehermit\",\"wheeloffortune\",\"justice\",\"thehangedman\",\"death\",\"temperance\",\"thedevil\",\"thetower\",\"thestar\",\"themoon\",\"thesun\",\"judgement\",\"theworld\"]");

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
                    "Does the lovers can regenerate nearby players and friendly mobs?",
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
        return config.getLong("updateTicks", 20L).intValue();
    }

    public List<Object> getActiveCards() {
        return config.getList("activeCards");
    }

    public boolean canLoversHealInRadius() {
        return config.getBoolean("theLoversHeal", false);
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

    public void initTurnToGoldItems(){
        turnToGoldItems.put(Items.CARROT, Items.GOLDEN_CARROT);
        turnToGoldItems.put(Items.APPLE, Items.GOLDEN_APPLE);
    }

    public HashMap<Item, Item> getTurnToGoldItems() {
        return turnToGoldItems;
    }

    public int getLoversHealAmount() {
        return config.getLong("theLoversHealAmount", 1L).intValue();
    }

    public float getTemperanceMaxHealth() {
        return config.getLong("temperanceMaxHealth", 20L);
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
        return config.getLong("theWorldSlowness", 3L).intValue();
    }
}