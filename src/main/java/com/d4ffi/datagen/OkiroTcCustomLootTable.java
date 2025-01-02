package com.d4ffi.datagen;

import com.d4ffi.item.OkiroItem;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import static com.d4ffi.datagen.OkiroTcLootTables.*;

public class OkiroTcCustomLootTable {

    public static void setCustomCaveLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(ABANDONED_MINESHAFT)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(ANCIENT_CITY_CHEST)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(ANCIENT_CITY_ICEBOX)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(STRONGHOLD_CROSSING)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);

        }

        if (id.equals(STRONGHOLD_CORRIDOR)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(STRONGHOLD_LIBRARY)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.HERMIT))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(SIMPLE_DUNGEON)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }


    }

    public static void setDesertPyramidLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(DESERT_PYRAMID)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(ARCH_PYRAMID)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS).weight(3))
                    .with(ItemEntry.builder(OkiroItem.LOVERS).weight(5))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE).weight(1))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }
    }

    public static void setTrailRuinsLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(TRAIL_RUINS)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(COMMON_TRAIL_RUIN))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));
            tableBuilder.pool(customPool);
        }

        if (id.equals(TRAIL_RUINS_RARE)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPECIAL_TRAIL_RUIN))
                    .with(ItemEntry.builder(OkiroItem.HERMIT))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));
            tableBuilder.pool(customPool);
        }
    }

    public static void setOceanLootTables(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(SHIPWRECK_TREASURE)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(UNDERWATER_RUIN_BIG)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(BURIED_TREASURE)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

    }

    public static void setNetherLootTables(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(NETHER_BRIDGE)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.DEVIL))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(BASTION_BRIDGE)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.DEVIL))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(BASTION_TREASURE)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .with(ItemEntry.builder(OkiroItem.DEVIL))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

    }

    public static void setPillagerRelatedLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(PILLAGER_OUTPOST)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(WOODLAND_MANSION)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(IGLOO)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }
    }

    public static void setExtraLootTable(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(JUNGLE_TEMPLE)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(RUINED_PORTAL)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }

        if (id.equals(END_CITY_TREASURE)) {
            LootPool.Builder customPool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(SPAWN_CHANCE))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.HERMIT))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(customPool);
        }
    }

}
