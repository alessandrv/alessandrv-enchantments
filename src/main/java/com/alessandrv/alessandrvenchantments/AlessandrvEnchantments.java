package com.alessandrv.alessandrvenchantments;

import com.alessandrv.alessandrvenchantments.enchantments.*;
import com.alessandrv.alessandrvenchantments.particles.ModParticles;
import com.alessandrv.alessandrvenchantments.statuseffects.*;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AlessandrvEnchantments implements ModInitializer {

    private static ModConfig config;
    public static final Logger LOGGER = LoggerFactory.getLogger("alessandrvenchantments");

    public static final Identifier SPOTTER_OUTLINE = new Identifier("alessandrvenchantments", "textures/misc/spotter_outline.png");

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


        //Enchantments
        ModEnchantments.registerEnchantments();
        //Status Effects
        ModStatusEffects.registerStatuses();
        //Particles
        ModParticles.registerParticles();

    }

    public static ModConfig getConfig() {
        return config;
    }


}