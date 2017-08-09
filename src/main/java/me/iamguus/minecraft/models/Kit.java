package me.iamguus.minecraft.models;

import org.bson.Document;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by guush on 7-8-2017.
 */
public class Kit {

    private int id;
    private UUID owner;
    private List<KitItem> items;

    public Kit(int id, UUID owner, List<KitItem> items) {
        this.id = id;
        this.owner = owner;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public UUID getOwner() {
        return owner;
    }

    public List<KitItem> getItems() {
        return items;
    }

    public static Kit createDefault(UUID uuid) {
        List<KitItem> kitItems = new ArrayList<KitItem>();
        kitItems.add(new KitItem(Material.DIAMOND_SWORD, "Diamond Sword", new ArrayList<String>(), KitItemType.PRIMARY, 0));
        kitItems.add(new KitItem(Material.BOW, "Bow", new ArrayList<String>(), KitItemType.SECONDARY, 0, new ItemStack(Material.ARROW, 5)));
        kitItems.add(new KitItem(new ItemStack(Material.APPLE, 5), "Apples", new ArrayList<String>(), KitItemType.FOOD, 0));
        kitItems.add(new KitItem(Material.IRON_HELMET, "Iron Helmet", new ArrayList<String>(), KitItemType.HELMET, 0));
        kitItems.add(new KitItem(Material.IRON_CHESTPLATE, "Iron Chestplate", new ArrayList<String>(), KitItemType.CHESTPLATE, 0));
        kitItems.add(new KitItem(Material.IRON_LEGGINGS, "Iron Leggings", new ArrayList<String>(), KitItemType.LEGGINGS, 0));
        kitItems.add(new KitItem(Material.IRON_BOOTS, "Iron Boots", new ArrayList<String>(), KitItemType.BOOTS, 0));

        return new Kit(1, uuid, kitItems);
    }
}
