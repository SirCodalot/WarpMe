package me.codalot.warpme.commands;

import me.codalot.warpme.setup.Message;
import me.codalot.warpme.setup.Permission;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpsCommand extends BaseCommand {

    public WarpsCommand() {
        super("warps");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Message.CMD_MUST_BE_PLAYER.send(sender);
            return true;
        }

        Player player = (Player) sender;

        if (!Permission.CMD_WARPS_SELF.hasPermission(player)) {
            Message.CMD_NO_PERMISSION.send(player);
            return true;
        }

        if (args.length != 0) {
            Message.CMD_INVALID_USAGE.send(player);
            return true;
        }

        List<Warp> warps = getManager().getWarps();
        String message = Message.PREFIX.getMessage() + "Warps§b: §e";
        for (int i = 0; i < warps.size(); i++)
            message += warps.get(i).getName() + (i + 1 == warps.size() ? "" : "§b, §e");

        if (warps.isEmpty())
            message += "§7There aren't any warps.";

        player.sendMessage(message);

        return true;
    }
}
