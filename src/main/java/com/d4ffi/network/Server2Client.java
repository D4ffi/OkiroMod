package com.d4ffi.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;

public class Server2Client {

    public static void registerPackets() {

        ClientPlayNetworking.registerGlobalReceiver(OkiroPackets.EMPRESS_FAILED, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                assert client.player != null;
                client.player.sendMessage(Text.translatable("message.okiro.empress_failed"), true);
            });
        });

    }
}

