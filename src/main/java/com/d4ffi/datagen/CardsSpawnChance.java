package com.d4ffi.datagen;

public enum CardsSpawnChance {

    ANCIENT_CITY_COMMON(0.05f),
    ANCIENT_CITY_SPECIAL(0.012f),
    BASTION_COMMON(0.1f),
    BASTION_SPECIAL(0.083f),
    BURIED_TREASURE_COMMON(0.2f), // USED ALSO IN RUINED_PORTAL
    BURIED_TREASURE_SPECIAL(0.15f), // USED ALSO IN RUINED_PORTAL
    DESERT_PYRAMID_COMMON(0.113f),
    DESERT_PYRAMID_SPECIAL(0.085f),
    DUNGEON_COMMON(0.15f),
    DUNGEON_SPECIAL(0.085f),
    END_CITY_COMMON(0.067f),
    END_CITY_SPECIAL(0.04f),
    FORTRESS_COMMON(0.08f),
    FORTRESS_SPECIAL(0.067f),
    IGLOO_COMMON(0.08f),
    IGLOO_SPECIAL(0.067f),
    JUNGLE_TEMPLE_COMMON(0.33f),
    JUNGLE_TEMPLE_SPECIAL(0.15f),
    MANSION_COMMON(0.5f),
    MANSION_SPECIAL(0.2f),
    MINESHAFT_COMMON(0.083f),
    MINESHAFT_SPECIAL(0.073f),
    PILLAGER_COMMON(0.18f),
    PILLAGER_SPECIAL(0.1f),
    SHIPWRECK_COMMON(0.16f), // USED ALSO IN UNDERWATER_RUIN
    SHIPWRECK_SPECIAL(0.1f), // USED ALSO IN UNDERWATER_RUIN
    STRONGHOLD_COMMON(0.1f),
    STRONGHOLD_SPECIAL(0.08f),
    TRIAL_RUINS_COMMON(0.083f),
    TRIAL_RUINS_SPECIAL(0.067f);

    private final float chance;

    CardsSpawnChance(float chance) {
        this.chance = chance;

    }

    public float getChance() {
        return chance;
    }

}