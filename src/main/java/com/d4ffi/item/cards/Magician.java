package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Magician extends TarotCardManager {

    public Magician(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.themagician"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.themagician"));

        super.appendTooltip(stack, world, tooltip, context);
    }


}
