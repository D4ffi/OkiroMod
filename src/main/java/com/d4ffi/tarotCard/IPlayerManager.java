package com.d4ffi.tarotCard;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IPlayerManager {

    default void checkInventory(PlayerEntity player) {
        for (ItemStack stack : player.getInventory().main){
            if (stack.getItem() instanceof TarotCardManager){
                if (((TarotCardManager) stack.getItem()).isCardActive(stack)){
                    ((TarotCardManager) stack.getItem()).activateCard(player);
                }
            }
        }
    }

}
