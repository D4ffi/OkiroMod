package com.d4ffi.mixin;

import com.d4ffi.item.cards.Devil;
import com.d4ffi.tarotCard.IPlayerManager;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.PiglinBruteBrain;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinBruteBrain.class)
public abstract class PiglinMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private static void tickMixin(PiglinBruteEntity piglinBrute, CallbackInfo ci) {
        Brain<?> brain = piglinBrute.getBrain();

        // Buscar jugadores cercanos
        PlayerEntity nearestPlayer = piglinBrute.getWorld().getClosestPlayer(
                piglinBrute,
                24.0 // radio de detección
        );

        if (nearestPlayer != null) {
            try {
                IPlayerManager playerManager = (IPlayerManager) nearestPlayer;
                if (playerManager.getActiveCard(Devil.class)) {
                    // Limpiar TODOS los estados de ataque y enojo
                    brain.forget(MemoryModuleType.ATTACK_TARGET);
                    brain.forget(MemoryModuleType.ANGRY_AT);
                    brain.forget(MemoryModuleType.UNIVERSAL_ANGER);
                    brain.forget(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER);
                    brain.forget(MemoryModuleType.NEAREST_VISIBLE_NEMESIS);
                    brain.forget(MemoryModuleType.NEAREST_ATTACKABLE);

                    // Prevenir que el tick normal continúe
                    ci.cancel();
                }
            } catch (Exception ignored) {
            }
        }
    }
}
