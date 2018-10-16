package me.codalot.warpme.managers;

import me.codalot.warpme.files.ConfigFile;
import me.codalot.warpme.utils.ConfigUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class WarpManager extends BaseManager {

    public static HashMap<Player, Warp> renameMode = new HashMap<>();
    public static HashMap<Player, Warp> reDisplayNameMode = new HashMap<>();

    private List<Warp> warps;

    private ConfigFile config;

    public WarpManager() {
        warps = new ArrayList<>();
        config = loader.getManager().getFileManager().getWarps();

        for (String key : config.getConfig().getConfigurationSection("warps").getKeys(false))
            loadWarp(key);
    }

    @Override
    protected void save() {
        HashMap<String, Object> map = new HashMap<>();
        for (Warp warp : warps)
            map.put(warp.getName(), warp.serialize());
        config.getConfig().set("warps", map);
    }

    private void loadWarp(String name) {
        warps.add(new Warp(name, ConfigUtils.getConfigSectionValue(config.getConfig().getConfigurationSection("warps." + name), true)));
    }

    public void deleteWarp(Warp warp) {
        warps.remove(warp);
    }



    public Warp getWarp(String name) {
        for (Warp warp : warps)
            if (warp.getName().equalsIgnoreCase(name))
                return warp;
        return null;
    }

    public Warp createWarp(String name, Location location) {
        Warp warp = new Warp(name, location);
        warps.add(warp);
        return warp;
    }

    public List<Warp> getWarps() {
        return warps;
    }
}
