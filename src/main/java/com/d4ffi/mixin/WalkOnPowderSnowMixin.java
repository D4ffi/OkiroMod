package com.d4ffi.mixin;

import com.d4ffi.item.cards.Hierophant;
import com.d4ffi.tarotCard.IPlayerManager;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PowderSnowBlock.class)
public class WalkOnPowderSnowMixin {

    @Inject(method = "canWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void canWalkOnPowderSnowMixin(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof PlayerEntity){
            IPlayerManager moddedPlayer = (IPlayerManager) entity;
            if (moddedPlayer.getActiveCard(Hierophant.class)){
                cir.setReturnValue(true);
            }
        }
    }

}
