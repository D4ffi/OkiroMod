package com.d4ffi.datagen;

import com.d4ffi.tarotCard.TarotConfigManager;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.util.Identifier;

import static com.d4ffi.datagen.OkiroTcCustomLootTable.*;
import static com.d4ffi.datagen.OkiroTcDefaultLootTable.*;

public class OkiroTcLootTables {

    static TarotConfigManager configManager = new TarotConfigManager();

    // CHANCE OF SPAWNING
    public static final float SPAWN_CHANCE = configManager.getSpawnChance();
    public static final boolean USE_CUSTOM_SPAWN_CHANCE = configManager.getSpecialSpawnChance();
    public static final float COMMON_TRAIL_RUIN = 0.35f;
    public static final float SPECIAL_TRAIL_RUIN = 0.30f;

    //ARCHEOLOGY CHESTS

    public static final Identifier ARCH_PYRAMID = new Identifier("minecraft", "archaeology/desert_pyramid");
    public static final Identifier TRAIL_RUINS = new Identifier("minecraft", "archaeology/trail_ruins_common");
    public static final Identifier TRAIL_RUINS_RARE = new Identifier("minecraft", "archaeology/trail_ruins_rare");

    // OVERWORLD CHESTS

    public static final Identifier ABANDONED_MINESHAFT = new Identifier("minecraft", "chests/abandoned_mineshaft");
    public static final Identifier ANCIENT_CITY_CHEST = new Identifier("minecraft", "chests/ancient_city");
    public static final Identifier ANCIENT_CITY_ICEBOX = new Identifier("minecraft", "chests/ancient_city_ice_box");
    public static final Identifier BURIED_TREASURE = new Identifier("minecraft", "chests/buried_treasure");
    public static final Identifier DESERT_PYRAMID = new Identifier("minecraft", "chests/desert_pyramid");
    public static final Identifier IGLOO = new Identifier("minecraft", "chests/igloo_chest");
    public static final Identifier JUNGLE_TEMPLE = new Identifier("minecraft", "chests/jungle_temple");
    public static final Identifier PILLAGER_OUTPOST = new Identifier("minecraft", "chests/pillager_outpost");
    public static final Identifier RUINED_PORTAL = new Identifier("minecraft", "chests/ruined_portal");
    public static final Identifier SHIPWRECK_TREASURE = new Identifier("minecraft", "chests/shipwreck_treasure");
    public static final Identifier SIMPLE_DUNGEON = new Identifier("minecraft", "chests/simple_dungeon");
    public static final Identifier STRONGHOLD_CORRIDOR = new Identifier("minecraft", "chests/stronghold_corridor");
    public static final Identifier STRONGHOLD_CROSSING = new Identifier("minecraft", "chests/stronghold_crossing");
    public static final Identifier STRONGHOLD_LIBRARY = new Identifier("minecraft", "chests/stronghold_library");
    public static final Identifier UNDERWATER_RUIN_BIG = new Identifier("minecraft", "chests/underwater_ruin_big");
    public static final Identifier WOODLAND_MANSION = new Identifier("minecraft", "chests/woodland_mansion");

    // NETHER CHESTS

    public static final Identifier BASTION_BRIDGE = new Identifier("minecraft", "chests/bastion_bridge");
    public static final Identifier BASTION_TREASURE = new Identifier("minecraft", "chests/bastion_treasure");
    public static final Identifier NETHER_BRIDGE = new Identifier("minecraft", "chests/nether_bridge");

    // END CHESTS

    public static final Identifier END_CITY_TREASURE = new Identifier("minecraft", "chests/end_city_treasure");

    public static void modifyLootTables() {
            LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

                if (USE_CUSTOM_SPAWN_CHANCE){
                    setCustomCaveLootTable(id, tableBuilder);
                    setDesertPyramidLootTable(id, tableBuilder);
                    setTrailRuinsLootTable(id, tableBuilder);
                    setOceanLootTables(id, tableBuilder);
                    setNetherLootTables(id, tableBuilder);
                    setPillagerRelatedLootTable(id, tableBuilder);
                    setExtraLootTable(id, tableBuilder);
                } else {
                    setAncientCity(id, tableBuilder);
                    setAbandonedMineshaft(id, tableBuilder);
                    setBuriedTreasure(id, tableBuilder);
                    setDesertTemple(id, tableBuilder);
                    setIgloo(id, tableBuilder);
                    setJungleTemple(id, tableBuilder);
                    setPillagerOutpost(id, tableBuilder);
                    setRuinedPortal(id, tableBuilder);
                    setShipwreck(id, tableBuilder);
                    setDungeon(id, tableBuilder);
                    setStrongHold(id, tableBuilder);
                    setUnderwaterRuins(id, tableBuilder);
                    setMansion(id, tableBuilder);
                    setArcheologyPyramid(id, tableBuilder);
                    setTrailRuins(id, tableBuilder);
                    setBastion(id, tableBuilder);
                    setFortress(id, tableBuilder);
                    setEndCity(id, tableBuilder);
                }
            }));
    }
}
