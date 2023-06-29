package com.alessandrv.alessandrvenchantments.mixin;


import com.alessandrv.alessandrvenchantments.util.SoulboundItemsHolder;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {


    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "onSpawn", at = @At("HEAD"))
    private void onSpawn(CallbackInfo ci) {
        if (this instanceof SoulboundItemsHolder) {
            List<ItemStack> soulboundItems = ((SoulboundItemsHolder) this).getSoulboundItems();

            if (!soulboundItems.isEmpty()) {
                List<Integer> soulboundItemsSlot = ((SoulboundItemsHolder) this).getSoulboundItemsSlot();
                for (int i = 0; i < soulboundItems.size(); i++) {
                    ItemStack soulboundItem = soulboundItems.get(i);
                    if (!soulboundItem.isEmpty()) {
                        int slot = soulboundItemsSlot.get(i);
                        getInventory().setStack(slot, soulboundItem);
                    }
                }
            }
        }
    }



}