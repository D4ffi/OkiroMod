package com.d4ffi.item;

import com.d4ffi.Okiro;
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

import java.util.ArrayList;
import java.util.List;

public class OkiroItem {

    static TarotConfigManager configManager = new TarotConfigManager();

    static List<Object> activeCards = configManager.getActiveCards();

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

    static List<Item> itemGroupCards = new ArrayList<>();

    public static void InstanceCards() {
        for (Object card : activeCards) {
            String cardName = card.toString();
            switch (cardName) {
                case "thefool":
                    FOOL = registerItem("thefool", new Item(new FabricItemSettings()));
                    itemGroupCards.add(FOOL);
                    break;
                case "themagician":
                    MAGICIAN = registerItem("themagician", new Magician(new FabricItemSettings()));
                    itemGroupCards.add(MAGICIAN);
                    break;
                case "thehighpriestess":
                    HIGH_PRIESTESS = registerItem("thehighpriestess", new Item(new FabricItemSettings()));
                    itemGroupCards.add(HIGH_PRIESTESS);
                    break;
                case "theempress":
                    EMPRESS = registerItem("theempress", new Item(new FabricItemSettings()));
                    itemGroupCards.add(EMPRESS);
                    break;
                case "theemperor":
                    EMPEROR = registerItem("theemperor", new Emperor(new FabricItemSettings()));
                    itemGroupCards.add(EMPEROR);
                    break;
                case "thehierophant":
                    HIEROPHANT = registerItem("thehierophant", new Hierophant(new FabricItemSettings()));
                    itemGroupCards.add(HIEROPHANT);
                    break;
                case "thelovers":
                    LOVERS = registerItem("thelovers", new Lovers(new FabricItemSettings()));
                    itemGroupCards.add(LOVERS);
                    break;
                case "thechariot":
                    CHARIOT = registerItem("thechariot", new Chariot(new FabricItemSettings()));
                    itemGroupCards.add(CHARIOT);
                    break;
                case "strength":
                    STRENGTH = registerItem("strength", new Strength(new FabricItemSettings()));
                    itemGroupCards.add(STRENGTH);
                    break;
                case "thehermit":
                    HERMIT = registerItem("thehermit", new Item(new FabricItemSettings()));
                    itemGroupCards.add(HERMIT);
                    break;
                case "wheeloffortune":
                    WHEEL_OF_FORTUNE = registerItem("wheeloffortune", new WheelOfFortune(new FabricItemSettings()));
                    itemGroupCards.add(WHEEL_OF_FORTUNE);
                    break;
                case "justice":
                    JUSTICE = registerItem("justice", new Item(new FabricItemSettings()));
                    itemGroupCards.add(JUSTICE);
                    break;
                case "thehangedman":
                    HANGED_MAN = registerItem("thehangedman", new Item(new FabricItemSettings()));
                    itemGroupCards.add(HANGED_MAN);
                    break;
                case "death":
                    DEATH = registerItem("death", new Death(new FabricItemSettings()));
                    itemGroupCards.add(DEATH);
                    break;
                case "temperance":
                    TEMPERANCE = registerItem("temperance", new Temperance(new FabricItemSettings()));
                    itemGroupCards.add(TEMPERANCE);
                    break;
                case "thedevil":
                    DEVIL = registerItem("thedevil", new Devil(new FabricItemSettings()));
                    itemGroupCards.add(DEVIL);
                    break;
                case "thetower":
                    TOWER = registerItem("thetower", new Tower(new FabricItemSettings()));
                    itemGroupCards.add(TOWER);
                    break;
                case "thestar":
                    STAR = registerItem("thestar", new Star(new FabricItemSettings()));
                    itemGroupCards.add(STAR);
                    break;
                case "themoon":
                    MOON = registerItem("themoon", new Moon(new FabricItemSettings()));
                    itemGroupCards.add(MOON);
                    break;
                case "thesun":
                    SUN = registerItem("thesun", new Sun(new FabricItemSettings()));
                    itemGroupCards.add(SUN);
                    break;
                case "judgement":
                    JUDGEMENT = registerItem("judgement", new Item(new FabricItemSettings()));
                    itemGroupCards.add(JUDGEMENT);
                    break;
                case "theworld":
                    WORLD = registerItem("theworld", new World(new FabricItemSettings()));
                    itemGroupCards.add(WORLD);
                    break;
                default:
                    Okiro.LOGGER.warn("Unknown card: " + cardName);
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
                    new ItemStack(FOOL)).entries(((displayContext, entries) -> {
                for (Item card : itemGroupCards) {
                    entries.add(cardWithProperties(card));
                }
            })).build());

    public static void registerItemGroups() {
        Okiro.LOGGER.info("Registering OKIRO ITEM GROUPS . . .");
    }

    private static ItemStack cardWithProperties(Item card){
        ItemStack stack = new ItemStack(card);
        stack.getOrCreateNbt().putBoolean("active", true);
        stack.getOrCreateNbt().putInt("mode", 0);
        return stack;
    }
}
