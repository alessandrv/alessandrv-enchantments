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
    public final EnderDefenseOptions enderDefenseOptions = new EnderDefenseOptions();
    @ConfigEntry.Gui.CollapsibleObject
    public final ExplosiveOptions explosiveOptions = new ExplosiveOptions();
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


    public static class BoneMealOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 5;
    }
    public static class EnderDefenseOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 30;
        public int radius = 5;
    }
    public static class ExplosiveOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 60;
        public float power = 3.0F;
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
        public int radius = 10;
    }
    public static class MobGuardOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 30;
        public int freezeTime = 10;
        public int radius = 10;
    }
    public static class NightStalkerOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = false;
        public boolean bookOffer = false;
        @ConfigEntry.BoundedDiscrete(min = 8, max = 64)
        public double radius = 16.0;
    }
    public static class RingOfFireOptions {
        public boolean isEnabled = true;
        public boolean randomSelection = true;
        public boolean bookOffer = true;
        public int cooldown = 60;
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




    @Override
    public void validatePostLoad() {
        nightStalkerOptions.radius = MathHelper.clamp(nightStalkerOptions.radius, 0.0, 64.0);
    }
}