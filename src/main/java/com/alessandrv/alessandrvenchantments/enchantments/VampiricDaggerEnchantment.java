package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.mixin.LivingEntityAccessor;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;


public class VampiricDaggerEnchantment extends Enchantment {
    public VampiricDaggerEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND});
    }
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem ? true : super.isAcceptableItem(stack);
    }
    public boolean canAccept(Enchantment other) {
        return !(other instanceof DamageEnchantment);
    }
    @Override
    public int getMinPower(int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
    @Override
    public boolean isAvailableForRandomSelection() {
        return true;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            if(target instanceof LivingEntity) {
                // Verifica se l'entità bersaglio ha registrato danni recenti
                float lastDamageTaken = ((LivingEntityAccessor) target).getLastDamageTaken();
                // Calcola l'ammontare di vita da restituire in base al danno inflitto
                float healingAmount = lastDamageTaken * 0.02f * level; // Restituisce il 25% del danno inflitto come vita
                // Applica la rigenerazione al giocatore
                player.heal(healingAmount);
                LivingEntity targetLiving = (LivingEntity) target;
                for (int i = 0; i < 10; i++) {
                    double particleX = targetLiving.getX() + targetLiving.getRandom().nextDouble() * targetLiving.getWidth();
                    double particleY = targetLiving.getY() + targetLiving.getRandom().nextDouble() * targetLiving.getHeight();
                    double particleZ = targetLiving.getZ() + targetLiving.getRandom().nextDouble() * targetLiving.getWidth();

                    ((ServerWorld)target.getWorld()).spawnParticles(ParticleTypes.HAPPY_VILLAGER  ,
                            target.getX(), target.getY()+1, target.getZ(), 1,
                            Math.cos(i) * 0.25d, 0.5d, Math.sin(i) * 0.25d, 1);
                }
            }


        }
    }

}
