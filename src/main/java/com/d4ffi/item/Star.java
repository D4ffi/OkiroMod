package com.d4ffi.item;

import com.d4ffi.Okiro;
import com.d4ffi.tarotCard.ITarotCard;
import com.d4ffi.tarotCard.TarotManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Star extends TarotManager {

    public Star(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 240, 0));
    }
}
