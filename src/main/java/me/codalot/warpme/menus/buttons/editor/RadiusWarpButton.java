package me.codalot.warpme.menus.buttons.editor;

import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class RadiusWarpButton extends WarpButton {

    public RadiusWarpButton(Warp warp) {
        super(warp, ItemUtils.build(Material.PAPER, 0, "§bRadius", "", "§7Change the Unlock Radius", "", "§7Left-Click to Increase", "§7Right-Click to Decrease", "§7Shift-Click to Change By 10", ""));
        setValue(warp.getRadius() + "");
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        int radius = warp.getRadius();
        switch (event.getClick()) {
            case LEFT:
                radius++;
                break;
            case RIGHT:
                radius--;
                break;
            case SHIFT_LEFT:
                radius += 10;
                break;
            case SHIFT_RIGHT:
                radius -= 10;
                break;
        }
        if (radius < 0)
            radius = 0;
        warp.setRadius(radius);
        setValue(radius + "");
    }
}
