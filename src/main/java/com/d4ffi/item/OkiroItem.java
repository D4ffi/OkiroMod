package com.d4ffi.item;

import com.d4ffi.Okiro;
import com.d4ffi.item.cards.*;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class OkiroItem {

    static TarotConfigManager configManager = new TarotConfigManager();

    static List<Object> activeCards = configManager.getActiveCards();

    public static final Item DECK = Registry.register(Registries.ITEM, new Identifier(Okiro.MOD_ID, "deck"),
            new Deck(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));

    public static Item FOOL;
    public static Item MAGICIAN;
    public static Item HIGH_PRIESTESS;
    public static Item EMPRESS;
    public static Item EMPEROR;
    public static Item HIEROPHANT;
    public static Item LOVERS;
    public static Item CHARIOT;
    public static Item STRENGTH;
    public static Item HERMIT;
    public static Item WHEEL_OF_FORTUNE;
    public static Item JUSTICE;
    public static Item HANGED_MAN;
    public static Item DEATH;
    public static Item TEMPERANCE;
    public static Item DEVIL;
    public static Item TOWER;
    public static Item STAR;
    public static Item MOON;
    public static Item SUN;
    public static Item JUDGEMENT;
    public static Item WORLD;

    public static List<Item> itemGroupCards = new ArrayList<>();

    public static void InstanceCards() {
        for (Object card : activeCards) {
            String cardName = card.toString();
            switch (cardName) {
                case "thefool":
                    FOOL = registerItem("thefool", new Fool(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(FOOL);
                    break;
                case "themagician":
                    MAGICIAN = registerItem("themagician", new Magician(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(MAGICIAN);
                    break;
                case "thehighpriestess":
                    HIGH_PRIESTESS = registerItem("thehighpriestess", new HighPriestess(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(HIGH_PRIESTESS);
                    break;
                case "theempress":
                    EMPRESS = registerItem("theempress", new Empress(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(EMPRESS);
                    break;
                case "theemperor":
                    EMPEROR = registerItem("theemperor", new Emperor(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(EMPEROR);
                    break;
                case "thehierophant":
                    HIEROPHANT = registerItem("thehierophant", new Hierophant(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(HIEROPHANT);
                    break;
                case "thelovers":
                    LOVERS = registerItem("thelovers", new Lovers(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(LOVERS);
                    break;
                case "thechariot":
                    CHARIOT = registerItem("thechariot", new Chariot(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(CHARIOT);
                    break;
                case "strength":
                    STRENGTH = registerItem("strength", new Strength(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(STRENGTH);
                    break;
                case "thehermit":
                    HERMIT = registerItem("thehermit", new Hermit(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(HERMIT);
                    break;
                case "wheeloffortune":
                    WHEEL_OF_FORTUNE = registerItem("wheeloffortune", new WheelOfFortune(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(WHEEL_OF_FORTUNE);
                    break;
                case "justice":
                    JUSTICE = registerItem("justice", new Justice(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(JUSTICE);
                    break;
                case "thehangedman":
                    HANGED_MAN = registerItem("thehangedman", new HangedMan(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(HANGED_MAN);
                    break;
                case "death":
                    DEATH = registerItem("death", new Death(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(DEATH);
                    break;
                case "temperance":
                    TEMPERANCE = registerItem("temperance", new Temperance(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(TEMPERANCE);
                    break;
                case "thedevil":
                    DEVIL = registerItem("thedevil", new Devil(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(DEVIL);
                    break;
                case "thetower":
                    TOWER = registerItem("thetower", new Tower(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(TOWER);
                    break;
                case "thestar":
                    STAR = registerItem("thestar", new Star(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(STAR);
                    break;
                case "themoon":
                    MOON = registerItem("themoon", new Moon(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(MOON);
                    break;
                case "thesun":
                    SUN = registerItem("thesun", new Sun(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(SUN);
                    break;
                case "judgement":
                    JUDGEMENT = registerItem("judgement", new Judgement(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(JUDGEMENT);
                    break;
                case "theworld":
                    WORLD = registerItem("theworld", new World(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
                    itemGroupCards.add(WORLD);
                    break;
                default:
                    Okiro.LOGGER.warn("Unknown card: {}", cardName);
                    break;
            }
        }
    }


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Okiro.MOD_ID, name), item);
    }

    public static void registerItems() {
        Okiro.LOGGER.info("Registering OKIRO ITEMS . . .");
        InstanceCards();
    }

    public static final ItemGroup OKIRO_CARD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Okiro.MOD_ID, "okiro_card_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.okiro_card_group")).icon(() ->
                    new ItemStack(cardWithProperties(JUDGEMENT).getItem())).entries(((displayContext, entries) -> {
                for (Item card : itemGroupCards) {
                    entries.add(cardWithProperties(card));
                }
                entries.add(DECK);
            })).build());

    public static void registerItemGroups() {
        Okiro.LOGGER.info("Registering OKIRO ITEM GROUPS . . .");
    }

    public static ItemStack cardWithProperties(Item card){
        ItemStack stack = new ItemStack(card);
        stack.getOrCreateNbt().putBoolean("active", true);
        //stack.getOrCreateNbt().putInt("mode", 0);
        return stack;
    }
}
