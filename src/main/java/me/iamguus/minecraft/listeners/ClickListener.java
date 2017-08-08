package me.iamguus.minecraft.listeners;

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

    private Plugin plugin;

    public ClickListener(Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction().name().contains("BLOCK")) {
            event.setCancelled(true);
            if (event.getClickedBlock().getType() == Material.ANVIL) {
                //TODO: Open GUI
            }
        }
    }
}
