package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Lovers extends TarotCardManager {

    TarotConfigManager configManager = new TarotConfigManager();
    int healAmount = configManager.getLoversHealAmount();

    public Lovers(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.thelovers"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.thelovers"));

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        if (player.getHealth() < player.getMaxHealth()) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, healAmount, true, false, false));
        }
    }
}

