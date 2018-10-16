package me.codalot.warpme;

import me.codalot.warpme.managers.Manager;
import org.bukkit.plugin.java.JavaPlugin;

public class Loader extends JavaPlugin {

    private static Loader instance;

    private Manager manager;

    @Override
    public void onEnable() {
        instance = this;

        manager = new Manager();
        manager.load();
    }

    @Override
    public void onDisable() {
        manager.save();
    }

    public Manager getManager() {
        return manager;
    }

    public static Loader getInstance() {
        return instance;
    }
}
