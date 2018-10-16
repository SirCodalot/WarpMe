package me.codalot.warpme.files;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFile extends YamlFile {

    public ConfigFile(String name) {
        super(loader.getDataFolder(), name);
    }

    protected void create() {
        file.getParentFile().mkdir();
        loader.saveResource(name, false);
    }

}
