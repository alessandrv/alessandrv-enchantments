package com.alessandrv.alessandrvenchantments.statuseffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatuses {


    public static final StatusEffect SPOTTERCOOLDOWN = new SpotterCooldown();
    public static final StatusEffect MOBGUARDCOOLDOWN = new MobGuardCooldown();
    public static final StatusEffect EXPLOSIVECOOLDOWN = new ExplosiveCooldown();
    public static final StatusEffect RINGOFFIRECOOLDOWN = new RingOfFireCooldown();
    public static final StatusEffect ENDERDEFENSECOOLDOWN = new EnderDefenseCooldown();
    public static final StatusEffect ABSORPTIONCOOLDOWN = new AbsorptionCooldown();
    public static final StatusEffect HEALINGHEARTCOOLDOWN = new HealingHeartCooldown();
    public static final StatusEffect UBIQUITYCOOLDOWN = new UbiquityCooldown();
    public static final StatusEffect BONEMEALCOOLDOWN = new BoneMealCooldown();
    public static final StatusEffect FORTUNE = new FortuneEffect();


    public static void registerStatuses(){
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "spottercooldown"), SPOTTERCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "mobguardcooldown"), MOBGUARDCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "ringoffirecooldown"), RINGOFFIRECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "explosivecooldown"), EXPLOSIVECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "enderdefensecooldown"), ENDERDEFENSECOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "absorptioncooldown"), ABSORPTIONCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "healingheartcooldown"), HEALINGHEARTCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "ubiquitycooldown"), UBIQUITYCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "bonemealcooldown"), BONEMEALCOOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("alessandrvenchantments", "fortune"), FORTUNE);

    }
}
