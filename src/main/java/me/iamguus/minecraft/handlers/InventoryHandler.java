package me.iamguus.minecraft.handlers;

import me.iamguus.minecraft.KitPvP;
import me.iamguus.minecraft.models.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by guush on 8-8-2017.
 */
public class InventoryHandler {

    private KitPvP plugin;

    public InventoryHandler(KitPvP plugin) {
        this.plugin = plugin;
    }

    private int calculateSize(int amount) {
        if (amount <= 9) {
            return 9;
        } else if (amount > 9 && amount <= 18) {
            return 18;
        } else if (amount > 18 && amount <= 27) {
            return 27;
        } else if (amount > 27 && amount <= 36) {
            return 36;
        } else if (amount > 36 && amount <= 45) {
            return 45;
        } else if (amount > 45 && amount <= 54) {
            return 54;
        }

        return 54;
    }

    public Inventory startEditKit(KPlayer player) {
        List<KItem> items = new ArrayList<KItem>();
        int[] blackSlots = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
        for (int i : blackSlots) {
            items.add(new KItem(i, createItem(Material.STAINED_GLASS_PANE, " ", new ArrayList<String>(), (short) 15)));
        }

        int[] kitSlots = new int[] { 10, 12, 14, 16 };
        for (int i = 0; i < player.getKits().size(); i++) {
            Kit k = player.getKits().get(i);
            Material mat = Material.NETHER_STAR;
            String title = ChatColor.BLUE + "Kit #" + (i + 1);
            List<String> lore = Arrays.asList("",
                    ChatColor.GRAY + "Click here to",
                    ChatColor.GRAY + "edit Kit #1"
            );
            items.add(new KItem(kitSlots[i], createItem(mat, title, lore)));
        }

        int[] remaining = new int[] { 16, 14, 12 };
        int[] number = new int[] { 4, 3, 2 };
        for (int i = 0; i < (4 - player.getKits().size()); i++) {
            int slot = remaining[i];
            items.add(new KItem(slot, createItem(Material.IRON_FENCE, ChatColor.RED + "Locked", Arrays.asList("",
                    ChatColor.GRAY + "You need a rank",
                    ChatColor.GRAY + "to edit Kit #" + number[i]))));
        }

        InventoryFactory invFact = new InventoryFactory("Your Kits", 27, items);

        return invFact.create();
    }

    public Inventory editKit(Kit k) {
        int size = 54;

        int[] blackSlots = new int[] { 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 22, 24, 26, 27, 28, 29,
                                       30, 31, 32, 33, 34, 35, 36, 38, 40, 42, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };

        List<KItem> items = new ArrayList<KItem>();
        for (int i : blackSlots) {
            items.add(new KItem(i, createItem(Material.STAINED_GLASS_PANE, " ", new ArrayList<String>(), (short) 15)));
        }

        items.add(new KItem(4, createItem(Material.CHEST, ChatColor.BLUE + "Inventory", Arrays.asList("",
                ChatColor.GRAY + "Click here to see",
                ChatColor.GRAY + "your inventory with",
                ChatColor.GRAY + "your latest winnings!",
                "",
                ChatColor.GRAY + "Click to open"))));

        Map<KitItemType, Integer> slots = new HashMap<KitItemType, Integer>() {
            {
                put(KitItemType.PRIMARY, 19);
                put(KitItemType.SECONDARY, 21);
                put(KitItemType.FOOD, 23);
//                put(KitItemType.ABILITY, )
                put(KitItemType.HELMET, 37);
                put(KitItemType.CHESTPLATE, 39);
                put(KitItemType.LEGGINGS, 41);
                put(KitItemType.BOOTS, 43);
            }
        };

        for (KitItem item : k.getItems()) {
            items.add(new KItem(slots.get(item.getType()), createItem(item.getMaterial(), ChatColor.GREEN + item.getName(), Arrays.asList("",
                    (item.getName().endsWith("s") ? ChatColor.GRAY + "These are your" : ChatColor.GRAY + "This is your"),
                    ChatColor.BLUE + "  " + item.getName(),
                    "",
                    ChatColor.GRAY + "Value: " + ChatColor.BLUE + item.getWorth() + " points",
                    "",
                    ChatColor.GRAY + "Click to unequip."))));
        }

        items.add(new KItem(25, createItem(Material.IRON_FENCE, ChatColor.RED + "Coming Soon!", Arrays.asList("", ChatColor.GRAY + "Coming Soon!"))));

        return new InventoryFactory("Kit #" + k.getId(), size, items).create();
    }

    private ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createItem(Material material, String name, List<String> lore, short durability) {
        ItemStack item = new ItemStack(material);
        item.setDurability(durability);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createItem(ItemStack item, String name, List<String> lore, short durability) {
        item.setDurability(durability);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createItem(ItemStack item, String name, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
