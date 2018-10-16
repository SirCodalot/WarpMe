package me.codalot.warpme.menus.buttons.general;

import me.codalot.warpme.menus.buttons.Button;
import me.codalot.warpme.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EscButton extends Button {

    public EscButton() {
        super(ItemUtils.build(Material.IRON_DOOR, 0, "§c§lClose Menu"));
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.getWhoClicked().closeInventory();
    }
}
