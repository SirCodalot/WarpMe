package me.codalot.warpme.files;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerFile extends YamlFile {

    private UUID uuid;

    public PlayerFile(UUID uuid) {
        super(new File(loader.getDataFolder(), "players"), uuid.toString() + ".yml");
    }

    @Override
    protected void create() {
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
