package com.d4ffi.item;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;

public class Devil extends TarotCardManager {

    public Devil(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 60, 0, true, true, true));
        playerAOE(player);
    }

    @Override
    public void playerAOE(PlayerEntity player) {
        for (PiglinEntity piglin : player.getWorld().getEntitiesByClass(PiglinEntity.class, player.getBoundingBox().expand(10), entity -> true)) {
            makePiglinFriendly(piglin, player);
        }
        for (PiglinBruteEntity brute : player.getWorld().getEntitiesByClass(PiglinBruteEntity.class, player.getBoundingBox().expand(10), entity -> true)) {
            makePiglinFriendly(brute, player);
        }
    }

    private void makePiglinFriendly(PiglinEntity piglin, PlayerEntity playerEntity) {
        // Limpiar todos los estados de agresión
        if (piglin.getBrain().hasMemoryModule(MemoryModuleType.ANGRY_AT)){
            piglin.getBrain().forget(MemoryModuleType.ANGRY_AT);
        }

        // Olvidar otros estados que podrían causar agresión
        piglin.getBrain().forget(MemoryModuleType.ATTACK_TARGET);
        piglin.getBrain().forget(MemoryModuleType.HUNTED_RECENTLY);
        piglin.getBrain().forget(MemoryModuleType.HURT_BY);
        piglin.getBrain().forget(MemoryModuleType.HURT_BY_ENTITY);

        // Establecer estados amigables
        piglin.setAttacking(false);
        piglin.setDancing(true);
        piglin.setTarget(null);

        // Opcional: Hacer que el piglin admire un objeto para mantenerlo distraído
        piglin.getBrain().remember(MemoryModuleType.ADMIRING_ITEM, true, 200L);

        // Establecer un tiempo de pacificación
        piglin.getBrain().remember(MemoryModuleType.PACIFIED, true, 200L);
    }
    private void makePiglinFriendly(PiglinBruteEntity piglin, PlayerEntity player){
        // Limpiar todos los estados de agresión
        if (piglin.getBrain().hasMemoryModule(MemoryModuleType.ANGRY_AT)){
            piglin.getBrain().forget(MemoryModuleType.ANGRY_AT);
        }

        // Olvidar otros estados que podrían causar agresión
        piglin.getBrain().forget(MemoryModuleType.ATTACK_TARGET);
        piglin.getBrain().forget(MemoryModuleType.HUNTED_RECENTLY);
        piglin.getBrain().forget(MemoryModuleType.HURT_BY);
        piglin.getBrain().forget(MemoryModuleType.HURT_BY_ENTITY);

        // Establecer estados amigables
        piglin.setAttacking(false);
        piglin.setTarget(null);

        // Opcional: Hacer que el piglin admire un objeto para mantenerlo distraído
        piglin.getBrain().remember(MemoryModuleType.ADMIRING_ITEM, true, 200L);

        // Establecer un tiempo de pacificación
        piglin.getBrain().remember(MemoryModuleType.PACIFIED, true, 200L);
    }

}
