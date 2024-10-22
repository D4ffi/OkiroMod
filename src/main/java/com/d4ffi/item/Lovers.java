package com.d4ffi.item;

import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import java.util.List;

public class Lovers extends TarotCardManager {

    TarotConfigManager configManager = new TarotConfigManager();
    boolean canHealInArea = configManager.canLoversHealInRadius();
    int healAmount = configManager.getLoversHealAmount();

    public Lovers(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        if (canHealInArea){
            playerAOE(player);
        } else {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, healAmount));
        }
    }

    @Override
    public void playerAOE(PlayerEntity player) {
        Box box = new Box(player.getBlockPos()).expand(5);
        List<Entity> entities = player.getWorld().getEntitiesByClass(Entity.class, box, entity -> true); // Include all entities

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, healAmount));
            }
        }
    }
}
