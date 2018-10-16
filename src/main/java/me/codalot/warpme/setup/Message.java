package me.codalot.warpme.setup;

import org.bukkit.command.CommandSender;

public enum Message {

    PREFIX("§b§lWarpMe §e"),
    ERROR_PREFIX(PREFIX.message + "§c"),

    CMD_MUST_BE_PLAYER(ERROR_PREFIX.message + "You must be a player to execute this command."),
    CMD_NO_PERMISSION(ERROR_PREFIX.message + "You don't have permission to execute this command"),
    CMD_CANNOT_FIND_PLAYER(ERROR_PREFIX.message + "Cannot find the player."),
    CMD_INVALID_USAGE(ERROR_PREFIX.message + "Invalid usage."),

    WARP_NAME_TAKEN(ERROR_PREFIX.message + "This warp already exists."),
    WARP_DOESNT_EXIST(ERROR_PREFIX.message + "This warp doesn't exist."),
    WARP_CANNOT_MAKE_MORE(ERROR_PREFIX.message + "You can't make any more warps."),

    WARP_INVALID_ICON(ERROR_PREFIX.message + "You're not holding anything."),
    WARP_SET_ICON(PREFIX.message + "Successfully changed §bwarp§e's icon."),
    WARP_RENAME(PREFIX.message + "Enter a new name in chat:"),
    WARP_RENAMED(PREFIX.message + "Successfully renamed §b%oldname §eto §b%newname§e."),
    WARP_RELOCATED(PREFIX.message + "Successfully changed §b%warp§e's location."),
    WARP_TOGGLED_LOCKABILITY(PREFIX.message + "Successfully toggled §b%warp§e's public state."),

    WARP_TELEPORTED(PREFIX.message + "You teleported to §b%warp§e."),

    WARP_CREATED(PREFIX.message + "Successfully created §b%warp§e."),
    WARP_DELETED(PREFIX.message + "Successfully deleted §b%warp§e."),

    UNLOCK_MESSAGE(PREFIX.message + "You unlocked §b%warp"),
    UNLOCK_NOT_UNLOCKED(ERROR_PREFIX.message + "This warp is locked.");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void send(CommandSender sender) {
        sender.sendMessage(message);
    }

}
