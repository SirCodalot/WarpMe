package me.codalot.warpme.player;

import me.codalot.warpme.Loader;
import me.codalot.warpme.files.PlayerFile;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.tasks.WarpTeleportTask;
import me.codalot.warpme.warps.Warp;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.util.*;

public class WarpPlayer {

    private Player player;
    private UUID uuid;

    private PlayerFile file;

    private Set<Warp> unlocked;

    private WarpTeleportTask teleport;

    public WarpPlayer(Player player) {
        this.player = player;
        uuid = player.getUniqueId();
        file = new PlayerFile(uuid);

        unlocked = new HashSet<>();
        for (String s : file.getConfig().getStringList("unlocked")) {
            Warp warp = Loader.getInstance().getManager().getWarpManager().getWarp(s);
            if (warp != null)
                unlocked.add(warp);
        }
    }

    public void save() {
        List<String> unlocked = new ArrayList<>();
        for (Warp warp : this.unlocked)
            unlocked.add(warp.getName());

        file.getConfig().set("unlocked", unlocked);

        file.save();
    }

    public boolean canTeleportTo(Warp warp) {
        if (warp.isUnlockable())
            return hasUnlocked(warp);
        return true;
    }

    public boolean hasUnlocked(Warp warp) {
        return unlocked.contains(warp);
    }

    public void unlockWarp(Warp warp) {
        player.sendMessage(Message.UNLOCK_MESSAGE.getMessage().replace("%warp", warp.getName()));
        player.sendTitle("§b§l" + warp.getDisplayName(), "§eYou Discovered a New Warp");
        unlocked.add(warp);
    }

    public void unload() {
        file.save();
    }

    public WarpTeleportTask getTeleport() {
        return teleport;
    }

    public void teleport(Warp warp) {
        if (!canTeleportTo(warp)) {
            Message.UNLOCK_NOT_UNLOCKED.send(player);
            return;
        }

        if (teleport == null)
            teleport = new WarpTeleportTask(player, warp);
    }

    public void removeTeleportTask() {
        teleport = null;
    }

}
