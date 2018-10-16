package me.codalot.warpme.menus.buttons.editor;

import me.codalot.warpme.setup.Message;
import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RelocateWarpButton extends WarpButton {

    public RelocateWarpButton(Warp warp) {
        super(warp, ItemUtils.build(Material.COMPASS, 0, "§9Location", "", "§7Click to Change the Warp's", "§7Location to Where You're Standing", ""));
        setValue(warp.getLocation().getBlockX() + "§7, §f" + warp.getLocation().getBlockY() + "§7, §f" + warp.getLocation().getBlockZ());
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        warp.setLocation(event.getWhoClicked().getLocation());
        event.getWhoClicked().sendMessage(Message.WARP_RELOCATED.getMessage().replace("%warp", warp.getName()));
        setValue(warp.getLocation().getBlockX() + "§7, §f" + warp.getLocation().getBlockY() + "§7, §f" + warp.getLocation().getBlockZ());
    }
}
