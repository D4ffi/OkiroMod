package com.d4ffi;

import com.d4ffi.event.AttackEvent;
import com.d4ffi.event.AutoSmeltEvent;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class OkiroEventRegister {

    public static void registerEvents() {
        AttackEntityCallback.EVENT.register(new AttackEvent());
        PlayerBlockBreakEvents.BEFORE.register(new AutoSmeltEvent());
    }
}
