package com.alessandrv.alessandrvenchantments.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {

    public static final DefaultParticleType BLASTWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType ICEWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType ENDERWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType HEALINGWAVE = FabricParticleTypes.simple();

    public static void registerParticles(){
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "blastwave"), BLASTWAVE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "icewave"), ICEWAVE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "enderwave"), ENDERWAVE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "healingwave"), HEALINGWAVE);

    }
}
