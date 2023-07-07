package com.alessandrv.alessandrvenchantments.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static Enchantment NIGHT_STALKER = new NightStalkerEnchantment();
    public static Enchantment MOBGUARD = new MobGuardEnchantment();
    public static Enchantment UBIQUITY = new UbiquityEnchantment();
    public static Enchantment SPOTTER  = new SpotterEnchantment();
    public static Enchantment GLOWING  = new GlowingEnchantment();
    public static Enchantment GLOWER  = new GlowerEnchantment();
    public static Enchantment VAMPIRIC  = new VampiricEnchantment();
    public static Enchantment RINGOFFIRE  = new RingOfFireEnchantment();
    public static final Enchantment EXPLOSIVE = new ExplosiveEnchantment();
    public static final Enchantment ENDERDEFENSE = new EnderDefenseEnchantment();
    public static final Enchantment HEALINGHEART = new HealingHeartEnchantment();
    public static final Enchantment BONEMEAL = new BoneMealEnchantment();
    public static final Enchantment SOULBOUND = new SoulboundEnchantment();
    public static final Enchantment FREEZEASPECT = new FreezeAspectEnchantment();
    //public static final Enchantment SOULSWAPPER = new SoulSwapperEnchantment();
    public static final Enchantment DIETWICE = new DieTwiceEnchantment();
    public static final Enchantment VOIDLESS = new VoidlessEnchantment();


    public static void registerEnchantments(){
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "nightstalker"), NIGHT_STALKER);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "ubiquity"), UBIQUITY);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "glowing"), GLOWING);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "glower"), GLOWER);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "vampiric"), VAMPIRIC);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "mobguard"), MOBGUARD);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "ringoffire"), RINGOFFIRE);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "explosive"), EXPLOSIVE);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "enderdefense"), ENDERDEFENSE);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "healingheart"), HEALINGHEART);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "bonemeal"), BONEMEAL);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "soulbound"), SOULBOUND);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "freezeaspect"), FREEZEASPECT);
        //Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "soulswapper"), SOULSWAPPER);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "spotter"), SPOTTER);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "dietwice"), DIETWICE);
        Registry.register(Registry.ENCHANTMENT, new Identifier("alessandrvenchantments", "voidless"), VOIDLESS);

    }

    public static boolean hasFullArmorSet(Enchantment enchantment, LivingEntity entity) {
        ItemStack headSlot = entity.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestSlot = entity.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsSlot = entity.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetSlot = entity.getEquippedStack(EquipmentSlot.FEET);
        return EnchantmentHelper.getLevel(enchantment, headSlot) > 0 &&
                EnchantmentHelper.getLevel(enchantment, chestSlot) > 0 &&
                EnchantmentHelper.getLevel(enchantment, legsSlot) > 0 &&
                EnchantmentHelper.getLevel(enchantment, feetSlot) > 0;

    }
}
