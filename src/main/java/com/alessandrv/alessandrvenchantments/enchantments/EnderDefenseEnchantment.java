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
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.FoxEntity;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.concurrent.atomic.AtomicBoolean;


public class EnderDefenseEnchantment extends Enchantment {
    private static final ModConfig.EnderDefenseOptions CONFIG = AlessandrvEnchantments.getConfig().enderDefenseOptions;

    public EnderDefenseEnchantment() {
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
    public boolean canAccept(Enchantment other) {
        return !(other instanceof MobGuardEnchantment || other instanceof RingOfFireEnchantment || other instanceof ExplosiveEnchantment || other instanceof HealingHeartEnchantment);
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
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (EnchantmentHelper.getLevel(ModEnchantments.ENDERDEFENSE, user.getEquippedStack(EquipmentSlot.LEGS)) <= 0) {
            return; // L'armatura incantata non è equipaggiata alle gambe o non ha l'incantesimo ExplosiveAttraction, esci dal metodo
        }
        if(!user.hasStatusEffect(ModStatusEffects.ENDERDEFENSECOOLDOWN)) {
            World world;
            world = user.getEntityWorld();
            if (world instanceof ServerWorld serverWorld) {
                AtomicBoolean badGuys = new AtomicBoolean(false);
                Box boundingBox = user.getBoundingBox().expand(CONFIG.radius + level); // Raggio di 10 blocchi intorno all'entità utente
                serverWorld.getEntitiesByClass(LivingEntity.class, boundingBox, (livingEntity) -> true)
                        .forEach((livingEntity) -> {
                            if (livingEntity instanceof HostileEntity hostileEntity) {

                                if (hostileEntity.getTarget() == user) {

                                    badGuys.set(true);
                                    double d = user.getX();
                                    double e = user.getY();
                                    double f = user.getZ();

                                    for (int i = 0; i < 16; ++i) {
                                        double g = user.getX() + (hostileEntity.getRandom().nextDouble() - 0.5) * 16.0 * level;
                                        double h = MathHelper.clamp(user.getY() + (double) (user.getRandom().nextInt(16) - 8), world.getBottomY(), world.getBottomY() + ((ServerWorld) world).getLogicalHeight() - 1);
                                        double j = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 16.0 * level;
                                        if (hostileEntity.hasVehicle()) hostileEntity.stopRiding();

                                        Vec3d vec3d = hostileEntity.getPos();
                                        if (hostileEntity.teleport(g, h, j, true)) {
                                            world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(hostileEntity));
                                            SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                                            world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                                            user.playSound(soundEvent, 1.0F, 1.0F);
                                            hostileEntity.setTarget(null);
                                            break;
                                        }
                                    }


                                }
                            }
                        });
                if(badGuys.get()){
                    double x = user.getX();
                    double y = user.getY() - 0.25;
                    double z = user.getZ();
                    ((ServerWorld) user.getWorld()).spawnParticles(ModParticles.ENDERWAVE,
                            x, y, z, 1, 0.0, 0, 0.0, 0.0);
                    user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.ENDERDEFENSECOOLDOWN, CONFIG.cooldown *20, 0, false, false, true));

                }
            }
        }


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
                            .apply(EnchantRandomlyLootFunction.create().add(ModEnchantments.ENDERDEFENSE)))
                    .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    .with(EmptyEntry.builder()
                            .weight(10))
                    .build());
        }));
    }

}

