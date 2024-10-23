package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

import static com.d4ffi.effect.OkiroEffect.MOON_GRACE;

public class Moon extends TarotCardManager {

    private static final double SPEED_BOOST = 0.05;

    public Moon(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        if (player.isSubmergedInWater()){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 60, 0, false, false));
            player.addStatusEffect(new StatusEffectInstance(MOON_GRACE, 60, 0, false, false));
        }
    }
}
