package me.codalot.warpme.files;

import me.codalot.warpme.Loader;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class YamlFile {

    protected static Loader loader = Loader.getInstance();

    protected String name;

    protected File file;
    protected FileConfiguration config;

    public YamlFile(File folder, String name) {
        this.name = name;
        file = new File(folder, name);

        if (!this.file.exists())
            create();
        config = YamlConfiguration.loadConfiguration(file);
    }

    protected abstract void create();

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
