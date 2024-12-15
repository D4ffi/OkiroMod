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
    boolean canHealInArea = configManager.canLoversHealInRadius();
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
        if (canHealInArea){
            playerAOE(player);
        } else {
            if (healAmount > 0){
                if (player.getHealth() < 10 ){
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, healAmount));
                } else{
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, healAmount-1));
                }
            } else {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, healAmount));
            }
        }
    }

    @Override
    public void playerAOE(PlayerEntity player) {
        Box box = new Box(player.getBlockPos()).expand(5);
        List<Entity> entities = player.getWorld().getEntitiesByClass(Entity.class, box, entity -> true); // Include all entities

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, healAmount));
            }
        }
    }
}
