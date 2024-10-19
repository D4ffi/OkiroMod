package com.d4ffi.item;

import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

public class Temperance extends TarotCardManager {

    static TarotConfigManager configManager = new TarotConfigManager();

    private final float DEFAULT_HEALTH = 20.0f;
    private final float EXTRA_HEALTH = configManager.getTemperanceMaxHealth();
    private boolean wasActivated = false;

    public Temperance(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        if (wasActivated == false) {
            addExtraHealth(player);
            wasActivated = true;
        }
    }

    @Override
    public void deactivateCard(PlayerEntity player) {
        if (wasActivated) {
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(DEFAULT_HEALTH);
            if (player.getHealth() > DEFAULT_HEALTH) {
                player.setHealth(DEFAULT_HEALTH);
            }
            wasActivated = false;
        }
    }

    private void addExtraHealth(PlayerEntity player) {
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(DEFAULT_HEALTH + EXTRA_HEALTH);
    }
}
