package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Chariot extends TarotCardManager {

    static  TarotConfigManager configManager = new TarotConfigManager();

    private static final int CHARIOT_SPEED = configManager.getChariotSpeed();

    public Chariot(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, CHARIOT_SPEED, false, false, false));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.thechariot"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.thechariot"));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
