package com.d4ffi.event;

import com.d4ffi.item.cards.Hermit;
import com.d4ffi.tarotCard.IPlayerManager;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ElytraFlightEvent implements EntityElytraEvents.Custom {

    @Override
    public boolean useCustomElytra(LivingEntity entity, boolean tickElytra) {
        if (entity instanceof PlayerEntity player) {
            // Manejar solo este jugador específico
            IPlayerManager playerManager = (IPlayerManager) player;
            if (playerManager.getActiveCard(Hermit.class)) {
                return true; // Aplicar vuelo personalizado solo para este jugador si tiene la carta
            }
            return false; // No aplicar vuelo personalizado para este jugador si no tiene la carta
        }
        return false; // No es un jugador
    }

}

