package com.d4ffi.tarotCard;

import com.d4ffi.Okiro;
import com.d4ffi.item.OkiroItem;
import com.d4ffi.item.Temperance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public interface IPlayerManager {

    Set<ItemStack> activeCards = new HashSet<>();

    void checkInventory(PlayerEntity player);

    default boolean getActiveCard(Class<?> card) {
        for (ItemStack activeCard : activeCards) {
            if (activeCard.getItem().getClass().equals(card)) {
                return true;
            }
        }
        return false;
    }

}
