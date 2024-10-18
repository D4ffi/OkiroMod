package com.d4ffi.item;

import com.d4ffi.Okiro;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OkiroItem {

    public static final Item STAR = registerItem("star", new Star(new FabricItemSettings()));
    public static final Item WHEEL_OF_FORTUNE = registerItem("wheel_of_fortune", new WheelOfFortune(new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Okiro.MOD_ID, name), item);
    }

    public static void registerItems(){
        Okiro.LOGGER.info("Registering OKIRO ITEMS . . .");
    }
}
