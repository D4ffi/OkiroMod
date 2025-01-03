package com.d4ffi.mixin;

import com.d4ffi.OkiroTarotCards;
import com.d4ffi.item.Deck;
import com.d4ffi.item.cards.Lovers;
import com.d4ffi.item.cards.Temperance;
import com.d4ffi.tarotCard.IPlayerManager;
import com.d4ffi.tarotCard.PlayerDataStorage;
import com.d4ffi.tarotCard.TarotCardManager;
import com.d4ffi.tarotCard.TarotConfigManager;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public class CheckInventory implements IPlayerManager {

    @Unique
    PlayerEntity player = (PlayerEntity) (Object) this;
    @Unique
    TarotConfigManager configManager = new TarotConfigManager();
    @Unique
    int tickupdate = configManager.getUpdateTicks();
    @Unique
    int loversTick = 40;
    @Unique
    int tick = 0;
    @Unique
    boolean isTemperanceActive = false;

    @Inject(at = @At("HEAD"), method = "tick")
    public void checkInventory(CallbackInfo ci) {
            tick++;
            if (tick >= tickupdate) {
                checkInventory(player);
                terminateTemperance(player);
            }

            if (tick >= loversTick) {
                activateLovers(player);
                tick = 0;
            }
    }

    @Override
    public void checkInventory(PlayerEntity player) {
        activeCards.clear();
        cardsInDeck.clear();
        for (ItemStack stack : player.getInventory().main) {

            if (stack.getItem() instanceof Deck) {
                hasDeckInInventory(stack);
            }

            if (stack.getItem() instanceof TarotCardManager) {
                cardActivationProcess(stack);
            }
        }
    }

    @Override
    public float getLostHearts(PlayerEntity player) {
        Float lostHearts = PlayerDataStorage.playerLostHearts.get(player.getUuid());
        return lostHearts != null ? lostHearts : 0.0f;
    }

    @Override
    public void setLostHearts(PlayerEntity player, float lostHealth) {
        PlayerDataStorage.playerLostHearts.put(player.getUuid(), lostHealth);
    }

    @Override
    public void addLostHearts(PlayerEntity player, float lostHealth) {
        PlayerDataStorage.playerLostHearts.put(player.getUuid(), getLostHearts(player) + lostHealth);
    }


    @Override
    public void returnLostHearts(PlayerEntity player) {
        try {
            if (isTemperanceActive) {
                Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).getBaseValue() + getLostHeartsFromTemperance(player));
                setLostHeartsFromTemperance(player, 0);
            }

            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).getBaseValue() + getLostHearts(player));
            setLostHearts(player, 0);

        } catch (NullPointerException e) {
            OkiroTarotCards.LOGGER.error("Error returning lost hearts: {}", String.valueOf(e));
        }
    }

    @Override
    public float getLostHeartsFromTemperance(PlayerEntity player) {
        Float lostHearts = PlayerDataStorage.playerLostHeartsFromTemperance.get(player.getUuid());
        return lostHearts != null ? lostHearts : 0.0f;

    }

    @Override
    public void setLostHeartsFromTemperance(PlayerEntity player, float lostHealth) {
        PlayerDataStorage.playerLostHeartsFromTemperance.put(player.getUuid(), lostHealth);
    }

    @Override
    public void addLostHeartsFromTemperance(PlayerEntity player, float lostHealth) {
        PlayerDataStorage.playerLostHeartsFromTemperance.put(player.getUuid(), getLostHeartsFromTemperance(player) + lostHealth);
    }

    @Unique
    private void terminateTemperance(PlayerEntity player) {
        float extraHealth = configManager.getTemperanceMaxHealth();
        if (!getActiveCard(Temperance.class) && isTemperanceActive) {
            float DEFAULT_HEALTH = 20.0f;
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(DEFAULT_HEALTH);
            if (player.getHealth() > DEFAULT_HEALTH) {
                player.setHealth(player.getHealth() - extraHealth);
            }
            isTemperanceActive = false;
            activeCards.clear();
        }
    }

    @Unique
    private void activateLovers(PlayerEntity player) {
        for (ItemStack stack : activeCards) {
            boolean isLoversActive = false;
            if (stack.getItem() instanceof Lovers) {
                isLoversActive = ((TarotCardManager) stack.getItem()).isCardActive(stack);
            }

            if (isLoversActive) {
                ((TarotCardManager) stack.getItem()).activateCard(player);
            }
        }

        for (ItemStack stack : cardsInDeck) {
            boolean isLoversActive = false;
            if (stack.getItem() instanceof Lovers) {
                isLoversActive = ((TarotCardManager) stack.getItem()).isCardActive(stack);
            }

            if (isLoversActive) {
                ((TarotCardManager) stack.getItem()).activateCard(player);
            }
        }
    }

    @Unique
    private void hasDeckInInventory(ItemStack deck) {
        NbtCompound deckNbt = deck.getNbt();
        if (deckNbt != null && deckNbt.contains("DeckItems", NbtElement.LIST_TYPE)) {
            NbtList nbtList = deckNbt.getList("DeckItems", NbtElement.COMPOUND_TYPE);
            cardsInDeck.clear(); // Clear the set before adding new items

            for (int i = 0; i < nbtList.size(); i++) {
                NbtCompound itemNbt = nbtList.getCompound(i);
                ItemStack itemStack = ItemStack.fromNbt(itemNbt);
                cardsInDeck.add(itemStack);
            }

            for (ItemStack stack : cardsInDeck) {
                cardActivationProcess(stack);
            }
        }
    }

    @Unique
    private void cardActivationProcess(ItemStack stack){
        activeCards.add(stack);

        if (((TarotCardManager) stack.getItem()).isCardActive(stack)) {

            if (stack.getItem() instanceof Lovers) {
                return;
            }

            if (stack.getItem() instanceof Temperance) {
                if (!isTemperanceActive) {
                    ((TarotCardManager) stack.getItem()).activateCard(player);
                }
                isTemperanceActive = true;
            } else {
                ((TarotCardManager) stack.getItem()).activateCard(player);
            }
        } else {
            activeCards.remove(stack);
            if (stack.getItem() instanceof Temperance) {
                if (isTemperanceActive) {
                    ((TarotCardManager) stack.getItem()).deactivateCard(player);
                }
                isTemperanceActive = false;
            } else {
                ((TarotCardManager) stack.getItem()).deactivateCard(player);
            }
        }
    }
}
