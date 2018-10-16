package me.codalot.warpme.commands;

import me.codalot.warpme.menus.WarpEditor;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.setup.Permission;
import me.codalot.warpme.warps.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditWarpCommand extends BaseCommand {

    public EditWarpCommand() {
        super("editwarp");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Message.CMD_MUST_BE_PLAYER.send(sender);
            return true;
        }

        Player player = (Player) sender;

        if (!Permission.CMD_EDITWARP.hasPermission(player)) {
            Message.CMD_NO_PERMISSION.send(player);
            return true;
        }

        if (args.length != 1) {
            Message.CMD_INVALID_USAGE.send(player);
            return true;
        }

        Warp warp = getManager().getWarp(args[0]);
        if (warp == null) {
            Message.WARP_DOESNT_EXIST.send(player);
            return true;
        }

        new WarpEditor(warp).open(player);

        return true;
    }
}
