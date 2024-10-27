package com.d4ffi.network;

import com.d4ffi.Okiro;
import com.d4ffi.item.cards.Empress;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class Server2Client {

    static List<Class<?>> cardsWithKeyBindResponse = new ArrayList<>();

    public static void registerPackets() {
        ServerPlayNetworking.registerGlobalReceiver(OkiroPackets.KEYBIND_PACKET, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
            });
        });
    }

    private static void getCardToActivate(PlayerEntity player) {
        for (Class<?> card : cardsWithKeyBindResponse) {
            if (player.getMainHandStack().getClass().equals(card)) {

            }
        }
    }

    public static void initCardWithKeyResponse() {
        TarotConfigManager configManager = new TarotConfigManager();
        List<Object> cardList = configManager.getActiveCards();

        for (Object card : cardList) {
            switch (card.toString()){
                case "theempress":
                    cardsWithKeyBindResponse.add(Empress.class);
                    break;
                default:
                    Okiro.LOGGER.info("No card found");
                    break;
            }
        }
    }
}


