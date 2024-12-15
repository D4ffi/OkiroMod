package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hermit extends TarotCardManager {

    public Hermit(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.thehermit"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.thehermit"));

        super.appendTooltip(stack, world, tooltip, context);
    }

    public static void activateKeyBindAbility(PlayerEntity player) {
        if (player.isFallFlying()){
            Vec3d lookDirection = player.getRotationVector();

            double boost = 1.3;
            player.addVelocity(lookDirection.x * boost, lookDirection.y * boost, lookDirection.z * boost);
            player.velocityModified = true;

            player.getItemCooldownManager().set(player.getMainHandStack().getItem(), 200);
        }
    }
}
