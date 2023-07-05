package com.alessandrv.alessandrvenchantments.util;

import net.minecraft.entity.effect.StatusEffectInstance;

public class PlayerEffectStorage {
    private static StatusEffectInstance preservedEffect;
    private static boolean dieTwice;

    public static void setPreservedEffect(StatusEffectInstance effect) {
        preservedEffect = effect;
    }

    public static StatusEffectInstance getPreservedEffect() {
        return preservedEffect;
    }
    public static void setDieTwice(boolean value){
        dieTwice = value;
    }
    public static boolean getDieTwice(){
        return dieTwice;
    }
}
