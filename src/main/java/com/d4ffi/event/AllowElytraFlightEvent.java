package com.d4ffi.event;

import com.d4ffi.item.cards.Hermit;
import com.d4ffi.tarotCard.IPlayerManager;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class AllowElytraFlightEvent implements EntityElytraEvents.Allow {

    @Override
    public boolean allowElytraFlight(LivingEntity entity) {
        if (entity instanceof PlayerEntity player) {
            // Manejar solo este jugador específico
            IPlayerManager playerManager = (IPlayerManager) player;
            if (playerManager.getActiveCard(Hermit.class)) {
                return true; // Permitir vuelo solo para este jugador si tiene la carta
            }
            return false; // No permitir vuelo para este jugador si no tiene la carta
        }
        return false; // No es un jugador
    }
}
