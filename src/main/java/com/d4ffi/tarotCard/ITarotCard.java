package com.d4ffi.tarotCard;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface ITarotCard {

    public String getCardTooltip();
    public int getCardMode();
    public void setCardMode(int mode);

    public boolean isCardActive(ItemStack stack);
    public void setCardActive(boolean active);
    public void activateCard(PlayerEntity player);

}
