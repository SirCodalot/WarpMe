package me.codalot.warpme.managers;

import me.codalot.warpme.files.ConfigFile;

import java.io.File;

public class FileManager extends BaseManager {

    private ConfigFile settings;
    private ConfigFile warps;

    public FileManager() {
        createFolder("players");

        settings = new ConfigFile("settings.yml");
        warps = new ConfigFile("warps.yml");
    }

    @Override
    public void save() {
        warps.save();
    }

    public ConfigFile getSettings() {
        return settings;
    }

    public ConfigFile getWarps() {
        return warps;
    }

    private void createFolder(String name) {
        File file = new File(loader.getDataFolder(), name);
        if (!file.exists())
            file.mkdirs();
    }
}
