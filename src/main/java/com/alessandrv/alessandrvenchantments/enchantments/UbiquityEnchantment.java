package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatuses;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import net.minecraft.enchantment.Enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.FoxEntity;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;


public class UbiquityEnchantment extends Enchantment {
    private static final ModConfig.UbiquityOptions CONFIG = AlessandrvEnchantments.getConfig().ubiquityOptions;

    public UbiquityEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[] {EquipmentSlot.CHEST});
    }


    @Override
    public int getMaxLevel() {
        return CONFIG.isEnabled ? 1 : 0;
    }

    @Override
    public int getMinPower(int level) {
        return 15;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CONFIG.bookOffer;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CONFIG.randomSelection;
    }

    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (EnchantmentHelper.getLevel(ModEnchantments.UBIQUITY, user.getEquippedStack(EquipmentSlot.CHEST)) <= 0) {
            return; // L'armatura incantata non è equipaggiata alle gambe o non ha l'incantesimo ExplosiveAttraction, esci dal metodo
        }
        World world = user.getEntityWorld();
        if(!user.hasStatusEffect(ModStatuses.UBIQUITYCOOLDOWN)){
            if (!world.isClient) {
                double d = user.getX();
                double e = user.getY();
                double f = user.getZ();
                user.addStatusEffect(new StatusEffectInstance(ModStatuses.UBIQUITYCOOLDOWN, CONFIG.cooldown * 20, 0, false, false, true));
                for (int i = 0; i < 16; ++i) {
                    double g = user.getX() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                    double h = MathHelper.clamp(user.getY() + (double) (user.getRandom().nextInt(16) - 8), world.getBottomY(), world.getBottomY() + ((ServerWorld) world).getLogicalHeight() - 1);
                    double j = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                    if (user.hasVehicle()) {
                        user.stopRiding();
                    }

                    Vec3d vec3d = user.getPos();
                    if (user.teleport(g, h, j, true)) {
                        world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                        SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        user.playSound(soundEvent, 1.0F, 1.0F);
                        break;
                    }
                }
            }
        }
    }

}
