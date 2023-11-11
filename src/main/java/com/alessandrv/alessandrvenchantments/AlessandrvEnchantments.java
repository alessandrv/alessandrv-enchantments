package com.alessandrv.alessandrvenchantments;

import com.alessandrv.alessandrvenchantments.enchantments.*;
import com.alessandrv.alessandrvenchantments.particles.ModParticles;
import com.alessandrv.alessandrvenchantments.statuseffects.*;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;

import com.google.gson.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AlessandrvEnchantments implements ModInitializer {

    private static ModConfig config;
    public static final Logger LOGGER = LoggerFactory.getLogger("alessandrvenchantments");

    public static final Identifier SPOTTER_OUTLINE = new Identifier("alessandrvenchantments", "textures/misc/spotter_outline.png");




        private static List<String> VALID_IDS;
    private static final ExecutorService em4esExecutor = Executors.newSingleThreadExecutor();

    public static final TagKey<Structure> DESERT_PYRAMID = TagKey.of(RegistryKeys.STRUCTURE, new Identifier("alessandvenchantments", "test"));






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
        //Items


    }

    public static ModConfig getConfig() {
        return config;
    }


}