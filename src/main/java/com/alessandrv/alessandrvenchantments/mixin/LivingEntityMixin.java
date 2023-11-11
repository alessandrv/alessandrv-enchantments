package com.alessandrv.alessandrvenchantments.mixin;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.statuseffects.CooldownStatusEffect;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract Map<StatusEffect, StatusEffectInstance> getActiveStatusEffects();

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Inject(method = "clearStatusEffects", at = @At("HEAD"), cancellable = true)
    private void clearStatusEffects(CallbackInfoReturnable<Boolean> ci) {

        //fixare dovrebbe essere cosi solo con secchio e quando quitti dovrebbero rimanere applicati
        if(this.hasStatusEffect(ModStatusEffects.DIETWICESTATUS)){
            ci.cancel();
        }

        Map<StatusEffect, StatusEffectInstance> statusEffects = this.getActiveStatusEffects();
        // Itera attraverso tutti gli status effect presenti
        for (StatusEffectInstance effect : statusEffects.values()) {
            // Controlla se l'effetto è una istanza di CooldownStatusEffect
            if (effect.getEffectType() instanceof CooldownStatusEffect) {
                // Verifica se è il tuo CooldownStatusEffect specifico da mantenere
                    // Rimuovi l'effetto dalla lista
                    statusEffects.remove(effect.getEffectType());

            }
        }

    }
}