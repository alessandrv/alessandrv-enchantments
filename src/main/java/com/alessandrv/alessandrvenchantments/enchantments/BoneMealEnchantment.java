package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class BoneMealEnchantment extends Enchantment {

    private static final ModConfig.BoneMealOptions CONFIG = AlessandrvEnchantments.getConfig().boneMealOptions;

    public BoneMealEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return CONFIG.isEnabled ? 3 : 0;
    }

    public static int getCooldown(){
        return CONFIG.cooldown;
    }
    public static int getLvl1Area(){
        return CONFIG.lvl1Area;
    }
    public static int getLvl2Area(){
        return CONFIG.lvl2Area;
    }
    public static int getLvl3Area(){
        return CONFIG.lvl3Area;
    }
    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CONFIG.bookOffer;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CONFIG.randomSelection;
    }
}
