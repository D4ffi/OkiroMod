package com.d4ffi.mixin;

import com.d4ffi.item.cards.Fool;
import com.d4ffi.item.cards.HangedMan;
import com.d4ffi.item.cards.Judgement;
import com.d4ffi.item.cards.Temperance;
import com.d4ffi.tarotCard.IPlayerManager;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Random;

import static com.d4ffi.item.cards.Empress.isValidTeleportLocation;
import static com.d4ffi.item.cards.Empress.spawnTeleportParticles;

@Mixin(PlayerEntity.class)
public class OnDamageMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.getWorld().isClient) {
            FoolDeflect(player, source, cir);
            HangedManDamage(player, amount, cir);
            Judgement(player, cir);
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

        IPlayerManager playerManager = (IPlayerManager) player;

        if (!playerManager.getActiveCard(HangedMan.class)) {
            return;
        }

        float maxHealth = (float) Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).getBaseValue();
        float casedHearts = maxHealth - player.getHealth();

        if (maxHealth == 2) {
            return;
        }

        if (casedHearts > 0) {

            if (playerManager.getActiveCard(Temperance.class)) {
                if (!(playerManager.getLostHeartsFromTemperance(player) > 20)) {
                    if (amount <= casedHearts) {
                        playerManager.addLostHeartsFromTemperance(player, amount);
                        Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth - amount);
                        player.setHealth(player.getHealth() + amount * .4f);
                    }
                }
            } else {
                if (amount <= casedHearts) {
                    playerManager.addLostHearts(player, amount);
                    Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth - amount);
                    player.setHealth(player.getHealth() + amount * .4f);
                }
            }
        }
    }

    @Unique
    private void Judgement(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        IPlayerManager playerM = (IPlayerManager) player;

        if (playerM.getActiveCard(Judgement.class) && !playerM.isCardOnCooldown(player, Judgement.class)) {
            cir.setReturnValue(false);
            teleportPlayer(player);
            playerM.setCardCooldown(player, Judgement.class, Judgement.JUDGEMENT_COOLDOWN);
        }

    }

    @Unique
    private static void teleportPlayerWithEffects(PlayerEntity player, int x, int y, int z) {
        spawnTeleportParticles(player, new Vec3d(x + 0.5, y, z + 0.5), false);
        player.teleport(x + 0.5, y + 1, z + 0.5);
        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 0.5F);

    }

    @Unique
    private void teleportPlayer(PlayerEntity player) {
        Vec3d origin = player.getPos();
        Random random = new Random();
        double offsetX = (random.nextDouble() - 0.5) * 10; // Random offset between -5 and 5
        double offsetZ = (random.nextDouble() - 0.5) * 10; // Random offset between -5 and 5
        int newX = (int) (origin.getX() + offsetX);
        int newZ = (int) (origin.getZ() + offsetZ);
        int newY = (int) origin.getY();

        try {
            if (isValidTeleportLocation(player, newX, (int) origin.getY(), newZ)) {
                teleportPlayerWithEffects(player, newX, (int) origin.getY(), newZ);
            } else {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        if (isValidTeleportLocation(player, newX + x, newY, newZ + z)) {
                            teleportPlayerWithEffects(player, newX + x, newY, newZ + z);
                            return;
                        }
                    }
                }
            }
        } catch (Exception ignored) {
            teleportPlayerWithEffects(player, (int) origin.getX(), (int) origin.getY(), (int) origin.getZ());
        }
    }


}
