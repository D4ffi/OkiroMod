package com.d4ffi.item;

import com.d4ffi.tarotCard.TarotManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class WheelOfFortune extends TarotManager {

    public WheelOfFortune(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 60, 2));
    }

}
