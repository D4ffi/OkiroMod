package com.d4ffi.item;

import com.d4ffi.OkiroTarotCards;
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

    public static final Item DECK = Registry.register(Registries.ITEM, new Identifier(OkiroTarotCards.MOD_ID, "deck"),
            new Deck(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));

    public static final Item FOOL = registerItem("thefool", new Fool(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item MAGICIAN = registerItem("themagician", new Magician(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item HIGH_PRIESTESS = registerItem("thehighpriestess", new HighPriestess(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item EMPRESS = registerItem("theempress", new Empress(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item EMPEROR = registerItem("theemperor", new Emperor(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item HIEROPHANT = registerItem("thehierophant", new Hierophant(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item LOVERS = registerItem("thelovers", new Lovers(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item CHARIOT = registerItem("thechariot", new Chariot(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item STRENGTH = registerItem("strength", new Strength(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item HERMIT = registerItem("thehermit", new Hermit(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item WHEEL_OF_FORTUNE = registerItem("wheeloffortune", new WheelOfFortune(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item JUSTICE = registerItem("justice", new Justice(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item HANGED_MAN = registerItem("thehangedman", new HangedMan(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item DEATH = registerItem("death", new Death(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item TEMPERANCE = registerItem("temperance", new Temperance(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item DEVIL = registerItem("thedevil", new Devil(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item TOWER = registerItem("thetower", new Tower(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item STAR = registerItem("thestar", new Star(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item MOON = registerItem("themoon", new Moon(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item SUN = registerItem("thesun", new Sun(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item JUDGEMENT = registerItem("judgement", new Judgement(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item WORLD = registerItem("theworld", new World(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OkiroTarotCards.MOD_ID, name), item);
    }

    public static void registerItems() {
        OkiroTarotCards.LOGGER.info("Registering OKIRO ITEMS . . .");

    }

    public static final ItemGroup OKIRO_CARD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(OkiroTarotCards.MOD_ID, "okiro_card_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.okiro_card_group")).icon(() ->
                    new ItemStack(cardWithProperties(JUDGEMENT).getItem())).entries(((displayContext, entries) -> {
                entries.add(DECK);
                entries.add(cardWithProperties(FOOL));
                entries.add(cardWithProperties(MAGICIAN));
                entries.add(cardWithProperties(HIGH_PRIESTESS));
                entries.add(cardWithProperties(EMPRESS));
                entries.add(cardWithProperties(EMPEROR));
                entries.add(cardWithProperties(HIEROPHANT));
                entries.add(cardWithProperties(LOVERS));
                entries.add(cardWithProperties(CHARIOT));
                entries.add(cardWithProperties(STRENGTH));
                entries.add(cardWithProperties(HERMIT));
                entries.add(cardWithProperties(WHEEL_OF_FORTUNE));
                entries.add(cardWithProperties(JUSTICE));
                entries.add(cardWithProperties(HANGED_MAN));
                entries.add(cardWithProperties(DEATH));
                entries.add(cardWithProperties(TEMPERANCE));
                entries.add(cardWithProperties(DEVIL));
                entries.add(cardWithProperties(TOWER));
                entries.add(cardWithProperties(STAR));
                entries.add(cardWithProperties(MOON));
                entries.add(cardWithProperties(SUN));
                entries.add(cardWithProperties(JUDGEMENT));
                entries.add(cardWithProperties(WORLD));

            })).build());

    public static void registerItemGroups() {
        OkiroTarotCards.LOGGER.info("Registering OKIRO ITEM GROUPS . . .");
    }

    public static ItemStack cardWithProperties(Item card){
        ItemStack stack = new ItemStack(card);
        stack.getOrCreateNbt().putBoolean("active", true);
        return stack;
    }
}
