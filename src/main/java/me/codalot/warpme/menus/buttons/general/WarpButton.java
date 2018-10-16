package me.codalot.warpme.menus.buttons.general;

import me.codalot.warpme.Loader;
import me.codalot.warpme.menus.buttons.Button;
import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WarpButton extends Button {

    private Warp warp;

    public WarpButton(Warp warp) {
        super(ItemUtils.build(warp.getIcon().getType(), warp.getIcon().getDurability(), "§a§l" + warp.getDisplayName(), "", "§7Click to Teleport", ""));
        this.warp = warp;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Loader.getInstance().getManager().getPlayerManager().getPlayer((Player) event.getWhoClicked()).teleport(warp);
        event.getWhoClicked().closeInventory();
    }

}
