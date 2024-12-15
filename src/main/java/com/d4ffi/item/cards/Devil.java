package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Devil extends TarotCardManager {

    public Devil(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        // Dar resistencia al fuego al jugador
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 60, 0, true, true, true));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.thedevil"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.thedevil"));

        super.appendTooltip(stack, world, tooltip, context);
    }
}