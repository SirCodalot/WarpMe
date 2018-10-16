package me.codalot.warpme.menus.buttons.editor;

import me.codalot.warpme.managers.WarpManager;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ReDisplayNameWarpButton extends WarpButton {

    public ReDisplayNameWarpButton(Warp warp) {
        super(warp, ItemUtils.build(Material.NAME_TAG, 0, "§dChange Display Name", "", "§7Click to Rename this Warp's Display Name", ""));
        setValue(warp.getDisplayName());
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        player.closeInventory();
        WarpManager.reDisplayNameMode.put(player, warp);
        Message.WARP_RENAME.send(player);
        setValue(warp.getName());
    }
}
