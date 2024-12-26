package com.d4ffi;

import com.d4ffi.datagen.OkiroTcLootTables;
import com.d4ffi.deck.DeckScreenHandler;
import com.d4ffi.effect.OkiroEffect;
import com.d4ffi.item.OkiroItem;
import com.d4ffi.network.Client2Server;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkiroTarotCards implements ModInitializer {

	public static final String MOD_ID = "okiro_tarot_cards";
	public static final String CONTAINER_ID = "deck";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ScreenHandlerType<DeckScreenHandler> CONTAINER_TYPE = Registry.register(Registries.SCREEN_HANDLER, CONTAINER_ID,
			new ExtendedScreenHandlerType<>(DeckScreenHandler::new));

	@Override
	public void onInitialize() {

		TarotConfigManager configManager = new TarotConfigManager();
		configManager.initAutoSmeltBlocks();
		configManager.initTurnToGoldItems();
		configManager.initHighPriestessNegateEffects();
		OkiroItem.registerItems();
		OkiroEffect.registerEffects();
		OkiroTcEventRegister.registerEvents();
		OkiroTcLootTables.modifyLootTables();
		Client2Server.registerPackets();

		LOGGER.info("Have a great day, Fabric World!");
	}
}