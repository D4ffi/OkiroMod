package com.d4ffi.tarotCard;

import net.minecraft.entity.player.PlayerEntity;

public interface ITarotCard {

    public String getCardTooltip();
    public int getCardMode();
    public void setCardMode(int mode);

    public boolean isCardActive();
    public void setCardActive(boolean active);
    public void activateCard(PlayerEntity player);
    public void deactivateCard();

}
