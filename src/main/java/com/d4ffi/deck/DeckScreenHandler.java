package com.d4ffi.deck;

import com.d4ffi.tarotCard.TarotCardManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public class DeckScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    public DeckScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ScreenHandlerType.GENERIC_3X3, syncId);
        this.inventory = new SimpleInventory(22);

        // Primera fila: 8 cartas
        for (int col = 0; col < 8; col++) {
            this.addSlot(new DeckSlot(inventory, col, 17 + col * 18, 20));
        }

        // Segunda fila: 8 cartas
        for (int col = 0; col < 8; col++) {
            this.addSlot(new DeckSlot(inventory, col + 8, 17 + col * 18, 45));
        }

        // Tercera fila: 6 cartas (centradas)
        for (int col = 0; col < 6; col++) {
            this.addSlot(new DeckSlot(inventory, col + 16, 35 + col * 18, 70));
        }

        // Inventario del jugador
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 102 + row * 18));
            }
        }

        // Hotbar del jugador
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 160));
        }


    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (index < 22) {
                // Si el click fue en el inventario del deck, mover al inventario del jugador
                if (!this.insertItem(originalStack, 22, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Si el click fue en el inventario del jugador, mover al deck
                if (originalStack.getItem() instanceof TarotCardManager) {
                    if (!this.insertItem(originalStack, 0, 22, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }

}
