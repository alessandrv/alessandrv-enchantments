package com.alessandrv.alessandrvenchantments;

import com.alessandrv.alessandrvenchantments.enchantments.*;
import com.alessandrv.alessandrvenchantments.statuseffects.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.minecraft.util.Identifier;


public class AlessandrvEnchantments implements ModInitializer {

    public static Enchantment NIGHT_STALKER = new NightStalkerEnchantment();
    public static Enchantment MOBGUARD = new MobGuardEnchantment();

    public static Enchantment UBIQUITY = new UbiquityEnchantment();

    public static Enchantment SPOTTER  = new SpotterEnchantment();
    public static Enchantment GLOWING  = new GlowingEnchantment();
    public static Enchantment GLOWER  = new GlowerEnchantment();
    public static Enchantment VAMPIRICDAGGER  = new VampiricDaggerEnchantment();
    public static Enchantment RINGOFFIRE  = new RingOfFireEnchantment();
    public static final Enchantment EXPLOSIVE = new ExplosiveEnchantment();
    public static final Enchantment ENDERDEFENSE = new EnderDefenseEnchantment();

    public static final StatusEffect SPOTTERCOOLDOWN = new SpotterCooldown();
    public static final StatusEffect MOBGUARDCOOLDOWN = new MobGuardCooldown();
    public static final StatusEffect EXPLOSIVECOOLDOWN = new ExplosiveCooldown();
    public static final StatusEffect RINGOFFIRECOOLDOWN = new RingOfFireCooldown();
    public static final StatusEffect ENDERDEFENSECOOLDOWN = new EnderDefenseCooldown();




    public static final DefaultParticleType BLASTWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType ICEWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType ENDERWAVE = FabricParticleTypes.simple();

    @Override
    public void onInitialize() {
        //Enchantments
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "nightstalker"), NIGHT_STALKER);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "spotter"), SPOTTER);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "ubiquity"), UBIQUITY);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "glowing"), GLOWING);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "glower"), GLOWER);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "vampiricdagger"), VAMPIRICDAGGER);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "mobguard"), MOBGUARD);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "ringoffire"), RINGOFFIRE);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "explosive"), EXPLOSIVE);
        Registry.register(Registries.ENCHANTMENT, new Identifier("alessandrvenchantments", "enderdefense"), ENDERDEFENSE);
        //Status Effects
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "spottercooldown"), SPOTTERCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "mobguardcooldown"), MOBGUARDCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "ringoffirecooldown"), RINGOFFIRECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "explosivecooldown"), EXPLOSIVECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "enderdefensecooldown"), ENDERDEFENSECOOLDOWN);
        //Particles
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "blastwave"), BLASTWAVE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "icewave"), ICEWAVE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "enderwave"), ENDERWAVE);
        //End
    }
}