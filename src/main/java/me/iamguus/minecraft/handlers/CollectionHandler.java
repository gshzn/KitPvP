package me.iamguus.minecraft.handlers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import me.iamguus.minecraft.KitPvP;
import me.iamguus.minecraft.models.collections.CItem;
import me.iamguus.minecraft.models.collections.Collection;
import org.bson.Document;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guush on 9-8-2017.
 */
public class CollectionHandler {

    private KitPvP plugin;

    MongoDatabase db;
    List<Collection> collections;

    public CollectionHandler(KitPvP plugin) {
        this.plugin = plugin;

        this.db = plugin.mongoHandler.db;
        this.collections = new ArrayList<Collection>();

        loadCollections();
    }

    private void loadCollections() {
        MongoCollection coll = db.getCollection("collections");
        MongoCursor docs = coll.find().iterator();

        while (docs.hasNext()) {
            Document doc = (Document) docs.next();
            String name = doc.getString("name");
            ItemStack collItem = new ItemStack(Material.valueOf(doc.getString("item").toUpperCase()));
            boolean rollable = doc.getBoolean("rollable");
            List<CItem> items = new ArrayList<CItem>();
            for (Document item : doc.get("items", new ArrayList<Document>())) {
                ItemStack itemStack = new ItemStack(Material.valueOf(item.getString("material").toUpperCase()), doc.getInteger("amount", 1));
                String itemName = item.getString("name");
                System.out.println(itemName);
                int value = item.getInteger("value");
                double chance = item.getDouble("chance");
                ItemStack secondary = null;

                if (item.containsKey("enchantments")) {
                    List<Document> enchants = item.get("enchantments", new ArrayList<Document>());
                    for (Document enchant : enchants) {
                        System.out.println(enchant.getString("name"));
                        itemStack.addUnsafeEnchantment(Enchantment.getByName(enchant.getString("name")), enchant.getInteger("level"));
                    }
                }

                if (item.containsKey("secondary")) {
                    Document secondaryDoc = item.get("secondary", Document.class);
                    System.out.println(secondaryDoc.getString("material"));
                    secondary = new ItemStack(Material.valueOf(secondaryDoc.getString("material").toUpperCase()), secondaryDoc.getInteger("amount"));
                }

                if (secondary != null) {
                    items.add(new CItem(itemStack, itemName, value, chance, secondary));
                } else {
                    items.add(new CItem(itemStack, itemName, value, chance));
                }
            }
            collections.add(new Collection(name, rollable, collItem, items));
        }
    }

    public List<Collection> getRollableCollections() {
        List<Collection> collections = new ArrayList<Collection>();
        for (Collection c : this.collections) {
            if (c.isRollable()) {
                collections.add(c);
            }
        }

        return collections;
    }
}
