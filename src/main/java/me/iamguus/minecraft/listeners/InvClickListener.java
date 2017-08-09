package me.iamguus.minecraft.listeners;

import me.iamguus.minecraft.KitPvP;
import me.iamguus.minecraft.models.Kit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * Created by guush on 9-8-2017.
 */
public class InvClickListener implements Listener {

    private KitPvP plugin;

    public InvClickListener(KitPvP plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void openEditKit(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getTitle().contains("Your Kits")) {
                event.setCancelled(true);
                if (event.getSlotType() != InventoryType.SlotType.OUTSIDE) {
                    if (event.getCurrentItem().getType() == Material.NETHER_STAR) {
                        String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
                        int kitId = Integer.parseInt(displayName.substring(displayName.length() - 1, displayName.length()));
                        Kit k = plugin.playerHandler.players.get(player.getUniqueId()).getKits().get(kitId - 1);
                        player.closeInventory();
                        player.openInventory(plugin.inventoryHandler.editKit(k));
                    }
                }
            }
        }
    }
}
