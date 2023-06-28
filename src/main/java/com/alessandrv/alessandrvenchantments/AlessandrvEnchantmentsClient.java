package com.alessandrv.alessandrvenchantments;


import com.alessandrv.alessandrvenchantments.particles.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class AlessandrvEnchantmentsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(AlessandrvEnchantments.BLASTWAVE, WaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(AlessandrvEnchantments.ICEWAVE, WaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(AlessandrvEnchantments.ENDERWAVE, WaveParticle.Factory::new);
    }
}