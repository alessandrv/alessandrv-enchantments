package com.alessandrv.alessandrvenchantments.util.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.math.MathHelper;

@Config(name = "alessandrvenchantments")
//@Config.Gui.Background("qu-enchantments:textures/block/hot_obsidian_2.png")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public final BoneMealOptions boneMealOptions = new BoneMealOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final DieTwiceOptions dieTwiceOptions = new DieTwiceOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final EnderDefenseOptions enderDefenseOptions = new EnderDefenseOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final ExplosiveOptions explosiveOptions = new ExplosiveOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final FreezeAspectOptions freezeAspectOptions = new FreezeAspectOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final GlowerOptions glowerOptions = new GlowerOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final GlowingOptions glowingOptions = new GlowingOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final HealingHeartOptions healingHeartOptions = new HealingHeartOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final MobGuardOptions mobGuardOptions = new MobGuardOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final NightStalkerOptions nightStalkerOptions = new NightStalkerOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final RingOfFireOptions ringOfFireOptions = new RingOfFireOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final SoulboundOptions soulboundOptions = new SoulboundOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final SpotterOptions spotterOptions = new SpotterOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final UbiquityOptions ubiquityOptions = new UbiquityOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final VampiricOptions vampiricOptions = new VampiricOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final VoidlessOptions voidlessOptions = new VoidlessOptions();




    public static class BoneMealOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 5;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int lvl1Area = 3;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int lvl2Area = 5;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int lvl3Area = 9;
    }
    public static class DieTwiceOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
    }
    public static class EnderDefenseOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 30;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int radius = 5;
    }
    public static class ExplosiveOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 60;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 32)
        public float power = 3.0F;
    }
    public static class FreezeAspectOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;

    }
    public static class GlowerOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
    }
    public static class GlowingOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
    }
    public static class HealingHeartOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 150;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int radius = 10;
    }
    public static class MobGuardOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 30;
        public int freezeTime = 10;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int radius = 10;
    }
    public static class NightStalkerOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;

    }
    public static class RingOfFireOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 60;
        @ConfigEntry.BoundedDiscrete(min = 2, max = 64)
        public int radius = 5;
    }
    public static class SoulboundOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = false;
        public boolean bookOffer = true;
        public boolean isTreasure = true;
    }
    public static class SpotterOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int radius = 25;

    }
    public static class UbiquityOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 15;
    }
    public static class VampiricOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public boolean mutuallyExlusive = true;
    }
    public static class VoidlessOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;

    }



    @Override
    public void validatePostLoad() {
        healingHeartOptions.radius = MathHelper.clamp(healingHeartOptions.radius, 0, 64);
        mobGuardOptions.radius = MathHelper.clamp(mobGuardOptions.radius, 0, 64);
        enderDefenseOptions.radius = MathHelper.clamp(enderDefenseOptions.radius, 0, 64);
        explosiveOptions.power = MathHelper.clamp(explosiveOptions.power, 0, 64);
        ringOfFireOptions.radius = MathHelper.clamp(ringOfFireOptions.radius, 0, 64);
        spotterOptions.radius = MathHelper.clamp(spotterOptions.radius, 0, 64);
        boneMealOptions.lvl1Area = MathHelper.clamp(boneMealOptions.lvl1Area, 1, 64);
        boneMealOptions.lvl2Area = MathHelper.clamp(boneMealOptions.lvl2Area, 1, 64);
        boneMealOptions.lvl3Area = MathHelper.clamp(boneMealOptions.lvl3Area, 1, 64);


    }
}