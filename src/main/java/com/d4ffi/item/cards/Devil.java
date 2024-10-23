package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

import java.util.*;

public class Devil extends TarotCardManager {
    private static final Map<UUID, Boolean> pacifiedPiglins = new HashMap<>();
    private static final int EFFECT_RANGE = 10;

    public Devil(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        // Dar resistencia al fuego al jugador
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 60, 0, true, true, true));
        playerAOE(player);
    }

    @Override
    public void playerAOE(PlayerEntity player) {

        checkAndRestorePiglinsOutOfRange(player);

        // Procesar Piglins normales
        player.getWorld().getEntitiesByClass(PiglinEntity.class,
                        player.getBoundingBox().expand(EFFECT_RANGE),
                        entity -> true)
                .forEach(piglin -> makePiglinFriendly(piglin, player));

        // Procesar Piglin Brutes
        player.getWorld().getEntitiesByClass(PiglinBruteEntity.class,
                        player.getBoundingBox().expand(EFFECT_RANGE),
                        entity -> true)
                .forEach(brute -> makePiglinFriendly(brute, player));
    }

    private void makePiglinFriendly(PiglinEntity piglin, PlayerEntity playerEntity) {
        UUID piglinId = piglin.getUuid();
        pacifiedPiglins.putIfAbsent(piglinId, true);

        piglin.getBrain().doExclusively(Activity.IDLE);

        if (!piglin.isDancing()) {
            piglin.setDancing(true);
        }

        piglin.setAiDisabled(true);
        piglin.setAttacking(false);
        piglin.setTarget(null);
    }

    private void makePiglinFriendly(PiglinBruteEntity piglin, PlayerEntity player) {
        UUID piglinId = piglin.getUuid();
        pacifiedPiglins.putIfAbsent(piglinId, true);

        piglin.getBrain().doExclusively(Activity.IDLE);
        piglin.setAiDisabled(true);
        piglin.setAttacking(false);
        piglin.setTarget(null);
    }

    private void checkAndRestorePiglinsOutOfRange(PlayerEntity player) {
        List<UUID> toRemove = new ArrayList<>();
        Box effectBox = player.getBoundingBox().expand(EFFECT_RANGE);

        // Comprobar Piglins normales
        player.getWorld().getEntitiesByClass(PiglinEntity.class,
                        player.getBoundingBox().expand(EFFECT_RANGE * 2), // Buscar en un área más grande
                        entity -> pacifiedPiglins.containsKey(entity.getUuid()))
                .forEach(piglin -> {
                    if (!effectBox.contains(piglin.getPos())) {
                        restorePiglin(piglin);
                        toRemove.add(piglin.getUuid());
                    }
                });

        // Comprobar Piglin Brutes
        player.getWorld().getEntitiesByClass(PiglinBruteEntity.class,
                        player.getBoundingBox().expand(EFFECT_RANGE * 2), // Buscar en un área más grande
                        entity -> pacifiedPiglins.containsKey(entity.getUuid()))
                .forEach(brute -> {
                    if (!effectBox.contains(brute.getPos())) {
                        restorePiglinBrute(brute);
                        toRemove.add(brute.getUuid());
                    }
                });

        // Remover los Piglins restaurados del Map
        toRemove.forEach(pacifiedPiglins::remove);
    }

    private void restorePiglin(PiglinEntity piglin) {
        piglin.setAiDisabled(false);
        piglin.setDancing(false);
        piglin.getBrain().refreshActivities(1L, 20);
    }

    private void restorePiglinBrute(PiglinBruteEntity brute) {
        brute.setAiDisabled(false);
        brute.getBrain().refreshActivities(1L, 20);
    }

    @Override
    public void deactivateCard(PlayerEntity player) {
        List<UUID> toRemove = new ArrayList<>();

        // Restaurar la IA de los Piglins normales
        player.getWorld().getEntitiesByClass(PiglinEntity.class,
                        player.getBoundingBox().expand(EFFECT_RANGE),
                        entity -> pacifiedPiglins.containsKey(entity.getUuid()))
                .forEach(piglin -> {
                    restorePiglin(piglin);
                    toRemove.add(piglin.getUuid());
                });

        // Restaurar la IA de los Piglin Brutes
        player.getWorld().getEntitiesByClass(PiglinBruteEntity.class,
                        player.getBoundingBox().expand(EFFECT_RANGE),
                        entity -> pacifiedPiglins.containsKey(entity.getUuid()))
                .forEach(brute -> {
                    restorePiglinBrute(brute);
                    toRemove.add(brute.getUuid());
                });

        // Remover los Piglins restaurados del Map
        toRemove.forEach(pacifiedPiglins::remove);
    }
}