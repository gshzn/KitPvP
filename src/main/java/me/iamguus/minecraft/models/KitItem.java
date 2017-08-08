package me.iamguus.minecraft.models;

import org.bson.Document;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guush on 7-8-2017.
 */
public class KitItem {

    private ItemStack item;
    private String name;
    private List<String> lore;
    private KitItemType type;
    private double worth;
    private ItemStack secondary;


    public KitItem(Material material, String name, List<String> lore, KitItemType type, double worth) {
        this.item = new ItemStack(material);
        this.name = name;
        this.lore = lore;
        this.type = type;
        this.worth = worth;
        this.secondary = new ItemStack(Material.AIR);
    }

    public KitItem(Material material, String name, List<String> lore, KitItemType type, double worth, ItemStack secondary) {
        this.item = new ItemStack(material);
        this.name = name;
        this.lore = lore;
        this.type = type;
        this.worth = worth;
        this.secondary = secondary;
    }

    public KitItem(ItemStack item, String name, List<String> lore, KitItemType type, double worth, ItemStack secondary) {
        this.item = item;
        this.name = name;
        this.lore = lore;
        this.type = type;
        this.worth = worth;
        this.secondary = secondary;
    }

    public KitItem(ItemStack item, String name, List<String> lore, KitItemType type, double worth) {
        this.item = item;
        this.name = name;
        this.lore = lore;
        this.type = type;
        this.worth = worth;
        this.secondary = new ItemStack(Material.AIR);
    }

    public ItemStack getMaterial() { return item; }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public KitItemType getType() {
        return type;
    }

    public double getWorth() { return worth; }

    public ItemStack getSecondary() { return secondary; }

    public Document stringify() {
        Document doc = new Document();
        doc     .append("material", this.getMaterial().getType().name())
                .append("name", this.getName())
                .append("lore", lore)
                .append("type", this.getType().name());

        if (this.getSecondary().getType() != Material.AIR) {
            doc.append("secondary", new Document("material", this.getSecondary().getType().name())
                                            .append("amount", this.getSecondary().getAmount()));
        }

        return doc;
    }

    public static KitItem fromString(Document doc) {
        ItemStack item = new ItemStack(Material.getMaterial(doc.getString("material").toUpperCase()));
        String name = doc.getString("name");
        List<String> lore = doc.get("lore", new ArrayList<String>());
        KitItemType type = KitItemType.valueOf(doc.getString("type").toUpperCase());
        ItemStack secondary = new ItemStack(Material.AIR);

        if (doc.containsKey("secondary")) {
            Document secondaryDoc = doc.get("secondary", Document.class);
            secondary.setType(Material.valueOf(secondaryDoc.getString("material")));
            secondary.setAmount(secondaryDoc.getInteger("amount"));
        }

        return new KitItem(item, name, lore, type, 0, secondary);
    }
}
