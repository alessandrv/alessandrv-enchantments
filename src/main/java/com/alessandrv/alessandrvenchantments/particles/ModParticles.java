package com.alessandrv.alessandrvenchantments.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {

    public static final DefaultParticleType BLASTWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType ICEWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType ENDERWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType HEALINGWAVE = FabricParticleTypes.simple();

    public static void registerParticles(){
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "blastwave"), BLASTWAVE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "icewave"), ICEWAVE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "enderwave"), ENDERWAVE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("alessandrvenchantments", "healingwave"), HEALINGWAVE);

    }
}
