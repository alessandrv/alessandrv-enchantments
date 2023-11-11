package com.alessandrv.alessandrvenchantments.enchantments;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import com.alessandrv.alessandrvenchantments.statuseffects.ModStatusEffects;
import com.alessandrv.alessandrvenchantments.util.config.ModConfig;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;


public class SoulSwapperEnchantment extends Enchantment {
    private static final ModConfig.EnderDefenseOptions CONFIG = AlessandrvEnchantments.getConfig().enderDefenseOptions;


    public SoulSwapperEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.BOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
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
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        Vec3d vec3dTarget = target.getPos();
        if (!user.hasStatusEffect(ModStatusEffects.ENDERDEFENSECOOLDOWN)) {
            World world;
            world = user.getEntityWorld();
            if (world instanceof ServerWorld) {

                if (target instanceof LivingEntity livingEntity) {


                    if (livingEntity.hasVehicle()) livingEntity.stopRiding();



                    user.teleport(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());

                   // livingEntity.teleport(vec3dUser.x, vec3dUser.y, vec3dUser.z);

                    world.emitGameEvent(GameEvent.TELEPORT, vec3dTarget, GameEvent.Emitter.of(livingEntity));
                    SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    user.playSound(soundEvent, 1.0F, 1.0F);
                }
            }
        }
    }

    static
    {
        if(CONFIG.isEnabled) {

            LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, builder, source) ->
            {
                if (!id.equals(LootTables.ANCIENT_CITY_CHEST))
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

}

