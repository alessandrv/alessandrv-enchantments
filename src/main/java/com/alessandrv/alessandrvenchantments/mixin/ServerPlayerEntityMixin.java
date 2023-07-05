package com.alessandrv.alessandrvenchantments.mixin;


import com.alessandrv.alessandrvenchantments.enchantments.ModEnchantments;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatusEffects;
import com.alessandrv.alessandrvenchantments.util.PlayerEffectStorage;
import com.alessandrv.alessandrvenchantments.util.SoulboundItemsHolder;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static com.alessandrv.alessandrvenchantments.statuseffects.DieTwiceStatusEffect.setCoords;

import java.util.List;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {


    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }



    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(CallbackInfo ci) {
        if(!this.hasStatusEffect(ModStatusEffects.DIETWICECOOLDOWN)){

            if (ModEnchantments.hasFullArmorSet(ModEnchantments.DIETWICE, this)) {
                PlayerEffectStorage.setDieTwice(true);
                setCoords(this.getX(), this.getY(), this.getZ());
            }

        }else{

            PlayerEffectStorage.setDieTwice(false);
        }

        if (this.hasStatusEffect(ModStatusEffects.DIETWICECOOLDOWN)) {
            StatusEffectInstance effect = this.getStatusEffect(ModStatusEffects.DIETWICECOOLDOWN);
            PlayerEffectStorage.setPreservedEffect(effect);

        }
    }
    @Inject(method = "onSpawn", at = @At("HEAD"))
    private void onSpawn(CallbackInfo ci) {

        StatusEffectInstance preservedEffect = PlayerEffectStorage.getPreservedEffect();
        if (preservedEffect != null) {

            this.addStatusEffect(preservedEffect);
            PlayerEffectStorage.setPreservedEffect(null);
        }
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
        if(PlayerEffectStorage.getDieTwice()){
            PlayerEffectStorage.setDieTwice(false);
            this.addStatusEffect(new StatusEffectInstance(ModStatusEffects.DIETWICESTATUS, 600, 0, false, false, true));

        }

    }



}