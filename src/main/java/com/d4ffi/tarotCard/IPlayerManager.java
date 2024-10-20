package com.d4ffi.tarotCard;

import com.d4ffi.Okiro;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IPlayerManager {

    Set<ItemStack> activeCards = new HashSet<>();

    default void checkInventory(PlayerEntity player) {
        activeCards.clear();
        for (ItemStack stack : player.getInventory().main){
            if (stack.getItem() instanceof TarotCardManager){
                if (((TarotCardManager) stack.getItem()).isCardActive(stack)){
                    ((TarotCardManager) stack.getItem()).activateCard(player);
                    activeCards.add(stack);
                } else {
                    ((TarotCardManager) stack.getItem()).deactivateCard(player);
                    activeCards.remove(stack);
                }
            }
        }
    }

    default boolean getActiveCard(Class<?> card){
        for (ItemStack activeCard : activeCards){
            if (activeCard.getItem().getClass().equals(card)){
                return true;
            }
        }
        return false;
    }




}
