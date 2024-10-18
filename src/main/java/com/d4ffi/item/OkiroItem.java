package com.d4ffi.item;

import com.d4ffi.Okiro;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class OkiroItem {

    static TarotConfigManager configManager = new TarotConfigManager();

    static List<Object> activeCards = configManager.getActiveCards();

    public static void InstanceCards() {
        for (Object card : activeCards) {
            String cardName = card.toString();
            switch (cardName) {
                case "thefool":
                    registerItem("thefool", new Item(new FabricItemSettings()));
                    break;
                case "themagician":
                    registerItem("themagician", new Item(new FabricItemSettings()));
                    break;
                case "thehighpriestess":
                    registerItem("thehighpriestess", new Item(new FabricItemSettings()));
                    break;
                case "theempress":
                    registerItem("theempress", new Item(new FabricItemSettings()));
                    break;
                case "theemperor":
                    registerItem("theemperor", new Item(new FabricItemSettings()));
                    break;
                case "thehierophant":
                    registerItem("thehierophant", new Item(new FabricItemSettings()));
                    break;
                case "theloovers":
                    registerItem("theloovers", new Item(new FabricItemSettings()));
                    break;
                case "thechariot":
                    registerItem("thechariot", new Item(new FabricItemSettings()));
                    break;
                case "strength":
                    registerItem("strength", new Item(new FabricItemSettings()));
                    break;
                case "thehermit":
                    registerItem("thehermit", new Item(new FabricItemSettings()));
                    break;
                case "wheeloffortune":
                    registerItem("wheeloffortune", new WheelOfFortune(new FabricItemSettings()));
                    break;
                case "justice":
                    registerItem("justice", new Item(new FabricItemSettings()));
                    break;
                case "thehangedman":
                    registerItem("thehangedman", new Item(new FabricItemSettings()));
                    break;
                case "death":
                    registerItem("death", new Item(new FabricItemSettings()));
                    break;
                case "temperance":
                    registerItem("temperance", new Item(new FabricItemSettings()));
                    break;
                case "thedevil":
                    registerItem("thedevil", new Item(new FabricItemSettings()));
                    break;
                case "thetower":
                    registerItem("thetower", new Item(new FabricItemSettings()));
                    break;
                case "thestar":
                    registerItem("thestar", new Star(new FabricItemSettings()));
                    break;
                case "themoon":
                    registerItem("themoon", new Item(new FabricItemSettings()));
                    break;
                case "thesun":
                    registerItem("thesun", new Item(new FabricItemSettings()));
                    break;
                case "judgement":
                    registerItem("judgement", new Item(new FabricItemSettings()));
                    break;
                case "theworld":
                    registerItem("theworld", new Item(new FabricItemSettings()));
                    break;
                default:
                    Okiro.LOGGER.warn("Unknown card: " + cardName);
                    break;
            }
        }
    }
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Okiro.MOD_ID, name), item);
    }
    public static void registerItems(){
        Okiro.LOGGER.info("Registering OKIRO ITEMS . . .");
        InstanceCards();
    }
}
