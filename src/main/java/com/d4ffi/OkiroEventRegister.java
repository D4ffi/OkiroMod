package com.d4ffi;

import com.d4ffi.event.*;
import com.d4ffi.item.cards.Emperor;
import com.d4ffi.item.cards.HighPriestess;
import com.d4ffi.tarotCard.IPlayerManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.ActionResult;

public class OkiroEventRegister {

    public static void registerEvents() {
        AttackEntityCallback.EVENT.register(new AttackEvent());
        PlayerBlockBreakEvents.BEFORE.register(new AutoSmeltEvent());
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!player.getWorld().isClient && entity instanceof VillagerEntity){
                IPlayerManager playerManager = (IPlayerManager) player;
                if (playerManager.getActiveCard(Emperor.class)){
                    VillagerTradeEvent.modifyVillagerTrades(player, (VillagerEntity) entity);
                } else {
                    VillagerTradeEvent.randomizeTradesPrices((VillagerEntity) entity);
                }
                return ActionResult.PASS;
            }
            return ActionResult.PASS;
        });
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getPlayerManager().getPlayerList().forEach(player -> {
                IPlayerManager playerManager = (IPlayerManager) player;
                if (playerManager.getActiveCard(HighPriestess.class)){
                    CancelNegativeEffectEvent.cancelNegativeEffect(player);
                }
            });
        });
        ServerTickEvents.START_SERVER_TICK.register(new JusticeEvent());


    }
}
