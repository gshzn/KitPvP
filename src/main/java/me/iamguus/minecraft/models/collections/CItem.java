package me.iamguus.minecraft.models.collections;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by guush on 9-8-2017.
 */
public class CItem {

    private ItemStack item;
    private String name;
    private int value;
    private double chance;
    private ItemStack secondary;

    public CItem(ItemStack item, String name, int value, double chance) {
        this.item = item;
        this.name = name;
        this.value = value;
        this.chance = chance;
        this.secondary = new ItemStack(Material.AIR);
    }

    public CItem(ItemStack item, String name, int value, double chance, ItemStack secondary) {
        this.item = item;
        this.name = name;
        this.value = value;
        this.chance = chance;
        this.secondary = secondary;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public double getChance() {
        return chance;
    }

    public ItemStack getSecondary() {
        return secondary;
    }
}
