package me.iamguus.minecraft.listeners;

import me.iamguus.minecraft.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

/**
 * Created by guush on 7-8-2017.
 */
public class ClickListener implements Listener {

    private KitPvP plugin;

    public ClickListener(KitPvP plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction().name().contains("BLOCK")) {
            if (event.getClickedBlock().getType() == Material.ANVIL) {
                event.setCancelled(true);
                event.getPlayer().openInventory(plugin.inventoryHandler.startEditKit(plugin.playerHandler.players.get(event.getPlayer().getUniqueId())));
            } else
            if (event.getClickedBlock().getType() == Material.DRAGON_EGG) {
                event.setCancelled(true);
                event.getPlayer().openInventory(plugin.inventoryHandler.getEggInventory());
            }
        }
    }
}
