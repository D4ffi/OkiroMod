package com.d4ffi.mixin;

import com.d4ffi.item.cards.Fool;
import com.d4ffi.tarotCard.IPlayerManager;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class OnDamageMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        FoolDeflect(player, source, cir);
    }

    private void FoolDeflect(PlayerEntity player, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        IPlayerManager playerManager = (IPlayerManager) player;

        float chance = 0.25f;

        if (playerManager.getActiveCard(Fool.class)) {
            if (source.getSource() instanceof ProjectileEntity && Math.random() <= chance) {
                player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 2.0f);
                cir.setReturnValue(false);
            }
        }
    }

}
