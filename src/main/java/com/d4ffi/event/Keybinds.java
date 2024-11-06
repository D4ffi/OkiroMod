package com.d4ffi.event;

import com.d4ffi.network.OkiroPackets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    public static final String KEYBIND_CATEGORY = "key.categories.okiro";
    public static final String KEYBIND_CARD = "key.okiro.card";

    private static boolean onground = true;

    public static KeyBinding okiroKeybind;

    public static void keyBindAction() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (okiroKeybind.wasPressed()) {
                ClientPlayNetworking.send(OkiroPackets.KEYBIND_PACKET, PacketByteBufs.empty());
                return;
            }

            if (onground && client.options.jumpKey.wasPressed()) {
                onground = false;
            }
            if (client.player != null){
                if (client.player.isOnGround()) {
                    onground = true;
                }
            }
            if (!onground && client.options.jumpKey.wasPressed()) {
                onground = true;
                ClientPlayNetworking.send(OkiroPackets.FOOL_DASH, PacketByteBufs.empty());
            }

        });
    }

    public static void registerKeys() {
        okiroKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEYBIND_CARD,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                KEYBIND_CATEGORY
        ));
        keyBindAction();
    }

}
