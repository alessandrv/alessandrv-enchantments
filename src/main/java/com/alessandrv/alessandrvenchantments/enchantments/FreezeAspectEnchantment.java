package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatusEffects;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;


public class FreezeAspectEnchantment extends Enchantment {
    private static final ModConfig.FreezeAspectOptions CONFIG = AlessandrvEnchantments.getConfig().freezeAspectOptions;

    public FreezeAspectEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxLevel() {
        return CONFIG.isEnabled ? 2 : 0;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CONFIG.bookOffer;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CONFIG.randomSelection;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof EnderDefenseEnchantment || other instanceof RingOfFireEnchantment || other instanceof ExplosiveEnchantment || other instanceof HealingHeartEnchantment);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {


        if (target instanceof LivingEntity targetEntity) {

            targetEntity.addStatusEffect(new StatusEffectInstance(ModStatusEffects.FREEZINGSTATUSEFFECT,  200 * level, 0, true, true, false)); // L'aura visibile dura 10 secondi
            //target.damage(target.getDamageSources().freeze(), 2.0f);
            //target.setFrozenTicks(600 * level);
        }

    }



}

