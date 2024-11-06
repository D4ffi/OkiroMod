package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Hermit extends TarotCardManager {

    public Hermit(Settings settings) {
        super(settings);
    }

    public static void activateKeyBindAbility(PlayerEntity player) {
        if (player.isFallFlying()){
            Vec3d lookDirection = player.getRotationVector();

            double boost = 1.5;
            player.addVelocity(lookDirection.x * boost, lookDirection.y * boost, lookDirection.z * boost);
            player.velocityModified = true;

            player.getItemCooldownManager().set(player.getMainHandStack().getItem(), 200);
        }
    }
}
