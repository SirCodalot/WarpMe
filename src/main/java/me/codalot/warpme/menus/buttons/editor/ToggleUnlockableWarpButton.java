package me.codalot.warpme.menus.buttons.editor;

import me.codalot.warpme.setup.Message;
import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ToggleUnlockableWarpButton extends WarpButton {

    public ToggleUnlockableWarpButton(Warp warp) {
        super(warp, ItemUtils.build(Material.MAP, 0, "§9Unlockable", "", "§7Click to Toggle the Warp's Unlockability"));
        setValue(warp.isUnlockable() ? "Unlockable" : "Public");
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        warp.setUnlockable(!warp.isUnlockable());
        event.getWhoClicked().sendMessage(Message.WARP_TOGGLED_LOCKABILITY.getMessage().replace("%warp", warp.getName()));
        setValue(warp.isUnlockable() ? "Unlockable" : "Public");
    }
}
