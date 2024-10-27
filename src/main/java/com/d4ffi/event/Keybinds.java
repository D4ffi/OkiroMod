package com.d4ffi.event;

import com.d4ffi.network.OkiroPackets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;

public class Keybinds {

    public static final String KEYBIND_CATEGORY = "key.categories.okiro";
    public static final String KEYBIND_CARD = "key.okiro.card";

    public static KeyBinding okiroKeybind;

    public static void keyBindAction() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (okiroKeybind.wasPressed()) {
                ClientPlayNetworking.send(OkiroPackets.KEYBIND_PACKET, PacketByteBufs.empty());
            }
        });
    }

}
