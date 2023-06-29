package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpotterEnchantment extends Enchantment {
    private static final ModConfig.SpotterOptions CONFIG = AlessandrvEnchantments.getConfig().spotterOptions;

    public SpotterEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});

    }
    @Override
    public int getMaxLevel() {
        return CONFIG.isEnabled ? 1 : 0;
    }


    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CONFIG.bookOffer;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CONFIG.randomSelection;
    }

    public static boolean checkIfAttacked(LivingEntity user) {
        AtomicBoolean badGuyNear = new AtomicBoolean(false);
        World world = user.getEntityWorld();
        if (world instanceof ServerWorld serverWorld) {
            Box boundingBox = user.getBoundingBox().expand(15); // Raggio di 20 blocchi intorno all'entità utente
            if (!serverWorld.getEntitiesByClass(LivingEntity.class, boundingBox, (livingEntity) -> true).isEmpty()) {
                serverWorld.getEntitiesByClass(LivingEntity.class, boundingBox, (livingEntity) -> true)
                        .forEach((livingEntity) -> {
                            if (livingEntity instanceof HostileEntity hostileEntity) {
                                if (hostileEntity.getTarget() == user) {

                                    badGuyNear.set(true);

                                }
                            }});

                return badGuyNear.get();
                        }
        }
        return false;
    }




}
