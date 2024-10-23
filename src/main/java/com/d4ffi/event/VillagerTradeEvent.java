package com.d4ffi.event;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;

import java.util.Random;

public class VillagerTradeEvent {

    public static boolean tradeAfterEmperor = false;

    // Metodo para modificar las ofertas de comercio y guardar las originales
    public static void modifyVillagerTrades(VillagerEntity villager) {
        TradeOfferList tradeOffers = villager.getOffers();
        if (tradeOffers == null || tradeOffers.isEmpty()) {
            return;
        }

        // Crear una nueva lista para las ofertas modificadas
        TradeOfferList modifiedOffers = new TradeOfferList();

        for (TradeOffer originalOffer : tradeOffers) {
            ItemStack sellItem = originalOffer.getSellItem();
            ItemStack firstBuyItem = originalOffer.getOriginalFirstBuyItem();
            ItemStack secondBuyItem = originalOffer.getSecondBuyItem();

            // Crear una nueva oferta con solo una esmeralda como precio
            TradeOffer modifiedOffer = new TradeOffer(
                    new ItemStack(firstBuyItem.getItem(), 1), // Primera entrada: 1 esmeralda
                    secondBuyItem.isEmpty() ? secondBuyItem.copy() : new ItemStack(secondBuyItem.getItem(), 1),
                    sellItem.copy(),
                    originalOffer.getMaxUses(),
                    originalOffer.getMerchantExperience(),
                    originalOffer.getPriceMultiplier()
            );

            modifiedOffers.add(modifiedOffer);
        }

        // Reemplazar las ofertas originales con las modificadas
        villager.setOffers(modifiedOffers);
    }

    public static int getRandomBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static void randomizeTradesPrices(VillagerEntity villager) {
        if (tradeAfterEmperor){
            TradeOfferList currentOffers = villager.getOffers();
            if (currentOffers == null || currentOffers.isEmpty()) {
                return;
            }

            // Crear una nueva lista para las ofertas con precios aleatorios
            TradeOfferList randomizedOffers = new TradeOfferList();

            for (TradeOffer currentOffer : currentOffers) {
                ItemStack sellItem = currentOffer.getSellItem();
                ItemStack firstBuyItem = currentOffer.getOriginalFirstBuyItem();
                ItemStack secondBuyItem = currentOffer.getSecondBuyItem();


                // Crear una nueva oferta con precio aleatorio
                TradeOffer randomizedOffer = new TradeOffer(
                        new ItemStack(currentOffer.getOriginalFirstBuyItem().getItem(), getRandomBetween(2,18)), // Precio aleatorio de esmeraldas
                        secondBuyItem.isEmpty() ? secondBuyItem.copy() : new ItemStack(secondBuyItem.getItem(), getRandomBetween(3,18)),
                        sellItem.copy(),
                        currentOffer.getMaxUses(),
                        currentOffer.getMerchantExperience(),
                        currentOffer.getPriceMultiplier()
                );

                randomizedOffers.add(randomizedOffer);
            }

            // Establecer las nuevas ofertas con precios aleatorios
            villager.setOffers(randomizedOffers);

            // Efectos visuales para mostrar la aleatorización
            ((ServerWorld) villager.getWorld()).spawnParticles(
                    ParticleTypes.WAX_OFF,
                    villager.getX(),
                    villager.getY() + 1,
                    villager.getZ(),
                    15, 0.5D, 0.5D, 0.5D, 0.0D
            );

            // Efecto de sonido
            villager.getWorld().playSound(
                    null,
                    villager.getX(),
                    villager.getY(),
                    villager.getZ(),
                    SoundEvents.BLOCK_DISPENSER_DISPENSE,
                    SoundCategory.NEUTRAL,
                    1.0F,
                    1.0F
            );

            tradeAfterEmperor = false;
        }
    }

    public static void modifyVillagerTrades(PlayerEntity player, VillagerEntity villager) {

        modifyVillagerTrades(villager);
        tradeAfterEmperor = true;

        // Efectos visuales para indicar que el aldeano ha sido modificado
        ((ServerWorld) villager.getWorld()).spawnParticles(
                ParticleTypes.HAPPY_VILLAGER,
                villager.getX(),
                villager.getY() + 1,
                villager.getZ(),
                10, 0.5D, 0.5D, 0.5D, 0.0D
        );

        // Efecto de sonido
        villager.getWorld().playSound(
                null,
                villager.getX(),
                villager.getY(),
                villager.getZ(),
                SoundEvents.ENTITY_VILLAGER_YES,
                SoundCategory.NEUTRAL,
                1.0F,
                1.0F
        );
    }
}
