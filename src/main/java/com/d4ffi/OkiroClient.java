package com.d4ffi;

import com.d4ffi.deck.DeckScreen;
import com.d4ffi.event.Keybinds;
import com.d4ffi.network.Server2Client;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class OkiroClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        Server2Client.registerPackets();
        Keybinds.registerKeys();
        HandledScreens.register(Okiro.CONTAINER_TYPE, DeckScreen::new);
    }

}
