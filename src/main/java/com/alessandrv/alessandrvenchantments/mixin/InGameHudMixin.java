package com.alessandrv.alessandrvenchantments.mixin;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatusEffects;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;

import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.gui.DrawableHelper.drawTexture;

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

    private void renderOverlay(MatrixStack matrices) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float) 0.5);
        RenderSystem.setShaderTexture(0, AlessandrvEnchantments.SPOTTER_OUTLINE);
        drawTexture(matrices, 0, 0, -90, 0.0F, 0.0F, scaledWidth, scaledHeight, scaledWidth, scaledHeight);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
    private void renderOverlay(MatrixStack matrices, Identifier texture, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
        RenderSystem.setShaderTexture(0, texture);
        drawTexture(matrices, 0, 0, -90, 0.0F, 0.0F, this.scaledWidth, this.scaledHeight, this.scaledWidth, this.scaledHeight);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }


    @Inject(method = "renderCrosshair", at = @At("HEAD"))
    public void renderCrosshair(MatrixStack matrices, CallbackInfo ci) {
        Window window = this.getClient().getWindow();
        setScaledWidth(window.getScaledWidth());
        setScaledHeight(window.getScaledHeight());
        MinecraftClient client = this.getClient();
        assert client.player != null;
        if(client.player.hasStatusEffect(ModStatusEffects.SPOTTER)) {
            this.renderOverlay(matrices);
        }
    }
}
