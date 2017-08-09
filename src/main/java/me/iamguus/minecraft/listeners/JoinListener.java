package me.iamguus.minecraft.listeners;

import me.iamguus.minecraft.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

/**
 * Created by guush on 8-8-2017.
 */
public class JoinListener implements Listener {

    private KitPvP plugin;

    public JoinListener(KitPvP plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.playerHandler.createPlayerRecords(player);
        plugin.playerHandler.loadPlayerRecords(player.getUniqueId());
    }
}
