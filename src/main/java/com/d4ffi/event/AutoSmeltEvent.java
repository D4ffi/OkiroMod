package com.d4ffi.event;

import com.d4ffi.Okiro;
import com.d4ffi.item.Sun;
import com.d4ffi.tarotCard.IPlayerManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class AutoSmeltEvent implements PlayerBlockBreakEvents.Before {

    TarotConfigManager configManager = new TarotConfigManager();
    HashMap<String, String> autoSmeltBlocks = configManager.getAutoSmeltBlocks();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        IPlayerManager moddedPlayer = (IPlayerManager) playerEntity;
        if (moddedPlayer.getActiveCard(Sun.class)){
            String blockName = blockState.getBlock().toString().replace("Block{", "").replace("}", "");
            Okiro.LOGGER.info("Block: {}", blockName);
            autoSmelt(blockState, blockName, world, blockPos, playerEntity);
        }
        return true;
    }
    private boolean autoSmelt(BlockState blockState, String blockName, World world, BlockPos blockPos, PlayerEntity playerEntity) {
        if (playerEntity.getMainHandStack().isSuitableFor(blockState)) {
            if (playerEntity.getMainHandStack().hasEnchantments() && playerEntity.getMainHandStack().getEnchantments().toString().contains("fortune")) {
                int fortuneLevel = EnchantmentHelper.getLevel(Enchantments.FORTUNE, playerEntity.getMainHandStack());
                for (String key : autoSmeltBlocks.keySet()) {
                    if (blockName.equals(key)) {
                        world.removeBlock(blockPos, false);
                        if (blockName.contains("ore")) {
                            //add a random value of xp
                            playerEntity.addExperience(world.random.nextInt(10) + 1);
                            dropSmeltedItemwithFortune(world, blockPos, autoSmeltBlocks.get(key), fortuneLevel);
                        } else {
                            dropSmeltedItem(world, blockPos, autoSmeltBlocks.get(key));
                        }
                        return false;
                    }
                }
            } else {
                for (String key : autoSmeltBlocks.keySet()) {
                    if (blockName.equals(key)) {
                        world.removeBlock(blockPos, false);
                        playerEntity.addExperience(world.random.nextInt(6) + 1);
                        dropSmeltedItem(world, blockPos, autoSmeltBlocks.get(key));
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private void dropSmeltedItemwithFortune(World world, BlockPos pos, String itemId, int fortuneLevel) {
        randomFortuneSmeltDrop(world, pos, itemId, fortuneLevel);
    }
    private void dropSmeltedItem(World world, BlockPos pos, String itemId) {
        Item item = Registries.ITEM.get(new Identifier(itemId));
        if (item != null) {
            ItemStack itemStack = new ItemStack(item);
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
            world.spawnEntity(itemEntity);
        }
    }
    private void randomFortuneSmeltDrop(World world, BlockPos pos, String itemId, int fortuneLevel) {
        Item item = Registries.ITEM.get(new Identifier(itemId));
        if (item != null) {
            ItemStack itemStack = new ItemStack(item);
            int quantity = world.random.nextInt(fortuneLevel+1) + 1; // Random number between 1 and 3
            for (int i = 0; i < quantity; i++) {
                ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
                world.spawnEntity(itemEntity);
            }
        }
    }

}
