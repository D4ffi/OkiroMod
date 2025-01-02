package com.d4ffi.datagen;

import com.d4ffi.datagen.OkiroTcLootTables.*;
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

public class OkiroTcDefaultLootTable {

    public static void setAncientCity(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(ANCIENT_CITY_CHEST)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.ANCIENT_CITY_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.ANCIENT_CITY_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }

        if (id.equals(ANCIENT_CITY_ICEBOX)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.ANCIENT_CITY_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.ANCIENT_CITY_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setAbandonedMineshaft(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(ABANDONED_MINESHAFT)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.MINESHAFT_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.MINESHAFT_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setBuriedTreasure(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(BURIED_TREASURE)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BURIED_TREASURE_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BURIED_TREASURE_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setDesertTemple(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(DESERT_PYRAMID)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.DESERT_PYRAMID_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.DESERT_PYRAMID_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setIgloo(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(IGLOO)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.IGLOO_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.IGLOO_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setJungleTemple(Identifier id, LootTable.Builder tableBuilder) {
        if (id.equals(JUNGLE_TEMPLE)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.JUNGLE_TEMPLE_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.JUNGLE_TEMPLE_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setPillagerOutpost(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(PILLAGER_OUTPOST)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.PILLAGER_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.PILLAGER_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setRuinedPortal(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(RUINED_PORTAL)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BURIED_TREASURE_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BURIED_TREASURE_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setShipwreck(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(SHIPWRECK_TREASURE)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.SHIPWRECK_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.SHIPWRECK_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setDungeon(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(SIMPLE_DUNGEON)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.DUNGEON_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.DUNGEON_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .with(ItemEntry.builder(OkiroItem.HIEROPHANT))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setStrongHold(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(STRONGHOLD_CORRIDOR)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.STRONGHOLD_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.STRONGHOLD_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }

        if(id.equals(STRONGHOLD_CROSSING)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.STRONGHOLD_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.CHARIOT))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.STRONGHOLD_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }

        if(id.equals(STRONGHOLD_LIBRARY)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.STRONGHOLD_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.STRONGHOLD_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.HERMIT))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setUnderwaterRuins(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(ANCIENT_CITY_ICEBOX)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.SHIPWRECK_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.HANGED_MAN))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.SHIPWRECK_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setMansion(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(WOODLAND_MANSION)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.MANSION_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.MANSION_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.WHEEL_OF_FORTUNE))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.WORLD))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setArcheologyPyramid(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(ARCH_PYRAMID)) {
            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.TRIAL_RUINS_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(special);
        }
    }

    public static void setTrailRuins(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(TRAIL_RUINS)) {
            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.TRIAL_RUINS_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE))
                    .with(ItemEntry.builder(OkiroItem.DEVIL))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(special);
        }

        if(id.equals(TRAIL_RUINS_RARE)) {
            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.TRIAL_RUINS_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.HERMIT))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(special);
        }
    }

    public static void setBastion(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(BASTION_BRIDGE)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BASTION_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BASTION_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.DEVIL))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }

        if(id.equals(BASTION_TREASURE)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BASTION_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BASTION_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.FOOL))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .with(ItemEntry.builder(OkiroItem.EMPEROR))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.DEVIL))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setFortress(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(NETHER_BRIDGE)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.FORTRESS_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.MAGICIAN))
                    .with(ItemEntry.builder(OkiroItem.STRENGTH))
                    .with(ItemEntry.builder(OkiroItem.DEATH))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.FORTRESS_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.JUSTICE))
                    .with(ItemEntry.builder(OkiroItem.DEVIL))
                    .with(ItemEntry.builder(OkiroItem.SUN))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.LOVERS))
                    .with(ItemEntry.builder(OkiroItem.HIGH_PRIESTESS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

    public static void setEndCity(Identifier id, LootTable.Builder tableBuilder) {
        if(id.equals(END_CITY_TREASURE)) {
            LootPool.Builder common = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BASTION_COMMON.getChance()))
                    .with(ItemEntry.builder(OkiroItem.TOWER))
                    .with(ItemEntry.builder(OkiroItem.STAR))
                    .with(ItemEntry.builder(OkiroItem.MOON))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            LootPool.Builder special = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(CardsSpawnChance.BASTION_SPECIAL.getChance()))
                    .with(ItemEntry.builder(OkiroItem.EMPRESS))
                    .with(ItemEntry.builder(OkiroItem.HERMIT))
                    .with(ItemEntry.builder(OkiroItem.TEMPERANCE))
                    .with(ItemEntry.builder(OkiroItem.JUDGEMENT))
                    .with(ItemEntry.builder(OkiroItem.DEVIL))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));

            tableBuilder.pool(common);
            tableBuilder.pool(special);
        }
    }

}
