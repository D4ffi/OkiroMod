package com.d4ffi.event;

import com.d4ffi.effect.OkiroEffect;
import com.d4ffi.item.cards.Death;
import com.d4ffi.item.cards.Hierophant;
import com.d4ffi.item.cards.Magician;
import com.d4ffi.tarotCard.IPlayerManager;
import com.d4ffi.tarotCard.TarotConfigManager;
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

    static TarotConfigManager configManager = new TarotConfigManager();

    private static final int MAGICIAN_BURN_TIME = configManager.getMagicianBurnTime();
    private static final int DEATH_WITHER_TIME = configManager.getDeathWitherTime();

    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {

        if (!playerEntity.getWorld().isClient){
            IPlayerManager moddedPlayer = (IPlayerManager) playerEntity;
            deathEffect(moddedPlayer, entity);
            magicianEffect(moddedPlayer, entity);
            hierophantEffect(moddedPlayer, entity);
        }

        return ActionResult.PASS;
    }

    private void deathEffect(IPlayerManager player, Entity entity) {
        if (player.getActiveCard(Death.class)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, DEATH_WITHER_TIME, 1));
            }
        }
    }

    private void magicianEffect(IPlayerManager player, Entity entity) {
        if (player.getActiveCard(Magician.class)) {
            if (entity instanceof LivingEntity) {
                entity.setOnFireFor(MAGICIAN_BURN_TIME);
            }
        }
    }

    private void hierophantEffect(IPlayerManager player, Entity entity) {
        if (player.getActiveCard(Hierophant.class)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(OkiroEffect.FREEZE, 100, 0));
            }
        }
    }


}
