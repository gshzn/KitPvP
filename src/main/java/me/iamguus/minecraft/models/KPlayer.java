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

    public KPlayer(UUID uuid, List<Kit> kits) {
        this.uuid = uuid;
        this.kits = kits;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Kit> getKits() {
        return kits;
    }
}
