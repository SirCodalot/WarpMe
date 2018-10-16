package me.codalot.warpme.menus.buttons.editor;

import me.codalot.warpme.menus.buttons.Button;
import me.codalot.warpme.warps.Warp;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class WarpButton extends Button {

    protected Warp warp;

    public WarpButton(Warp warp, ItemStack item) {
        super(item);
        this.warp = warp;
    }

    public void setValue(String value) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null)
            return;
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        String remove = null;
        for (String s : lore)
            if (s.contains("Value:"))
                remove = s;
        if (remove != null)
            lore.remove(remove);
        lore.add("§7Value: §f" + value);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
