package com.d4ffi.tarotCard;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public interface IPlayerManager {

    Set<ItemStack> activeCards = new HashSet<>();

    Set<ItemStack> cardsInDeck = new HashSet<>();

    void checkInventory(PlayerEntity player);
    float getLostHearts(PlayerEntity player);
    void setLostHearts(PlayerEntity player, float lostHealth);
    void addLostHearts(PlayerEntity player, float lostHealth);
    void returnLostHearts(PlayerEntity player);
    float getLostHeartsFromTemperance(PlayerEntity player);
    void setLostHeartsFromTemperance(PlayerEntity player, float lostHealth);
    void addLostHeartsFromTemperance(PlayerEntity player, float lostHealth);


    default boolean getActiveCard(Class<?> card) {
        for (ItemStack activeCard : activeCards) {
            if (activeCard.getItem().getClass().equals(card)) {
                return true;
            }
        }
        return false;
    }

    default void setCardCooldown(PlayerEntity player, Class<?> card, int cooldownTicks) {
        for (ItemStack activeCard : activeCards) {
            if (activeCard.getItem().getClass().equals(card)) {
                player.getItemCooldownManager().set(activeCard.getItem(), cooldownTicks);
            }
        }
    }
    default boolean isCardOnCooldown(PlayerEntity player, Class<?> card) {
        for (ItemStack activeCard : activeCards) {
            if (activeCard.getItem().getClass().equals(card)) {
                return player.getItemCooldownManager().isCoolingDown(activeCard.getItem());
            }
        }
        return false;
    }


}
