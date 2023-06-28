package com.alessandrv.alessandrvenchantments.enchantments;

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

    public SpotterEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});

    }


    @Override
    public int getMinPower(int level) {
        return 1;
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
