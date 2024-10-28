package com.d4ffi.tarotCard;

import com.d4ffi.Okiro;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface ITarotCard {

    public String getCardTooltip();
    public int getCardMode();
    public void setCardMode(int mode);

    public boolean isCardActive(ItemStack stack);
    public void setCardActive(boolean active);
    public void activateCard(PlayerEntity player);
    public void deactivateCard(PlayerEntity player);
    public void forceDeactivation(PlayerEntity player);
    public default void playerAOE(PlayerEntity player){
        // does nothing by default
        Okiro.LOGGER.info("AOE not implemented for this card");
    }
}
