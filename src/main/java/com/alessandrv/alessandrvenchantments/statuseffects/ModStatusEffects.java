package com.alessandrv.alessandrvenchantments.statuseffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {


    public static final StatusEffect SPOTTER = new CooldownStatusEffect();
    public static final StatusEffect DIETWICESTATUS = new DieTwiceStatusEffect();
    public static final StatusEffect DIETWICECOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect MOBGUARDCOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect EXPLOSIVECOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect RINGOFFIRECOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect ENDERDEFENSECOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect ABSORPTIONCOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect HEALINGHEARTCOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect UBIQUITYCOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect BONEMEALCOOLDOWN = new CooldownStatusEffect();
    public static final StatusEffect VOIDLESS = new CooldownStatusEffect();

    public static final StatusEffect FORTUNE = new FortuneEffect();

    public static final StatusEffect FREEZINGSTATUSEFFECT = new FreezingStatusEffect();


    public static void registerStatuses(){
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "spotter"), SPOTTER);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "mobguardcooldown"), MOBGUARDCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "ringoffirecooldown"), RINGOFFIRECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "explosivecooldown"), EXPLOSIVECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "enderdefensecooldown"), ENDERDEFENSECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "absorptioncooldown"), ABSORPTIONCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "healingheartcooldown"), HEALINGHEARTCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "ubiquitycooldown"), UBIQUITYCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "bonemealcooldown"), BONEMEALCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "fortune"), FORTUNE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "freezing"), FREEZINGSTATUSEFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "dietwicestatus"), DIETWICESTATUS);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "dietwicecooldown"), DIETWICECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "voidless"), VOIDLESS);

    }
}
