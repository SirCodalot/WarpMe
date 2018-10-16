package me.codalot.warpme.managers;

import me.codalot.warpme.player.WarpPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerManager extends BaseManager {

    private HashMap<Player, WarpPlayer> players;

    public PlayerManager() {
        players = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers())
            loadPlayer(player);
    }

    @Override
    public void save() {
        for (Player player : players.keySet())
            unloadPlayer(player);
    }

    public WarpPlayer loadPlayer(Player player) {
        WarpPlayer wPlayer = new WarpPlayer(player);
        players.put(player, wPlayer);
        return wPlayer;
    }

    public void unloadPlayer(Player player) {
        players.get(player).unload();
        players.remove(player);
    }

    public WarpPlayer getPlayer(Player player) {
        return players.get(player);
    }
}
