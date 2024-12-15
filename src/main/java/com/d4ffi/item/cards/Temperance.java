package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Temperance extends TarotCardManager {

    static TarotConfigManager configManager = new TarotConfigManager();

    private final float DEFAULT_HEALTH = 20.0f;
    private final float EXTRA_HEALTH = configManager.getTemperanceMaxHealth();

    public Temperance(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(DEFAULT_HEALTH + EXTRA_HEALTH);
    }

    @Override
    public void deactivateCard(PlayerEntity player) {
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(DEFAULT_HEALTH);
            if (player.getHealth() > DEFAULT_HEALTH) {
                player.setHealth(DEFAULT_HEALTH);
            }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.temperance"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.temperance"));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
