package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hierophant extends TarotCardManager {

    public Hierophant(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.thehierophant"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.thehierophant"));

        super.appendTooltip(stack, world, tooltip, context);
    }

}
