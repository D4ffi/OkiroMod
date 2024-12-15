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

import static com.d4ffi.effect.OkiroEffect.MOON_GRACE;

public class Moon extends TarotCardManager {

    private static final double SPEED_BOOST = 0.05;

    public Moon(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.themoon"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.themoon"));

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        if (player.isSubmergedInWater()){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 60, 0, false, false));
            player.addStatusEffect(new StatusEffectInstance(MOON_GRACE, 60, 0, false, false));
        }
    }
}
