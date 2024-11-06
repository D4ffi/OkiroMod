package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.IPlayerManager;
import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class Fool extends TarotCardManager {

    public Fool(Settings settings) {
        super(settings);
    }

    public static void activateKeyBindAbility(PlayerEntity player) {

        IPlayerManager playerManager = (IPlayerManager) player;

        if (!player.isOnGround() && !playerManager.isCardOnCooldown(player, Fool.class)){
            if(!player.isCreative()){
                Vec3d lookDirection = player.getRotationVector();
                double dash = 1.1;
                player.addVelocity(lookDirection.x * dash, 0, lookDirection.z * dash);
                player.velocityModified = true;
            }
            player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 0.7f);
            playerManager.setCardCooldown(player, Fool.class, 5);
        }
    }
}
