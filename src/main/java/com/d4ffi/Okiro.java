package com.d4ffi;

import com.d4ffi.effect.OkiroEffect;
import com.d4ffi.item.OkiroItem;
import com.d4ffi.network.Client2Server;
import com.d4ffi.network.Server2Client;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Okiro implements ModInitializer {

	public static final String MOD_ID = "okiro";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		TarotConfigManager configManager = new TarotConfigManager();
		configManager.initAutoSmeltBlocks();
		configManager.initTurnToGoldItems();
		configManager.initHighPriestessNegateEffects();
		OkiroItem.registerItems();
		OkiroEffect.registerEffects();
		OkiroEventRegister.registerEvents();
		Client2Server.registerPackets();
		LOGGER.info("Hello Fabric world!");
	}
}