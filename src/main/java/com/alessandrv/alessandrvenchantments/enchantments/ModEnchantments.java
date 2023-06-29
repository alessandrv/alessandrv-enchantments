package com.alessandrv.alessandrvenchantments.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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


    public static void registerEnchantments(){
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "nightstalker"), NIGHT_STALKER);
        //Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "spotter"), SPOTTER);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "ubiquity"), UBIQUITY);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "glowing"), GLOWING);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "glower"), GLOWER);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "vampiric"), VAMPIRIC);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "mobguard"), MOBGUARD);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "ringoffire"), RINGOFFIRE);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "explosive"), EXPLOSIVE);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "enderdefense"), ENDERDEFENSE);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "healingheart"), HEALINGHEART);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "bonemeal"), BONEMEAL);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "soulbound"), SOULBOUND);

    }
}
