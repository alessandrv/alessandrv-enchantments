package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.particles.ModParticles;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatusEffects;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.concurrent.atomic.AtomicBoolean;


public class RingOfFireEnchantment extends Enchantment {
    private static final ModConfig.RingOfFireOptions CONFIG = AlessandrvEnchantments.getConfig().ringOfFireOptions;

    public RingOfFireEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_LEGS, new EquipmentSlot[] {EquipmentSlot.LEGS});
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return CONFIG.randomSelection;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return CONFIG.bookOffer;
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
    public boolean canAccept(Enchantment other) {
        return !(other instanceof MobGuardEnchantment || other instanceof EnderDefenseEnchantment || other instanceof ExplosiveEnchantment || other instanceof HealingHeartEnchantment);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (EnchantmentHelper.getLevel(ModEnchantments.RINGOFFIRE, user.getEquippedStack(EquipmentSlot.LEGS)) <= 0) {
            return; // L'armatura incantata non è equipaggiata alle gambe o non ha l'incantesimo ExplosiveAttraction, esci dal metodo
        }
        if(!user.hasStatusEffect(ModStatusEffects.RINGOFFIRECOOLDOWN)){
            World world = user.getEntityWorld();
            AtomicBoolean badGuys = new AtomicBoolean(false);
            if (world instanceof ServerWorld serverWorld) {
                Box boundingBox = user.getBoundingBox().expand(CONFIG.radius + level); // Raggio di 10 blocchi intorno all'entità utente
                serverWorld.getEntitiesByClass(LivingEntity.class, boundingBox, (livingEntity) -> true)
                        .forEach((livingEntity) -> {
                            if (livingEntity instanceof HostileEntity hostileEntity) {
                                Vec3d userPos = user.getPos();
                                Vec3d targetPos = hostileEntity.getPos();
                                Vec3d direction = userPos.subtract(targetPos).normalize();
                                double distance = 2.0 * level; // Distanza desiderata (3 blocchi)
                                Vec3d knockbackVec = direction.multiply(distance);
                                float xC = (float) knockbackVec.getX();
                                float zC = (float) knockbackVec.getZ();

                                hostileEntity.setOnFire(true);
                                hostileEntity.setFireTicks(200);

                                hostileEntity.takeKnockback(2, xC, zC);

                                user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.RINGOFFIRECOOLDOWN, CONFIG.cooldown *20 /level, 0, false, false, true));
                                badGuys.set(true);
                            }
                        });
                if (badGuys.get()){

                    double x = user.getX();
                    double y = user.getY()-0.25;
                    double z = user.getZ();
                    ((ServerWorld)user.getWorld()).spawnParticles(ModParticles.BLASTWAVE,
                            x, y, z, 1, 0.0, 0, 0.0, 0.0);

                    SoundEvent soundEvent = SoundEvents.ITEM_FIRECHARGE_USE;

                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 300, 0, false, false, true));


                    world.playSound(null, x, y, z, soundEvent, SoundCategory.PLAYERS, 2.0F, 0.5F);
                    user.playSound(soundEvent, 2.0F, 0.5F);
                }
            }
        }

        //}

    }

    static
    {


        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, builder, source) ->
        {
            if(!id.equals(LootTables.ANCIENT_CITY_CHEST))
                return;

            // Adding enchanted book to ancient city loot table3
            builder.pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(Items.BOOK)
                            .weight(5)
                            .apply(EnchantRandomlyLootFunction.create().add(ModEnchantments.RINGOFFIRE)))
                    .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    .with(EmptyEntry.builder()
                            .weight(10))
                    .build());
        }));
    }

}

