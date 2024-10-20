package com.d4ffi;

import com.d4ffi.event.AttackEvent;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;

public class OkiroEventRegister {

    public static void registerEvents() {
        AttackEntityCallback.EVENT.register(new AttackEvent());
    }
}
