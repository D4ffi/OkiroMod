package com.d4ffi.tarotCard;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class TarotCardManager extends Item implements ITarotCard {

    public Box box = new Box(-2.5, -2.5, -2.5, 2.5, 2.5, 2.5);

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!user.getWorld().isClient){
            user.getMainHandStack().getOrCreateNbt().putBoolean("active", !user.getMainHandStack().getOrCreateNbt().getBoolean("active"));
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getOrCreateNbt().getBoolean("active");
    }
    public TarotCardManager(Settings settings) {
        super(settings);
    }
    @Override
    public String getCardTooltip() {
        return "No Tooltip available";
    }
    @Override
    public int getCardMode() {
        return 0;
    }
    @Override
    public void setCardMode(int mode) {}
    @Override
    public boolean isCardActive(ItemStack stack) {
        return hasGlint(stack);
    }
    @Override
    public void setCardActive(boolean active) {}
    @Override
    public void activateCard(PlayerEntity player) {}
    @Override
    public void deactivateCard(PlayerEntity player) {}

    public void playerAOE(PlayerEntity player){}

}
