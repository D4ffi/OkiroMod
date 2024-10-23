package com.d4ffi.item.cards;

import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class Emperor extends TarotCardManager {

    TarotConfigManager configManager = new TarotConfigManager();
    private HashMap<Item, Item> emperorItems = configManager.getTurnToGoldItems();

    public Emperor(Settings settings) {
        super(settings);
    }

    @Override
    public void activateCard(PlayerEntity player) {
        midasTouch(player);
    }

    void midasTouch(PlayerEntity player) {

        ItemStack offHandStack = player.getOffHandStack();

        for (Item item : emperorItems.keySet()) {
            if (offHandStack.getItem() == item) {
                ItemStack itemStack = new ItemStack(emperorItems.get(item));

                int count = offHandStack.getCount();
                offHandStack.decrement(offHandStack.getCount());

                itemStack.setCount(count);
                player.getInventory().offerOrDrop(itemStack);

            }
        }
    }


}
