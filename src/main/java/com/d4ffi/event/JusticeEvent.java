package com.d4ffi.event;


import com.d4ffi.item.cards.Justice;
import com.d4ffi.tarotCard.IPlayerManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public class JusticeEvent implements ServerTickEvents.StartTick{

    private static final UUID ARMOR_MODIFIER_ID = UUID.fromString("d9468aa4-a4b3-4c18-9c13-c9b5c5443505");
    private static final String ARMOR_MODIFIER_NAME = "custom_armor_bonus";
    private static final double ARMOR_BONUS = 10.0D;
    boolean active = false;

    @Override
    public void onStartTick(MinecraftServer minecraftServer) {
        minecraftServer.getPlayerManager().getPlayerList().forEach(player -> {
            IPlayerManager playerManager = (IPlayerManager) player;
            if (playerManager.getActiveCard(Justice.class)){
                if (!active) {
                    applyArmorBonus(player);
                    active = true;
                }
            } else {
                if (active) {
                    removeArmorBonus(player);
                    active = false;
                }
            }
        });
    }

    private void applyArmorBonus(ServerPlayerEntity player) {
        EntityAttributeInstance armorAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);

        if (armorAttribute != null) {
            // Removemos el modificador existente si existe
            armorAttribute.removeModifier(ARMOR_MODIFIER_ID);

            // Creamos y aplicamos el nuevo modificador de armadura
            EntityAttributeModifier armorModifier = new EntityAttributeModifier(
                    ARMOR_MODIFIER_ID,
                    ARMOR_MODIFIER_NAME,
                    ARMOR_BONUS,
                    EntityAttributeModifier.Operation.ADDITION
            );

            armorAttribute.addPersistentModifier(armorModifier);
        }
    }

    private void removeArmorBonus(ServerPlayerEntity player) {
        EntityAttributeInstance armorAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);

        if (armorAttribute != null) {
            armorAttribute.removeModifier(ARMOR_MODIFIER_ID);
        }
    }
}
