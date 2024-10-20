package com.d4ffi.event;

import com.d4ffi.item.Death;
import com.d4ffi.tarotCard.IPlayerManager;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEvent implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {

        if (!playerEntity.getWorld().isClient){
            IPlayerManager moddedPlayer = (IPlayerManager) playerEntity;
            deathEffect(moddedPlayer, entity);
        }

        return ActionResult.PASS;
    }

    private void deathEffect(IPlayerManager player, Entity entity) {
        if (player.getActiveCard(Death.class)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 0));
            }
        }
    }
}
