package com.alessandrv.alessandrvenchantments.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpotterEnchantment extends Enchantment {

    public SpotterEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});

    }

    public static final Logger LOGGER = LoggerFactory.getLogger("alepagliaccioenchantments");

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return true;
    }

    public static boolean checkIfAttacked(LivingEntity user, int level) {
        AtomicBoolean badGuyNear = new AtomicBoolean(false);
        World world = user.getEntityWorld();
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            Box boundingBox = user.getBoundingBox().expand(15); // Raggio di 20 blocchi intorno all'entità utente
            if (!serverWorld.getEntitiesByClass(LivingEntity.class, boundingBox, (livingEntity) -> true).isEmpty()) {
                serverWorld.getEntitiesByClass(LivingEntity.class, boundingBox, (livingEntity) -> true)
                        .forEach((livingEntity) -> {
                            if (livingEntity instanceof HostileEntity) {
                                HostileEntity hostileEntity = (HostileEntity) livingEntity;
                                if (hostileEntity.getTarget() == user) {

                                    badGuyNear.set(true);

                                }
                            }});

                            if(badGuyNear.get() == true){
                                return true;
                            }
                            else{
                                return false;
                            }
                        }
        }
        return false;
    }




}
