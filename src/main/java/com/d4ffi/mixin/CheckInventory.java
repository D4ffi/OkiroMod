package com.d4ffi.mixin;

import com.d4ffi.Okiro;
import com.d4ffi.item.OkiroItem;
import com.d4ffi.item.Star;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class CheckInventory {

    @Unique
    PlayerEntity player = (PlayerEntity) (Object) this;
    boolean serverSide = !player.getWorld().isClient;

    @Inject(at = @At("HEAD"), method = "tick")
    public void checkInventory(CallbackInfo ci) {
        if (serverSide){

        }
    }
}
