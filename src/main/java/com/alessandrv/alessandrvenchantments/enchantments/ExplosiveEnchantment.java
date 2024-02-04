package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatusEffects;
import com.alessandrv.alessandrvenchantments.util.CustomExplosion;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;


public class ExplosiveEnchantment extends Enchantment {
    private static final ModConfig.ExplosiveOptions CONFIG = AlessandrvEnchantments.getConfig().explosiveOptions;


    public ExplosiveEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_LEGS, new EquipmentSlot[] {EquipmentSlot.LEGS});
    }

    @Override
    public int getMinPower(int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxLevel() {
        return CONFIG.isEnabled ? 5 : 0;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CONFIG.bookOffer;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CONFIG.randomSelection;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof MobGuardEnchantment || other instanceof RingOfFireEnchantment || other instanceof EnderDefenseEnchantment || other instanceof HealingHeartEnchantment);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {

        if (EnchantmentHelper.getLevel(ModEnchantments.EXPLOSIVE, user.getEquippedStack(EquipmentSlot.LEGS)) <= 0) {
            return; // L'armatura incantata non è equipaggiata alle gambe o non ha l'incantesimo ExplosiveAttraction, esci dal metodo
        }
        if(!user.hasStatusEffect(ModStatusEffects.EXPLOSIVECOOLDOWN)) {
            World world = user.getEntityWorld();
            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();
            float power = CONFIG.power; // Potenza dell'esplosione

            // Genera l'esplosione alle coordinate dell'entità
            CustomExplosion explosion = new CustomExplosion(world, user, x, y, z, power, false, Explosion.DestructionType.KEEP);

            explosion.DamageEntities();


            if (!user.getWorld().isClient()) {
                ((ServerWorld) user.getWorld()).spawnParticles(ParticleTypes.EXPLOSION_EMITTER,
                        x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
            }

            SoundEvent soundEvent = SoundEvents.ENTITY_GENERIC_EXPLODE;
            world.playSound(null, x, y, z, soundEvent, SoundCategory.PLAYERS, 2.0F, 0.5F);
            user.playSound(soundEvent, 2.0F, 0.5F);
            user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.EXPLOSIVECOOLDOWN, CONFIG.cooldown*20/level, 0, false, false, true));

        }

    }

    static {
        if (CONFIG.isEnabled) {

            LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, builder, source) ->
            {
                if (!id.equals(LootTables.ANCIENT_CITY_CHEST))
                    return;

                // Adding enchanted book to ancient city loot table3
                builder.pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.BOOK)
                                .weight(5)
                                .apply(EnchantRandomlyLootFunction.create().add(ModEnchantments.EXPLOSIVE)))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                        .with(EmptyEntry.builder()
                                .weight(10))
                        .build());
            }));
        }
    }

}

