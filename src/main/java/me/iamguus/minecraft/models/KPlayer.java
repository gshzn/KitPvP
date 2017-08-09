package me.iamguus.minecraft.models;

import java.util.List;
import java.util.UUID;

/**
 * Created by guush on 7-8-2017.
 */
public class KPlayer {

    //TODO: add more vars

    private UUID uuid;
    private List<Kit> kits;
    private int eggs;

    public KPlayer(UUID uuid, List<Kit> kits, int eggs) {
        this.uuid = uuid;
        this.kits = kits;
        this.eggs = eggs;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Kit> getKits() {
        return kits;
    }

    public int getEggs() {
        return eggs;
    }
}
