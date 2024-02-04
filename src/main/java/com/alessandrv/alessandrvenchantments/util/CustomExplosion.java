package com.alessandrv.alessandrvenchantments.util;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class CustomExplosion extends Explosion {

    private static final ExplosionBehavior DEFAULT_BEHAVIOR = new ExplosionBehavior();
    private static final int field_30960 = 16;
    private final boolean createFire;
    private final DestructionType destructionType;
    private final Random random;
    private final World world;
    private final double x;
    private final double y;
    private final double z;
    @Nullable
    private final Entity entity;
    private final float power;
    private final DamageSource damageSource;

    private final ObjectArrayList<BlockPos> affectedBlocks;
    private final Map<PlayerEntity, Vec3d> affectedPlayers;
    public CustomExplosion(World world, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, DestructionType destructionType) {
        super(world, entity, damageSource, behavior, x, y, z, power, createFire, destructionType);
        this.random = Random.create();
        this.affectedBlocks = new ObjectArrayList();
        this.affectedPlayers = Maps.newHashMap();
        this.world = world;
        this.entity = entity;
        this.power = power;
        this.x = x;
        this.y = y;
        this.z = z;
        this.createFire = createFire;
        this.destructionType = destructionType;
        this.damageSource = damageSource == null ? world.getDamageSources().explosion(this) : damageSource;

    }


    public void DamageEntities() {
        this.world.emitGameEvent(this.entity, GameEvent.EXPLODE, new Vec3d(this.x, this.y, this.z));

        List<Entity> entities = this.world.getOtherEntities(this.entity, new Box(this.x - this.power, this.y - this.power, this.z - this.power, this.x + this.power, this.y + this.power, this.z + this.power));
        Vec3d explosionPosition = new Vec3d(this.x, this.y, this.z);

        for (Entity entity : entities) {
            if (!(entity instanceof ItemEntity) && !(entity instanceof ExperienceOrbEntity)){


                if (!entity.isImmuneToExplosion()) {
                    double distanceToEntity = entity.squaredDistanceTo(explosionPosition);
                    double proximity = 1.0 - Math.sqrt(distanceToEntity) / this.power;

                    if (proximity > 0) {
                        double damage = (proximity * proximity + proximity) / 2.0 * 7.0 * this.power + 1.0;
                        entity.damage(this.getDamageSource(), (float) damage);

                        if (entity instanceof LivingEntity livingEntity) {
                            double knockback = ProtectionEnchantment.transformExplosionKnockback(livingEntity, proximity);
                            Vec3d knockbackVector = explosionPosition.subtract(entity.getPos()).normalize().multiply(knockback);
                            entity.setVelocity(entity.getVelocity().add(knockbackVector));
                        }
                    }
            }
            }
        }
    }



}