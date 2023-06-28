package com.alessandrv.alessandrvenchantments.mixin;


import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.enchantments.SpotterEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
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

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow protected abstract void spawnParticles(ParticleEffect parameters);

    boolean isSpotterInCooldown;



    private static final Logger LOGGER = LoggerFactory.getLogger("alepagliaccioenchantments");

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        ItemStack itemStackHead = this.getEquippedStack(EquipmentSlot.HEAD);

        int nightStalkerLevel = EnchantmentHelper.getLevel(AlessandrvEnchantments.NIGHT_STALKER, itemStackHead);
        if (nightStalkerLevel > 0) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 250, 0, false, false, false));
        }
        int glowingLevel = EnchantmentHelper.getLevel(AlessandrvEnchantments.GLOWING, itemStackHead);
        if (glowingLevel > 0) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 250, 0, false, false, false));
        }

        int spotterlevel = EnchantmentHelper.getLevel(AlessandrvEnchantments.SPOTTER, itemStackHead);
        if(spotterlevel>0 && !this.hasStatusEffect(AlessandrvEnchantments.SPOTTERCOOLDOWN)){

            if(SpotterEnchantment.checkIfAttacked(this, spotterlevel)){
                LOGGER.info("Ti vogliono");
                //this.addStatusEffect(new StatusEffectInstance(AlepagliaccioEnchantments.SPOTTERCOOLDOWN, 1200, 0, false, false, false));
                ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.ELDER_GUARDIAN  ,
                        this.getX(), this.getY()+1, this.getZ(), 1,
                        0,0,0, 1);
            }
        }
    }











}