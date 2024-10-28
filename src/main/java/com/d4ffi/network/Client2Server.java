package com.d4ffi.network;

import com.d4ffi.item.OkiroItem;
import com.d4ffi.item.cards.Empress;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class Client2Server {
    public static void registerPackets() {
        ServerPlayNetworking.registerGlobalReceiver(OkiroPackets.KEYBIND_PACKET, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                getCardToActivate((player));
            });
        });
    }

    private static void getCardToActivate(PlayerEntity player) {
        for (Item card : OkiroItem.itemGroupCards) {
            if (player.getMainHandStack().getItem().getClass().equals(card.getClass()) || player.getOffHandStack().getItem().getClass().equals(card.getClass())) {
                if (card.getClass().equals(Empress.class)){
                    Empress.activateKeyBindAbility(player);
                    break;
                }
            }
        }
    }
}
