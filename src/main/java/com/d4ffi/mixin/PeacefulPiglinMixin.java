package com.d4ffi.mixin;

import com.d4ffi.item.Devil;
import com.d4ffi.tarotCard.IPlayerManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public class PeacefulPiglinMixin {

    @Inject(method = "wearsGoldArmor", at = @At("HEAD"), cancellable = true)
    private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        try {
            if (entity instanceof PlayerEntity) {
                IPlayerManager playerManager = (IPlayerManager) entity;
                if (playerManager.getActiveCard(Devil.class)) {
                    cir.setReturnValue(true);
                } else {
                    cir.setReturnValue(false);
                }
            }
        } catch (Exception ignored) {
        }
    }

//    @Inject(method = "onGuardedBlockInteracted", at = @At("HEAD"), cancellable = true)
//    private static void ignoreBlockInteract(PlayerEntity player, boolean blockOpen, CallbackInfo ci) {
//        try {
//            IPlayerManager playerManager = (IPlayerManager) player;
//            if (playerManager.getActiveCard(Devil.class)) {
//
//                ci.cancel();
//            }
//        } catch (Exception ignored) {
//        }
//    }

}