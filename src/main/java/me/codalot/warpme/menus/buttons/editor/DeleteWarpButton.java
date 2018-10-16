package me.codalot.warpme.menus.buttons.editor;

import me.codalot.warpme.Loader;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DeleteWarpButton extends WarpButton {

    public DeleteWarpButton(Warp warp) {
        super(warp, ItemUtils.build(Material.REDSTONE_BLOCK, 0, "§4§lDelete", "", "§7Click to Delete the Warp", ""));
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.getWhoClicked().sendMessage(Message.WARP_DELETED.getMessage().replace("%warp", warp.getName()));
        event.getWhoClicked().closeInventory();
        Loader.getInstance().getManager().getWarpManager().deleteWarp(warp);
    }
}
