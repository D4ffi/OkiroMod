package com.d4ffi.item;

import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

public class Emperor extends TarotCardManager {

    TarotConfigManager configManager = new TarotConfigManager();
    private HashMap<String, String> emperorItems = configManager.getTurnToGoldItems();

    public Emperor(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 60, 4));
        midasTouch(player);
    }

    private void midasTouch(PlayerEntity player) {
        if (player.experienceLevel > 0) {

        }
    }
}
