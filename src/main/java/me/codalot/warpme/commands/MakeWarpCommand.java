package me.codalot.warpme.commands;

import me.codalot.warpme.managers.WarpManager;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.setup.Permission;
import me.codalot.warpme.warps.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MakeWarpCommand extends BaseCommand {

    public MakeWarpCommand() {
        super("makewarp");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Message.CMD_MUST_BE_PLAYER.send(sender);
            return true;
        }

        Player player = (Player) sender;
        if (!Permission.CMD_MAKEWARP.hasPermission(player)) {
            Message.CMD_NO_PERMISSION.send(player);
            return true;
        }

        if (args.length != 1) {
            Message.CMD_INVALID_USAGE.send(player);
            return true;
        }

        if (getManager().getWarp(args[0]) != null) {
            Message.WARP_NAME_TAKEN.send(player);
            return true;
        }

        Warp warp = getManager().createWarp(args[0], player.getLocation());

        player.sendMessage(Message.WARP_CREATED.getMessage().replace("%warp", args[0]));

        return true;
    }
}
