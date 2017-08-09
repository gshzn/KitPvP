package me.iamguus.minecraft.models.collections;

import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by guush on 9-8-2017.
 */
public class Collection {

    private String name;
    private boolean rollable;
    private ItemStack item;
    private List<CItem> items;

    public Collection(String name, boolean rollable, ItemStack item, List<CItem> items) {
        this.name = name;
        this.rollable = rollable;
        this.item = item;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItem() {
        return item;
    }

    public boolean isRollable() {
        return rollable;
    }

    public List<CItem> getItems() {
        return items;
    }
}
