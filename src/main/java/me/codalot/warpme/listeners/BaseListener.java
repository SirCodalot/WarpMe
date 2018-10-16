package me.codalot.warpme.listeners;

import me.codalot.warpme.Loader;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class BaseListener implements Listener {

    protected static Loader loader = Loader.getInstance();

    public BaseListener() {
        Bukkit.getPluginManager().registerEvents(this, loader);
    }

}
