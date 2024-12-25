package com.d4ffi.deck;

import com.d4ffi.Okiro;
import com.d4ffi.item.Deck;
import com.d4ffi.tarotCard.TarotCardManager;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import java.util.ArrayList;
import java.util.List;

public class DeckScreenHandler extends ScreenHandler {

    private final ItemStack deckStack;
    private final int ROWS = 2;
    private final int COLUMNS = 11;
    Inventory deckInventory = new SimpleInventory(11 * 2);


    public DeckScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf ) {
        this(syncId, playerInventory, packetByteBuf.readItemStack());
    }

    public DeckScreenHandler(int synchronizationID, PlayerInventory playerInventory, ItemStack deckStack) {
        super(Okiro.CONTAINER_TYPE, synchronizationID);
        this.deckStack = deckStack;

        if (deckStack.getItem() instanceof Deck) {
            setupContainer(playerInventory, deckStack);
        } else {
            PlayerEntity player = playerInventory.player;
            this.onClosed(player);
        }

    }

    private void setupContainer(PlayerInventory playerInventory, ItemStack deckStack) {

        NbtCompound deckNbt = deckStack.getNbt();
        if (deckNbt != null && deckNbt.contains("DeckItems", NbtElement.LIST_TYPE)) {
            NbtList nbtList = deckNbt.getList("DeckItems", NbtElement.COMPOUND_TYPE);

            for (int i = 0; i < nbtList.size(); i++) {
                NbtCompound itemNbt = nbtList.getCompound(i);
                int slot = itemNbt.getInt("Slot");
                if (slot >= 0 && slot < deckInventory.size()) {
                    deckInventory.setStack(slot, ItemStack.fromNbt(itemNbt));
                }
            }
        }

        // Add Tarot Card slots (2 rows x 11 columns)
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int index = col + row * COLUMNS;
                int x = 8 + col * 18;  // 18 pixels is the standard size for slots
                int y = 25 + row * 18; // Starting at y=18 to leave space for title
                this.addSlot(new DeckSlot(deckInventory, index, x, y));
            }
        }

        // Add Player Inventory (3 rows x 9 columns)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = 26 + col * 18;
                int y = 84 + row * 18; // 84 is standard spacing after container slots
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, x, y));
            }
        }

        // Add Hotbar (1 row x 9 columns)
        for (int col = 0; col < 9; col++) {
            int x = 26 + col * 18;
            int y = 142; // Standard y position for hotbar
            this.addSlot(new Slot(playerInventory, col, x, y));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            if (index < ROWS * COLUMNS) {
                // If clicking in deck inventory, try to move to player inventory
                if (!this.insertItem(slotStack, ROWS * COLUMNS, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // If clicking in player inventory, and it's a tarot card, try to move to deck
                if (slotStack.getItem() instanceof TarotCardManager) {
                    if (!this.insertItem(slotStack, 0, ROWS * COLUMNS, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (slotStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        saveInventory(deckStack);
    }

    private void saveInventory(ItemStack deckStack) {
        NbtCompound deckNbt = deckStack.getOrCreateNbt();
        NbtList nbtList = new NbtList();

        for (int i = 0; i < deckInventory.size(); i++) {
            ItemStack stack = deckInventory.getStack(i);
            if (!stack.isEmpty()) {
                NbtCompound itemNbt = new NbtCompound();
                itemNbt.putInt("Slot", i);
                stack.writeNbt(itemNbt);
                nbtList.add(itemNbt);
            }
        }

        deckNbt.put("DeckItems", nbtList);
        deckStack.setNbt(deckNbt);
    }

    public List<String> getDeckContents() {
        List<String> deckContents = new ArrayList<>();
        for (int i = 0; i < deckInventory.size(); i++) {
            ItemStack stack = deckInventory.getStack(i);
            if (!stack.isEmpty()) {
                deckContents.add(stack.getName().getString());
            }
        }
        return deckContents;
    }
}
