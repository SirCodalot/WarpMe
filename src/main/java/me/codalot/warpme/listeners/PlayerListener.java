package me.codalot.warpme.listeners;

import me.codalot.warpme.managers.PlayerManager;
import me.codalot.warpme.managers.WarpManager;
import me.codalot.warpme.menus.WarpEditor;
import me.codalot.warpme.player.WarpPlayer;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.tasks.WarpTeleportTask;
import me.codalot.warpme.warps.Warp;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener extends BaseListener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        getManager().loadPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        getManager().unloadPlayer(event.getPlayer());
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (WarpManager.renameMode.containsKey(player)) {
            event.setCancelled(true);
            renameWarp(player, WarpManager.renameMode.get(player), event.getMessage());
        } else if (WarpManager.reDisplayNameMode.containsKey(player)) {
            event.setCancelled(true);
            reDisplayNameWarp(player, WarpManager.reDisplayNameMode.get(player), event.getMessage());
        }

    }

    private void renameWarp(Player player, Warp warp, String message) {
        if (loader.getManager().getWarpManager().getWarp(message) != null)
            Message.WARP_NAME_TAKEN.send(player);
        else {
            player.sendMessage(Message.WARP_RENAMED.getMessage().replace("%oldname", warp.getName()).replace("%newname", message));
            warp.setName(message);
        }
        WarpManager.renameMode.remove(player);
        new WarpEditor(warp).open(player);
    }

    private void reDisplayNameWarp(Player player, Warp warp, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        player.sendMessage(Message.WARP_RENAMED.getMessage().replace("%oldname", warp.getDisplayName()).replace("%newname", message));
        warp.setDisplayName(message);

        WarpManager.reDisplayNameMode.remove(player);
        new WarpEditor(warp).open(player);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        WarpPlayer data = getManager().getPlayer(player);

        WarpTeleportTask task = data.getTeleport();
        if (task != null)
            task.cancel();

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        WarpPlayer data = getManager().getPlayer(player);

        for (Warp warp : loader.getManager().getWarpManager().getWarps()) {
            if (!warp.isUnlockable())
                continue;
            if (data.hasUnlocked(warp))
                continue;
            if (player.getLocation().distance(warp.getLocation()) < warp.getRadius())
                data.unlockWarp(warp);
        }
    }

    private PlayerManager getManager() {
        return loader.getManager().getPlayerManager();
    }

}
