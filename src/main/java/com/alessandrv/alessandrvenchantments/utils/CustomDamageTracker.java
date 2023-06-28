package com.alessandrv.alessandrvenchantments.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;

public class CustomDamageTracker extends DamageTracker {
    public CustomDamageTracker(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void onDamage(DamageSource damageSource, float damage) {
        super.onDamage(damageSource, damage);

        if (damageSource.getAttacker() instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) damageSource.getAttacker();

            // Calcola la quantità di salute da rubare (1% del danno inflitto)
            float healthToSteal = damage * 0.01f;

            // Applica la cura all'attaccante
            attacker.heal(healthToSteal);
        }
    }
}