package me.codalot.warpme.commands;

import me.codalot.warpme.Loader;
import me.codalot.warpme.managers.WarpManager;
import org.bukkit.command.CommandExecutor;

public abstract class BaseCommand implements CommandExecutor {

    protected static Loader loader = Loader.getInstance();

    public BaseCommand(String name) {
        loader.getCommand(name).setExecutor(this);
    }

    protected static WarpManager getManager() {
        return loader.getManager().getWarpManager();
    }

}
