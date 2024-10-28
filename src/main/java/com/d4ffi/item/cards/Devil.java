package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class Devil extends TarotCardManager {

    public Devil(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        // Dar resistencia al fuego al jugador
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 60, 0, true, true, true));
    }
}