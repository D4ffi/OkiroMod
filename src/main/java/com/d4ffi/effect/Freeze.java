package com.d4ffi.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class Freeze extends StatusEffect {

    protected Freeze(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient()) {

            // Apply damage over time (similar to powder snow)
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                if (!player.getAbilities().invulnerable) {
                    player.damage(player.getDamageSources().freeze(), 1.0F);
                }
            } else {
                entity.damage(entity.getDamageSources().freeze(), 1.0F);
            }

            // Increase freezing
            if (entity.getFrozenTicks() < entity.getMinFreezeDamageTicks()) {
                entity.setFrozenTicks(entity.getFrozenTicks() + 1);
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Apply the effect every second (20 ticks)
        return duration % 20 == 0;
    }
}
