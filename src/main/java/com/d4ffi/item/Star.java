package com.d4ffi.item;

import com.d4ffi.tarotCard.ITarotCard;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Star extends Item implements ITarotCard {

    private boolean changeState = false;

    public Star(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        changeState = !changeState;
        setCardActive(changeState);
        return super.use(world, user, hand);
    }

    @Override
    public String getCardTooltip() {
        return "";
    }

    @Override
    public int getCardMode() {
        return 0;
    }

    @Override
    public void setCardMode(int mode) {

    }

    @Override
    public boolean isCardActive() {
        return changeState;
    }

    @Override
    public void setCardActive(boolean active) {
        changeState = active;
    }

    @Override
    public void activateCard(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 240, 1, false, false));
    }

    @Override
    public void deactivateCard() {

    }
}
