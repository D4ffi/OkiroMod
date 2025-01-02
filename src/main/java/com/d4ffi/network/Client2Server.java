package com.d4ffi.network;

import com.d4ffi.item.OkiroItem;
import com.d4ffi.item.cards.Empress;
import com.d4ffi.item.cards.Fool;
import com.d4ffi.item.cards.Hermit;
import com.d4ffi.tarotCard.IPlayerManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class Client2Server {
    public static void registerPackets() {
        ServerPlayNetworking.registerGlobalReceiver(OkiroPackets.KEYBIND_PACKET, (server, player, handler, buf, responseSender) -> server.execute(() -> getCardToActivate((player))));

        ServerPlayNetworking.registerGlobalReceiver(OkiroPackets.FOOL_DASH, (server, player, handler, buf, responseSender) -> server.execute(() -> {

            IPlayerManager playerManager = (IPlayerManager) player;

            if (playerManager.getActiveCard(Fool.class)) {
                Fool.activateKeyBindAbility(player);
            }
        }));
    }

    private static void getCardToActivate(PlayerEntity player) {

        IPlayerManager playerManager = (IPlayerManager) player;

        if (player.getMainHandStack().getItem().getClass().equals(Empress.class)) {
            if (!playerManager.isCardOnCooldown(player, Empress.class)) {
                Empress.activateKeyBindAbility(player);
            }

        } else if (player.getMainHandStack().getItem().getClass().equals(Hermit.class)) {
            if (!playerManager.isCardOnCooldown(player, Hermit.class)) {
                Hermit.activateKeyBindAbility(player);
            }
        }
    }
}
