package me.iamguus.minecraft.models;

import org.bukkit.inventory.ItemStack;

/**
 * Created by guush on 7-8-2017.
 */
public class KItem {

    private int slot;
    private ItemStack item;

    public KItem(int slot, ItemStack item) {
        this.slot = slot;
        this.item = item;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }
}
