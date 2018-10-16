package me.codalot.warpme.menus.buttons.editor;

import me.codalot.warpme.menus.WarpEditor;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.utils.ItemUtils;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ChangeIconButton extends WarpButton {

    private WarpEditor editor;

    public ChangeIconButton(Warp warp, WarpEditor editor) {
        super(warp, ItemUtils.build(warp.getIcon().getType(), warp.getIcon().getDurability(), "§aChange Icon", "", "§7Click to Change the Warp's", "§7Icon to The Item You're Holding", ""));
        this.editor = editor;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() == Material.AIR) {
            Message.WARP_INVALID_ICON.send(player);
            return;
        }

        warp.setIcon(item);
        player.sendMessage(Message.WARP_SET_ICON.getMessage().replace("%warp", warp.getName()));

        editor.setItems();
    }
}
