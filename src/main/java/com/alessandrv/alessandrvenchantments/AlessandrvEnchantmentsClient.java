package com.alessandrv.alessandrvenchantments;


import com.alessandrv.alessandrvenchantments.particles.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AlessandrvEnchantmentsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLASTWAVE, WaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ICEWAVE, WaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ENDERWAVE, WaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.HEALINGWAVE, WaveParticle.Factory::new);
     }
}