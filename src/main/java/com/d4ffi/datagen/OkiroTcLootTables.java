package com.d4ffi.datagen;

import com.d4ffi.item.OkiroItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class OkiroTcLootTables {

    // CHANCE OF SPAWNING
    private static final float COMMON_SPAWN_CHANCE = 0.20f;
    private static final float SPECIAL_SPAWN_CHANCE = 0.10f;
    private static final float COMMON_TRAIL_RUIN = 0.35f;
    private static final float SPECIAL_TRAIL_RUIN = 0.30f;

    //ARCHEOLOGY CHESTS

    private static final Identifier ARCH_PYRAMID = new Identifier("minecraft", "archaeology/desert_pyramid");
    private static final Identifier TRAIL_RUINS = new Identifier("minecraft", "archaeology/trail_ruins_common");
    private static final Identifier TRAIL_RUINS_RARE = new Identifier("minecraft", "archaeology/trail_ruins_rare");


    // OVERWORLD CHESTS

    private static final Identifier ABANDONED_MINESHAFT = new Identifier("minecraft", "chests/abandoned_mineshaft");
    private static final Identifier ANCIENT_CITY_CHEST = new Identifier("minecraft", "chests/ancient_city");
    private static final Identifier ANCIENT_CITY_ICEBOX = new Identifier("minecraft", "chests/ancient_city_ice_box");
    private static final Identifier BURIED_TREASURE = new Identifier("minecraft", "chests/buried_treasure");
    private static final Identifier DESERT_PYRAMID = new Identifier("minecraft", "chests/desert_pyramid");
    private static final Identifier IGLOO = new Identifier("minecraft", "chests/igloo_chest");
    private static final Identifier JUNGLE_TEMPLE = new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier PILLAGER_OUTPOST = new Identifier("minecraft", "chests/pillager_outpost");
    private static final Identifier RUINED_PORTAL = new Identifier("minecraft", "chests/ruined_portal");
    private static final Identifier SHIPWRECK_TREASURE = new Identifier("minecraft", "chests/shipwreck_treasure");
    private static final Identifier SIMPLE_DUNGEON = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier STRONGHOLD_CORRIDOR = new Identifier("minecraft", "chests/stronghold_corridor");
    private static final Identifier STRONGHOLD_CROSSING = new Identifier("minecraft", "chests/stronghold_crossing");
    private static final Identifier STRONGHOLD_LIBRARY = new Identifier("minecraft", "chests/stronghold_library");
    private static final Identifier UNDERWATER_RUIN_BIG = new Identifier("minecraft", "chests/underwater_ruin_big");
    private static final Identifier WOODLAND_MANSION = new Identifier("minecraft", "chests/woodland_mansion");

    // NETHER CHESTS

    private static final Identifier BASTION_BRIDGE = new Identifier("minecraft", "chests/bastion_bridge");
    private static final Identifier BASTION_TREASURE = new Identifier("minecraft", "chests/bastion_treasure");
    private static final Identifier NETHER_BRIDGE = new Identifier("minecraft", "chests/nether_bridge");

    // END CHESTS

    private static final Identifier END_CITY_TREASURE = new Identifier("minecraft", "chests/end_city_treasure");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            setCavesLootTable(id, tableBuilder);
            setDesertPyramidLootTable(id, tableBuilder);
            setTrailRuinsLootTable(id, tableBuilder);
            setOceanLootTables(id, tableBuilder);
            setNetherLootTables(id, tableBuilder);
            setPillagerRelatedLootTable(id, tableBuilder);
            setExtraLootTable(id, tableBuilder);

        }));
    }

    private static void setCavesLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(ABANDONED_MINESHAFT)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN).weight(3))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT).weight(3))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH).weight(1))
                    .with(ItemEntry.builder(OkiroItem.DEATH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.TOWER).weight(1))
                    .with(ItemEntry.builder(OkiroItem.STAR).weight(4))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE).weight(2))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(ANCIENT_CITY_CHEST)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT).weight(3))
                    .with(ItemEntry.builder(OkiroItem.TOWER).weight(1))
                    .with(ItemEntry.builder(OkiroItem.STAR).weight(4))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(3))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT).weight(2))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(ANCIENT_CITY_ICEBOX)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.FOOL).weight(3))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT).weight(4))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS).weight(3))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(STRONGHOLD_CROSSING)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT).weight(1))
                    .with(ItemEntry.builder(OkiroItem.DEATH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.TOWER).weight(1))
                    .with(ItemEntry.builder(OkiroItem.STAR).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(STRONGHOLD_CORRIDOR)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT).weight(2))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.DEATH).weight(1))
                    .with(ItemEntry.builder(OkiroItem.STAR).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(STRONGHOLD_LIBRARY)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.DEATH).weight(1))
                    .with(ItemEntry.builder(OkiroItem.MOON).weight(3))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.LOVERS).weight(4))
                    .with(ItemEntry.builder(OkiroItem.HERMIT).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(SIMPLE_DUNGEON)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.FOOL).weight(3))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT).weight(2))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS).weight(1))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE).weight(4))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(3))
                    .with(ItemEntry.builder(OkiroItem.SUN).weight(2))
                    .with(ItemEntry.builder(OkiroItem.WORLD).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }


    }

    private static void setDesertPyramidLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(DESERT_PYRAMID)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN).weight(4))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(ARCH_PYRAMID)) {
            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_TRAIL_RUIN))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS).weight(3))
                    .with(ItemEntry.builder(OkiroItem.LOVERS).weight(5))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(specialPool);
        }
    }

    private static void setTrailRuinsLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(TRAIL_RUINS)) {
            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_TRAIL_RUIN))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS).weight(1))
                    .with(ItemEntry.builder(OkiroItem.LOVERS).weight(2))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE).weight(3))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));
            tableBuilder.pool(specialPool);
        }

        if (id.equals(TRAIL_RUINS_RARE)) {
            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_TRAIL_RUIN))
                    .with(ItemEntry.builder(OkiroItem.HERMIT).weight(3))
                    .with(ItemEntry.builder(OkiroItem.LOVERS).weight(2))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE).weight(5))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));
            tableBuilder.pool(specialPool);
        }
    }

    private static void setOceanLootTables(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(SHIPWRECK_TREASURE)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN).weight(2))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT).weight(1))
                    .with(ItemEntry.builder(OkiroItem.MOON).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.WORLD).weight(3))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS).weight(1))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(3))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE).weight(3))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(UNDERWATER_RUIN_BIG)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH).weight(1))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN).weight(1))
                    .with(ItemEntry.builder(OkiroItem.TOWER).weight(1))
                    .with(ItemEntry.builder(OkiroItem.MOON).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.WORLD).weight(2))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(BURIED_TREASURE)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN).weight(1))
                    .with(ItemEntry.builder(OkiroItem.DEATH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.TOWER).weight(1))
                    .with(ItemEntry.builder(OkiroItem.STAR).weight(3))
                    .with(ItemEntry.builder(OkiroItem.MOON).weight(4))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.WORLD).weight(3))
                    .with(ItemEntry.builder(OkiroItem.SUN).weight(2))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(2))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE).weight(3))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

    }

    private static void setNetherLootTables(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(NETHER_BRIDGE)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN).weight(4))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.DEATH).weight(1))
                    .with(ItemEntry.builder(OkiroItem.TOWER).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS).weight(1))
                    .with(ItemEntry.builder(OkiroItem.LOVERS).weight(1))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(2))
                    .with(ItemEntry.builder(OkiroItem.DEVIL).weight(4))
                    .with(ItemEntry.builder(OkiroItem.SUN).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(BASTION_BRIDGE)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN).weight(2))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.DEATH).weight(2))
                    .with(ItemEntry.builder(OkiroItem.TOWER).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT).weight(1))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(2))
                    .with(ItemEntry.builder(OkiroItem.DEVIL).weight(4))
                    .with(ItemEntry.builder(OkiroItem.SUN).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(BASTION_TREASURE)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT).weight(3))
                    .with(ItemEntry.builder(OkiroItem.FOOL).weight(2))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR).weight(2))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS).weight(2))
                    .with(ItemEntry.builder(OkiroItem.DEVIL).weight(4))
                    .with(ItemEntry.builder(OkiroItem.SUN).weight(3))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

    }

    private static void setPillagerRelatedLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(PILLAGER_OUTPOST)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.FOOL).weight(4))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT).weight(3))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(2))
                    .with(ItemEntry.builder(OkiroItem.LOVERS).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(WOODLAND_MANSION)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.FOOL).weight(3))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS).weight(3))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT).weight(2))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS).weight(2))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(3))
                    .with(ItemEntry.builder(OkiroItem.WORLD).weight(3))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(IGLOO)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }
    }

    private static void setExtraLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(JUNGLE_TEMPLE)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE).weight(4))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT).weight(2))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE).weight(3))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS).weight(2))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(RUINED_PORTAL)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }

        if (id.equals(END_CITY_TREASURE)) {
            LootPool.Builder commonPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder specialPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.HERMIT))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(commonPool);
            tableBuilder.pool(specialPool);
        }
    }



}
