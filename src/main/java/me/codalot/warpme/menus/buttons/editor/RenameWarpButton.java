package me.codalot.warpme.menus.buttons.editor;

import me.codalot.warpme.managers.WarpManager;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RenameWarpButton extends WarpButton {

    public RenameWarpButton(Warp warp) {
        super(warp, ItemUtils.build(Material.NAME_TAG, 0, "§eRename", "", "§7Click to Rename this Warp", ""));
        setValue(warp.getName());
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        player.closeInventory();
        WarpManager.renameMode.put(player, warp);
        Message.WARP_RENAME.send(player);
        setValue(warp.getName());
    }

}
