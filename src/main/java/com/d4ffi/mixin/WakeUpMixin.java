package com.d4ffi.mixin;

import com.d4ffi.item.cards.Temperance;
import com.d4ffi.tarotCard.IPlayerManager;
import com.d4ffi.tarotCard.PlayerDataStorage;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class WakeUpMixin {

    @Inject(method = "wakeUp*", at = @At("HEAD"))
    private void onWakeUp(CallbackInfo ci) {

        PlayerEntity player = (PlayerEntity) (Object) this;
        IPlayerManager playerManager = (IPlayerManager) player;

        try {

            // Todo: Implement logic to prevent player from receiving temperance hearts if he had turn off and then on the temperance card

            if (playerManager.getLostHearts(player) > 0 || playerManager.getLostHeartsFromTemperance(player) > 0) {
                playerManager.returnLostHearts(player);
                playerManager.setLostHearts(player, 0);
                playerManager.setLostHeartsFromTemperance(player, 0);
                PlayerDataStorage.playerLostHearts.put(player.getUuid(), 0.0f);
                PlayerDataStorage.playerLostHeartsFromTemperance.put(player.getUuid(), 0.0f);
            }
        } catch (Exception e) {
            PlayerDataStorage.playerLostHearts.put(player.getUuid(), 0.0f);
            PlayerDataStorage.playerLostHeartsFromTemperance.put(player.getUuid(), 0.0f);
            e.printStackTrace();
        }

    }
}
