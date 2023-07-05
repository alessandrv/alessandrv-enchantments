package com.alessandrv.alessandrvenchantments.mixin;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatusEffects;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Accessor("client")
    public abstract MinecraftClient getClient();

    private int scaledWidth;
    private int scaledHeight;
    public void setScaledHeight(int value) {
        scaledHeight = value;
    }

    public void setScaledWidth(int scaledWidth) {
        this.scaledWidth = scaledWidth;
    }

    private void renderOverlay(DrawContext context) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        context.setShaderColor(1.0F, 1.0F, 1.0F, (float) 0.5);
        context.drawTexture(AlessandrvEnchantments.SPOTTER_OUTLINE, 0, 0, -90, 0.0F, 0.0F, scaledWidth, scaledHeight, scaledWidth, scaledHeight);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }



    @Inject(method = "renderCrosshair", at = @At("HEAD"))
    public void renderCrosshair(DrawContext context, CallbackInfo ci) {
        setScaledWidth(context.getScaledWindowWidth());
        setScaledHeight(context.getScaledWindowHeight());
        MinecraftClient client = this.getClient();
        assert client.player != null;
        if(client.player.hasStatusEffect(ModStatusEffects.SPOTTER)) {
            this.renderOverlay(context);
        }
    }
}
