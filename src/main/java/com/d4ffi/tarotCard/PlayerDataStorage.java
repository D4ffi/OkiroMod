package com.d4ffi.tarotCard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataStorage {

    public static final Map<UUID, Float> playerLostHearts = new HashMap<>();
    public static final Map<UUID, Float> playerLostHeartsFromTemperance = new HashMap<>();

    public static void setLostHearts(UUID player, float lostHearts){
        playerLostHearts.put(player, lostHearts);
    }

    public static void setLostHeartsFromTemperance(UUID player, float lostHearts){
        playerLostHeartsFromTemperance.put(player, lostHearts);
    }

}
