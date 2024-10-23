package com.d4ffi.event;

import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Set;

public class CancelNegativeEffectEvent {

    static TarotConfigManager configManager = new TarotConfigManager();
    static Set<StatusEffect> effects = configManager.getHighPriestessNegateEffects();

    public static void cancelNegativeEffect(PlayerEntity player) {

        for (StatusEffect effect : effects) {
            if (effect != null && player.hasStatusEffect(effect)) {
                player.removeStatusEffect(effect);
            }
        }

    }

}
