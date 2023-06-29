package com.alessandrv.alessandrvenchantments;


import com.alessandrv.alessandrvenchantments.particles.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class AlessandrvEnchantmentsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(ModParticles.BLASTWAVE, WaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ICEWAVE, WaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ENDERWAVE, WaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.HEALINGWAVE, WaveParticle.Factory::new);
    }
}