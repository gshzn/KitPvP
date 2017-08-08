package me.iamguus.minecraft.handlers;

import me.iamguus.minecraft.KitPvP;
import me.iamguus.minecraft.models.KPlayer;
import me.iamguus.minecraft.models.Kit;
import me.iamguus.minecraft.models.KitItem;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by guush on 7-8-2017.
 */
public class PlayerHandler {

    private KitPvP plugin;

    private Map<UUID, KPlayer> players;

    public PlayerHandler(KitPvP plugin) {
        this.plugin = plugin;

        this.players = new HashMap<UUID, KPlayer>();
    }

    public void loadPlayerKits(UUID uuid) {
        Document playerDoc = plugin.mongoHandler.findPlayer(uuid);

        List<Kit> kits = new ArrayList<Kit>();

        ArrayList<Document> kitList = playerDoc.get("kits", new ArrayList<Document>());
        for (Document kitDoc : kitList) {
            List<KitItem> kitItems = new ArrayList<KitItem>();
            for (Map.Entry<String, Object> entry : kitDoc.entrySet()) {
                Document tempDoc = (Document) entry.getValue();
                kitItems.add(KitItem.fromString(tempDoc));
            }
            kits.add(new Kit(uuid, kitItems));
        }

        this.players.put(uuid, new KPlayer(uuid, kits));

    }

    public void createPlayerRecords(Player player) {
        if (!this.plugin.mongoHandler.exists(player.getUniqueId())) {
            KPlayer newPlayer = new KPlayer(player.getUniqueId(), Arrays.asList(Kit.createDefault(player.getUniqueId())));

            this.plugin.mongoHandler.insertNewPlayer(newPlayer);
        }
    }
}
