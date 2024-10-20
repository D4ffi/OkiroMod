package com.d4ffi.mixin;

import com.d4ffi.Okiro;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static com.d4ffi.effect.OkiroEffect.FREEZE;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Unique
    private static final Identifier FROZEN_OVERLAY = new Identifier(Okiro.MOD_ID, "textures/misc/frozen.png");

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "render", at = @At("TAIL"))
    private void renderFrozenOverlay(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (this.client.player != null && this.client.player.hasStatusEffect(FREEZE)) {
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, FROZEN_OVERLAY);

            int width = this.client.getWindow().getScaledWidth();
            int height = this.client.getWindow().getScaledHeight();
            context.drawTexture(FROZEN_OVERLAY, 0, 0, -90, 0.0F, 0.0F, width, height, width, height);

            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @ModifyArg(method = "renderHealthBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIIZZ)V"), index = 5)
    private boolean modifyHeartTexture(boolean bl) {
        if (this.client.player != null && this.client.player.hasStatusEffect(FREEZE)) {
            return true; // Esto hace que se use la textura de corazón congelado
        }
        return bl;
    }

}
