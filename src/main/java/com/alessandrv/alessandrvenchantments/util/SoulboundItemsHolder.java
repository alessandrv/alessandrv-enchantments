package com.alessandrv.alessandrvenchantments.util;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface SoulboundItemsHolder {
    List<ItemStack> getSoulboundItems();
    List<Integer> getSoulboundItemsSlot();
}