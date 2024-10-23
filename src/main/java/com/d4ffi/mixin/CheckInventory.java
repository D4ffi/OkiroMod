package com.d4ffi.mixin;

import com.d4ffi.item.cards.Temperance;
import com.d4ffi.tarotCard.IPlayerManager;
import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class CheckInventory implements IPlayerManager {

    @Unique
    PlayerEntity player = (PlayerEntity) (Object) this;
    @Unique
    boolean serverSide = !player.getWorld().isClient;
    @Unique
    TarotConfigManager configManager = new TarotConfigManager();
    @Unique
    int tickupdate = configManager.getUpdateTicks();
    @Unique
    int tick = 0;
    @Unique
    boolean isTemperanceActive = false;

    @Inject(at = @At("HEAD"), method = "tick")
    public void checkInventory(CallbackInfo ci) {
        if (serverSide){
            tick++;
            if(tick >= tickupdate){
                checkInventory(player);
                terminateTemperance(player);
            }
        }
    }

    @Override
    public void checkInventory(PlayerEntity player) {
        activeCards.clear();
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() instanceof TarotCardManager) {
                activeCards.add(stack);
                if (((TarotCardManager) stack.getItem()).isCardActive(stack)) {
                    if (stack.getItem() instanceof Temperance) {
                        if (!isTemperanceActive) {
                            ((TarotCardManager) stack.getItem()).activateCard(player);
                        }
                        isTemperanceActive = true;
                    } else {
                        ((TarotCardManager) stack.getItem()).activateCard(player);
                    }
                } else {
                    activeCards.remove(stack);
                    if (stack.getItem() instanceof Temperance) {
                        if (isTemperanceActive) {
                            ((TarotCardManager) stack.getItem()).deactivateCard(player);
                        }
                        isTemperanceActive = false;
                    } else {
                        ((TarotCardManager) stack.getItem()).deactivateCard(player);
                    }
                }
            }
        }
    }

    @Unique
    private void terminateTemperance(PlayerEntity player) {
        float extraHealth = configManager.getTemperanceMaxHealth();
        if (!getActiveCard(Temperance.class) && isTemperanceActive) {
            float DEFAULT_HEALTH = 20.0f;
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(DEFAULT_HEALTH);
            if (player.getHealth() > DEFAULT_HEALTH) {
                player.setHealth(player.getHealth()-extraHealth);
            }
            isTemperanceActive = false;
            activeCards.clear();
        }
    }
}
