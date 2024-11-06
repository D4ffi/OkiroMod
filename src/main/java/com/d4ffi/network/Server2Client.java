package com.d4ffi.network;

import com.d4ffi.Okiro;
import com.d4ffi.item.OkiroItem;
import com.d4ffi.item.cards.Empress;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

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

