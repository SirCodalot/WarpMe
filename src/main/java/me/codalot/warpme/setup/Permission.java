package me.codalot.warpme.setup;

import org.bukkit.command.CommandSender;

public enum Permission {

    CMD_WARPS_SELF("warpme.command.warps.self"),
    CMD_WARPS_OTHERS("warpme.command.warps.others"),

    CMD_EDITWARP("warpme.command.editwarp"),

    CMD_WARP("warpme.command.warp"),

    CMD_MAKEWARP("warpme.command.makewarp"),

    CMD_DELWARP("warpme.command.delwarp");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission(permission);
    }
}
