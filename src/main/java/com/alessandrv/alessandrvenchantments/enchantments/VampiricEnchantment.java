package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.mixin.LivingEntityAccessor;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
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
import net.minecraft.world.World;


public class VampiricEnchantment extends Enchantment {
    private static final ModConfig.VampiricOptions CONFIG = AlessandrvEnchantments.getConfig().vampiricOptions;

    public VampiricEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND});
    }
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem || super.isAcceptableItem(stack);
    }
    public boolean canAccept(Enchantment other) {
        if(CONFIG.mutuallyExlusive) return !(other instanceof DamageEnchantment);
        else return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CONFIG.randomSelection;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CONFIG.bookOffer;
    }

    @Override
    public int getMinPower(int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxLevel() {
        return CONFIG.isEnabled ? 5 : 0;
    }



    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (user instanceof PlayerEntity player) {
            if(target instanceof LivingEntity) {
                // Verifica se l'entità bersaglio ha registrato danni recenti
                float lastDamageTaken = ((LivingEntityAccessor) target).getLastDamageTaken();
                // Calcola l'ammontare di vita da restituire in base al danno inflitto
                float healingAmount = lastDamageTaken * 0.02f * level; // Restituisce il 25% del danno inflitto come vita
                // Applica la rigenerazione al giocatore
                player.heal(healingAmount);
                World world = target.getWorld();
                if (world instanceof ServerWorld serverWorld) {
                    for (int i = 0; i < 10; i++) {
                        ;
                        serverWorld.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                                target.getX(), target.getY() + target.getHeight(), target.getZ(), 1,
                                Math.cos(i) * 0.25d, 0.5d, Math.sin(i) * 0.25d, 1);
                    }
                }
            }


        }
    }

}
