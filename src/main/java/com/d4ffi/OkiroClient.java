package com.d4ffi;

import com.d4ffi.event.Keybinds;
import com.d4ffi.network.Client2Server;
import com.d4ffi.network.OkiroPackets;
import com.d4ffi.network.Server2Client;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.ScreenRect;

public class OkiroClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        Server2Client.registerPackets();
        Keybinds.registerKeys();


    }

}
