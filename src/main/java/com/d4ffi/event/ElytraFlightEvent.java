package com.d4ffi.event;

import com.d4ffi.item.cards.Hermit;
import com.d4ffi.tarotCard.IPlayerManager;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ElytraFlightEvent implements EntityElytraEvents.Allow {

    @Override
    public boolean allowElytraFlight(LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity){
            IPlayerManager playerManager = (IPlayerManager) livingEntity;
            return playerManager.getActiveCard(Hermit.class);
        }
        return false;
    }

}
