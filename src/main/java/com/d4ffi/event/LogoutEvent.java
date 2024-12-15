package com.d4ffi.event;

import com.d4ffi.tarotCard.IPlayerManager;
import com.d4ffi.tarotCard.PlayerDataStorage;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import java.util.UUID;

public abstract class LogoutEvent implements ServerPlayConnectionEvents.Disconnect, IPlayerManager {


    @Override
    public void onPlayDisconnect(ServerPlayNetworkHandler serverPlayNetworkHandler, MinecraftServer minecraftServer) {
        UUID playerUUID = serverPlayNetworkHandler.player.getUuid();
        PlayerDataStorage.setLostHearts(playerUUID, lostHearts(serverPlayNetworkHandler) );
    }

    public float lostHearts(ServerPlayNetworkHandler serverhandler){
        PlayerEntity player = serverhandler.player;
        return ((IPlayerManager) player).getLostHearts(player);

    }
}
