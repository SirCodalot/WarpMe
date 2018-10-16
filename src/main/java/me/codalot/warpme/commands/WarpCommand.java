package me.codalot.warpme.commands;

import me.codalot.warpme.Loader;
import me.codalot.warpme.menus.WarpsMenu;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.setup.Permission;
import me.codalot.warpme.tasks.WarpTeleportTask;
import me.codalot.warpme.warps.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends BaseCommand {

    public WarpCommand() {
        super("warp");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Message.CMD_MUST_BE_PLAYER.send(sender);
            return true;
        }

        Player player = (Player) sender;

        if (!Permission.CMD_WARP.hasPermission(player)) {
            Message.CMD_NO_PERMISSION.send(player);
            return true;
        }

        if (args.length == 0) {
            new WarpsMenu().open(player);

            return true;
        } else if (args.length == 1) {

            Warp warp = getManager().getWarp(args[0]);
            if (warp == null) {
                Message.WARP_DOESNT_EXIST.send(player);
                return true;
            }

            Loader.getInstance().getManager().getPlayerManager().getPlayer(player).teleport(warp);

            return true;
        }

        Message.CMD_INVALID_USAGE.send(player);

        return true;
    }
}
