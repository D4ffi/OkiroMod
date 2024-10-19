package com.d4ffi.item;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.player.PlayerEntity;

public class Moon extends TarotCardManager {

    public Moon(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        //todo: add speed boost that only affects player when card is active and player is underwater
    }

    @Override
    public void deactivateCard(PlayerEntity player) {
        //todo: remove speed boost when card is deactivated
    }
}
