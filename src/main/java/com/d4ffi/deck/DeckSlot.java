package com.d4ffi.deck;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class DeckSlot extends Slot {

    public DeckSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof TarotCardManager;
    }
}
