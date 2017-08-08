package me.iamguus.minecraft.models;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.Set;

/**
 * Created by guush on 7-8-2017.
 */
public class InventoryFactory {

    private String title;
    private int size;
    private Set<KItem> items;

    public InventoryFactory(String title, int size, Set<KItem> items) {
        this.title = title;
        this.size = size;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public Set<KItem> getItems() {
        return items;
    }

    public Inventory create() {
        Inventory inv = Bukkit.createInventory(null, size, title);

        for (KItem item : this.items) {
            inv.setItem(item.getSlot(), item.getItem());
        }

        return inv;
    }
}
