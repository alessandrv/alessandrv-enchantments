package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;


public class GlowingEnchantment extends Enchantment {
    private static final ModConfig.GlowingOptions CONFIG = AlessandrvEnchantments.getConfig().glowingOptions;

    public GlowingEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});

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
}
