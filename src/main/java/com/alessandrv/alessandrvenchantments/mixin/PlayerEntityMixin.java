package com.alessandrv.alessandrvenchantments.mixin;

import com.alessandrv.alessandrvenchantments.enchantments.ModEnchantments;
import com.alessandrv.alessandrvenchantments.enchantments.SpotterEnchantment;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatuses;
import com.alessandrv.alessandrvenchantments.util.SoulboundItemsHolder;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements SoulboundItemsHolder {
    private static final List<ItemStack> soulboundItems = new ArrayList<>(); // Lista degli oggetti con l'incantesimo "Soulbound"
    private static final List<Integer> soulboundItemsSlot = new ArrayList<>(); // Lista degli oggetti con l'incantesimo "Soulbound"

    // Implementa il metodo dell'interfaccia
    @Override
    public List<ItemStack> getSoulboundItems() {
        return soulboundItems;
    }
    @Override
    public List<Integer> getSoulboundItemsSlot() {
        return soulboundItemsSlot;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger("alepagliaccioenchantments");

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        ItemStack itemStackHead = this.getEquippedStack(EquipmentSlot.HEAD);

        int nightStalkerLevel = EnchantmentHelper.getLevel(ModEnchantments.NIGHT_STALKER, itemStackHead);
        if (nightStalkerLevel > 0) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 250, 0, false, false, false));
        }

        int glowingLevel = EnchantmentHelper.getLevel(ModEnchantments.GLOWING, itemStackHead);
        if (glowingLevel > 0) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 250, 0, false, false, false));
        }

        int spotterlevel = EnchantmentHelper.getLevel(ModEnchantments.SPOTTER, itemStackHead);
        if(spotterlevel>0 && !this.hasStatusEffect(ModStatuses.SPOTTERCOOLDOWN)){

            if(SpotterEnchantment.checkIfAttacked(this)){
                LOGGER.info("Ti vogliono");
                //this.addStatusEffect(new StatusEffectInstance(AlepagliaccioEnchantments.SPOTTERCOOLDOWN, 1200, 0, false, false, false));
                ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.ELDER_GUARDIAN  ,
                        this.getX(), this.getY()+1, this.getZ(), 1,
                        0,0,0, 1);
            }
        }

    }


    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Shadow public abstract PlayerInventory getInventory();

    @Inject(method = "dropInventory", at = @At("HEAD"))
    private void dropInventory(CallbackInfo ci) {
        soulboundItems.clear(); // Pulisci la lista soulboundItems
        soulboundItemsSlot.clear(); // Pulisci la lista soulboundItemsSlot
        for (int slot = 0; slot < getInventory().size(); slot++) {
            ItemStack stack = getInventory().getStack(slot);
            if (hasSoulboundEnchantment(stack)) {
                soulboundItems.add(stack.copy()); // Conserva l'oggetto nell'inventario
                stack.setCount(0); // Rimuovi l'oggetto dall'equipaggiamento
                soulboundItemsSlot.add(slot);
            }
        }
    }

    private boolean hasSoulboundEnchantment(ItemStack stack) {
        return EnchantmentHelper.getLevel(ModEnchantments.SOULBOUND, stack) > 0;
    }

}