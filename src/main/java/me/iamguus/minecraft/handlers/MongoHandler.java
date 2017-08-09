package me.iamguus.minecraft.handlers;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import me.iamguus.minecraft.models.KPlayer;
import me.iamguus.minecraft.models.Kit;
import me.iamguus.minecraft.models.KitItem;
import org.apache.commons.lang.Validate;
import org.bson.Document;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by guush on 8-8-2017.
 */
public class MongoHandler {

    private Plugin plugin;
    private MongoClient client;

    public MongoDatabase db;

    public MongoHandler(Plugin plugin) {
        this.plugin = plugin;
        this.client = new MongoClient("localhost", 27017);
        this.db = this.client.getDatabase("minecraft");
    }

    public boolean exists(UUID uuid) {
        MongoCollection<Document> collection = db.getCollection("users");

        MongoCursor<Document> found = collection.find(Filters.eq("uuid", uuid)).iterator();
        if (found.hasNext()) {
            return true;
        } else {
            return false;
        }
    }

    public void insertNewPlayer(KPlayer player) {
        MongoCollection<Document> collection = db.getCollection("users");

        Document document = new Document("uuid", player.getUuid());

        List<Document> kitList = new ArrayList<Document>();
        for (Kit k : player.getKits()) {
            Document doc = new Document();
            for (KitItem item : k.getItems()) {
                doc.append(item.getType().name(), item.stringify());
            }
            kitList.add(doc);
        }

        document.append("kits", kitList);

        collection.insertOne(document);
    }

    public Document findPlayer(UUID uuid) {
        Validate.isTrue(exists(uuid), "The user with the given uuid does not exist!");

        MongoCollection<Document> collection = db.getCollection("users");

        MongoCursor<Document> found = collection.find(Filters.eq("uuid", uuid)).iterator();

        Document toReturn = null;
        while (found.hasNext()) {
            toReturn = found.next();
        }

        return toReturn;
    }
}