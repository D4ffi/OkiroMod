package com.d4ffi.item.cards;

import com.d4ffi.Okiro;
import com.d4ffi.tarotCard.IPlayerManager;
import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Fool extends TarotCardManager {

    static int FOOL_COOLDOWN = 80;

    public Fool(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("tooltip.lore.thefool"));
        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.description.thefool"));

        super.appendTooltip(stack, world, tooltip, context);
    }

    public static void activateKeyBindAbility(PlayerEntity player) {

        IPlayerManager playerManager = (IPlayerManager) player;

        if (!player.isOnGround() && !playerManager.isCardOnCooldown(player, Fool.class)){
            if(!player.isCreative()){
                Vec3d lookDirection = player.getRotationVector();
                double dash = .90;
                player.addVelocity(lookDirection.x * dash, 0, lookDirection.z * dash);
                player.velocityModified = true;
            }
            player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 0.7f);
            playerManager.setCardCooldown(player, Fool.class, FOOL_COOLDOWN);
        }
    }
}
