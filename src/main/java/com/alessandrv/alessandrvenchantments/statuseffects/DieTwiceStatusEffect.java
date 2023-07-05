package com.alessandrv.alessandrvenchantments.statuseffects;

import com.alessandrv.alessandrvenchantments.AlessandrvEnchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class DieTwiceStatusEffect extends StatusEffect {
    public DieTwiceStatusEffect() {
        super(
                StatusEffectCategory.NEUTRAL, // whether beneficial or harmful for entities
                0x00000); // color in RGB
    }

    // This method is called every tick to check whether it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return false;
    }
    private static double f;
    private static double g;
    private static double h;
    public static void setCoords(double x, double y, double z){
        f =x;
        g=y;
        h=z;

    }
    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(f != 0.0 && g != 0 && h!=0){
            entity.teleport(f,g,h);

            World world = entity.getWorld();
            Vec3d vec3d = entity.getPos();

            world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
            SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
            world.playSound(null, f, g, h, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
            entity.playSound(soundEvent, 1.0F, 1.0F);
            setCoords(0,0,0);
             // Rimuovi l'effetto corrente

            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0, false, false, true));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 2, false, false, true));
            entity.addStatusEffect(new StatusEffectInstance(ModStatusEffects.DIETWICECOOLDOWN, 6000, 0, false, false, true));

        }
        else{
            AlessandrvEnchantments.LOGGER.info("Coordinate sbagliate");
        }

        super.onRemoved(entity, attributes, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 640, 0, false, false, true));

        super.onApplied(entity, attributes, amplifier);
    }
}