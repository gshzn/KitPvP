package me.iamguus.minecraft;

import me.iamguus.minecraft.handlers.CollectionHandler;
import me.iamguus.minecraft.handlers.InventoryHandler;
import me.iamguus.minecraft.handlers.MongoHandler;
import me.iamguus.minecraft.handlers.PlayerHandler;
import me.iamguus.minecraft.listeners.ClickListener;
import me.iamguus.minecraft.listeners.InvClickListener;
import me.iamguus.minecraft.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by guush on 7-8-2017.
 */
public class KitPvP extends JavaPlugin {

    public PlayerHandler playerHandler;
    public MongoHandler mongoHandler;
    public InventoryHandler inventoryHandler;
    public CollectionHandler collectionHandler;

    public void onEnable() {
        mongoHandler = new MongoHandler(this);
        collectionHandler = new CollectionHandler(this);
        playerHandler = new PlayerHandler(this);
        inventoryHandler = new InventoryHandler(this);

        new ClickListener(this);
        new JoinListener(this);
        new InvClickListener(this);

        System.out.println("KitPvP by iAmGuus has been enabled!");
    }

    public void onDisable() {
        System.out.println("KitPvP by iAmGuus is now disabling!");
    }
}
