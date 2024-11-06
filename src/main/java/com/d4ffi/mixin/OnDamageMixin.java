package com.d4ffi.mixin;

import com.d4ffi.item.cards.Fool;
import com.d4ffi.item.cards.HangedMan;
import com.d4ffi.item.cards.Temperance;
import com.d4ffi.tarotCard.IPlayerManager;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public class OnDamageMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.getWorld().isClient) {
            FoolDeflect(player, source, cir);
            HangedManDamage(player, amount, cir);
        }

    }

    @Unique
    private void FoolDeflect(PlayerEntity player, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        IPlayerManager playerManager = (IPlayerManager) player;

        if (!playerManager.getActiveCard(Fool.class)) {
            return;
        }

        float chance = 0.25f;

        if (source.getSource() instanceof ProjectileEntity && Math.random() <= chance) {
            player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 2.0f);
            cir.setReturnValue(false);
        }

    }

    @Unique
    private void HangedManDamage(PlayerEntity player, float amount, CallbackInfoReturnable<Boolean> cir) {
        float amountForTemperance = 0;
        float cancel = 0;
        IPlayerManager playerManager = (IPlayerManager) player;

        if (!playerManager.getActiveCard(HangedMan.class)) {
            return;
        }

        float maxHealth = (float) Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).getBaseValue();
        float casedHearts = maxHealth - player.getHealth();
        float useCasedHearts = 1;

        if (maxHealth == 2){
            return;
        }

        if (casedHearts > 0){
            if (playerManager.getActiveCard(Temperance.class)){
                if (amount <= casedHearts) {
                    playerManager.addLostHearts(player, amount);
                    playerManager.addDamageFromTemperance(player, amount);
                    if (playerManager.getLostHeartsFromTemperance(player) > 20){
                        playerManager.setLostHeartsFromTemperance(player, 20);
                    }
                    Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth - amount);
                    return;
                }
            }
            if (amount <= casedHearts) {
                playerManager.addLostHearts(player, amount);
                Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth - amount);
            }
        }
    }

}
